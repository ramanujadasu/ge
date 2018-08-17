
Steps:
Java Question Solution: /ticket-booking-ms/src/main/java/com/ge/binarysearch
To run BinarySearchMain.java

SQL Question Solution: /ticket-booking-ms/src/main/java/com/ge/sqlquery/sql_solution.txt


Spring Question Solution:
I have implemented required APIs, attached screen shorts, postman dumps in the resource folder. 
Just you can import the postman dumps and processed order images in the screenshort folder images.
I have incorporated Swager, Authentication concepts
I didn't used any database, just i have used inbuild heap memory objects.

Need execute following APIs in the order:
=====================

To generate token, 
GET: http://localhost:8080/token
===
Add Movie
POST: http://localhost:8080//tb/add-movie
HEADERS: 
[{"key":"Content-Type","value":"application/json"},{"key":"token","value":"{{token}}","description":"","enabled":true}]
BODY:
{
  "movieId":"KES02", "movieName":"kesava2"
}
===
GET All movies
GET: http://localhost:8080//tb/all-movies
HEADERS: 
[{"key":"Content-Type","value":"application/json"},{"key":"token","value":"{{token}}","description":"","enabled":true}]
===
Book the ticket
POST: http://localhost:8080//tb/book-ticket
HEADERS: 
[{"key":"Content-Type","value":"application/json"},{"key":"token","value":"{{token}}","description":"","enabled":true}]
BODY:
{
  "movieId":"KES01", "userId":"kesava1", "bookingId":"BOOK1","seats":[5,24]
}
===
Get the all tickets
GET: http://localhost:8080//tb/tickets
HEADERS: 
[{"key":"Content-Type","value":"application/json"},{"key":"token","value":"{{token}}","description":"","enabled":true}]
===
Get the booking tickets by userId
GET: http://localhost:8080//tb/user-tickets?userId=kesava1
HEADERS: 
[{"key":"Content-Type","value":"application/json"},{"key":"token","value":"{{token}}","description":"","enabled":true}]



=========================




Instructions: -
Please refer to individual questions for instructions.
Java Question: -
1.	You’re given a binary tree (Please see below)
You have to write code which does the following:
a.	Inputs the given tree into memory.
b.	Given an integer n, finds the integer in the tree and returns a Boolean.

 
Instructions: -
Please create a file in your repository and name the class as Tree.java and share us the file URL in Git.

Spring Question
1.	Develop a ticket booking application in Spring/Spring boot using MVC design pattern. The application should perform the below operations: -
a.	Add Movies.
b.	See the list of movies added.
c.	Book Tickets for the movie (Assume that the max number of tickets that can be booked for a movie is 100). Create an API that accepts the seats no (1….100) as input.
d.	When the user books the tickets. Lock those seats such that no other user can book those seats for 2 minutes.
Points To consider: -
1.	Just develop the rest APIs for the requirements mentioned above. 
2.	It would be great if you use Java8 features.
3.	It would be great if you secure the APIs (using Basic Auth).

Instructions: -
Create a new project in your repository named “Ticket Booking MicroService” and share us the link. Please mention the request/response Json Structure for all the APIs In ReadMe file or you can also implement swagger API documentation in your code.

SQL Question
I have two tables as shown below: -
Model Table
id	name	model_id	is_deleted
1	ABC	1234	FALSE
2	ABC	5678	FALSE
3	XYZ	4567	FALSE

Revision Table
id	model_id	rev_id	created_at
1	1234	2	23456
2	1234	3	1111
3	5678	1	10000
4	5678	2	10001

I want to get the name, model_id, rev_id,created_at from this table such that the result set has the data like shown below:-
Model_Revision Table
name	model_id		rev_id	created_at
ABC	1234		2	23456
ABC	5678		2	10001
XYZ	4567		 	 

Here the data is grouped by name, model_id and only 1 data for each group is shown which has the highest value of created_at.
Please write a SQL Query in mysql/Postgres/Oracle to get the above result.
Instructions: -
1.	You can share the SQL query with us in email or you can also execute the query in SQL Fiddle and share us the link.





