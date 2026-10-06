#!/usr/bin/env bash

# Cleanup
rm -r ./test-back 1>/dev/null 2>&1
rm -r ./test-front 1>/dev/null 2>&1

openapi-generator-cli generate \
    --strict-spec true \
    -i src/main/resources/openapi/openapi.yaml \
    -c src/main/resources/openapi/config-back.yaml \
    -g spring \
    -o ./test-back

openapi-generator-cli generate \
    --strict-spec true \
    -i src/main/resources/openapi/openapi.yaml \
    -c src/main/resources/openapi/config-front.yaml \
    -g javascript-closure-angular \
    -o ./test-front
