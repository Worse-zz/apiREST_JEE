#!/bin/sh
mvn clean package && docker build -t fr.baillieul/RESTAPI .
docker rm -f RESTAPI || true && docker run -d -p 9080:9080 -p 9443:9443 --name RESTAPI fr.baillieul/RESTAPI