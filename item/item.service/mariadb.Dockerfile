FROM mariadb:10.4

ENV MYSQL_DATABASE items-table
ENV MYSQL_RANDOM_ROOT_PASSWORD yes
ENV MYSQL_USER bengt
ENV MYSQL_PASSWORD bengt

COPY sql/ /docker-entrypoint-initdb.d/
EXPOSE 3306