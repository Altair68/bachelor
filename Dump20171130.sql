CREATE DATABASE  IF NOT EXISTS `gpm_server2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gpm_server2`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: gpm_server2
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thesis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `supervisor` varchar(255) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
INSERT INTO `thesis` VALUES (1,1,'camunda!','prof',-1),(2,2,'asdf','qwer',-1),(3,2,'asdf','qwer',-1),(4,2,'asdf','Matze',-1),(5,1,'asdf','asdf',-1),(6,1,'asdf','sadf',1),(7,1,'lalalalalalalalala','sdf',1),(8,1,'equipbgreuqibgureiqpgbrpeq','fdsafdsa',0),(9,2,'lalelu','asdf',-1),(10,2,'hammer','asdf',1),(11,2,'dlddldldld','qwerfdhj4',-1),(12,2,'t1','adsf',0),(13,1,'t2','asdf',1);
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30 20:07:35
CREATE DATABASE  IF NOT EXISTS `gpm_server1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gpm_server1`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: gpm_server1
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `prename` varchar(255) NOT NULL,
  `passed_practice_project` tinyint(1) NOT NULL,
  `passed_bachelor_thesis` tinyint(1) NOT NULL,
  `passed_colloquium` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Müller','Robert',1,0,0),(2,'Mustermann','Max',1,0,0),(3,'Meier','Daniela',0,0,0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `ects` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Höhere Mathematik 1',8),(2,'Grundlagen der Informatik und höhere Programmiersprache für \r\nInformatik',13),(3,'Physik und Grundlagen der Elektrotechnik',6),(4,'Technisches Englisch für Informatik',3),(5,'Höhere Mathematik 2 für Informatik',9),(6,'Digitaltechnik/Technische Informatik',9),(7,'Algorithmen und Datenstrukturen',10),(8,'Softskill­-Wahlpflichtmodul 1',2),(9,'Theoretische Informatik & Wissensbasierte Systeme',9),(10,'Datenbanken',10),(11,'Architektur von Rechnersystemen und Betriebssystemkonzepte',9),(12,'Softskill­-Wahlpflichtmodul 2',2),(13,'Grundlagen der Computernetze',9),(14,'Objektorientierte Softwareentwicklung',9),(15,'Verteilte Systeme',6),(16,'Wahlpflichtmodul 1',6),(17,'Informationssicherheit',5),(18,'Software Engineering',9),(19,'Wahlpflichtmodul 2',6),(20,'Wahlpflichtmodul 3',6),(21,'BWL für Ingenieure',4);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `written_exam`
--

DROP TABLE IF EXISTS `written_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `written_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `grade` enum('1.0','1.3','1.7','2.0','2.3','2.7','3.0','3.3','3.7','4.0') NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `written_exam`
--

LOCK TABLES `written_exam` WRITE;
/*!40000 ALTER TABLE `written_exam` DISABLE KEYS */;
INSERT INTO `written_exam` VALUES (1,1,'3.3',1),(2,2,'3.7',1),(3,3,'4.0',1),(4,4,'4.0',1),(5,5,'2.7',1),(6,6,'2.7',1),(7,7,'1.7',1),(8,8,'3.0',1),(9,9,'3.7',1),(10,10,'4.0',1),(11,11,'2.0',1),(12,12,'4.0',1),(13,13,'3.3',1),(14,14,'2.7',1),(15,15,'3.0',1),(16,16,'4.0',1),(17,17,'2.3',1),(18,18,'4.0',1),(19,19,'3.3',1),(20,20,'3.0',1),(21,21,'2.7',1),(22,1,'1.7',2),(23,2,'1.0',2),(24,3,'1.7',2),(25,4,'3.0',2),(26,5,'1.3',2),(27,6,'1.7',2),(28,7,'2.3',2),(29,8,'1.3',2),(30,9,'1.7',2),(31,10,'2.3',2),(32,11,'3.0',2),(33,12,'1.3',2),(34,13,'1.3',2),(35,14,'1.0',2),(36,15,'2.3',2),(37,16,'1.7',2),(38,17,'2.3',2),(39,18,'1.3',2),(40,19,'1.0',2),(41,1,'2.0',3),(42,2,'3.3',3),(43,3,'1.7',3),(44,4,'3.0',3),(45,5,'1.7',3),(46,6,'3.3',3),(47,7,'4.0',3),(48,8,'1.3',3),(49,9,'2.0',3),(50,10,'2.7',3),(51,11,'3.7',3),(52,12,'2.0',3),(53,13,'3.0',3),(54,14,'1.7',3),(55,15,'1.3',3);
/*!40000 ALTER TABLE `written_exam` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30 20:07:35
