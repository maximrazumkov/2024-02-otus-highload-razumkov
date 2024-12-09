#!/bin/bash

until /usr/bin/mongosh --port 27017 --quiet --eval 'db.getMongo()';
do
  sleep 1
done

readarray -d';' -t shards <<< "$SHARDS;"

for shard in "${shards[@]}"
do
  if [ $(echo "$shard" | wc -l) -gt 1 ];
  then
    break
  fi
  /usr/bin/mongosh --eval "sh.addShard(\"$shard\")"
done