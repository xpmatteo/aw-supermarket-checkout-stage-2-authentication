
# Supermarket Checkout

We are now going to add authentication and authorization to this application. We want the cashier to enter his id and password to begin a work session.  The system will authenticate the cashier against the credentials stored in the database.

If the authentication is successful, the server returns a token to the page, that will be needed for all further communications.

## Exercise 0: make it work

Recreate the database with

    script/create_databases.sh

Note that on Ubuntu you will need to install the package

    sudo apt-get install postgresql-contrib

Check that the application is up and works as usual.


## Exercise 1: add the /authenticate action

Requirement:

A POST request to the url /authenticate with parameters id=X and password=Y should be checked by the CashiersRepository object (which is already present and should work).  If the id and password are found, then the server responds with

    200 OK
    { "session_id": 1234 }

(Initially the session ID can be fixed.)  If the id or password is wrong, then the server responds with

    401 Not Authenticated
    { "description": "Bad id or password" }


## Exercise 2: add a session repository

We will write a SessionRepository object.  Its responsibilities are:

 - to create new sessions
 - to store sessions in memory, and return session by ID

    class SessionRepository {
      CashierSession createSession();
      CashierSession findSession(String sessionId);
    }

    class CashierSession {
      String sessionId;
    }


## Exercise 3: authorize every other action

Make sure that when /scan or /total is invoked, an additional parameter sessionId is sent.  This parameter will be checked by the server with the SessionRepository; if the session is not found, or if the sessionId parameter is missing, then the request will fail with "401 Not Authorized"


