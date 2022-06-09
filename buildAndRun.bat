@echo off
call mvn clean package
call docker build -t fr.baillieul/RESTAPI .
call docker rm -f RESTAPI
call docker run -d -p 9080:9080 -p 9443:9443 --name RESTAPI fr.baillieul/RESTAPI