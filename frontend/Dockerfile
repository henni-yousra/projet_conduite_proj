FROM node:lts
WORKDIR /app
COPY package*.json .
COPY tsconfig*.json .
COPY angular.json .
COPY src src
RUN npm install
EXPOSE 4200
CMD ["npm", "run", "start"]
