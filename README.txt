
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




