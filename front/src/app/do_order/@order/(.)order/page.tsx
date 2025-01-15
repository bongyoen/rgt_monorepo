// 'use client'
import {Suspense} from 'react';
import {ApiResponseType} from "@/src/types/base_type";
import OrderMsg from "@/src/app/do_order/@order/(.)order/order_msg";
import Loading from "@/src/app/do_order/@order/(.)order/loading";


async function Page(params: any) {

    const query = await params.searchParams
    const orders = JSON.parse(query.orders)

    try {
        const res = await fetch(`${process.env.BASE_URL}/putOrder`, {
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
    } catch (e) {
        return (
            <Suspense fallback={<Loading/>}>
                <OrderMsg ordRes={undefined}/>
            </Suspense>
        );
    }


}

export default Page;
