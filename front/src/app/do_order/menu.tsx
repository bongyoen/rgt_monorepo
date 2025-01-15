'use client';

import BURGER from '../../../public/Image/burger.png';
import CHICKEN from '../../../public/Image/chicken.png';
import PIZZA from '../../../public/Image/pizza2.png';
import Image from "next/image";
import {useState} from "react";
import MenuIncDcrBtn from "@/src/app/do_order/menu_inc_dcr_btn";
import {condType} from "@/src/types/base_type";
import {useRouter} from 'next/navigation';

const Menu = () => {
    const [burger, setBurger] = useState<number>(0);
    const [chicken, setChicken] = useState<number>(0);
    const [pizza, setPizza] = useState<number>(0);
    const router = useRouter();

    const onOrder = async () => {
        const orders: condType = {condEqList: []};

        if (burger > 0 && burger < 100) orders.condEqList.push({menu: 'burger', qntty: burger});
        if (chicken > 0 && chicken < 100) orders.condEqList.push({menu: 'chicken', qntty: chicken});
        if (pizza > 0 && pizza < 100) orders.condEqList.push({menu: 'pizza', qntty: pizza});

        console.log(orders)
        const orderLink = `/do_order/order?orders=${encodeURIComponent(JSON.stringify(orders))}`;
        router.push(orderLink);
    };

    return (
        <>
            <div
                className="grid grid-cols-4 bg-card text-card-foreground group relative mx-auto overflow-hidden rounded-lg border shadow-sm transition-all hover:shadow-lg justify-center items-center">
                <div>사진</div>
                <div>이름</div>
                <div>가격</div>
                <div>수량</div>
            </div>
            <div
                className="grid grid-cols-4 bg-card text-card-foreground group relative mx-auto overflow-hidden rounded-lg border shadow-sm transition-all hover:shadow-lg justify-center items-center">
                <div>
                    <Image src={BURGER} width={100} height={100} alt="BURGER"/>
                </div>
                <div>BURGER</div>
                <div>10000</div>
                <MenuIncDcrBtn value={burger} setValue={setBurger}/>
            </div>
            <div
                className="grid grid-cols-4 bg-card text-card-foreground group relative mx-auto overflow-hidden rounded-lg border shadow-sm transition-all hover:shadow-lg justify-center items-center">
                <div>
                    <Image src={CHICKEN} width={100} height={100} alt="CHICKEN"/>
                </div>
                <div>CHICKEN</div>
                <div>10000</div>
                <MenuIncDcrBtn value={chicken} setValue={setChicken}/>
            </div>
            <div
                className="grid grid-cols-4 bg-card text-card-foreground group relative mx-auto overflow-hidden rounded-lg border shadow-sm transition-all hover:shadow-lg justify-center items-center">
                <div>
                    <Image src={PIZZA} width={100} height={100} alt="PIZZA"/>
                </div>
                <div>PIZZA</div>
                <div>10000</div>
                <MenuIncDcrBtn value={pizza} setValue={setPizza}/>
            </div>
            <div className="text-end">
                <button
                    className="focus:ring-ring bg-primary text-primary-foreground hover:bg-primary/80 left-5 top-4 mb-2 inline-flex items-center rounded-full border text-white text-xl border-transparent px-2.5 py-0.5 font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2 justify-between mt-2"
                    onClick={onOrder}>
                    주문하기
                </button>
            </div>
        </>
    );
};

export default Menu;
