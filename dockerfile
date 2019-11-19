FROM node:12.12.0-alpine
RUN apk update
COPY ./web-api .
EXPOSE 8080
CMD npm start