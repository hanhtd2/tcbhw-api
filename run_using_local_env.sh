#!/bin/bash

mvn clean install -DskipTest
java -jar target/tcbhw-api-spring-boot.jar