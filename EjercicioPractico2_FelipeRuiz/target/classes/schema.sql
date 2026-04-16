DROP DATABASE IF EXISTS eventos;
CREATE DATABASE eventos
 CHARACTER SET = ug8mb4
 COLLATE = ug8mb4_unicode_ci;
USE eventos;
-- Tabla roles
CREATE TABLE rol (
 id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(100) NOT NULL UNIQUE
);
-- Tabla usuarios
CREATE TABLE usuario (
 id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(150),
 email VARCHAR(200) UNIQUE,
 password VARCHAR(255),
 rol_id BIGINT UNSIGNED,
 ac2vo BOOLEAN DEFAULT TRUE,
 fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (rol_id) REFERENCES rol(id)
);
-- Tabla eventos
CREATE TABLE evento (
 id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(150),
 descripcion TEXT,
 fecha DATE,
 capacidad INT,
 ac2vo BOOLEAN DEFAULT TRUE
);
-- Datos de prueba
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('ORGANIZADOR'), ('CLIENTE');
INSERT INTO usuario (nombre, email, password, rol_id) VALUES
('Admin', 'admin@email.com', '12345', 1),
('Organizador', 'org@email.com', '12345', 2),
('Cliente', 'cliente@email.com', '12345', 3);
INSERT INTO evento (nombre, descripcion, fecha, capacidad) VALUES
('Conferencia Tech', 'Evento de tecnología', '2026-05-10', 100),
('Taller Web', 'Curso prác2co', '2026-06-15', 50);