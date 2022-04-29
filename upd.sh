#!/usr/bin/env bash

URL="https://sirekanian.github.io/warmongers.json"
OUTPUT="app/src/main/res/raw/default1.json"
COUNT="10"

wget -qO- "$URL" | jq ".[0:$COUNT]" >"$OUTPUT"
