The project has following functionalities

    Users: Customers and the Owner. Owner needs to be authenticated.
    Customers | Creating a Reservation:
        Customers can reserve a table by providing date, time, party size, contact details.
        If reservation is in Waiting status, ask customer to confirm.
        Provide a unique Confirmation Code and status back to the customer.
    Customers | Edit/Cancel a Reservation:
        Customers can edit an existing reservation using Confirmation Code.
        Customers can edit date, time, and party size.
        Use same Confirmation Code and return new status.
        Customers can cancel an existing reservation using Confirmation Code.
    Owner | Login:
        Owner can login using email and password.
        No registration module is required.
    Owner | View Reservation:
        Owner can view list of reservations.
        Click on each reservation item for more details.
    Owner | View Seating Area
        Owner can view seating area (tables) in a list form.
        Each item can have ConfirmationCode, Size, Status, Since fields.
        On clicking ConfirmationCode, open reservation detail screen.
    Owner | Create and Edit a Reservation:
        Same as Customer Create and Edit Reservation flows.

    Owner | Profile & Settings:
        Owner can view/edit restaurant profile details like Name, Contact, Email, Address etc.
        Owner can update settings like Auto Assign, Restaurant Open/Closing days and times etc.
    Owner | Assign Table:
        Open reservation detail screen from list of reservations.
        On clicking Assign Table, open seating map and select table.
    Owner | Auto Assign Table:
        If Auto Assign is enabled, system should assign the table to a new reservation automatically.
    Owner | Change Table:
        Owner should be able to change the table for a reservation.
    Owner | View Contact List:
        Owner can view contact list of all the customers and their past reservations.
        
        
User Interface Design:
    
        Responsive Design. Test on desktop and tablet. Mobile optional but recommended.
        You can use a CSS framework like Bootstrap.
        Use HTML5 and CSS3 features like new elements, fonts, transitions, transformations.
    RESTful API:
        Data exchange format: JSON
        Jersey + Jackson (preferred)
        MySQL, Tomcat (or any other J2EE server/container)
    JavaScript :
        Single Page Application using AngularJS
        Multiple views, routing, follow a style-guide.
    Build, Optimization, and Testing:
        grunt
        Protractor for e2e testing.

