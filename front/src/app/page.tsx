import Dashboard from "@/src/components/dashboard/dashboard";
import {ApiResponseType, orderReceivedType} from "@/src/types/base_type";

export default async function Home() {

    const res = await fetch(`http://localhost:8080/connSw`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
        },
    });

    const resData = (await res.json()) as ApiResponseType;

    const preOrders = (resData.rtnModel as []).map(rtnModel => {

        return {
            orderNo: rtnModel["orderNo"],
            menu: rtnModel["menu"],
            qntty: rtnModel["qntty"],
            ordDt: rtnModel['ordDt']
        }
    });

    console.log(preOrders)
    return (
        <>
            <div>
                <Dashboard preOrders={preOrders}/>
            </div>
        </>
    );
}
