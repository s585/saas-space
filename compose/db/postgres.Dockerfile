FROM postgres:16.1-alpine
COPY postgres/init-scripts /docker-entrypoint-initdb.d