-- V1__Create_schema.sql

-- Crear la tabla "users"
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Crear la tabla "products"
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    category VARCHAR(255)
);

-- Crear la tabla "sales"
CREATE TABLE IF NOT EXISTS sales (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    pay_method VARCHAR(50),
    total DECIMAL(10, 2) NOT NULL,
    products JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_sales_user FOREIGN KEY (user_id) REFERENCES users(id)
);
