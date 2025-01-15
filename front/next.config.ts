import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    reactStrictMode: false,
    output: 'standalone',
    compiler: {
        styledComponents: true,
    },
};

export default nextConfig;
