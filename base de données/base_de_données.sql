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

--
-- Table structure for table `donneur`
--

DROP TABLE IF EXISTS `donneur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donneur` (
  `ID` int NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `adresse` varchar(20) DEFAULT NULL,
  `numtel` varchar(20) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `grpsn` varchar(20) DEFAULT NULL,
  `remarque` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `donneur_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donneur`
--

LOCK TABLES `donneur` WRITE;
/*!40000 ALTER TABLE `donneur` DISABLE KEYS */;
INSERT INTO `donneur` VALUES (6,'Alouane','wail','boumerdes','055125698','Homme','A+','Donneur',20),(7,'a','a','a','a','Femme','A+','Donneur',21),(8,'MERDAS','AMIR','boumerdase','025525522','Homme','O-','Donneur',21),(9,'Zitouni','Abdelbari','saoula','0551759229','Homme','A-','Donneur',26),(10,'HADOUCHE','NADIA','usthb','0000','Femme','A+','Donneur',18),(11,'z','z','z','z','Femme','A+','Donneur',10);
/*!40000 ALTER TABLE `donneur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evenement` (
  `IDEVNT` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `dateEv` date DEFAULT NULL,
  PRIMARY KEY (`IDEVNT`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evenement`
--

LOCK TABLES `evenement` WRITE;
/*!40000 ALTER TABLE `evenement` DISABLE KEYS */;
INSERT INTO `evenement` VALUES (5,'Universite','USTHB','2021-04-08'),(6,'Camps Militaire','ALMOJAHID_AIMENE','2021-04-28'),(7,'Universite','UNI_ALGER','2021-04-07');
/*!40000 ALTER TABLE `evenement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rdv` (
  `IDRDV` int NOT NULL AUTO_INCREMENT,
  `IDonneur` int DEFAULT NULL,
  `typededons` varchar(20) DEFAULT NULL,
  `grpsn` varchar(20) DEFAULT NULL,
  `datedon` date DEFAULT NULL,
  PRIMARY KEY (`IDRDV`),
  KEY `IDonneur` (`IDonneur`),
  CONSTRAINT `rdv_ibfk_1` FOREIGN KEY (`IDonneur`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdv`
--

LOCK TABLES `rdv` WRITE;
/*!40000 ALTER TABLE `rdv` DISABLE KEYS */;
INSERT INTO `rdv` VALUES (19,7,'plasma','A+','2021-04-07');
/*!40000 ALTER TABLE `rdv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!50001 DROP VIEW IF EXISTS `stock`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `stock` AS SELECT 
 1 AS `IDDon`,
 1 AS `IDdonnateur`,
 1 AS `hospital`,
 1 AS `groupeSanguin`,
 1 AS `typeDon`,
 1 AS `dateDeCollect`,
 1 AS `dateDexpiration`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `IDtransaction` int NOT NULL AUTO_INCREMENT,
  `IDdemmande` int DEFAULT NULL,
  `typeDon` varchar(20) DEFAULT NULL,
  `IDdonneur` int DEFAULT NULL,
  `recepteur` varchar(50) DEFAULT NULL,
  `dateTransaction` date DEFAULT NULL,
  PRIMARY KEY (`IDtransaction`),
  KEY `IDdonneur` (`IDdonneur`),
  KEY `IDdemmande` (`IDdemmande`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`IDdonneur`) REFERENCES `demdon` (`IDdonneur`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`IDdemmande`) REFERENCES `demdon` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (5,10,'sang',8,'WAIL','2021-04-07'),(6,11,'sang',9,'AIMEN','2021-04-07'),(7,12,'sang',10,'AIMEN','2021-04-07');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `typeuser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'lazalokmane@gmail.com','0000','admin'),(6,'wail@hotmail.com','0000','Donneur'),(7,'a','a','Donneur'),(8,'AMIR@gmail.com','0000','Donneur'),(9,'abdou@gmail.com','0000','Donneur'),(10,'hadouche@gmail.com','0000','Donneur'),(11,'z','z','Donneur');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `stock`
--

/*!50001 DROP VIEW IF EXISTS `stock`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `stock` AS select `don`.`IDDon` AS `IDDon`,`don`.`IDdonnateur` AS `IDdonnateur`,`don`.`hospital` AS `hospital`,`don`.`groupeSanguin` AS `groupeSanguin`,`don`.`typeDon` AS `typeDon`,`don`.`dateDeCollect` AS `dateDeCollect`,`don`.`dateDexpiration` AS `dateDexpiration` from `don` where (`don`.`instock` = true) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-07 14:01:36
