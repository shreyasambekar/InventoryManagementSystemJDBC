# InventoryManagementSystemJDBC

Mysql database server was used. 
If you are using other database server, replace the driver with the required driver for database in the config.properties.file.
Also change the ip address of database server and its credentials in config.properties.

Run DDLs.sql on your database server to create required tables. 

The following functions are implemented in service classes. These can be called from main method in Tester.java.
* Create and insert customers data in database.
* Create and insert data for different items in stock.
* Create and insert purchase orders for customers.
* Create and insert order items corresponding to some stock item that are associated with particular purchase order.
* Get all orders that are not yet shipped from database.
* Print labels for orders.
* Ship order for some customer.
* Print total sales by customers.
* Print all invoices.
* Checking if stock of some stock item is below some minimum defined quantity.
* Populate customers and stockitems tables from csv files.
* Find all orders placed by particular customer.
* Find all orders that are shipped on particular date.
* Segregate orders based on area and customer and calculate area wise total billing results.
* Print all shipped orders in last month.
* Find total sale done in last month.
* Print all items to ship in current week.

