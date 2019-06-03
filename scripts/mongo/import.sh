#!/usr/bin/env bash
mongoimport --db univerage --collection terms --file /import/terms.json --jsonArray
mongoimport --db univerage --collection courses --file /import/courses.json --jsonArray
mongoimport --db univerage --collection classes --file /import/classes.json --jsonArray
