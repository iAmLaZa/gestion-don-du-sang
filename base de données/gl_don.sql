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
-- Table structure for table `don`
--

DROP TABLE IF EXISTS `don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `don` (
  `IDDon` int NOT NULL AUTO_INCREMENT,
  `IDdonnateur` int DEFAULT NULL,
  `hospital` varchar(20) DEFAULT NULL,
  `groupeSanguin` varchar(20) DEFAULT NULL,
  `infermierMedcin` varchar(20) DEFAULT NULL,
  `typeDon` varchar(20) DEFAULT NULL,
  `passe` tinyint(1) DEFAULT NULL,
  `pasDeSacDeSang` tinyint(1) DEFAULT NULL,
  `dateDeCollect` date DEFAULT NULL,
  `dateDexpiration` date DEFAULT NULL,
  `maladies` varchar(50) DEFAULT NULL,
  `instock` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDDon`),
  KEY `IDdonnateur` (`IDdonnateur`),
  CONSTRAINT `don_ibfk_1` FOREIGN KEY (`IDdonnateur`) REFERENCES `donneur` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `don`
--

LOCK TABLES `don` WRITE;
/*!40000 ALTER TABLE `don` DISABLE KEYS */;
INSERT INTO `don` VALUES (10,8,'MUSTAPHA_BACHA','O-','LOKMANE','sang',1,1,'2021-04-07','2021-05-19','saint',1),(11,9,'fdare','A-','lokmane','sang',1,1,'2021-04-07','2021-05-19','saint',1),(12,10,'MUSTPHABACHA','A+','LOKMANE','sang',1,1,'2021-04-07','2021-05-19','saint',1),(13,11,'a','A+','a','plasma',1,1,'2021-04-07','2021-05-19','vhc/',0);
/*!40000 ALTER TABLE `don` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-07 13:37:38
