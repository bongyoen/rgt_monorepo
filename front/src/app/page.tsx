import Dashboard from "@/src/components/dashboard/dashboard";
import {ApiResponseType} from "@/src/types/base_type";

export default async function Home() {


    let preOrders: any[] = [];
    try {
        const res = await fetch(`http://localhost:8080/connSw`, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            },
        });

        const resData = (await res.json()) as ApiResponseType;

        preOrders = (resData.rtnModel as []).map(rtnModel => {

            return {
                orderNo: rtnModel["orderNo"],
                menu: rtnModel["menu"],
                qntty: rtnModel["qntty"],
                ordDt: rtnModel['ordDt']
            }
        });
    } catch (e) {
        console.info("주문내역이 없거나 불러올 수 없습니다")
    }

    return (
        <>
            <div>
                <Dashboard preOrders={preOrders}/>
            </div>
        </>
    );
}
