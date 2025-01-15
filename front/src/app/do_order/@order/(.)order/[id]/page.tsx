import {Suspense} from 'react';
import Loading from "@/src/app/do_order/@order/(.)order/[id]/loading";




async function Page({ params }: any) {

    console.log("notionKey : ", params.id)
    return (
        <>
            <Suspense fallback={<Loading/>}>
                <>
                    <div
                        style={{}}
                        className={`
					fixed inset-0 z-40 flex h-full w-full items-center justify-center
					bg-[rgba(229,218,218,0.4)]
				`}
                    >
                        <div
                            className={`
						absolute left-1/2 top-1/2 z-50 h-5/6 w-5/6 -translate-x-1/2
						-translate-y-1/2 transform overflow-auto bg-blue-100 text-center
					`}
                        >
                            <div className="notion__container">
                                hello??
                            </div>
                        </div>
                    </div>
                </>
            </Suspense>
        </>
    );
}

export default Page;
