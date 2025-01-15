// 'use client'
import {Suspense} from 'react';
import {ApiResponseType} from "@/src/types/base_type";
import OrderMsg from "@/src/app/do_order/@order/(.)order/order_msg";
import Loading from "@/src/app/do_order/@order/(.)order/loading";


async function Page(params: any) {

    const query = await params.searchParams
    const orders = JSON.parse(query.orders)

    const res = await fetch(`http://localhost:8080/putOrder`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(orders)
    });
    const apiRes: ApiResponseType = JSON.parse(await res.text());

    return (
        <Suspense fallback={<Loading/>}>

            <OrderMsg ordRes={apiRes}/>
        </Suspense>
    );
}

export default Page;
