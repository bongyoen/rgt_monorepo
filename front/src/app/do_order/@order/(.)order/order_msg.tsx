'use client';

import {ApiResponseType} from "@/src/types/base_type";
import {useEffect, useRef} from "react";
import {useRouter} from 'next/navigation';

type OrderMsgProps = {
    ordRes?: ApiResponseType
}

const OrderMsg = ({ordRes}: OrderMsgProps) => {
    const scrollRef = useRef<HTMLDivElement>(null);
    const outerRef = useRef<HTMLDivElement>(null);
    const router = useRouter();
    document.body.style.overflow = 'hidden';

    const handleClickOutside = (event: any) => {
        if (
            outerRef.current &&
            scrollRef.current &&
            outerRef.current.contains(event.target) &&
            !scrollRef.current?.contains(event.target)
        ) {
            router.back();
        }
    };
    useEffect(() => {
        scrollRef.current?.scrollTo({left: 0, top: 0});

        document.addEventListener('mousedown', handleClickOutside);

        return () => {
            document.body.style.overflow = 'auto';
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [router]);
    return (
        <>
            <div
                style={{}}
                className={`
					fixed inset-0 z-40 flex h-full w-full items-center justify-center
					bg-[rgba(229,218,218,0.4)]
				`}
                ref={outerRef}
            >
                <div
                    className={`
						absolute left-1/2 top-1/2 z-50 h-1/5 w-1/5 -translate-x-1/2
						-translate-y-1/2 transform overflow-auto bg-blue-100 text-center
						border shadow-xl rounded-lg
					`}
                    ref={scrollRef}
                >
                    <div className="mt-8 flex h-modal justify-between flex-col">
                        <div>
                            {ordRes ? ordRes.message : "응답이 없습니다."}
                        </div>
                        <div>
                            <button
                                className="focus:ring-ring bg-primary text-primary-foreground hover:bg-primary/80 left-5 top-4 mb-2 inline-flex items-center rounded-full border text-white text-xl border-transparent px-2.5 py-0.5 font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2 justify-between mt-2"
                                onClick={router.back}>
                                확인
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </>
    );
}

export default OrderMsg;