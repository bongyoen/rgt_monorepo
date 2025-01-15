FROM node:22-alpine AS builder
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install --include=optional sharp

COPY . .
RUN npm run build

FROM node:22-alpine
WORKDIR /app

RUN addgroup -S nextjs && adduser -S nextjs -G nextjs

COPY --from=builder --chown=nextjs:nextjs /app/.next/standalone ./
COPY --from=builder --chown=nextjs:nextjs /app/.next/static ./.next/static
COPY --from=builder --chown=nextjs:nextjs /app/public ./public
COPY --from=builder --chown=nextjs:nextjs /app/package.json ./package.json

USER nextjs

EXPOSE 3000

CMD ["node", "server.js"]