#!/usr/bin/env bash

set -e

JSON=$(find app/schemas/com.sirekanian.acf.data.local.Database/*.json | sort -V | tail -1)
TABLE="WarmongerEntity"

wget --header="Accept-Encoding: gzip" -qO- https://sirekanian.github.io/warmongers.json | gunzip |
  jq -r 'map([.["0"],.["1"],.["4"]])[] | @csv' \
    >"app/schemas/init.csv"

jq -r ".database.setupQueries[]" "$JSON" |
  sed 's/$/;/' \
    >app/schemas/init.sql

jq -r ".database.entities[] | select(.tableName==\"$TABLE\") | .createSql" "$JSON" |
  sed "s/\${TABLE_NAME}/$TABLE/" |
  sed 's/$/;/' \
    >>app/schemas/init.sql

echo ".import --csv app/schemas/init.csv $TABLE" \
  >>app/schemas/init.sql

rm -f app/src/main/assets/warmongers.db
sqlite3 app/src/main/assets/warmongers.db <app/schemas/init.sql
