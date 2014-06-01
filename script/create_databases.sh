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


# if we're on Ubuntu
if uname -a | grep -qi ubuntu; then
  # if the postgres user for the current login does not exist
  if ! psql -tAc "select 3 + 4" template1 > /dev/null 2> /dev/null; then
    # then create the postgres user with superuser privileges
    sudo -u postgres createuser --superuser $(whoami) 
  fi
fi


# create database and user
dropdb $dbname || true
createdb $dbname
dropuser $dbuser || true
createuser --no-superuser --createdb --no-createrole $dbuser
echo "ALTER USER $dbuser WITH PASSWORD '$dbpassword'" | psql $dbname

# load all sql scripts in database
cat $src/???_*.sql $src/seed.sql | psql $dbname

# grant all privileges on all tables to our user
echo "GRANT ALL PRIVILEGES ON TABLE products TO $dbuser " | psql $dbname

echo "OK"
