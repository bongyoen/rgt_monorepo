import imgLogo from '@imgs/logo.png';
import Image from 'next/image';
import MenuBtn from "@/src/components/btn/menuBtn";

const Header = () => {
    return (
        <header
            className={`
				sticky top-0 z-50 mx-auto
				bg-[color:hsl(var(--primary-foreground))]
				dark:bg-slate-800
			`}
        >
            <div
                className={`
					mx-auto my-0 flex w-full max-w-screen-xl justify-between border-b-4
					border-solid border-gray-200 px-5 pb-3 pt-2
					dark:border-gray-950 dark:bg-slate-800
				`}
            >
                <div className="flex items-center">
                    <a href="/">
                        <Image src={imgLogo} width={50} height={50} alt="logo"/>
                    </a>
                </div>

                <div className="flex items-center">
                    <a href="/">
                        RGT ORDER
                    </a>

                </div>
                <div className="flex gap-x-4">
                    <nav
                        className={`
					flex items-center gap-x-8 text-sm
					dark:text-white
				`}
                    >
                        <MenuBtn link="/">대시보드</MenuBtn>
                        <MenuBtn link="/do_order">주문하기</MenuBtn>
                    </nav>
                </div>
            </div>
        </header>
    )
}

export default Header;