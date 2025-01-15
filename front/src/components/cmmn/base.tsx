import {BaseType} from "@/src/types/base_type";
import Header from "@/src/components/cmmn/header";
import Footer from "@/src/components/cmmn/footer";


const Base = ({children}: BaseType) => {
    return (
        <>
            <Header/>
            <main
                className={`
					container relative min-h-screen max-w-screen-xl
					dark:text-white
				`}
            >

                {children}
            </main>
            <Footer/>
        </>
    )
}
export default Base