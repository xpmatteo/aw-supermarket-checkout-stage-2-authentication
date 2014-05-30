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


dropdb --if-exists $dbname
createdb $dbname
dropuser --if-exists $dbuser
createuser $dbuser

cat $src/???_*.sql $src/seed.sql | psql $dbname $dbuser

echo "OK"
