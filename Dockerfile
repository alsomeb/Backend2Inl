FROM mariadb:10.4

# Måste lägga till 1 db för att sedan i sql entrypoint bash scripts lägga till flera
ENV MYSQL_DATABASE customers
ENV MYSQL_RANDOM_ROOT_PASSWORD yes
ENV MYSQL_USER bengt
ENV MYSQL_PASSWORD bengt

COPY sql/ /docker-entrypoint-initdb.d/
EXPOSE 3306