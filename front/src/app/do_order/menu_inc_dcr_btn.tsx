'use client';

import {Dispatch, SetStateAction} from "react";

type MenuIncDcrBtnType = {
    value: number;
    setValue: Dispatch<SetStateAction<number>>
}

const MenuIncDcrBtn = ({value, setValue}: MenuIncDcrBtnType) => {
    return (
        <div>
            <div className="
									focus:ring-ring
									bg-primary text-primary-foreground
									hover:bg-primary/80
									 left-5 top-4 mb-2 inline-flex items-center rounded-full border text-white  text-xl
									border-transparent px-2.5 py-0.5 font-medium transition-colors
									focus:outline-none focus:ring-2 focus:ring-offset-2
									justify-between
									w-1/2
								">
                <button onClick={() => {
                    setValue(prevState => {
                        let fixed = prevState - 1;
                        if (fixed < 0) fixed = 0;
                        return fixed
                    })
                }}>
                    -
                </button>

                {value}


                <button onClick={() => {
                    setValue(prevState => {
                        let fixed = prevState + 1;
                        if (fixed > 99) fixed = 99;
                        return fixed
                    })
                }}>
                    +
                </button>
            </div>

        </div>
    );
}

export default MenuIncDcrBtn