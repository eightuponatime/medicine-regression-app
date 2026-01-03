#!/bin/bash
# custom script to run certain tests

./gradlew test \
    --tests data.ReadMimicTest \
    --tests algorithms.outliers.IQRDetectorTest \
    --tests algorithms.normality.JarqueBeraTestTest \
    --tests algorithms.outliers.ZScoreDetectorTest \
    --tests algorithms.outliers.AdaptiveDetectorTest \
    --stacktrace
