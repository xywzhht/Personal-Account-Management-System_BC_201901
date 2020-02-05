/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.26-log : Database - account
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`account` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `account`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `PID` int(15) NOT NULL,
  `category` varchar(20) DEFAULT NULL,
  `content` varchar(20) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`PID`,`category`,`content`,`money`,`time`) values (12138,'生活','洗衣液',-100,'2018-12-03'),(12326,'工作','加班',1500,'2019-01-08'),(12345,'娱乐','打机',78,'2018-07-01'),(12358,'生活','肥皂',-12,'2018-12-13'),(16525,'生活','吃大餐',-390,'2018-11-01'),(16545,'工作','工资',2000,'2018-05-01'),(16548,'学习','草稿本',-52,'2018-11-06'),(16554,'娱乐','游乐园',-250,'2018-08-01'),(16584,'学习','铅笔',-36,'2018-12-13'),(16595,'娱乐','钓鱼',-98,'2018-07-01'),(56588,'生活','买零食',-20,'2019-01-16'),(3262455,'生活','打火锅',-60,'2019-01-09');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
