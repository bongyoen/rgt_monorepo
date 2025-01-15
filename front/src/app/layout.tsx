import type {Metadata} from "next";
import {Inter} from "next/font/google";
import "./globals.css";
import {ReactNode} from "react";
import Base from "@/src/components/cmmn/base";

export const metadata: Metadata = {
    title: "rgt_front",
    description: "프론트엔드",
};

const inter = Inter({subsets: ['latin']});

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: ReactNode;
}>) {
    return (
        <html lang="ko" className="h-full" suppressHydrationWarning>
        <body
            className={`
					${inter.className}
					h-full bg-none
					dark:bg-slate-800
				`}
        >
        <Base>{children}</Base>
        </body>
        </html>
    );
}
