#!/bin/bash
#
# Purpose: to work on the production database on Heroku
#
# To work interactively, just type
#    script/production_dbconsole.sh
#
# To send a file to the production database, use something like
#
#    cat MYFILE.sql | script/production_dbconsole.sh
#

cd "$(dirname "$0")/.."

psql $* $(heroku pg:credentials DATABASE | grep ://)

