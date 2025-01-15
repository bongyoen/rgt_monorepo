'use client';
import { ReactNode } from 'react';
import { usePathname } from 'next/navigation';

type BtnProps = {
	link: string;
	children: ReactNode;
};

export default function MenuBtn({ link, children }: BtnProps) {
	const router = usePathname();

	let isTarget = false;
	if (typeof children === 'string' && router === link.toLocaleLowerCase()) {
		isTarget = true;
	}
	return (
		<a href={link} className={isTarget ? 'text-lg font-bold' : ''}>
			{children}
		</a>
	);
}
