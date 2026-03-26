

CREATE TABLE IF NOT EXIST Pizzas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(3,2) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXIST Toppings(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(3,2) NOT NULL
);

CREATE TABLE IF NOT EXIST Pizza_Toppings(
    pizza_id INT,
    topping_id INT,
    PRIMARY KEY (pizza_id, topping_id),
    FOREIGN KEY(pizza_id) REFERENCES Pizzas(id),
    FOREIGN KEY(topping_id) REFERENCES toppings(id)
);

CREATE TABLE IF NOT EXIST Users(
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    points INT NOT NULL
);


CREATE TABLE Orders(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(50),
    totalPrice DECIMAL(4,2),
    FOREIGN KEY (user_email) REFERENCES Users(email)
    );

CREATE TABLE Order_pizzas(
    order_id INT,
    pizza_id INT,
    PRIMARY KEY (order_id, pizza_id),
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (pizza_id) REFERENCES Pizzas(id)
);


