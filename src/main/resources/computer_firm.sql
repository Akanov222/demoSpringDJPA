DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS PC;
DROP TABLE IF EXISTS Laptop;
DROP TABLE IF EXISTS Printer;

CREATE TABLE Product (
    product_id SERIAL PRIMARY KEY,
    maker varchar(100),
    model integer not null,
    type varchar(100)
);

CREATE TABLE PC (
    pc_id SERIAL PRIMARY KEY,
    model integer not null,
    speed integer not null,
    ram integer not null,
    hd float(1) not null,
    cd varchar(100),
    price float(2)
);

CREATE TABLE Laptop (
    laptop_id SERIAL PRIMARY KEY,
    model integer not null,
    speed integer not null,
    ram integer not null,
    hd float(1) not null,
    price float(2) not null,
    screen integer not null
);

CREATE TABLE Printer (
    printer_id SERIAL PRIMARY KEY,
    model integer not null,
    color varchar(100),
    type varchar(100),
    price float(2)
);