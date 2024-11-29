# build with: docker build -t lenartl/petstore-frontend .
# push with: docker push lenartl/petstore-frontend
FROM node:alpine AS build
WORKDIR /usr/local/app
COPY ./frontend /usr/local/app/
RUN npm install && npm run build

FROM nginx:alpine-perl
COPY ./docker/frontend/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/local/app/dist/frontend/browser /usr/share/nginx/html
EXPOSE 4200
