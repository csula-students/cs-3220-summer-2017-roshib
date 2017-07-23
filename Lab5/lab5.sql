USE lab5;

#creating food item table
CREATE TABLE food_items (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
	food_name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
    price double
);
#adding item to the table
INSERT INTO food_items (id, food_name,description,price) VALUES (1, 'Hamburger','A hamburger',9.99);
INSERT INTO food_items (id, food_name,description,price) VALUES (2, 'Fries','Some fries',4.99);
INSERT INTO food_items (id, food_name,description,price) VALUES (3, 'Coke','Coke cola',2.99);
#Update food item name from "Hamburger" to "Salad"
UPDATE food_items SET food_name='Salad' WHERE id=1;


###########################################################


#creating order table
CREATE TABLE orders (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
	customer_name VARCHAR(255) NOT NULL,
	created datetime
);
#adding items to table
INSERT INTO orders (id, customer_name,created) VALUES (1, 'Eric','2017-07-20 13:35:55');
INSERT INTO orders (id, customer_name,created) VALUES (2, 'John','2017-07-22 10:35:55');
INSERT INTO orders (id, customer_name,created) VALUES (3, 'Jane','2017-07-22 15:35:55');
INSERT INTO orders (id, customer_name,created) VALUES (4, 'Alice','2017-07-22 16:35:55');
#Update customer name from "Jane" to "Doe"
UPDATE orders SET customer_name='Doe' WHERE id=3;


#################################################################
#creating shopping cart table
CREATE TABLE shopping_cart (
	id INTEGER NOT NULL,
	customer_name VARCHAR(255) NOT NULL,
    food_id INTEGER REFERENCES food_items(id),
	quantity INTEGER NOT NULL
);
#adding items to table
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (1,'Anonymous',1,2);
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (1,'Anonymous',2,1);
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (1,'Anonymous',2,1);
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (2,'Mike',1,1);
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (2,'Mike',2,1);
INSERT INTO shopping_cart (id, customer_name,food_id,quantity) VALUES (3,'Bob',3,1);


###########################################################



#creating order foods table
CREATE TABLE  order_foods (
	order_id INTEGER REFERENCES orders(id),
	food_id INTEGER REFERENCES food_items(id),
	quantity INTEGER NOT NULL
);
#adding items to table
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (1,1,1);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (1,2,2);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (2,2,2);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (1,2,2);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (2,3,1);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (3,3,1);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (4,2,1);
INSERT INTO order_foods (order_id, food_id,quantity) VALUES (4,3,2);


#Find out which food item has the most orders 
SELECT food_items.food_name,sum(quantity) FROM order_foods JOIN food_items WHERE food_items.id= order_foods.food_id group by food_name order by sum(quantity) desc limit 1;


#Find out which food item in least shopping carts
SELECT food_items.food_name,sum(quantity) FROM shopping_cart JOIN food_items WHERE food_items.id= shopping_cart.food_id group by food_name order by sum(quantity) asc limit 1;

#Find out all restaurant food items using SELECT query
SELECT * FROM food_items;


#Find out all restaurant order statuses using SELECT query
SELECT * FROM order_foods;



#Find out the order statuses that is created today
SELECT * FROM orders WHERE DATE (created)=DATE(now());


#DELETE restaurant food items table
DROP TABLE food_items;


#Delete restaurant order statuses table
DROP TABLE orders;


#Delete restaurant cart table
DROP TABLE shopping_cart;