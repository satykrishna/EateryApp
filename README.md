ABOUT THIS PROJECT
===================
The website offers online reservation system where customer can reserve a table at restaurant online and can specify what kind of event he/she would like to celebrate at the restaurant for that particular reservation. The owner of the restaurant can monitor the reservations and restaurant details.

relevant script files and html files  are located in webcontent folder

DETAILED EXPLANATION
===================================
Users:
=====
    1. Customers 
    2. Owner. Owner needs to be authenticated.
 Customers
=========
    Creating a Reservation:
        Customers can reserve a table by providing date, time, party size, contact details.
        If reservation is in Waiting status, ask customer to confirm.
        Provide a unique Confirmation Code and status back to the customer.
    Edit/Cancel a Reservation:
        Customers can edit an existing reservation using Confirmation Code.
        Customers can edit date, time, and party size.
        Use same Confirmation Code and return new status.
        Customers can cancel an existing reservation using Confirmation Code.
   
Owner
=====
     Login:
        Owner can login using email and password.
        No registration module is required.
     View Reservation:
        Owner can view list of reservations.
        Click on each reservation item for more details.
    View Seating Area
        Owner can view seating area (tables) in a list form.
        Each item can have ConfirmationCode, Size, Status, Since fields.
        On clicking ConfirmationCode, open reservation detail screen.
     Create and Edit a Reservation:
        Same as Customer Create and Edit Reservation flows.
     Profile & Settings:
        Owner can view/edit restaurant profile details like Name, Contact, Email, Address etc.
        Owner can update settings like Auto Assign, Restaurant Open/Closing days and times etc.
    Assign Table:
        Open reservation detail screen from list of reservations.
        On clicking Assign Table, open seating map and select table.
    Auto Assign Table:
        If Auto Assign is enabled, system should assign the table to a new reservation automatically.
    Change Table:
        Owner should be able to change the table for a reservation.
    View Contact List:
        Owner can view contact list of all the customers and their past reservations.
       
USER INTERFACE DESIGN
=======================
    
       It's a responsive web design. Tested on desktop and tablet. 
       Bootstrap framework is used

RESTful API:
------------
        Data exchange format: JSON
        Jersey + Jackson
        Database : MySQL
        Server: Tomcat

JavaScript :
------------
        Single Page Application using AngularJS
        Multiple views, routing, followed John papa styleguide.

Build, Optimization, and Testing:
--------------------------------
    grunt is used for minification.


