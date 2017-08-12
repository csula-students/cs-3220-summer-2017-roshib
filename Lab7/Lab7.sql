USE lab7;
CREATE TABLE food_items (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
	food_name VARCHAR(255) NOT NULL,
	price VARCHAR(255),
	description VARCHAR(255),
    image text
);
#adding item to the table
INSERT INTO food_items (id, food_name,price,description,image) VALUES (1, 'Alfredo Chicken Penne','$15.89','Italian','https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (2, 'Caesar Salad','$12.08','Italian','https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (3, 'Pepperoni Stromboli','$9.89','Italian','http://samandlouiespizza.com/wp-content/uploads/2016/11/Capture0003-4-1-300x200.jpg');




	CREATE TABLE orders (
    id INTEGER AUTO_INCREMENT NOT NULl PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    statuse enum('IN_PROGRESS','IN_QUEUE','COMPLETED') NOT NULl, 
    created DATETIME
	);
    
    CREATE TABLE order_foods (
	order_id INTEGER NULL REFERENCES orders(id),
	food_id INTEGER NOT NULL REFERENCES food_items(id)
);

INSERT INTO orders (id,customer_name,statuse,created) 
VALUES (1,'Roshanak','IN_QUEUE','2017-08-10');
INSERT INTO orders (id,customer_name,statuse,created) 
VALUES (2,'Hiroko','IN_QUEUE','2017-08-11');


INSERT INTO order_foods (order_id, food_id) VALUES 
(1, 1),
(2, 1);