/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.28 : Database - cardvoucher
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cardvoucher` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cardvoucher`;

/*Table structure for table `voucher_template` */

DROP TABLE IF EXISTS `voucher_template`;

CREATE TABLE `voucher_template` (
  `voucherId` bigint(20) NOT NULL,
  `adaptedStore` varchar(255) DEFAULT NULL,
  `background` varchar(255) DEFAULT NULL,
  `businessId` bigint(20) DEFAULT NULL,
  `deadlineTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `giftName` varchar(255) DEFAULT NULL,
  `ledVoucherLimit` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `offerDetail` varchar(300) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `receiveTimes` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `usageNotes` varchar(300) DEFAULT NULL,
  `useTimes` int(11) DEFAULT NULL,
  `voucherUseRole` varchar(255) DEFAULT NULL,
  `storeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`voucherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `voucher_template` */

insert  into `voucher_template`(`voucherId`,`adaptedStore`,`background`,`businessId`,`deadlineTime`,`endTime`,`giftName`,`ledVoucherLimit`,`name`,`offerDetail`,`phone`,`receiveTimes`,`startTime`,`state`,`stock`,`subtitle`,`title`,`type`,`usageNotes`,`useTimes`,`voucherUseRole`,`storeName`) values (144655383649093465,'华泰店','a8a8a8',144622269107968586,NULL,'2015-11-26 00:00:00','',2,'北京天宇餐饮折扣券','在规定的活动时间内通过接受微信卡券，可享受一定折扣的优惠','0536-2877321',1,'2015-11-04 00:00:00','',600,'一律8折优惠，多买多得','庆祝天宇餐饮10周年',3,'必须在规定活动时间持此卡进行消费',3,'on,on',NULL),(144655490766797187,'-1','008000',144622269107968586,NULL,'2015-11-21 00:00:00','咖啡之翼吉祥物',1,'咖啡之翼开业折扣券','凡是来咖啡之翼就餐者均可享受6折优惠，还有精品赠送','07236-11324563',1,'2015-11-01 00:00:00','',600,'为一代年轻人打造','咖啡之翼欢迎你',3,'通过微信领取，持此卡券到门店核销即可',2,'on,on',NULL),(144663326096397187,'-1','db5800',144622264597054044,NULL,'2015-11-27 00:00:00','可口可乐',3,'小掌柜礼品券','凡是在规定时间使用小掌柜优惠券吃饭者，一律赠送可乐一瓶','6785-4356783',0,'2015-11-01 00:00:00','',600,'即日起享受大优惠','庆祝小掌柜上市',4,'必须在规定时间内',0,'on,on',NULL),(144697955263497187,'','008000',144622269107968586,NULL,'2015-11-28 00:00:00','床垫',1,'现世界代金券','凡是在规定的而活动时间购买本券的顾客一律三折优惠','695314-36562148',0,'2015-11-07 00:00:00','',623,'给你最优惠的价格','做出完美世界',2,'在规定时间销售',0,'on,on',NULL),(144699040354797187,NULL,'008000',144622269107968586,NULL,'2015-11-19 00:00:00','可口可乐',1,' 杨钰名黄焖鸡折扣券',NULL,NULL,1,'2015-11-01 00:00:00','\0',600,'凡是到此连锁店消费者','一律三折优惠',3,NULL,0,NULL,NULL),(144713870798697187,'144622272179535148','008000',144622269107968586,NULL,'2015-11-19 00:00:00','火烧',1,'潍坊火烧代金券','凡是在活动期间用代金券购买火烧者均可享受3折优惠','6532-456321',NULL,'2015-11-03 00:00:00','',800,'此代金券可低10元','为客户提供更好的优惠',2,'一定要在活动时间',NULL,'on,on',NULL),(144713960147397187,'','008000',144622269107968586,NULL,'2015-11-20 00:00:00','鞋子',1,'星美国际折扣券','为广大用户带来极致的优惠','65412-6548',NULL,'2015-11-01 00:00:00','',500,'还有礼品赠送','一律5折优惠',3,'必须在活动时间内使用',NULL,'2',NULL),(144731654961493465,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL),(144731655587336609,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL),(144731655929097528,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(144731663267758845,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL),(144731663885652083,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL),(144731663892780791,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL),(144731776199197187,'144622272179535148','ff3838',144622269107968586,NULL,'2015-11-30 00:00:00','潍坊人官方',1,'星美折扣券','etgrhyt','24354445',NULL,'2015-11-01 00:00:00','\0',400,'还有更多优惠','店庆10周年',3,'gdfhytd',NULL,'1,2',NULL);

/*Table structure for table `voucher_user` */

DROP TABLE IF EXISTS `voucher_user`;

CREATE TABLE `voucher_user` (
  `id` bigint(20) NOT NULL,
  `code` bigint(20) DEFAULT NULL,
  `ownUse` bit(1) NOT NULL,
  `receiveDate` datetime DEFAULT NULL,
  `useDate` datetime DEFAULT NULL,
  `useStore` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `verificated` bit(1) NOT NULL,
  `cardId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g252snffsoab3s19k5a68t8m0` (`cardId`),
  CONSTRAINT `FK_g252snffsoab3s19k5a68t8m0` FOREIGN KEY (`cardId`) REFERENCES `voucher_template` (`voucherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `voucher_user` */

insert  into `voucher_user`(`id`,`code`,`ownUse`,`receiveDate`,`useDate`,`useStore`,`userId`,`verificated`,`cardId`) values (144655579574497187,446555795744,'','2015-11-03 21:03:15','2015-11-07 19:16:48',NULL,7653478,'',144655383649093465),(144682264096697187,446822640966,'','2015-11-06 23:10:40','2015-11-07 19:17:23',NULL,7653478,'',144655490766797187),(144731638559897187,447316385598,'','2015-11-12 16:19:45',NULL,NULL,7653478,'\0',144699040354797187);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
