/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.60 : Database - sms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sms`;

/*Table structure for table `email` */

DROP TABLE IF EXISTS `email`;

CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件验证码主键',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `code` varchar(20) DEFAULT NULL COMMENT '验证码',
  `c_time` datetime DEFAULT NULL COMMENT '发送时间',
  `case_type` tinyint(4) DEFAULT NULL COMMENT '发送方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `sms` */

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `code` varchar(20) DEFAULT NULL COMMENT '验证码',
  `message` varchar(200) DEFAULT NULL COMMENT '状态码的描述',
  `request_id` varchar(50) DEFAULT NULL COMMENT '请求id',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态',
  `c_time` datetime DEFAULT NULL COMMENT '发送时间',
  `case_type` tinyint(4) DEFAULT NULL COMMENT '发送方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
