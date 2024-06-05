start transaction; 

create database `Acme-Dancer`;
use `Acme-Dancer`;

create user 'acme-user'@'%' 
	identified by 'ACME-Us3r-P@ssw0rd';

create user 'acme-manager'@'%' 
	identified by 'ACME-M@n@ger-6874';
    
grant select, insert, update, delete 
	on `Acme-Dancer`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Dancer`.* to 'acme-manager'@'%';
    
    grant select, insert, update, delete 
	on `Sample`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Sample`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: Acme-Dancer
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `academia`
--

DROP TABLE IF EXISTS `academia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academia` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `correoElectronico` varchar(255) DEFAULT NULL,
  `direccionPostal` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numeroTelefono` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `nombreComercial` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43hyh4v8a2gqh588skk25pyd` (`userAccount_id`),
  UNIQUE KEY `UK_hj5pibhbscfjdio2gh2vi5ntm` (`correoElectronico`),
  UNIQUE KEY `UK_4dcqham95y48iryyvjxiwnfs5` (`numeroTelefono`),
  UNIQUE KEY `UK_68mwmujkhlox3763w5pr7kyoa` (`nombre`,`apellidos`),
  CONSTRAINT `FK_r43hyh4v8a2gqh588skk25pyd` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academia`
--

LOCK TABLES `academia` WRITE;
/*!40000 ALTER TABLE `academia` DISABLE KEYS */;
INSERT INTO `academia` VALUES (41,0,'Flores Muñoz','academia1@mail.com','22938','Pepito','892734233',36,'Academia Danza');
/*!40000 ALTER TABLE `academia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `correoElectronico` varchar(255) DEFAULT NULL,
  `direccionPostal` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numeroTelefono` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  UNIQUE KEY `UK_2x7riy5l6bjpyvacaibnwfn1e` (`correoElectronico`),
  UNIQUE KEY `UK_53w7qt8gelh2e8otqoyd05lkc` (`numeroTelefono`),
  UNIQUE KEY `UK_mk98ti9hyng33ji3tikd9ieki` (`nombre`,`apellidos`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (37,0,'Pavon Correa','admin1@mail.com','26327','Admin 1','Phone-1',33);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `correoElectronico` varchar(255) DEFAULT NULL,
  `direccionPostal` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numeroTelefono` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `tarjetaDeCredito_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dl831sy6dk64rw6nu6lysmgmv` (`userAccount_id`),
  UNIQUE KEY `UK_av7409mgnda8fa6dr0bt9y2ww` (`correoElectronico`),
  UNIQUE KEY `UK_8jk4cwsnka4i98u345nb3yofm` (`numeroTelefono`),
  UNIQUE KEY `UK_sya11pwgu3bmqlnfy14xjmamo` (`nombre`,`apellidos`),
  KEY `FK_7d9ja2ebytp15wne3pt9qyosw` (`tarjetaDeCredito_id`),
  CONSTRAINT `FK_7d9ja2ebytp15wne3pt9qyosw` FOREIGN KEY (`tarjetaDeCredito_id`) REFERENCES `tarjetadecredito` (`id`),
  CONSTRAINT `FK_dl831sy6dk64rw6nu6lysmgmv` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (38,1,'Rodriguez Gomez','alumno1@mail.com','41533','Pepe','666333222',34,44),(40,1,'Perez Ochoa','alumno2@mail.com','83948','Alba','123456789',35,45);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `fechaCom` datetime DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `actores_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (39,0,'2023-01-02 23:59:00','Holaaa',38);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `diaSemana` varchar(255) DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `hora` varchar(255) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `academia_id` int(11) NOT NULL,
  `estilo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rr6dbcjnt3e250rs6n1twyjei` (`academia_id`),
  KEY `FK_l12m5p31kvmwky318oikci60y` (`estilo_id`),
  CONSTRAINT `FK_l12m5p31kvmwky318oikci60y` FOREIGN KEY (`estilo_id`) REFERENCES `estilo` (`id`),
  CONSTRAINT `FK_rr6dbcjnt3e250rs6n1twyjei` FOREIGN KEY (`academia_id`) REFERENCES `academia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (47,0,'1','2026-07-07 22:59:00','2024-01-07 00:00:00','18:00',0,'BreakDance nivel Principiante',41,46),(50,0,'1','2026-07-08 22:59:00','2024-01-08 00:00:00','18:00',1,'BreakDance nivel Medio',41,46);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estilo`
--

DROP TABLE IF EXISTS `estilo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estilo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagenes` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `videos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estilo`
--

LOCK TABLES `estilo` WRITE;
/*!40000 ALTER TABLE `estilo` DISABLE KEYS */;
INSERT INTO `estilo` VALUES (46,0,'El break dance o breaking (bboying) es una danza social que forma parte de la cultura del hip hop, junto con el grafiti, rap y djing','https://img.redbull.com/images/c_crop,x_820,y_0,h_2133,w_2133/c_fill,w_1000,h_960/q_auto,f_auto/redbullcom/2019/10/11/8e9ca35b-f25c-426a-bb40-338feabff9cb/world-urban-games-2019-shigekix-freeze','BreakDance','https://www.youtube.com/embed/JOTcdSQ4xko'),(53,0,'La salsa es la denominación utilizada para referirse al conjunto de géneros musicales bailables resultantes de la síntesis de ritmos cubanos como el son cubano.','https://basicescoladeball.com/wp-content/uploads/2023/03/bailando-salsa-pareja-980x784.webp','Salsa','https://www.youtube.com/embed/qmxro3w79dw'),(54,0,'Las raíces originales del Mambo pueden ser encontradas en el “Danzón de Nuevo Ritmo”,fue popularizado por la orquesta “Arcaño y sus Maravillas”.','https://visitcubago.com/wp-content/uploads/2020/10/grupo-artistico.jpg','Mambo','https://www.youtube.com/embed/ixU09BtzO8w'),(55,0,'La bachata es un género musical bailable originario de República Dominicana dentro de lo que se denomina folclore urbano. Está considerado como un derivado del merengue.','https://thumbs.dreamstime.com/b/bachata-latino-social-de-la-danza-del-baile-joven-los-pares-merengue-salsa-actitud-elegancia-dos-en-el-fondo-blanco-145565992.jpg','Bachata','https://www.youtube.com/embed/DcgSb1aYUO0'),(56,0,'La pachanga es una mezcla de son montuno y merengue. [cita requerida] El uso del término para referirse a este género musical comenzó en Cuba en 1959.','https://i.ytimg.com/vi/iYMHhlWHwIY/maxresdefault.jpg','Pachanga','https://www.youtube.com/embed/qM9C2B-87Mo'),(57,0,'El pasodoble (también, marcha redoblada) es una marcha ligera de origen español utilizada en los desfiles militares, adoptada como paso dos reglamentario de la infantería.','https://www.collinsdictionary.com/images/full/pasodoble_72886123_1000.jpg','Pasodoble','https://www.youtube.com/embed/r0hGGE2cWQ4'),(58,0,'Las sevillanas, antiguamente llamadas seguidillas sevillanas, son una música y danza típicas de Andalucía sobre todo en Sevilla.','https://allflamenco.net/wp-content/uploads/2023/07/Bailar-sevillanas.jpg','Sevillanas','https://www.youtube.com/embed/UQYcb8EGxeI'),(59,0,'El tango es un género musical y una danza, característica de la región del Río de la Plata y su zona de influencia','https://observatorio.tec.mx/wp-content/uploads/2022/03/EntreTangoyMatemC3A1ticas.jpg','Tango','https://www.youtube.com/embed/aPt3U9rISRA'),(60,0,'El chachachá es un ritmo musical cubano,2​3​ así como un estilo de baile popular.','https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Cha-Cha-Cha_Vodicar_Bychkova_0686.JPG/1200px-Cha-Cha-Cha_Vodicar_Bychkova_0686.JPG','Chachachá','https://www.youtube.com/embed/UfX9mlmS4K0'),(61,0,'La rumba es un género de música tradicional que se originó en Cuba durante el siglo xix. De raíces africanas.','https://www.shutterstock.com/image-vector/rumba-dance-latin-music-couple-260nw-2122934252.jpg','Rumba','https://www.youtube.com/embed/_gWZkE5bxJQ'),(62,0,'La kizomba es un género musical y un baile que comenzó a componerse entre finales de los años 70 y principios de los años 80 en Angola.','https://www.shutterstock.com/image-vector/couple-dancing-kizomba-bright-costumes-260nw-239379844.jpg','Kizomba','https://www.youtube.com/embed/yaX1A-Mw7GE');
/*!40000 ALTER TABLE `estilo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shout`
--

DROP TABLE IF EXISTS `shout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shout` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shout`
--

