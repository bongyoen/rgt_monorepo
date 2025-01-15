'use client';
import {useEffect, useState} from 'react';
import {orderReceivedType} from "@/src/types/base_type";


interface DashboardProps {
    preOrders: orderReceivedType[]
}

export default function Dashboard({preOrders}: DashboardProps) {
    const [orders, setOrders] = useState<orderReceivedType[]>(preOrders);


    useEffect(() => {
        const socket = new WebSocket('ws://localhost:8080/ws');
        socket.onmessage = (event) => {

            console.log(JSON.parse(event.data));
            const parsedData = JSON.parse(event.data);
            const dateArr = parsedData.ordDt as string[]
            const res: orderReceivedType = {
                orderNo: parsedData.orderNo,
                menu: parsedData.menu,
                qntty: parsedData.qntty,
                ordDt: `${dateArr[0]}-${dateArr[1]}-${dateArr[2]}`,
            };

            // Message received: {"orderNo":1,"menu":"pizza","qntty":2,"ordDt":[2025,1,15]}

            setOrders((prevMessages) => {
                const arr = [...prevMessages, res];
                if (arr.length > 20) {
                    arr.shift();
                }
                return arr;
            });
        };

        return () => socket.close();
    }, []);

    return (<div className="container mx-auto p-4"><h1 className="text-2xl font-bold mb-4">Order Dashboard</h1>
        <table className="min-w-full bg-white border border-gray-200">
            <thead>
            <tr>
                <th className="py-2 px-4 border-b">Order Number</th>
                <th className="py-2 px-4 border-b">Menu Name</th>
                <th className="py-2 px-4 border-b">Quantity</th>
                <th className="py-2 px-4 border-b">Order Date</th>
            </tr>
            </thead>
            <tbody>
            {orders.map((order, index) => (<tr key={index} className="hover:bg-gray-100">
                <td className="py-2 px-4 border-b">{order.orderNo}</td>
                <td className="py-2 px-4 border-b">{order.menu}</td>
                <td className="py-2 px-4 border-b">{order.qntty}</td>
                <td className="py-2 px-4 border-b">{order.ordDt}</td>
            </tr>)) ?? null

            }
            </tbody>
        </table>
    </div>);
}
