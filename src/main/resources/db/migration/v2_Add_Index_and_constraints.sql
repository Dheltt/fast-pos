-- V2__Add_index_and_constraints.sql

-- Índice en el correo electrónico de la tabla "users"
CREATE INDEX idx_users_email ON users(email);

-- Índice en el nombre de los productos
CREATE INDEX idx_products_name ON products(name);

-- Índice en la fecha de creación de las ventas
CREATE INDEX idx_sales_created_at ON sales(created_at);
