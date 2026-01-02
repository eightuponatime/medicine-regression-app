#!/bin/bash
# custom script to run certain tests

# read csv test
./gradlew test --tests data.ReadMimicTest --stacktrace
