/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : fnboot

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/02/2021 19:42:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `accessToken` varchar(255) NOT NULL COMMENT '用户请求token',
  `userId` bigint(20) NOT NULL COMMENT '用户主键id',
  `userType` int(1) NOT NULL COMMENT '用户类型',
  `expiresTime` datetime NOT NULL COMMENT '过期时间',
  `valid` bit(1) NOT NULL COMMENT '是否有效 0、禁用 1、正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='请求token';

-- ----------------------------
-- Records of oauth2_access_token
-- ----------------------------
BEGIN;
INSERT INTO `oauth2_access_token` VALUES (1, 'de27204bc88645adb91e7b8dade2b48b', 1, 1, '2020-02-20 16:53:50', b'0');
INSERT INTO `oauth2_access_token` VALUES (2, '9cf21b2db8894c27bbb21f32b358ec69', 1, 1, '2020-02-20 16:56:21', b'0');
INSERT INTO `oauth2_access_token` VALUES (3, '2212605cb84c4de69b799770cd746245', 1, 1, '2020-02-20 16:56:26', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `pid` bigint(20) NOT NULL COMMENT '上级组织id',
  `name` varchar(255) NOT NULL COMMENT '组织名称',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点id，0顶级',
  `code` varchar(255) NOT NULL COMMENT '组织代码',
  `isEnable` bit(1) NOT NULL COMMENT '是否可用',
  `isDeleted` bit(1) NOT NULL COMMENT '是否删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `uid` bigint(20) DEFAULT NULL COMMENT '部门负责人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
BEGIN;
INSERT INTO `sys_department` VALUES (1, 0, '超级管理', 0, '00', b'1', b'0', '2019-08-14 09:17:07', '2019-08-14 09:17:07', 1, NULL);
INSERT INTO `sys_department` VALUES (2, 1, '江苏省', 1, '0001', b'1', b'0', '2020-02-20 17:18:26', NULL, 2, NULL);
INSERT INTO `sys_department` VALUES (3, 2, '徐州市', 2, '000101', b'1', b'0', '2020-02-20 17:19:31', NULL, 3, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL COMMENT '字典id',
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `pid` bigint(20) NOT NULL COMMENT '类型id',
  `type` smallint(1) NOT NULL COMMENT '0.类型 1.字典',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='字典表';

-- ----------------------------
-- Table structure for sys_error_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_error_code`;
CREATE TABLE `sys_error_code` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` int(10) DEFAULT NULL COMMENT '错误代码',
  `message` varchar(255) DEFAULT NULL COMMENT '错误提示',
  `type` int(1) DEFAULT NULL COMMENT '1、自动生成，2、手动便捷',
  `group` varchar(255) DEFAULT NULL COMMENT '分组名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='错误代码表';

-- ----------------------------
-- Records of sys_error_code
-- ----------------------------
BEGIN;
INSERT INTO `sys_error_code` VALUES (1, 100010, '用户不存在', 1, 'admin-web-service-impl', NULL, '2021-02-01 05:59:56', '2021-02-01 05:59:56');
INSERT INTO `sys_error_code` VALUES (4, 100011, '该用户已被禁用', 1, 'admin-web-service-impl', NULL, '2021-02-05 02:10:49', '2021-02-05 02:10:49');
INSERT INTO `sys_error_code` VALUES (5, 100012, '用户密码错误', 1, 'admin-web-service-impl', NULL, '2021-02-05 02:10:49', '2021-02-05 02:10:49');
INSERT INTO `sys_error_code` VALUES (6, 100013, '登录验证码错误', 1, 'admin-web-service-impl', NULL, '2021-02-05 21:40:48', '2021-02-05 21:40:48');
INSERT INTO `sys_error_code` VALUES (7, 100014, '当前手机号已存在', 1, 'admin-web-service-impl', NULL, '2021-02-22 04:53:38', '2021-02-22 04:53:38');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `log_type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `request_ip` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='日志表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `platform` smallint(6) DEFAULT NULL COMMENT '0、管理平台1、App',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、按钮',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `url` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '地址',
  `permCode` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '权限编码',
  `icon` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `description` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `isEnable` bit(1) DEFAULT NULL COMMENT '是否使用',
  `isDeleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, 0, '测试菜单', 0, 0, 0, '/user/info', 'admin', NULL, NULL, '2019-08-31 15:41:00', '2019-08-31 15:41:03', b'0', b'0');
INSERT INTO `sys_permission` VALUES (2, 1, '测试按钮', 0, 1, 0, 'user:add', 'user:add', NULL, NULL, '2021-02-06 09:53:06', '2021-02-06 09:53:11', b'0', b'0');
INSERT INTO `sys_permission` VALUES (3, 0, '用户列表', 0, 0, 0, '/users/adminUser', 'departmentUser', NULL, NULL, '2021-02-06 13:44:12', '2021-02-06 13:44:18', b'0', b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint(20) NOT NULL COMMENT '职务id',
  `deptId` bigint(20) DEFAULT NULL COMMENT '部门id',
  `name` varchar(255) NOT NULL COMMENT '职务名称',
  `code` varchar(255) NOT NULL COMMENT '职务代码',
  `isEnable` bit(1) DEFAULT NULL COMMENT '是否可用',
  `isDeleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='岗位表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` VALUES (1, 1, '超级管理员', '0', b'1', b'1', '2019-08-12 07:46:00', '2019-08-12 07:46:00', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `description` varchar(32) DEFAULT NULL COMMENT '描述',
  `loginJurisdiction` int(2) DEFAULT NULL COMMENT '登录权限',
  `createId` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `isEnable` bit(1) DEFAULT NULL COMMENT '是否使用',
  `isDeleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '测试', NULL, 1, '2019-08-31 15:40:20', '2019-08-31 15:40:25', b'1', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=FIXED COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1, 2);
INSERT INTO `sys_role_permission` VALUES (3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(20) NOT NULL COMMENT '用户名',
  `phone` varchar(11) NOT NULL COMMENT '用户手机号',
  `sex` varchar(1) NOT NULL COMMENT '用户性别',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `departmentId` bigint(20) DEFAULT NULL COMMENT '部门id',
  `postId` bigint(20) DEFAULT NULL COMMENT '岗位id',
  `roleId` bigint(20) NOT NULL COMMENT '角色id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `isDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0:正常，1:删除',
  `isEnable` bit(1) NOT NULL DEFAULT b'0' COMMENT '0、正常 1、禁用',
  `lastPasswordResetTime` datetime DEFAULT NULL COMMENT '最后修改日期的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1364882862733643779 DEFAULT CHARSET=utf8 COMMENT='系统用户\n';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '18252113278', '男', '320859852@qq.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1, '2019-08-31 15:39:57', b'0', b'0', NULL);
INSERT INTO `sys_user` VALUES (1363818702365011970, 'test', '13307117777', '男', '320859852@qq.com', NULL, '9b0f4932ee013ec1b2afb5057c2d34e8', 1, NULL, 1, '2021-02-22 05:51:32', b'0', b'0', NULL);
INSERT INTO `sys_user` VALUES (1364866497083621377, 'asdad', '18888888888', '', 'asada', NULL, '1c395a8dce135849bd73c6dba3b54809', 1, NULL, 1, '2021-02-25 03:15:05', b'1', b'0', NULL);
INSERT INTO `sys_user` VALUES (1364869292549488642, 'asdfdas', '12345678901', '', 'asdf', NULL, 'e48b8e626e77b7c218788eb92341f773', 2, NULL, 1, '2021-02-25 03:26:12', b'1', b'0', NULL);
INSERT INTO `sys_user` VALUES (1364875678759010306, '阿士大夫', '1825211322', '男', '阿斯顿发的是范德萨', NULL, 'dc960c46c38bd16e953d97cdeefdbc68', 1, NULL, 1, '2021-02-25 03:51:35', b'1', b'0', NULL);
INSERT INTO `sys_user` VALUES (1364882862733643778, '你好啊', '18267171821', '男', 'asdfaffasdf', NULL, 'e9c225055678356fb83992415f92ba7b', 1, NULL, 1, '2021-02-25 04:20:07', b'0', b'0', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
