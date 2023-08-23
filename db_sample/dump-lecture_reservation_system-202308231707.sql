-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: lecture_reservation_system
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lecture_registration`
--

DROP TABLE IF EXISTS `lecture_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_registration` (
  `lecture_registration_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '강연 신청 ID',
  `lecture_id` bigint(20) NOT NULL COMMENT '강연 ID',
  `employee_id` bigint(20) NOT NULL COMMENT '신청 직원 ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 시간',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  `use_yn` tinyint(1) NOT NULL DEFAULT '1' COMMENT '사용 여부',
  PRIMARY KEY (`lecture_registration_id`),
  UNIQUE KEY `idx_lecture_registration_1` (`lecture_id`,`employee_id`) USING BTREE,
  KEY `lecture_registration_FK` (`employee_id`),
  CONSTRAINT `lecture_registration_FK` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `lecture_registration_FK_1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='강연 신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_registration`
--

LOCK TABLES `lecture_registration` WRITE;
/*!40000 ALTER TABLE `lecture_registration` DISABLE KEYS */;
INSERT INTO `lecture_registration` VALUES (1,10,2,'2023-08-23 07:58:26','2023-08-23 07:58:26',0),(2,14,2,'2023-08-23 05:05:29','2023-08-23 05:05:29',0),(3,21,2,'2023-08-23 05:06:10','2023-08-23 05:06:10',0),(4,11,2,'2023-08-23 05:07:53','2023-08-23 05:07:53',0),(5,10,3,'2023-08-23 04:59:45','2023-08-23 05:07:44',1),(6,13,2,'2023-08-23 06:24:43','2023-08-23 06:24:43',0),(7,17,2,'2023-08-23 06:50:37','2023-08-23 06:50:37',0),(8,7,2,'2023-08-23 07:58:26','2023-08-23 07:58:26',0),(9,9,2,'2023-08-23 06:54:52','2023-08-23 06:54:52',0),(11,15,2,'2023-08-23 06:56:46','2023-08-23 06:56:46',0),(12,6,2,'2023-08-23 07:10:48','2023-08-23 07:10:48',0),(14,5,2,'2023-08-23 07:48:47','2023-08-23 07:48:47',0),(17,6,5,'2023-08-23 07:58:36','2023-08-23 07:58:36',1),(18,7,5,'2023-08-23 07:58:43','2023-08-23 08:00:32',0),(19,7,9,'2023-08-23 07:58:55','2023-08-23 07:58:55',1),(20,11,6,'2023-08-23 07:59:26','2023-08-23 07:59:26',1);
/*!40000 ALTER TABLE `lecture_registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 17:07:02
