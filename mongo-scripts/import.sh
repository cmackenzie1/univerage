#!/usr/bin/env bash
mongoimport -d univerage -c term --type csv --file /tmp/data/terms.csv --headerline
mongoimport -d univerage -c course --type csv --file /tmp/data/courses.csv --headerline
mongoimport -d univerage -c class --type csv --file /tmp/data/classes.csv --headerline