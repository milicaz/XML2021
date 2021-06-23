FROM mysql
ENV MYSQL_DATABASE xml
COPY ./dump.sql/ /docker-entrypoint-initdb.d/