#!/bin/bash

# define key information
src=src/main/sql
dbname=aw_supermarket_checkout_development
dbuser=aw_supermarket_checkout
dbpassword="secret"

# no customization needed beyond this line

# Stop at the first error
set -e

# Go to the main project directory
cd "$(dirname $0)/.."


dropuser $dbuser || true
sudo -u postgres createuser --no-superuser --createdb --no-createrole $dbuser
dropdb $dbname || true
createdb $dbname

echo "ALTER USER $dbuser WITH PASSWORD '$dbpassword'" | sudo -u postgres psql $dbname 

cat $src/???_*.sql $src/seed.sql | sudo -u postgres psql $dbname 

echo "OK"
