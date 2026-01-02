#!/bin/bash
# custom script to run certain tests

./gradlew test \
    --tests data.ReadMimicTest \
    --tests algorithms.outliers.IQRDetectorTest \
    --stacktrace
