#!/usr/bin/env bash

set -e

ENDPOINT="https://sirekanian.github.io/warmongr"
SCHEMAS="app/schemas/com.sirekanian.acf.data.local.Database"
SCHEMA=$(find "$SCHEMAS" -name "*.json" | sort -V | tail -1)

# transform meta.json to csv
wget -qO- "$ENDPOINT/meta.json" |
  jq -r 'to_entries[] | [.key, .value] | @csv' \
    >"app/schemas/MetaEntity.csv"

# transform data.json to csv
wget --header="Accept-Encoding: gzip" -qO- "$ENDPOINT/data.json" | gunzip |
  jq -r 'map([.["0"],.["1"],.["4"],(.["5"] | join(" "))])[] | @csv' \
    >"app/schemas/WarmongerEntity.csv"

# transform tags.json to csv
wget -qO- "$ENDPOINT/tags.json" |
  jq -r 'map([.["id"],.["shortName"],.["ruShortName"]])[] | @csv' \
    >"app/schemas/TagEntity.csv"

# transform index.json to csv
wget -qO- "$ENDPOINT/index.json" |
  jq -r 'to_entries[] | [.key, .value] | @csv' \
    >"app/schemas/IndexEntity.csv"

# copy setupQueries from schema
jq -r ".database.setupQueries[]" "$SCHEMA" |
  sed 's/$/;/' \
    >app/schemas/init.sql

for TABLE in MetaEntity WarmongerEntity TagEntity IndexEntity; do

  # copy createSql from schema
  jq -r ".database.entities[] | select(.tableName==\"$TABLE\") | .createSql" "$SCHEMA" |
    sed "s/\${TABLE_NAME}/$TABLE/" |
    sed 's/$/;/' \
      >>app/schemas/init.sql

  # generate import csv command
  echo ".import --csv app/schemas/$TABLE.csv $TABLE" \
    >>app/schemas/init.sql

done

# recreate pre-packaged database
rm -f app/src/main/assets/warmongers.db
sqlite3 app/src/main/assets/warmongers.db <app/schemas/init.sql

# commit changes
git add app/schemas/*.csv
git add app/src/main/assets/warmongers.db
git commit -m "updated pre-packaged database"
