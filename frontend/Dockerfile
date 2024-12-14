FROM node:lts
WORKDIR /app
COPY package*.json .
COPY tsconfig*.json .
COPY angular.json .
COPY src src
RUN npm install -g @angular/cli
RUN npm install
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0"]
# CMD ["npm", "run", "start","--host 0.0.0.0"]
