USE lab6;
CREATE TABLE food_items (
        id INTEGER AUTO_INCREMENT PRIMARY KEY,
	food_name VARCHAR(255) NOT NULL,
	price VARCHAR(255),
	description VARCHAR(255),
        mage text
);

#adding item to the table
INSERT INTO food_items (id, food_name,price,description,image) VALUES (1, 'Alfredo Chicken Penne','$15.89','Italian','https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (2, 'Caesar Salad','$12.08','Italian','https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (3, 'Pepperoni Stromboli','$9.89','Italian','http://samandlouiespizza.com/wp-content/uploads/2016/11/Capture0003-4-1-300x200.jpg');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (4, 'Baked Ziti','$8.89','Italian','https://www.rodalesorganiclife.com/sites/rodalesorganiclife.com/files/styles/recipe_teaser_custom_user_desktop_1x/public/recipe/protein-power-baked-ziti.jpg?itok=JFCgNIh2&timestamp=1457047141');
INSERT INTO food_items (id, food_name,price,description,image) VALUES (5, 'Garlic Breadstick','$11.49','Italian','http://images.smuckers.ca/images/recipes/17/recipe_2745_daily.jpg');
