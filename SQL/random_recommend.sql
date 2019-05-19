/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : random_recommend

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 19/05/2019 09:53:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_email_send
-- ----------------------------
DROP TABLE IF EXISTS `t_email_send`;
CREATE TABLE `t_email_send`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `subject` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件主题',
  `receivers` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `cc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送人',
  `addattachment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件列表',
  `send_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'system' COMMENT '发送人',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间',
  `send_success` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否发送成功',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IX_CREATED_DATE`(`created_date`) USING BTREE,
  INDEX `IX_RECEIVER`(`receivers`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件发送表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_crawler_url
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawler_url`;
CREATE TABLE `tb_crawler_url`  (
  `URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'URL',
  `TYPE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Url类型',
  `BUSY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否正在使用，0未使用，1正在使用',
  `INFO` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`URL`) USING BTREE,
  UNIQUE INDEX `IX_URL`(`URL`) USING BTREE COMMENT 'url索引，用来删除',
  INDEX `IX_TYPE_BUSY`(`TYPE`, `BUSY`) USING BTREE COMMENT '返回第一列'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
