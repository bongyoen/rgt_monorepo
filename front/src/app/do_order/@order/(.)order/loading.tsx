'use client';

import React from 'react';

const LoadingSpinner = () => {
	return (
		<div style={spinnerStyle}>
			로딩중...
		</div>
	);
};
const spinnerStyle = {
	display: 'flex',
	justifyContent: 'center',
	alignItems: 'center',
	height: '100vh',
};

export default function Loading() {
	return (
		<div
			style={{}}
			className={`
					fixed inset-0 z-40 flex h-full w-full items-center justify-center
					bg-[rgba(229,218,218,0.4)]
				`}
		>
			<div
				className={`
						absolute left-1/2 top-1/2 z-50 h-1/5 w-1/5 -translate-x-1/2
						-translate-y-1/2 transform overflow-auto bg-white text-center
					`}
			>
				<LoadingSpinner />
			</div>
		</div>
	);
}
