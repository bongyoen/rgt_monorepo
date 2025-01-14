import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    reactStrictMode: false,
    output: 'standalone',
    swcMinify: true,
    compiler: {
        styledComponents: true,
    },
};

export default nextConfig;
