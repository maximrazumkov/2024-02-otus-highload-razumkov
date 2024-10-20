#!/bin/bash
set -e

# Подождем, пока PostgreSQL запустится
until psql -U postgres -c '\l'; do
  echo "Postgres is unavailable - sleeping"
  sleep 1
done

# Копируем наши конфигурационные файлы
echo "Copying custom configuration files..."
cp /docker-entrypoint-initdb.d/config/master/postgresql.conf /var/lib/postgresql/data/postgresql.conf
cp /docker-entrypoint-initdb.d/config/master/pg_hba.conf /var/lib/postgresql/data/pg_hba.conf

# Перезапускаем PostgreSQL, чтобы применить настройки
pg_ctl -D /var/lib/postgresql/data restart

# Создаем пользователя replicator
psql -v ON_ERROR_STOP=1 --username postgres <<-EOSQL
    CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD 'replicator';
EOSQL