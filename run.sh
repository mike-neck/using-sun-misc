#!/usr/bin/env bash

# shellcheck disable=SC2046
sdk u java $(sdk l java | grep installed | grep 8 | awk '{print $NF}')

./gradlew clean :java8:jar

cp java8/build/libs/java8-1.0-SNAPSHOT.jar java8-lib

# shellcheck disable=SC2046
sdk u java $(sdk l java | grep installed | grep 11 | awk '{print $NF}')

./gradlew runApp
