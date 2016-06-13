#!/usr/bin/env bash

cd platform/serviceregistry;  ./gradlew clean bootRun &
cd -
cd platform/edgeserver;  ./gradlew clean bootRun &
cd -
cd platform/hystrix-dashboard;  ./gradlew clean bootRun &
cd -

cd services/Profilbild;  ./gradlew clean bootRun &
cd -
cd services/Trainer;  ./gradlew clean bootRun &
cd -
cd services/Webseite;  ./gradlew clean bootRun &
