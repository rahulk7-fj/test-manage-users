Service Name: api-manage-user 
This service uses MySql Database
User data is stored in T_USERS table
Initial build performs two operations 
 - Creates a new user - POST method - inserts data into the database and returns user ID
 - Fetch a user - Get method - user id should be passed as path variable - talks to db and fetch user data if present else give empty object
