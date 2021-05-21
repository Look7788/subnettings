/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mybank

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/12/2020 17:35:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `A_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `A_cord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `A_adress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `A_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`A_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('吴昕', '222333', '河南省', '123456');
INSERT INTO `administrator` VALUES ('张晓', '12345', '平顶山市', '123456');
INSERT INTO `administrator` VALUES ('黄三', '121212', '西安', '123454');

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer`  (
  `transfer_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transfer_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transfer_money` double(255, 2) NOT NULL,
  `transfer_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` double(255, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`transfer_people`, `transfer_object`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transfer
-- ----------------------------
INSERT INTO `transfer` VALUES ('小张', '小花', 200.00, '2019-03-12', 200.00);
INSERT INTO `transfer` VALUES ('小张', '张三', 121.00, '2101-03-03 12:22:22', 200.00);
INSERT INTO `transfer` VALUES ('张三', '李四', 100.01, '2020-12-23 17:53:40', 100.22);
INSERT INTO `transfer` VALUES ('李四', '小南', 100.12, '2020-12-27 18:33:16', 200.00);
INSERT INTO `transfer` VALUES ('李四', '小张', 100.01, '2020-12-27 18:14:11', 200.33);
INSERT INTO `transfer` VALUES ('李四', '小环', 200.21, '2020-12-13 12:11:11', 300.22);
INSERT INTO `transfer` VALUES ('李四', '小砸', 200.00, '2020-12-11 12:11:11', 300.00);
INSERT INTO `transfer` VALUES ('李四', '张三', 100.00, '2000-02-20 12:22:22', 100.00);
INSERT INTO `transfer` VALUES ('李四', '王五', 100.12, '2020-12-28 17:25:47', 400.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` double(100, 2) NULL DEFAULT NULL,
  `acount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12344', '123456', '123', '67985543387', '12334', '123455', 0.00, '12234');
INSERT INTO `user` VALUES ('小张', '1234567', '13233', '67985543342', '1234', '1234', 100.01, '12');
INSERT INTO `user` VALUES ('王五', '123456', '23212', '211', '122', '123345', 200.34, '2233');
INSERT INTO `user` VALUES ('李四', '1234567', '412321', '684283', '12345', '123545', 400.00, '123');
INSERT INTO `user` VALUES ('小南', '123456', '413223445', '67985543347', '1234', '12132434', 200.12, '2313');

SET FOREIGN_KEY_CHECKS = 1;
