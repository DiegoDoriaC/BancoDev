-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: microservicio_empleado
-- ------------------------------------------------------
-- Server version	8.4.5

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `tbl_empleado`
--

LOCK TABLES `tbl_empleado` WRITE;
/*!40000 ALTER TABLE `tbl_empleado` DISABLE KEYS */;
INSERT INTO `tbl_empleado` VALUES (1,1,'Rodriguez','rebeca@analista.com','https://storagearchitectcrowd.blob.core.windows.net/bancodev/analista-rebeca.png','Rebeca','49442a8bccaa5b9c6ce95da7c7c16362c2cba5a7154ade43569894f8eaad3f69','ANALISTA'),(2,1,'Matos','fiorela@analista.com','https://storagearchitectcrowd.blob.core.windows.net/bancodev/analista-fiorela.png','Fiorela','eb66d7fcd648a0a82258495d36687fb3e25a85fa4cf622cdeec51a3165b02431','ANALISTA'),(3,1,'Redhead','raul@analista.com','https://storagearchitectcrowd.blob.core.windows.net/bancodev/analista-raul.png','Raul','16f04c3e2876cf0054398f1093f0e6913ecb5064cb60ae6bee899c0e16f46418','ANALISTA'),(4,1,'Mendoza','juan@cajero.com','https://storagearchitectcrowd.blob.core.windows.net/bancodev/analista-raul.png','Juan','f6ccb3e8d609012238c0b39e60b2c9632b3cdede91e035dad1de43469768f4cc','CAJERO');
/*!40000 ALTER TABLE `tbl_empleado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-10 20:21:41
