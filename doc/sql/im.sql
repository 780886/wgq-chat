/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.4.50
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 192.168.4.50:49153
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 26/10/2023 15:15:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit`  (
  `id` int unsigned NOT NULL,
  `apply_user_id` int unsigned NOT NULL COMMENT '用户ID',
  `business_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '业务类型',
  `business_id` int unsigned NOT NULL COMMENT '业务ID',
  `audit_user_id` int unsigned NOT NULL COMMENT '审核用户ID',
  `apply_reason` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '申请理由',
  `audit_reason` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审核理由',
  `status` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态',
  `audit_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '审核时间',
  `apply_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '申请时间',
  `read_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '阅读状态 1未读 2已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'audit' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit
-- ----------------------------
INSERT INTO `audit` VALUES (13, 1, 1, 2, 2, '添加好友', '', 'ENABLE', 1697619402711, 1697619349144, 1);

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `id` int unsigned NOT NULL,
  `room_id` int unsigned NOT NULL COMMENT '会话表id',
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `friend_id` int unsigned NOT NULL COMMENT '好友ID',
  `last_message_id` int unsigned NOT NULL COMMENT '会话中的最后一条消息id',
  `last_send_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '最后消息发送时间(只有普通会话需要维护，全员会话不需要维护)',
  `apply_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '申请时间',
  `audit_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'contact' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `event_id` int unsigned NOT NULL COMMENT 'primary key',
  `user_id` int unsigned NOT NULL COMMENT 'user id',
  `business_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'business typ',
  `business_id` int unsigned NOT NULL COMMENT 'business id',
  `times` int(0) NOT NULL DEFAULT 0 COMMENT 'times',
  `channel` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'channel',
  `ip` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ip',
  `device` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'device',
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'device unique id',
  `device_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'device model 5s e.g ...',
  `event` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'event type',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'content',
  `website` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'website home url',
  `app_id` int unsigned NOT NULL COMMENT 'app id',
  `app_version` float(11, 2) NOT NULL DEFAULT 0.00 COMMENT 'app version',
  `platform` tinyint(0) NOT NULL DEFAULT -1 COMMENT 'platform',
  `os` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'operation system',
  `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'use agent',
  `client_version` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'client os version',
  `longitude` double NOT NULL DEFAULT 0 COMMENT 'longitude',
  `latitude` double NOT NULL DEFAULT 0 COMMENT 'latitude',
  `network` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'network',
  `simulate` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'is simulate',
  `imei` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'imei',
  `bssid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'bssi',
  `ssid` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ssid',
  `idfa` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'idfa',
  `start_time` bigint(0) NOT NULL DEFAULT 0 COMMENT 'client start time',
  `resume_time` bigint(0) NOT NULL DEFAULT 0 COMMENT 'client resume time',
  `create_time` bigint(0) NOT NULL DEFAULT 0,
  `update_time` bigint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'event' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int unsigned NOT NULL COMMENT 'id',
  `room_id` int unsigned NOT NULL COMMENT '会话表id',
  `sender_user_id` int unsigned NOT NULL COMMENT '消息发送者uid',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '消息内容',
  `reply_message_id` int unsigned NOT NULL COMMENT '回复的消息id',
  `status` int unsigned NOT NULL COMMENT '消息状态 0正常 1删除',
  `gap_count` int unsigned NOT NULL COMMENT '与回复的消息间隔多少条',
  `type` int unsigned NOT NULL COMMENT '消息类型 1正常文本 2.撤回消息',
  `extra` json NULL COMMENT '扩展信息',
  `send_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '消息发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_from_uid`(`sender_user_id`) USING BTREE,
  INDEX `idx_room_id`(`room_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qun
-- ----------------------------
DROP TABLE IF EXISTS `qun`;
CREATE TABLE `qun`  (
  `id` int unsigned NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '群名称',
  `announcement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '群公告',
  `nationality_id` int(0) NOT NULL DEFAULT 0 COMMENT '国籍id',
  `organization_id` int unsigned NOT NULL COMMENT '机构ID',
  `owner_id` int unsigned NOT NULL COMMENT '群主',
  `category_id` int unsigned NOT NULL COMMENT '所属类别',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint unsigned NOT NULL COMMENT 'STATUS',
  `create_user_id` int unsigned NOT NULL COMMENT '创建人ID',
  `gmt_create` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `modified_user_id` int unsigned NOT NULL COMMENT '更新人ID',
  `gmt_modified` bigint(0) NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `modified_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'qun' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qun_member
-- ----------------------------
DROP TABLE IF EXISTS `qun_member`;
CREATE TABLE `qun_member`  (
  `id` int unsigned NOT NULL,
  `qun_id` int unsigned NOT NULL COMMENT '群ID',
  `member_id` int unsigned NOT NULL COMMENT '群成员ID',
  `apply_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '申请时间',
  `audit_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'qun_member' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int unsigned NOT NULL COMMENT 'id',
  `type` int unsigned NOT NULL COMMENT '房间类型 1群聊 2单聊',
  `hot_flag` int unsigned NOT NULL COMMENT '是否全员展示 0否 1是',
  `last_message_id` int unsigned NOT NULL COMMENT '会话中的最后一条消息id',
  `last_send_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '群最后消息的更新时间（热点群不需要写扩散，只更新这里）',
  `ext_json` json NULL COMMENT '额外信息（根据不同类型房间有不同存储的东西）',
  `gmt_create` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '房间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room_friend
-- ----------------------------
DROP TABLE IF EXISTS `room_friend`;
CREATE TABLE `room_friend`  (
  `id` int unsigned NOT NULL COMMENT 'id',
  `room_id` int unsigned NOT NULL COMMENT '会话表id',
  `smaller_user_id` int unsigned NOT NULL COMMENT 'uid1（更小的uid）',
  `larger_user_id` int unsigned NOT NULL COMMENT 'uid2（更大的uid）',
  `room_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '房间key由两个uid拼接，先做排序uid1_uid2',
  `status` int unsigned NOT NULL COMMENT '房间状态 0正常 1禁用(删好友了禁用)',
  `gmt_create` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_key`(`room_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '单聊房间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int unsigned NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'E-MAIL',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `gender` tinyint(0) NULL DEFAULT 0 COMMENT '性别',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头象',
  `personal_signature` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '签名',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '手机号码',
  `last_login_time` bigint(0) NULL DEFAULT 0 COMMENT '最近登录时间',
  `activate` tinyint(1) NULL DEFAULT 0 COMMENT '是否激活',
  `activate_time` bigint(0) NULL DEFAULT 0 COMMENT '激活时间',
  `channel` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '渠道来源',
  `secret_mobile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'SECRET MOBILE',
  `device` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '设备',
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '设备id',
  `device_model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '设备模型',
  `ip` bigint(0) NULL DEFAULT 0 COMMENT 'ip',
  `status` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'STATUS',
  `gmt_create` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'user' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wgq', 'wgq', '135168675@qq.com', 'B4BA82809368FE1AB108C6DE674DD8A0', 0, 'https://wgq-im.oss-cn-nanjing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230804100625.jpg?Expires=1692868991&OSSAccessKeyId=TMP.3KkXZVn8tW6eEn2MJzEavgJ2Yu3pHnr7kSUWjGWKFU5bAyEggMVLxDvdqMW3x5RFTvbqStBztfD7jhmgiNZ97G16Axh37D&Signature=fp5DbZElnTO2SAxTRHr7z1ShL8I%3D', NULL, '2023-09-25', '15551763327', 1697622283261, NULL, NULL, '801520', NULL, NULL, NULL, NULL, 1, 'ENABLE', 1684947088869, 1684947088869);
INSERT INTO `user` VALUES (2, 'yy', 'yy', '2460280678@qq.com', 'B4BA82809368FE1AB108C6DE674DD8A0', 1, 'https://wgq-im.oss-cn-nanjing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230804100625.jpg?Expires=1692868991&OSSAccessKeyId=TMP.3KkXZVn8tW6eEn2MJzEavgJ2Yu3pHnr7kSUWjGWKFU5bAyEggMVLxDvdqMW3x5RFTvbqStBztfD7jhmgiNZ97G16Axh37D&Signature=fp5DbZElnTO2SAxTRHr7z1ShL8I%3D', NULL, '2023-04-22', '18256564680', 1697622286226, NULL, NULL, '801520', NULL, NULL, NULL, NULL, 2, 'ENABLE', 1684947088869, 1684947088869);

SET FOREIGN_KEY_CHECKS = 1;