LOCK TABLES `shout` WRITE;
/*!40000 ALTER TABLE `shout` DISABLE KEYS */;
/*!40000 ALTER TABLE `shout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `fechaSolicitud` datetime DEFAULT NULL,
  `alumno_id` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hg8s7cynpq4hpeutjmdwxqcgp` (`alumno_id`),
  KEY `FK_lsb0ybudam2ceq8dghco6bltf` (`curso_id`),
  CONSTRAINT `FK_hg8s7cynpq4hpeutjmdwxqcgp` FOREIGN KEY (`alumno_id`) REFERENCES `alumno` (`id`),
  CONSTRAINT `FK_lsb0ybudam2ceq8dghco6bltf` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (48,0,0,'2024-01-02 00:00:00',38,47),(49,0,1,'2024-01-02 00:00:00',40,47),(51,0,0,'2024-01-02 00:00:00',38,50),(52,0,1,'2024-01-02 00:00:00',40,50);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripcion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creador_id` int(11) NOT NULL,
  `suscriptor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7p475h4nee1p5irx6orlcpr9d` (`creador_id`),
  UNIQUE KEY `UK_s62k3cbm9sv2t9wmp7toagum9` (`suscriptor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripcion`
--

LOCK TABLES `suscripcion` WRITE;
/*!40000 ALTER TABLE `suscripcion` DISABLE KEYS */;
INSERT INTO `suscripcion` VALUES (63,0,40,40),(64,0,38,38);
/*!40000 ALTER TABLE `suscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjetadecredito`
--

DROP TABLE IF EXISTS `tarjetadecredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetadecredito` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `anyoCaducidad` int(11) NOT NULL,
  `cvv` int(11) NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `mesCaducidad` int(11) NOT NULL,
  `numeroValido` varchar(255) DEFAULT NULL,
  `titular` varchar(255) DEFAULT NULL,
  `alumno_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dstcjtjwvwgxl14lsru0n405y` (`alumno_id`),
  CONSTRAINT `FK_dstcjtjwvwgxl14lsru0n405y` FOREIGN KEY (`alumno_id`) REFERENCES `alumno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjetadecredito`
--

LOCK TABLES `tarjetadecredito` WRITE;
/*!40000 ALTER TABLE `tarjetadecredito` DISABLE KEYS */;
INSERT INTO `tarjetadecredito` VALUES (44,0,2025,123,'Visa',12,'4111111111111111','Pepe',38),(45,0,2026,125,'MasterCard',6,'5555555555554444','Juana',40);
/*!40000 ALTER TABLE `tarjetadecredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `visualizaciones` int(11) NOT NULL,
  `academia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i9lo9i238gl0w43op29uooib5` (`academia_id`),
  CONSTRAINT `FK_i9lo9i238gl0w43op29uooib5` FOREIGN KEY (`academia_id`) REFERENCES `academia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
INSERT INTO `tutorial` VALUES (42,0,'Introducción a breakdance','breakdance vol.1 Introducción','https://www.youtube.com/watch?v=COpVzrQvnyU',0,41),(43,0,'Coreografía básica de break dance para aprender a bailar break dance','breakdance vol.1 coreografía básica','https://www.youtube.com/watch?v=C38mTasXE7g',0,41);
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (33,0,'e00cf25ad42683b3df678c61f42c6bda','admin1'),(34,0,'ef616bc25965a2fa55e9019bef0aca76','alumn1'),(35,0,'cd5e41cdddec23756b0260df7018bb8e','alumn2'),(36,0,'2867dc13a84476546c069b33dc859bc7','academy1');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (33,'ADMIN'),(34,'ALUMN'),(35,'ADMIN'),(35,'ALUMN'),(36,'ACADEMY');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-05 18:02:38
