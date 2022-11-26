FROM node:17-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install cypress@10.0.3

COPY . .

EXPOSE 3000

CMD [ "npm", "start"]
