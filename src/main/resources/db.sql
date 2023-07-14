/*
 Navicat Premium Data Transfer

 Source Server         : database
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 14/07/2023 14:27:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `dish_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `dish_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜品名',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单品金额',
  PRIMARY KEY (`dish_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------

-- ----------------------------
-- Table structure for dish_detail
-- ----------------------------
DROP TABLE IF EXISTS `dish_detail`;
CREATE TABLE `dish_detail`  (
  `dish_detail_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜品明细ID',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账户',
  `dish_ID` int(11) NOT NULL COMMENT '菜品ID',
  `money` float NOT NULL COMMENT '金额',
  `number` int(11) NOT NULL COMMENT '数量',
  `status` enum('已送达','正在配送','正在出餐') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1:已送达,2:正在配送,3:正在出餐',
  PRIMARY KEY (`dish_detail_ID`) USING BTREE,
  INDEX `user_ID`(`account`) USING BTREE,
  INDEX `dish_ID`(`dish_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_detail
-- ----------------------------
INSERT INTO `dish_detail` VALUES (1, '2', 2, 2.1, 3, '正在配送');
INSERT INTO `dish_detail` VALUES (2, '3', 1, 1.1, 1, '正在出餐');
INSERT INTO `dish_detail` VALUES (4, '2', 2, 2.1, 2, '已送达');
INSERT INTO `dish_detail` VALUES (5, '2', 2, 2.1, 2, '已送达');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单品金额',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `goods_detail`;
CREATE TABLE `goods_detail`  (
  `goods_detail_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品明细ID',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账户',
  `goods_ID` int(11) NOT NULL COMMENT '商品ID',
  `money` float NOT NULL COMMENT '金额',
  `number` int(11) NOT NULL COMMENT '数量',
  `status` enum('已送达','正在配送','正在出餐') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1:已送达,2:正在配送,3:正在出餐',
  PRIMARY KEY (`goods_detail_ID`) USING BTREE,
  INDEX `user_ID`(`account`) USING BTREE,
  INDEX `goods_ID`(`goods_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_detail
-- ----------------------------

-- ----------------------------
-- Table structure for medical
-- ----------------------------
DROP TABLE IF EXISTS `medical`;
CREATE TABLE `medical`  (
  `medical_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '医疗ID',
  `medical_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医疗服务名',
  `medical_time_ID` int(11) NOT NULL COMMENT '医疗服务时间ID',
  PRIMARY KEY (`medical_ID`) USING BTREE,
  INDEX `MedicalTimeID`(`medical_time_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of medical
-- ----------------------------

-- ----------------------------
-- Table structure for medicalschedule
-- ----------------------------
DROP TABLE IF EXISTS `medicalschedule`;
CREATE TABLE `medicalschedule`  (
  `medical_schedule_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '医疗时间ID',
  `medical_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗服务名',
  `Time` date NOT NULL COMMENT '时间',
  PRIMARY KEY (`medical_schedule_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of medicalschedule
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账户',
  `dish_detail_ID` int(11) NOT NULL COMMENT '菜品明细ID',
  `goods_detail_ID` int(11) NOT NULL COMMENT '商品明细ID',
  `money` float NOT NULL COMMENT '总金额',
  `data` date NOT NULL COMMENT '订单日期',
  PRIMARY KEY (`order_ID`) USING BTREE,
  INDEX `dish_detail_ID`(`dish_detail_ID`) USING BTREE,
  INDEX `goods_detail_ID`(`goods_detail_ID`) USING BTREE,
  INDEX `user_ID`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `reservations_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '预订订单ID',
  `medical_ID` int(11) NOT NULL COMMENT '医疗服务ID',
  `visiting_ID` int(11) NOT NULL COMMENT '基地预约ID',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  PRIMARY KEY (`reservations_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `pswd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1:男,2:女',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `status` int(11) NULL DEFAULT NULL COMMENT '管理员0，其他用户1',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片网站',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '1234567890', '123456', '男', '12345674567', 1, NULL);
INSERT INTO `user` VALUES (3, 'xiaomin2', '1234567892', 'passwo2rd', '女', '19186243797', 0, NULL);
INSERT INTO `user` VALUES (5, 'xiaom5', '12345678967', '1a1dc91c907325c69271ddf0c944bc72', '男', '19186243797', 0, NULL);
INSERT INTO `user` VALUES (6, 'xiaom6', '12345634967', '4b94c0ea58dc6f9ec69dfa78d8a937b1', '男', '19186247797', 0, NULL);
INSERT INTO `user` VALUES (7, 'xiaom7', '12375634967', '7e8859faa4b991719d1642a346eae3ee', '男', '19186247797', 0, NULL);
INSERT INTO `user` VALUES (8, 'xiam7', '12395634967', 'a51a7213a73f953fc958a4e73cee0c62', '男', '19186247797', 0, NULL);
INSERT INTO `user` VALUES (9, 'xiaomin123', '12345678967', 'c1572d05424d0ecb2a65ec6a82aeacbf', '男', '188271329842', 0, NULL);

-- ----------------------------
-- Table structure for visiting
-- ----------------------------
DROP TABLE IF EXISTS `visiting`;
CREATE TABLE `visiting`  (
  `visiting_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '绿化基地ID',
  `visitingname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '绿化基地名称',
  `vIsiting_time_ID` int(11) NOT NULL COMMENT '绿化基地时间',
  PRIMARY KEY (`visiting_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of visiting
-- ----------------------------

-- ----------------------------
-- Table structure for visitingschedule
-- ----------------------------
DROP TABLE IF EXISTS `visitingschedule`;
CREATE TABLE `visitingschedule`  (
  `visiting_schedule_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '基地时间ID',
  `visiting_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基地ID',
  `time` date NULL DEFAULT NULL COMMENT '基地时间',
  PRIMARY KEY (`visiting_schedule_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of visitingschedule
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
