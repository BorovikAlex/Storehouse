-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.18 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных storehouse
CREATE DATABASE IF NOT EXISTS `storehouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `storehouse`;

-- Дамп структуры для таблица storehouse.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.admin: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id_admin`, `login`, `password`) VALUES
	(3, 'qwe', 'qwe'),
	(7, 'admin', 'admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`id_brand`),
  UNIQUE KEY `brand_UNIQUE` (`brand`),
  KEY `brand` (`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.brand: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`id_brand`, `brand`, `country`) VALUES
	(1, 'Dell', 'США'),
	(2, 'Apple', 'США'),
	(3, 'Samsung', 'Корея'),
	(4, 'Acer', 'Китай'),
	(5, 'Asus', 'Китай'),
	(6, 'Lenovo', 'Китай'),
	(7, 'Hewlett-Packard', 'США'),
	(8, 'LG', 'Корея');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.product
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(50) NOT NULL,
  `vendorcode` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `vendorcode_UNIQUE` (`vendorcode`),
  KEY `brand` (`brand`),
  KEY `vendorcode` (`vendorcode`),
  KEY `product` (`product`),
  CONSTRAINT `FK_product_brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`brand`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product_products` FOREIGN KEY (`product`) REFERENCES `products` (`products`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.product: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id_product`, `product`, `vendorcode`, `brand`) VALUES
	(1, 'ПК', 1, 'Asus'),
	(2, 'Ноутбук', 2, 'Asus'),
	(3, 'Монитор', 3, 'Asus'),
	(5, 'Мышка', 4, 'Asus'),
	(6, 'Колонки', 5, 'Asus'),
	(7, 'Клавиатура', 6, 'Asus');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.products
CREATE TABLE IF NOT EXISTS `products` (
  `id_products` int(11) NOT NULL AUTO_INCREMENT,
  `products` varchar(50) NOT NULL,
  PRIMARY KEY (`id_products`),
  UNIQUE KEY `products_UNIQUE` (`products`),
  KEY `products` (`products`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.products: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id_products`, `products`) VALUES
	(8, 'Зарядка'),
	(4, 'Клавиатура'),
	(7, 'Колонки'),
	(2, 'Монитор'),
	(1, 'Мышка'),
	(5, 'Ноутбук'),
	(6, 'ПК'),
	(3, 'Системник');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.store
CREATE TABLE IF NOT EXISTS `store` (
  `id_store` int(11) NOT NULL AUTO_INCREMENT,
  `storenumber` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `housenumber` int(11) NOT NULL,
  PRIMARY KEY (`id_store`),
  KEY `storenumber` (`storenumber`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.store: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` (`id_store`, `storenumber`, `street`, `housenumber`) VALUES
	(7, '111a', 'Ленина', 25),
	(8, '14a', 'Гикало', 9);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.storehouse
CREATE TABLE IF NOT EXISTS `storehouse` (
  `id_storehouse` int(11) NOT NULL AUTO_INCREMENT,
  `storenumber` varchar(45) NOT NULL,
  `vendorcode` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id_storehouse`),
  KEY `product` (`vendorcode`),
  KEY `storenumber` (`storenumber`),
  CONSTRAINT `FK_storehouse_product` FOREIGN KEY (`vendorcode`) REFERENCES `product` (`vendorcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_storehouse_store` FOREIGN KEY (`storenumber`) REFERENCES `store` (`storenumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.storehouse: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `storehouse` DISABLE KEYS */;
INSERT INTO `storehouse` (`id_storehouse`, `storenumber`, `vendorcode`, `amount`) VALUES
	(4, '111a', 3, 221),
	(5, '14a', 3, 78),
	(7, '14a', 6, 30);
/*!40000 ALTER TABLE `storehouse` ENABLE KEYS */;

-- Дамп структуры для таблица storehouse.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы storehouse.user: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id_user`, `name`, `surname`, `login`, `password`, `email`) VALUES
	(1, 'qwe', 'qwe', 'qew', 'qwe', 'wqew'),
	(3, 'asd', 'asd', 'asd', 'asd', 'asd');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
