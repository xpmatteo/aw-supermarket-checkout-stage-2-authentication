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

(Initially the session ID can be fixed.)   IMPORTANT: it should also set a COOKIE with the name "session_id" and the value 1234 (the same number that was produced in the output.)  In order to set a cookie using the servlet API, you can use the HttpServletResponse object:

    Cookie cookie = new Cookie("session_id", "1234");
    response.addCookie(cookie);

If the id or password is wrong, then the server responds with

    401 Not Authenticated
    { "description": "Bad id or password" }

and does not set the cookie.

If the parameter "id" is not present, you should send the response

    400 Bad Request
    { "description": "Parameter 'id' is required" }

The same should happen if the parameter "password" is not present.  This way the user of our API can learn how to use it by interacting with it; we make our API self-documenting (and this is a very good thing to do).

If the parameter "id" is not a number, we should NOT crash with an error 500.  Please catch the error and treat as if the id was not found in the CashiersRepository.


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

Then change the response to a successful /authenticate action: whenever the /authenticate is successful, you should create a new session, with a new random session id, and use that session id in the response and the cookie that is sent back to the browser.


## Exercise 3: authorize every other action

When /scan or /total or /reset is invoked, we must check that the user is authenticated by looking for the session_id cookie.  Use the HttpServletRequest to examine all cookies:

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
    	// ...
    }

If you find the session_id cookie, and the corresponding session is present in the SessionRepository, then the action will continue as usual.  Otherwise, the response should be

    401 Not Authenticated
    { "description": "Please go to /authenticate" }

## Exercise 4: publish the application on Heroku

The code now supports working with the database in Heroku (see what happens in Main.java).  You will have to load the schema in the production database.  You may obtain the postgresql url with

    heroku config

And then use it with

    psql URL

Where URL is the url you got previously with 'heroku config'

You can also use the command

    script/production_dbconsole.sh

See the instructions in that file for how to copy a sql file to heroku in production.

## Exercise 5 (optional): increase security

Use a "salt" to encrypt the password: extract a random byte, convert it to an hexadecimal string and save it to a new column in the cashiers table.  When you encrypt the password, you encrypt salt + password.  When you authenticate the cashier, you must first find the salt for the cashier in the DB; then you encrypt the password in clear that you got from the web together with the salt that you got from the DB.  The resulting string should be equal to the encrypted password you got from the DB.

## Exercise 6 (optional): even more security

Use "bcrypt" instead of "sha256" to encrypt the password.  The reason why it is more secure is explained in this article: http://codahale.com/how-to-safely-store-a-password/

