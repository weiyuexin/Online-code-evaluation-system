/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : onlineoj

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 20/03/2023 00:19:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `author_id` int(0) NOT NULL COMMENT '作者id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正文',
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `read_num` int(0) NULL DEFAULT 0 COMMENT '阅读量',
  `star_num` int(0) NULL DEFAULT 0 COMMENT '点赞量',
  `comment_num` int(0) NULL DEFAULT 0 COMMENT '点赞量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` bigint(0) NOT NULL COMMENT '题目id',
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目标题',
  `describe` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `input` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输入',
  `output` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输出',
  `sample_input` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '样例输入',
  `sample_output` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '样例输出',
  `hint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `time_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时间限制(ms)',
  `memory_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内存限制(kb)',
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '难度',
  `uploader` bigint(0) NULL DEFAULT NULL COMMENT '上传用户',
  `submit_num` int(0) NULL DEFAULT 0 COMMENT '提交次数',
  `solved_num` int(0) NULL DEFAULT NULL COMMENT '解决次数',
  `contset_id` int(0) NULL DEFAULT NULL COMMENT '所属竞赛id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------

-- ----------------------------
-- Table structure for solution
-- ----------------------------
DROP TABLE IF EXISTS `solution`;
CREATE TABLE `solution`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `problem_id` bigint(0) NOT NULL COMMENT '题目id',
  `run_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行使用时间(ms)',
  `memory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行内存',
  `language_id` int(0) NULL DEFAULT NULL COMMENT '语言id',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `result` varchar(999) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行结果',
  `contest_id` int(0) NULL DEFAULT NULL COMMENT '所属竞赛id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of solution
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人简介',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `submit_num` bigint(0) NULL DEFAULT 0 COMMENT '提交次数',
  `solved_num` bigint(0) NULL DEFAULT 0 COMMENT '解决的题数',
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `access_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的ip地址',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校',
  `language_id` int(0) NULL DEFAULT 0 COMMENT '默认使用的语言id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'weiyuexin', 'root', '男', '你好，我来自henu', '18331361148', '3022422894@qq.com', 0, 0, '2023-02-12 20:09:44', '2023-02-24 20:09:49', NULL, '河南大学', 0);

SET FOREIGN_KEY_CHECKS = 1;
