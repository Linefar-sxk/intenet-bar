/*
 Navicat Premium Data Transfer

 Source Server         : hyb
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 111.231.250.50:3306
 Source Schema         : internet_bar

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 21/04/2019 18:15:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_create` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_update` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `date_delete` bigint(30) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('adsfa', 'GL', '13305928899', '123456', '2019-04-20 21:42:17', '2019-04-20 21:42:19', 0);
COMMIT;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `money` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activation_state` int(2) DEFAULT NULL COMMENT '激活状态',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_create` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_update` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `date_delete` bigint(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO `customer` VALUES ('55d26fe3-1267-4da6-a1eb-05a5504d496a', '人才', '65216208988', '1449', 0, '1111', 'year', '2019-04-20 09:21:52', '2019-04-21 05:01:43', 0);
INSERT INTO `customer` VALUES ('78571e78-8be2-493c-9f96-aabe59577e44', '陈', '520525200', '83', 0, NULL, 'month', '2019-04-20 09:18:01', '2019-04-21 05:01:29', 0);
INSERT INTO `customer` VALUES ('8abf5712-a727-4926-aeb0-d5f4adbeb7fa', '尘尘', '65263202226', '91', 0, NULL, 'year', '2019-04-20 09:17:44', '2019-04-21 05:01:31', 0);
COMMIT;

-- ----------------------------
-- Table structure for net_play_record
-- ----------------------------
DROP TABLE IF EXISTS `net_play_record`;
CREATE TABLE `net_play_record` (
  `id` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `id_card` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `duration` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '秒为单位',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `date_create` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_update` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `date_delete` bigint(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of net_play_record
-- ----------------------------
BEGIN;
INSERT INTO `net_play_record` VALUES ('15c5cb8c-8038-4a27-bde5-3c9d0b0b79ce', '65216208988', '9634', '2019-04-20 10:51:10', '2019-04-21 05:01:32', '2019-04-20 10:51:10', '2019-04-21 05:01:32', 1555840892365);
INSERT INTO `net_play_record` VALUES ('1be2bbec-b614-414c-9087-fe55f8f7ef21', '65216208988', '9634', '2019-04-20 10:52:38', '2019-04-21 05:01:32', '2019-04-20 10:52:38', '2019-04-21 05:01:32', 1555840892365);
INSERT INTO `net_play_record` VALUES ('a2ef590f-ff25-4fc7-9198-916fdad51624', '520525200', '352', '2019-04-21 04:55:37', '2019-04-21 05:01:30', '2019-04-21 04:55:37', '2019-04-21 05:01:30', 1555840889642);
INSERT INTO `net_play_record` VALUES ('e5b6e9db-516b-49b6-b27f-4f0e57e5f00f', '65263202226', '356', '2019-04-21 04:55:34', '2019-04-21 05:01:31', '2019-04-21 04:55:34', '2019-04-21 05:01:31', 1555840890937);
INSERT INTO `net_play_record` VALUES ('ea8ea781-3a86-470b-8c92-92e35102f935', '65216208988', '9634', '2019-04-21 02:20:58', '2019-04-21 05:01:32', '2019-04-21 02:20:58', '2019-04-21 05:01:32', 1555840892365);
INSERT INTO `net_play_record` VALUES ('fc07180c-8603-4b1f-a780-f895a46a8e4f', '65216208988', '9634', '2019-04-20 11:03:14', '2019-04-21 05:01:32', '2019-04-20 11:03:14', '2019-04-21 05:01:32', 1555840892365);
COMMIT;

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `id` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `change_money` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_create` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_update` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `date_delete` bigint(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
BEGIN;
INSERT INTO `recharge_record` VALUES ('02bd22b8-1f28-4e64-8a99-3ddd702a600e', '6', 'ADD', '65216208988', '2019-04-21 05:01:43', '2019-04-21 05:01:43', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
