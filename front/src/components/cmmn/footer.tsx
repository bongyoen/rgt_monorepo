import {UNION_STRING} from "@/src/data/union_str";

const Footer = () => {
    return (
        <footer className="dark:text-white">
            <div
                className={`
					flex h-full flex-col items-center justify-center border-t px-2 py-4
				`}
            >
                <p className="my-6 flex h-2 w-full items-center justify-center">
                    © 2025
                    <span className="mx-2 font-bold">{UNION_STRING.devel.name}</span>
                    All rights reserved.
                </p>
            </div>
        </footer>
    );
}
export default Footer;