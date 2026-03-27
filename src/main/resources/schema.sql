

CREATE TABLE IF NOT EXISTS pizzas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    price DECIMAL(5,2) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS toppings(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(5,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS pizza_toppings(
    pizza_id INT,
    topping_id INT,
    PRIMARY KEY (pizza_id, topping_id),
    FOREIGN KEY(pizza_id) REFERENCES Pizzas(id),
    FOREIGN KEY(topping_id) REFERENCES toppings(id)
);

CREATE TABLE IF NOT EXISTS users(
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    points INT NOT NULL
);


CREATE TABLE IF NOT EXISTS orders(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(50),
    totalPrice DECIMAL(7,2),
    created_at DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (user_email) REFERENCES Users(email)
    );

CREATE TABLE IF NOT EXISTS order_pizzas(
    order_id INT,
    pizza_id INT,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY (order_id, pizza_id),
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (pizza_id) REFERENCES Pizzas(id)
);


