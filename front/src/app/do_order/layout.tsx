import {ReactNode} from 'react';

type RootLayoutType = { children: ReactNode; order: ReactNode };

export default function RootLayout({children, order}: RootLayoutType) {
    return (
        <>
            {order}
            {children}
        </>
    );
}
