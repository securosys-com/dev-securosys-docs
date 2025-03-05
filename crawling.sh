#!/bin/bash

pwd
echo "stop typesense container"
cd /home/docusaurus/securosys_wiki
docker compose down typesense

file_path="/home/typesense/db"
if [ -e "$file_path" ]; then
  rm -rf "$file_path"
  echo "File deleted: $file_path"
  cd /home/typesense/
  echo "Create db directory"
  mkdir -p /home/typesense/db && chown -R docusaurus:docusaurus /home/typesense/db
  echo "Get the archived file from jfrog artifactory"
  curl -L -X GET https://robot.writer.securosys-docs:aRWM-f9W23-P0pe2f-qocCLZUS@securosys.jfrog.io/artifactory/securosys-docs/db.tar.gz --output db.tar.gz
  echo "unzip the artifact"
  tar xzvf db.tar.gz -C db
  echo "revome the archived file"
  rm db.tar.gz
else
  echo "File not found: $file_path"
  cd /home/typesense/
  mkdir -p /home/typesense/db && chown -R docusaurus:docusaurus /home/typesense/db
  echo "Get the archived file from jfrog artifactory"
  curl -L -X GET https://robot.writer.securosys-docs:aRWM-f9W23-P0pe2f-qocCLZUS@securosys.jfrog.io/artifactory/securosys-docs/db.tar.gz --output db.tar.gz
  echo "unzip the artifact"
  tar xzvf db.tar.gz -C db
  echo "revome the archived file"
  rm db.tar.gz
fi
echo "start typesense container"
cd /home/docusaurus/securosys_wiki
docker compose up -d typesense
