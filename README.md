Banking Sytem

This project aims at retrieving the bank statements of the customers based on the permissions they have to access .
-Admin can  retrieve the bank statements on screen by entering
1)Account ID only
2)Account ID + startdate and end date of transactions
3)AccountID + startAmount and endAmount of transactions

-User can retrieve the bank statements on screen by entering 
1)AccountID only 

-Any other way of retrieving statements by him will display an erro page.

-The user cannot login twice (the user should logout before login).

- The session time out is 5 minutes. 

-Account Number on the statement is partially masked .

Pre Requisites to  execute :
In order to get this project working we need 
1)Server 
2)Db with
  - BANKUSERS table where  users are registered along with there login status)
  - account table where accounttype and accountnumber are retrieved from.
  -statement table where all the fields required for bankstatement are retrieved.
  
Installation & Execution

1)One needs to deploy the code in the server choosen and then ensure the server is up and runing with no errors
2)On the browser hit url:  http://localhost:8080/nagarro/login
3)Then a login page is displayed .Enter the Username either with "admin" or "user" case ignored
4)Once the credentials are validated you can view a home page where details are to be entered
5)Based on the above criteria mentioned on the Account ID only and for the above fields values aneeds to be entered
   and click on submit to view the bankstatement.
 6)Each user can login once only after logout

Scope for development in Future

-Scenerio of logout feature when session is expired is yet to be handled.
