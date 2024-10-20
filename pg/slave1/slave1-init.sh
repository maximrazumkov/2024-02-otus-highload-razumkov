#!/bin/bash
set -e

PGDATA="/var/lib/postgresql/data"

echo "===================================================="
echo "===================================================="
echo "                  init-slave.sh                     "
echo $(hostname)
echo "===================================================="
echo "===================================================="

echo "Data directory is empty, initializing replication..."

# Очистка директории данных (на всякий случай)
rm -rf $PGDATA/*

# Что важнее, удобство или безопасность? :)
export PGPASSWORD='replicator'

# Выполнение pg_basebackup для инициализации реплики
pg_basebackup -h pgmaster -D $PGDATA -U replicator -v -P --wal-method=stream

# Конечно, безопасность!
unset PGPASSWORD

# Копируем наши конфигурационные файлы
echo "Copying custom configuration files..."
cp /docker-entrypoint-initdb.d/config/slave1/postgresql.conf /var/lib/postgresql/data/postgresql.conf
cp /docker-entrypoint-initdb.d/config/slave1/standby.signal /var/lib/postgresql/data/standby.signal

# Перезапускаем PostgreSQL, чтобы применить настройки
pg_ctl -D /var/lib/postgresql/data restart