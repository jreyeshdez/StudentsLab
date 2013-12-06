/*
Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : practica_rst

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `laboratorygroups`
-- ----------------------------
DROP TABLE IF EXISTS `laboratorygroups`;
CREATE TABLE `laboratorygroups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lgroup` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Table structure for `problemgroups`
-- ----------------------------
DROP TABLE IF EXISTS `problemgroups`;
CREATE TABLE `problemgroups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pgroup` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idnum` varchar(255) NOT NULL,
  `sname` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdate` datetime NOT NULL,
  `idprob` int(11) NOT NULL,
  `idpract` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

