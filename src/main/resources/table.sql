/*
Navicat MySQL Data Transfer

Source Server         : localhost_1
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : fastdfs

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-06-26 15:11:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fastdfs_file
-- ----------------------------
DROP TABLE IF EXISTS `fastdfs_file`;
CREATE TABLE `fastdfs_file` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上传时的文件名',
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'fastdfs路径',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，true=已删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='fastdfs文件列表';
