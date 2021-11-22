-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: gl
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `demdon`
--

DROP TABLE IF EXISTS `demdon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demdon` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `groupeSanguin` varchar(20) DEFAULT NULL,
  `objectif` varchar(20) DEFAULT NULL,
  `remarque` varchar(50) DEFAULT NULL,
  `typeDon` varchar(20) DEFAULT NULL,
  `etat` varchar(20) DEFAULT NULL,
  `IDdonneur` int DEFAULT NULL,
  `recepteur` varchar(50) DEFAULT NULL,
  `pasDeSacs` tinyint(1) DEFAULT NULL,
  `dateDemmande` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDdonneur` (`IDdonneur`),
  CONSTRAINT `demdon_ibfk_1` FOREIGN KEY (`IDdonneur`) REFERENCES `donneur` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demdon`
--

LOCK TABLES `demdon` WRITE;
/*!40000 ALTER TABLE `demdon` DISABLE KEYS */;
INSERT INTO `demdon` VALUES (10,'O-','NDIROU_LKHIR','WALu','sang','approuver',8,'WAIL',1,'2021-04-07'),(11,'A-','AAAAAA','WALU','sang','approuver',9,'AIMEN',1,'2021-04-07'),(12,'A+','a','aa','sang','approuver',10,'AIMEN',1,'2021-04-07');
/*!40000 ALTER TABLE `demdon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-07 13:37:39
