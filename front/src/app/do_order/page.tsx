import Menu from "@/src/app/do_order/menu";
import Link from "next/link";

const doOrder = () => {

    return (
        <div>
            <Link href={'/do_order/order'} scroll={false}>
                test
            </Link>
            <Menu/>
        </div>

    );
}

export default doOrder;