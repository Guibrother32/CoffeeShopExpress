DROP DATABASE IF EXISTS CoffeeShopDB;

CREATE DATABASE IF NOT EXISTS CoffeeShopDB;

USE CoffeeShopDB;
DROP TABLE IF EXISTS Produto;

CREATE TABLE Produto(
	idProduto INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    preco double NOT NULL,
	qtd INT
    );


SELECT * FROM Produto;

UPDATE Produto SET nome='ijsd' WHERE idProduto = 3; 