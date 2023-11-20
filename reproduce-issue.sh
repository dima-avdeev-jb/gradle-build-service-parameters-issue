#!/bin/bash
cd "$(dirname "$0")"
set -e # fail fast

cd plugin
./gradlew :my-plugin-with-build-service:publishToMavenLocal
cd -

cd usage
./gradlew --stop -q
rm -rf build
rm -rf .gradle
rm -rf buildSrc/build
rm -rf buildSrc/.gradle
./gradlew clean build --no-daemon #--stacktrace -i
