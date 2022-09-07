/*
 Navicat Premium Data Transfer

 Source Server         : 本机数据库
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : nirvana_reborn

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 07/09/2022 16:25:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_user
-- ----------------------------
DROP TABLE IF EXISTS `biz_user`;
CREATE TABLE `biz_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `open_id1` varchar(255) DEFAULT '' COMMENT '微信openId',
  `open_id2` varchar(255) DEFAULT '' COMMENT '微信openId',
  `union_id` varchar(255) DEFAULT '' COMMENT '微信unionId',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `real_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '0' COMMENT '用户类型（0普通用户）',
  `user_level` varchar(2) DEFAULT '0' COMMENT '用户等级（0普通用户）',
  `user_vip` varchar(2) DEFAULT '0' COMMENT '用户vip（ 0否 1是）',
  `user_vip_level` varchar(2) DEFAULT '-1' COMMENT '用户vip等级（-1非vip 0白银级别 1黄金等级 2白金等级 3钻石等级）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='业务用户信息表';

-- ----------------------------
-- Records of biz_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_info`;
CREATE TABLE `enterprise_info` (
  `enterprise_id` bigint NOT NULL AUTO_INCREMENT COMMENT '企业id',
  `enterprise_name` varchar(255) DEFAULT '' COMMENT '企业名称',
  `enterprise_type` varchar(2) DEFAULT '0' COMMENT '企业类型（0普通企业）',
  `enterprise_level` varchar(2) DEFAULT '0' COMMENT '企业等级（0普通企业）',
  `enterprise_default_img` varchar(500) DEFAULT '' COMMENT '默认图片',
  `enterprise_catalog_ids` varchar(1000) DEFAULT NULL COMMENT '企业分类id集合',
  `enterprise_catalog_name` varchar(1000) DEFAULT NULL COMMENT '企业分类名称集合',
  `enterprise_desc` varchar(1000) DEFAULT '' COMMENT '企业描述',
  `enterprise_score` decimal(10,2) DEFAULT NULL COMMENT '企业评分',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '企业状态（0正常 1停用）',
  `enterprise_location` varchar(1000) DEFAULT '' COMMENT '企业位置',
  `enterprise_longitude` varchar(1000) DEFAULT '' COMMENT '企业位置经度',
  `enterprise_latitude` varchar(1000) DEFAULT '' COMMENT '企业位置纬度',
  `enterprise_trade_area` varchar(1000) DEFAULT '' COMMENT '企业所属商圈',
  `enterprise_contact` varchar(1000) DEFAULT '' COMMENT '企业联系人',
  `enterprise_phone` varchar(1000) DEFAULT '' COMMENT '企业联系电话',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业信息';

-- ----------------------------
-- Records of enterprise_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1789 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `accessToken` varchar(255) NOT NULL COMMENT '用户请求token',
  `userId` bigint NOT NULL COMMENT '用户主键id',
  `userType` int NOT NULL COMMENT '用户类型',
  `expiresTime` datetime NOT NULL COMMENT '过期时间',
  `valid` bit(1) NOT NULL COMMENT '是否有效 0、禁用 1、正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='请求token';

-- ----------------------------
-- Records of oauth2_access_token
-- ----------------------------
BEGIN;
INSERT INTO `oauth2_access_token` (`id`, `accessToken`, `userId`, `userType`, `expiresTime`, `valid`) VALUES (1, 'de27204bc88645adb91e7b8dade2b48b', 1, 1, '2020-02-20 16:53:50', b'0');
INSERT INTO `oauth2_access_token` (`id`, `accessToken`, `userId`, `userType`, `expiresTime`, `valid`) VALUES (2, '9cf21b2db8894c27bbb21f32b358ec69', 1, 1, '2020-02-20 16:56:21', b'0');
INSERT INTO `oauth2_access_token` (`id`, `accessToken`, `userId`, `userType`, `expiresTime`, `valid`) VALUES (3, '2212605cb84c4de69b799770cd746245', 1, 1, '2020-02-20 16:56:26', b'1');
COMMIT;

-- ----------------------------
-- Table structure for product_attr
-- ----------------------------
DROP TABLE IF EXISTS `product_attr`;
CREATE TABLE `product_attr` (
  `attr_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品属性id',
  `product_id` bigint NOT NULL COMMENT '商品id',
  `attr_name` varchar(255) NOT NULL COMMENT '属性名',
  `attr_value` varchar(1000) DEFAULT NULL COMMENT '属性值',
  `attr_sort` int NOT NULL COMMENT '属性排序',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性';

-- ----------------------------
-- Records of product_attr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_value`;
CREATE TABLE `product_attr_value` (
  `attr_value_id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性value id',
  `attr_id` bigint NOT NULL COMMENT '商品属性id',
  `attr_value_name` varchar(255) DEFAULT '' COMMENT '属性值名称',
  `attr_value_sort` int DEFAULT '0' COMMENT '属性 值排序',
  PRIMARY KEY (`attr_value_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性';

-- ----------------------------
-- Records of product_attr_value
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `brand_id` bigint NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(200) NOT NULL COMMENT '品牌名',
  `brand_logo` varchar(2000) DEFAULT '' COMMENT '品牌logo地址',
  `brand_describe` text COMMENT '介绍',
  `show_status` char(1) DEFAULT '0' COMMENT '显示状态[0-显示；1-不显示]',
  `first_letter` char(1) DEFAULT '' COMMENT '检索首字母',
  `brand_sort` int DEFAULT '0' COMMENT '排序',
  `enable` bit(1) DEFAULT b'0' COMMENT '品牌状态（0正常 1停用）',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌';

-- ----------------------------
-- Records of product_brand
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_catalog
-- ----------------------------
DROP TABLE IF EXISTS `product_catalog`;
CREATE TABLE `product_catalog` (
  `catalog_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `catalog_name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父分类id',
  `shop_id` bigint DEFAULT '0' COMMENT '商家id',
  `shop_name` varchar(1000) DEFAULT '' COMMENT '商家名称',
  `ancestors` varchar(1000) DEFAULT '' COMMENT '祖先节点',
  `catalog_level` int DEFAULT '0' COMMENT '层级',
  `catalog_sort` int DEFAULT '0' COMMENT '排序',
  `catalog_icon` varchar(255) DEFAULT '' COMMENT '图标地址',
  `enable` bit(1) DEFAULT b'0' COMMENT '商品状态（0正常 1停用）',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`catalog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品三级分类';

-- ----------------------------
-- Records of product_catalog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_code` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品编码',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `product_default_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '默认图片',
  `product_carousel_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品轮播图地址',
  `product_label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品标签',
  `product_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品描述',
  `product_unit` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品计量单位',
  `product_new` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品新品',
  `product_hot` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品热卖',
  `product_onsale` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品在售',
  `catalog_id` bigint DEFAULT '0' COMMENT '所属分类id',
  `catalog_name` varchar(500) DEFAULT '' COMMENT '分类名称',
  `brand_id` bigint DEFAULT '0' COMMENT '品牌id',
  `brand_name` varchar(500) DEFAULT '' COMMENT '所属品牌名称',
  `shop_id` bigint NOT NULL COMMENT '所属商家id',
  `shop_name` varchar(500) DEFAULT '' COMMENT '所属商家名称',
  `has_sku` varchar(2) DEFAULT '0' COMMENT '商品规格（0无 1有）',
  `publish_status` varchar(2) DEFAULT '-1' COMMENT '上架状态[-1 已创建 0 - 上架，1 - 下架]',
  `old_price` decimal(10,2) DEFAULT '0.00' COMMENT '原价',
  `new_price` decimal(10,2) DEFAULT '0.00' COMMENT '现价',
  `pack_fee` decimal(10,2) DEFAULT '0.00' COMMENT '包装费',
  `sale_start_time` varchar(255) DEFAULT '' COMMENT '售卖开始时间',
  `sale_end_time` varchar(255) DEFAULT '' COMMENT '售卖结束时间',
  `must_choose` char(1) DEFAULT '0' COMMENT '是否必选（0非 1是）',
  `sale_count_day` int DEFAULT '0' COMMENT '日销量',
  `sale_count_month` int DEFAULT '0' COMMENT '月销量',
  `sale_start_unit` int DEFAULT '0' COMMENT '起售份数',
  `enable` bit(1) DEFAULT b'0' COMMENT '商品状态（0正常 1停用）',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品信息';

-- ----------------------------
-- Records of product_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
CREATE TABLE `product_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `order_type` varchar(2) DEFAULT '0' COMMENT '订单类型 0正常 1 预定',
  `order_source_channel` varchar(2) DEFAULT '0' COMMENT '订单来源渠道',
  `order_pay_type` varchar(2) DEFAULT '0' COMMENT '订单支付类型',
  `order_old_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单原价',
  `order_real_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单实际价格',
  `order_discount_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单优惠价格',
  `delivery_fee` decimal(10,2) DEFAULT '0.00' COMMENT '订单配送费用',
  `pack_fee` decimal(10,2) DEFAULT '0.00' COMMENT '订单包装费用',
  `order_total_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单总价',
  `platform_service_fee` decimal(10,2) DEFAULT '0.00' COMMENT '订单平台服务费',
  `platform_allowancen_fee` decimal(10,2) DEFAULT '0.00' COMMENT '订单平台补贴费',
  `user_give_fee` decimal(10,2) DEFAULT '0.00' COMMENT '用户打赏费用',
  `tenant_id` bigint DEFAULT '0' COMMENT '分销商id',
  `shop_id` bigint NOT NULL COMMENT '订单所属商家 ',
  `shop_name` varchar(200) DEFAULT '' COMMENT '订单所属商家名称',
  `shop_main_img` varchar(500) DEFAULT '' COMMENT '商家主图',
  `shop_order_number` int DEFAULT '0' COMMENT '订单所属商家序号',
  `user_id` bigint NOT NULL COMMENT '下单人 ',
  `address_id` bigint NOT NULL COMMENT '订单收货地址id ',
  `user_phone` varchar(15) DEFAULT '' COMMENT '下单用户手机号',
  `need_dinner_set` varchar(32) DEFAULT '随餐量提供' COMMENT '餐具份数 ',
  `need_invoice` varchar(2) DEFAULT '0' COMMENT '是否需要发票 0不要 1 要  ',
  `status` char(2) DEFAULT '\0' COMMENT '订单状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `evaluate_time` varchar(500) DEFAULT '' COMMENT '预计送达时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '订单支付时间',
  `shop_accept_time` datetime DEFAULT NULL COMMENT '商家接单时间',
  `delivery_accept_time` datetime DEFAULT NULL COMMENT '骑手接单时间',
  `shop_meal_time` datetime DEFAULT NULL COMMENT '商家出餐时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '订单配送时间',
  `estimated_complete_time` datetime DEFAULT NULL COMMENT '订单预计完成时间',
  `user_confirm_time` datetime DEFAULT NULL COMMENT '订单用户确认收货时间',
  `complete_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(2000) DEFAULT '' COMMENT '备注',
  `cancel_order_remark` varchar(2000) DEFAULT '' COMMENT '取消订单备注',
  `order_logistics_no` varchar(2000) DEFAULT '' COMMENT '订单发货物流单号 ',
  `order_logistics_name` varchar(2000) DEFAULT '' COMMENT '订单发货物流公司 ',
  `order_return_logistics_no` varchar(2000) DEFAULT '' COMMENT '订单退货物流单号 ',
  `order_return_logistics_name` varchar(2000) DEFAULT '' COMMENT '订单退货物流公司 ',
  `pay_order_id` varchar(200) DEFAULT '' COMMENT '订单支付id',
  `apply_status` varchar(2) DEFAULT '0' COMMENT '订单申请审核状态',
  `move_status` varchar(2) DEFAULT '0' COMMENT '订单流程变动状态',
  `share_user_id` bigint DEFAULT '0' COMMENT '推广用户',
  `fetch_code` varchar(255) DEFAULT '' COMMENT '提货码',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单表';

-- ----------------------------
-- Records of product_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_audit
-- ----------------------------
DROP TABLE IF EXISTS `product_order_audit`;
CREATE TABLE `product_order_audit` (
  `order_audit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单审核明细id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `apply_user_id` bigint NOT NULL COMMENT '申请人id',
  `apply_user_name` varchar(500) DEFAULT '' COMMENT '申请人姓名',
  `audit_user_id` bigint NOT NULL COMMENT '审核人id',
  `audit_user_name` varchar(500) DEFAULT '' COMMENT '审核人姓名',
  `status` varchar(2) DEFAULT '0' COMMENT '审核状态 -1 审核拒绝  0审核中  1审核通过',
  `deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单审核明细表';

-- ----------------------------
-- Records of product_order_audit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_comment
-- ----------------------------
DROP TABLE IF EXISTS `product_order_comment`;
CREATE TABLE `product_order_comment` (
  `order_comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单评论id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `user_id` bigint NOT NULL COMMENT '用户id ',
  `shop_id` bigint DEFAULT '0' COMMENT '商家id',
  `goods_id` bigint DEFAULT '0' COMMENT '商品id ',
  `total_star` varchar(32) NOT NULL DEFAULT '0' COMMENT '服务星级 ',
  `pack_star` varchar(32) DEFAULT '0' COMMENT '包装星级',
  `taste_star` varchar(32) DEFAULT '0' COMMENT '口味星级',
  `comment_label` varchar(500) DEFAULT '' COMMENT '评价标签',
  `comment_content` varchar(1000) DEFAULT '' COMMENT '评论内容',
  `comment_img` varchar(1000) DEFAULT '' COMMENT '评论图片',
  `anon_name` varchar(2) DEFAULT '0' COMMENT '是否匿名 0匿名 1否',
  `reply_content` varchar(1000) DEFAULT NULL COMMENT '回复内容',
  `reply_user_id` bigint DEFAULT '0' COMMENT '回复人id ',
  `status` varchar(2) DEFAULT '0' COMMENT '订单评论状态',
  `deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单评论表';

-- ----------------------------
-- Records of product_order_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_discount
-- ----------------------------
DROP TABLE IF EXISTS `product_order_discount`;
CREATE TABLE `product_order_discount` (
  `order_discount_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单优惠明细id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `order_type` varchar(2) DEFAULT '0' COMMENT '优惠类型 ',
  `order_type_name` varchar(255) DEFAULT '' COMMENT '优惠类型名称 ',
  `discount_relation_id` bigint DEFAULT '0' COMMENT '优惠关联id（如红包编号，无则默认0） ',
  `discount_price` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `status` varchar(2) DEFAULT '0' COMMENT '订单优惠明细状态',
  `deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单优惠明细表';

-- ----------------------------
-- Records of product_order_discount
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_item
-- ----------------------------
DROP TABLE IF EXISTS `product_order_item`;
CREATE TABLE `product_order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `product_id` bigint NOT NULL COMMENT '商品id',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品名称',
  `product_default_img` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品图片',
  `product_sku_id` bigint DEFAULT '0' COMMENT '商品sku编号 ',
  `product_sku_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品sku名称',
  `product_sku_name_front` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '商品sku名称前台',
  `product_count` int DEFAULT '0' COMMENT '商品数量',
  `product_unit_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `product_total_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品总价',
  `order_logistics_no` varchar(2000) DEFAULT '' COMMENT '订单发货物流单号 ',
  `order_logistics_name` varchar(2000) DEFAULT '' COMMENT '订单发货物流公司 ',
  `order_return_logistics_no` varchar(2000) DEFAULT '' COMMENT '订单退货物流单号 ',
  `order_return_logistics_name` varchar(2000) DEFAULT '' COMMENT '订单退货物流公司 ',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '订单条目状态(0正常 1退款中 2退款完成)',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单下单时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单明细表';

-- ----------------------------
-- Records of product_order_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_logistics
-- ----------------------------
DROP TABLE IF EXISTS `product_order_logistics`;
CREATE TABLE `product_order_logistics` (
  `order_logistics_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单物流明细id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `order_logistics_no` varchar(2000) DEFAULT '' COMMENT '订单发货物流单号 ',
  `order_logistics_name` varchar(2000) DEFAULT '' COMMENT '订单发货物流公司 ',
  `order_logistics_old_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单物流原价',
  `order_logistics_real_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单物流价格实付',
  `order_logistics_discount_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单物流价格优惠',
  `order_logistics_type` varchar(2) DEFAULT '0' COMMENT '订单物流类型  发货0 退货1',
  `order_logistics_nodes` text COMMENT '订单物流节点轨迹 ',
  `status` varchar(2) DEFAULT '0' COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_logistics_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单物流表';

-- ----------------------------
-- Records of product_order_logistics
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_pay
-- ----------------------------
DROP TABLE IF EXISTS `product_order_pay`;
CREATE TABLE `product_order_pay` (
  `order_pay_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单支付明细id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `pay_order_id` varchar(200) DEFAULT '' COMMENT '订单支付id',
  `order_pay_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单支付金额',
  `order_pay_source` varchar(2) DEFAULT '0' COMMENT '订单支付金额来源(0微信 1支付宝 2余额 3组合支付 4积分)',
  `status` varchar(2) DEFAULT '0' COMMENT '订单支付明细状态',
  `deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单支付明细表';

-- ----------------------------
-- Records of product_order_pay
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_order_refund
-- ----------------------------
DROP TABLE IF EXISTS `product_order_refund`;
CREATE TABLE `product_order_refund` (
  `order_refund_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单退款id ',
  `user_id` bigint NOT NULL COMMENT '用户id ',
  `order_no` bigint NOT NULL COMMENT '订单编号 ',
  `order_total_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单原支付金额',
  `order_refund_price` decimal(10,2) DEFAULT '0.00' COMMENT '订单退款金额',
  `order_refund_source` varchar(2) DEFAULT '0' COMMENT '订单退款渠道 0原路返回 1自定义',
  `refund_mode` varchar(2) DEFAULT '0' COMMENT '退款方式 0 整单  1 部分',
  `status` varchar(2) DEFAULT '0' COMMENT '订单退款明细状态 0退款中  1 退款成功',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`order_refund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品订单退款明细表';

-- ----------------------------
-- Records of product_order_refund
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_sku_info
-- ----------------------------
DROP TABLE IF EXISTS `product_sku_info`;
CREATE TABLE `product_sku_info` (
  `sku_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'skuId',
  `product_id` bigint DEFAULT NULL COMMENT '商品Id',
  `sku_code` varchar(1000) DEFAULT '',
  `sku_name` varchar(1000) DEFAULT '' COMMENT 'sku名称',
  `sku_desc` varchar(2000) DEFAULT '' COMMENT 'sku介绍描述',
  `sku_default_img` varchar(255) DEFAULT '' COMMENT '默认图片',
  `stock` int DEFAULT '0' COMMENT '库存',
  `old_price` decimal(10,2) DEFAULT '0.00' COMMENT '原价',
  `new_price` decimal(10,2) DEFAULT '0.00' COMMENT '现价',
  `weight` decimal(10,2) DEFAULT '0.00' COMMENT '重量',
  `status` char(1) DEFAULT '0' COMMENT '商品状态（0正常 1停用）',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品sku信息';

-- ----------------------------
-- Records of product_sku_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for shop_catalog
-- ----------------------------
DROP TABLE IF EXISTS `shop_catalog`;
CREATE TABLE `shop_catalog` (
  `catalog_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `catalog_name` varchar(255) DEFAULT '' COMMENT '分类名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父分类id',
  `ancestors` varchar(1000) DEFAULT '' COMMENT '祖先节点',
  `catalog_level` int DEFAULT '1' COMMENT '层级',
  `catalog_sort` int DEFAULT '0' COMMENT '排序',
  `catalog_icon` varchar(255) DEFAULT '' COMMENT '图标地址',
  `status` char(1) DEFAULT '0' COMMENT '商品状态（0正常 1停用）',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标志（0代表存在 1代表删除）',
  `creator` bigint DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`catalog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家三级分类';

-- ----------------------------
-- Records of shop_catalog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for shop_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info` (
  `shop_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `shop_name` varchar(255) DEFAULT '' COMMENT '商家名称',
  `shop_type` varchar(2) DEFAULT '0' COMMENT '商家类型（0普通商家）',
  `shop_level` varchar(2) DEFAULT '0' COMMENT '商家等级（0普通商家）',
  `tenant_id` bigint DEFAULT '0' COMMENT '租户Id',
  `region_id` bigint DEFAULT '0' COMMENT '区域Id',
  `enterprise_id` bigint DEFAULT '0' COMMENT '企业Id',
  `tenant_name` varchar(500) DEFAULT '' COMMENT '租户名称',
  `region_name` varchar(500) DEFAULT '' COMMENT '区域名称',
  `enterprise_name` varchar(500) DEFAULT '' COMMENT '企业名称',
  `brand_id` varchar(32) DEFAULT '' COMMENT '品牌id',
  `brand_name` varchar(500) DEFAULT '' COMMENT '品牌名称',
  `shop_default_img` varchar(500) DEFAULT '' COMMENT '默认背景图片',
  `shop_main_img` varchar(500) DEFAULT '' COMMENT '商家主图',
  `shop_notice` varchar(500) DEFAULT '' COMMENT '商家公告',
  `shop_desc` text COMMENT '商家描述',
  `shop_score` decimal(10,2) DEFAULT '0.00' COMMENT '商家评分',
  `shop_chain` varchar(2) DEFAULT '' COMMENT '是否连锁 0否 1是',
  `business_status` char(1) DEFAULT '0' COMMENT '营业状态[0 - 营业，1 - 歇业]',
  `business_start_time` varchar(255) DEFAULT '' COMMENT '营业开始时间',
  `business_end_time` varchar(255) DEFAULT '' COMMENT '营业结束时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '商家状态（0正常 1停用）',
  `deliver_mode` char(1) DEFAULT '0' COMMENT '配送方式（0立即配送 1预定配送）',
  `deliver_platform` char(1) DEFAULT '0' COMMENT '配送平台（0平台配送 1商家配送）',
  `deliver_time` decimal(10,2) DEFAULT '0.00' COMMENT '配送时长',
  `deliver_start_fee` decimal(10,2) DEFAULT '0.00' COMMENT '配送起步金额',
  `deliver_fee` decimal(10,2) DEFAULT '0.00' COMMENT '配送费用',
  `deliver_distance` decimal(10,2) DEFAULT '0.00' COMMENT '配送距离',
  `shop_location` varchar(1000) DEFAULT '' COMMENT '商家位置',
  `shop_longitude` varchar(1000) DEFAULT '' COMMENT '商家位置经度',
  `shop_latitude` varchar(1000) DEFAULT '' COMMENT '商家位置纬度',
  `shop_trade_area` varchar(1000) DEFAULT '' COMMENT '商家所属商圈',
  `shop_discount` int DEFAULT '0' COMMENT '商家折扣',
  `shop_contact` varchar(1000) DEFAULT '' COMMENT '商家联系人',
  `shop_phone` varchar(1000) DEFAULT '' COMMENT '商家联系电话',
  `shop_star` varchar(32) DEFAULT '' COMMENT '商家评分',
  `shop_label` varchar(100) DEFAULT '' COMMENT '商家标签',
  `shop_order_accept` char(1) DEFAULT '0' COMMENT '自动接单 0否 1是',
  `shop_catalog_ids` varchar(1000) DEFAULT '' COMMENT '商家分类id集合',
  `shop_catalog_name` varchar(1000) DEFAULT '' COMMENT '商家品类名称集合',
  `shop_new` varchar(2) DEFAULT '' COMMENT '是否新店  0否  1是',
  `shop_section_business` char(1) DEFAULT '0' COMMENT '是否分段营业 0否 1是',
  `person_consume_per` decimal(10,2) DEFAULT '0.00' COMMENT '人均消费',
  `has_invoice` char(1) DEFAULT '0' COMMENT '是否有发票（0无 1有）',
  `sale_count_day` int DEFAULT '0' COMMENT '日销量',
  `sale_count_month` int DEFAULT '0' COMMENT '月销量',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家信息';

-- ----------------------------
-- Records of shop_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for shop_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL COMMENT '商户用户名称',
  `phone` varchar(11) NOT NULL COMMENT '商户用户手机号',
  `age` int NOT NULL COMMENT '商户用户年龄',
  `password` varchar(255) NOT NULL COMMENT '商户用户密码',
  `creator` bigint NOT NULL COMMENT '创建用户id',
  `updater` bigint DEFAULT NULL COMMENT '修改用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '0:正常，1:删除',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='商户-用户表';

-- ----------------------------
-- Records of shop_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `pid` bigint NOT NULL COMMENT '上级组织id',
  `name` varchar(255) NOT NULL COMMENT '组织名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父节点id，0顶级',
  `code` varchar(255) NOT NULL COMMENT '组织代码',
  `sort` int DEFAULT NULL COMMENT '排序',
  `uid` bigint DEFAULT NULL COMMENT '部门负责人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint NOT NULL COMMENT '创建人',
  `updater` bigint DEFAULT NULL COMMENT '修改人',
  `enable` bit(1) NOT NULL COMMENT '是否可用',
  `deleted` bit(1) NOT NULL COMMENT '是否删除',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
BEGIN;
INSERT INTO `sys_department` (`id`, `pid`, `name`, `parent_id`, `code`, `sort`, `uid`, `create_time`, `update_time`, `creator`, `updater`, `enable`, `deleted`, `tenant_id`) VALUES (1, 0, '超级管理', 0, '00', 1, NULL, '2019-08-14 09:17:07', '2019-08-14 09:17:07', 0, NULL, b'1', b'0', 0);
INSERT INTO `sys_department` (`id`, `pid`, `name`, `parent_id`, `code`, `sort`, `uid`, `create_time`, `update_time`, `creator`, `updater`, `enable`, `deleted`, `tenant_id`) VALUES (2, 1, '江苏省', 1, '0001', 2, NULL, '2020-02-20 17:18:26', NULL, 0, NULL, b'1', b'0', 0);
INSERT INTO `sys_department` (`id`, `pid`, `name`, `parent_id`, `code`, `sort`, `uid`, `create_time`, `update_time`, `creator`, `updater`, `enable`, `deleted`, `tenant_id`) VALUES (3, 2, '徐州市', 2, '000101', 3, NULL, '2020-02-20 17:19:31', NULL, 0, NULL, b'1', b'0', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL COMMENT '字典id',
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `pid` bigint NOT NULL COMMENT '类型id',
  `type` smallint NOT NULL COMMENT '0.类型 1.字典',
  `creator` bigint NOT NULL COMMENT '创建人',
  `updater` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否删除 0未删除，1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_error_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_error_code`;
CREATE TABLE `sys_error_code` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` int DEFAULT NULL COMMENT '错误代码',
  `message` varchar(255) DEFAULT NULL COMMENT '错误提示',
  `type` int DEFAULT NULL COMMENT '1、自动生成，2、手动便捷',
  `group` varchar(255) DEFAULT NULL COMMENT '分组名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='错误代码表';

-- ----------------------------
-- Records of sys_error_code
-- ----------------------------
BEGIN;
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (1, 100010, '用户不存在', 1, 'admin-web-service-impl', NULL, '2021-02-01 05:59:56', '2021-02-01 05:59:56');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (4, 100011, '该用户已被禁用', 1, 'admin-web-service-impl', NULL, '2021-02-05 02:10:49', '2021-02-05 02:10:49');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (5, 100012, '用户密码错误', 1, 'admin-web-service-impl', NULL, '2021-02-05 02:10:49', '2021-02-05 02:10:49');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (6, 100013, '登录验证码错误', 1, 'admin-web-service-impl', NULL, '2021-02-05 21:40:48', '2021-02-05 21:40:48');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (7, 100014, '当前手机号已存在', 1, 'admin-web-service-impl', NULL, '2021-02-22 04:53:38', '2021-02-22 04:53:38');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (8, 100020, '菜单父级不能是自己', 1, 'admin-web-service-impl', NULL, '2022-03-24 06:18:09', '2022-03-24 06:18:09');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (9, 100021, '外链必须以http://或者https://开头', 1, 'admin-web-service-impl', NULL, '2022-03-24 06:18:09', '2022-03-24 06:18:09');
INSERT INTO `sys_error_code` (`id`, `code`, `message`, `type`, `group`, `memo`, `create_time`, `update_time`) VALUES (10, 100022, '菜单不存在', 1, 'admin-web-service-impl', NULL, '2022-03-24 06:18:09', '2022-03-24 06:18:09');
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `log_type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `request_ip` varchar(255) DEFAULT NULL,
  `time` bigint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `plat_form` smallint DEFAULT NULL COMMENT '0、管理平台1、App',
  `iFrame` smallint DEFAULT '1' COMMENT '是否是外链，0是，1否',
  `type` smallint NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、按钮',
  `sub_count` int DEFAULT '0' COMMENT '子节点数量',
  `sort` int DEFAULT '0' COMMENT '排序',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '地址',
  `perm_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限编码',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `description` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator` bigint NOT NULL COMMENT '创建人',
  `updater` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `enable` bit(1) NOT NULL COMMENT '是否使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜单资源表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `pid`, `title`, `plat_form`, `iFrame`, `type`, `sub_count`, `sort`, `url`, `perm_code`, `icon`, `description`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `enable`) VALUES (1, 0, '测试菜单', 0, 1, 0, 1, 0, '/user/info', 'admin', NULL, NULL, 0, NULL, '2019-08-31 15:41:00', '2019-08-31 15:41:03', b'0', b'0');
INSERT INTO `sys_permission` (`id`, `pid`, `title`, `plat_form`, `iFrame`, `type`, `sub_count`, `sort`, `url`, `perm_code`, `icon`, `description`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `enable`) VALUES (2, 1, '测试按钮', 0, 1, 1, 0, 0, 'user:add', 'user', NULL, NULL, 0, NULL, '2021-02-06 09:53:06', '2021-02-06 09:53:11', b'0', b'0');
INSERT INTO `sys_permission` (`id`, `pid`, `title`, `plat_form`, `iFrame`, `type`, `sub_count`, `sort`, `url`, `perm_code`, `icon`, `description`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `enable`) VALUES (3, 0, '用户列表', 0, 1, 0, 0, 0, '/users/adminUser', 'dashboard', NULL, NULL, 0, NULL, '2021-02-06 13:44:12', '2021-02-06 13:44:18', b'0', b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint NOT NULL COMMENT '职务id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `name` varchar(255) NOT NULL COMMENT '职务名称',
  `code` varchar(255) NOT NULL COMMENT '职务代码',
  `sort` int DEFAULT NULL COMMENT '排序',
  `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `updater` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `enable` bit(1) DEFAULT NULL COMMENT '是否可用0、正常 1、禁用',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否删除0未删除，1删除',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='岗位表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` (`id`, `dept_id`, `name`, `code`, `sort`, `creator`, `updater`, `create_time`, `update_time`, `enable`, `deleted`, `tenant_id`) VALUES (1, 1, '超级管理员', '0', 1, 0, NULL, '2019-08-12 07:46:00', '2019-08-12 07:46:00', b'1', b'1', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `sort` smallint DEFAULT NULL COMMENT '排序',
  `description` varchar(32) DEFAULT NULL COMMENT '描述',
  `creator` bigint NOT NULL COMMENT '创建人id',
  `updater` bigint NOT NULL COMMENT '修改人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `enable` bit(1) DEFAULT NULL COMMENT '是否使用',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `sort`, `description`, `creator`, `updater`, `create_time`, `update_time`, `enable`, `deleted`, `tenant_id`) VALUES (1, '超级管理员', 1, '测试', 1, 0, '2019-08-31 15:40:20', '2019-08-31 15:40:25', b'0', b'0', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint NOT NULL COMMENT '角色ID',
  `pid` bigint NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=FIXED COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `rid`, `pid`) VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` (`id`, `rid`, `pid`) VALUES (2, 1, 2);
INSERT INTO `sys_role_permission` (`id`, `rid`, `pid`) VALUES (3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '租户编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '租户名',
  `contact_user_id` bigint DEFAULT NULL COMMENT '联系人的用户编号',
  `contact_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系人',
  `contact_mobile` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系手机',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
  `domain` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '绑定域名',
  `package_id` bigint NOT NULL COMMENT '租户套餐编号',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `account_count` int NOT NULL COMMENT '账号数量',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant_package
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant_package`;
CREATE TABLE `sys_tenant_package` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '套餐编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '套餐名',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `menu_ids` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关联的菜单编号',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户套餐表';

-- ----------------------------
-- Records of sys_tenant_package
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `phone` varchar(11) NOT NULL COMMENT '用户手机号',
  `sex` varchar(1) NOT NULL COMMENT '用户性别',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `post_id` bigint DEFAULT NULL COMMENT '岗位id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '0、正常 1、禁用',
  `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `updater` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0:正常，1:删除',
  `tenant_id` bigint NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1364882862733643779 DEFAULT CHARSET=utf8mb3 COMMENT='系统用户\n';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1, 'admin', '18252113278', '男', '320859852@qq.com', NULL, '$2a$10$afv1lHR8UhmWs.5g9E9RQuXPlZ1nmSbqUslnFfC2.AgzcCsGAhic.', 1, 1, 1, b'0', 0, NULL, '2019-08-31 15:39:57', NULL, b'0', 0);
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1363818702365011970, 'test123', '13307117777', '男', '320859852@qq.com', NULL, '9b0f4932ee013ec1b2afb5057c2d34e8', 2, NULL, 1, b'0', 0, 1, '2021-02-22 05:51:32', '2022-08-12 10:56:30', b'0', 0);
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1364866497083621377, 'asdad', '18888888888', '', 'asada', NULL, '1c395a8dce135849bd73c6dba3b54809', 1, NULL, 1, b'0', 0, NULL, '2021-02-25 03:15:05', NULL, b'1', 0);
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1364869292549488642, 'asdfdas', '12345678901', '', 'asdf', NULL, 'e48b8e626e77b7c218788eb92341f773', 2, NULL, 1, b'0', 0, NULL, '2021-02-25 03:26:12', NULL, b'1', 0);
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1364875678759010306, '阿士大夫', '1825211322', '男', '阿斯顿发的是范德萨', NULL, 'dc960c46c38bd16e953d97cdeefdbc68', 1, NULL, 1, b'0', 0, NULL, '2021-02-25 03:51:35', NULL, b'1', 0);
INSERT INTO `sys_user` (`id`, `user_name`, `phone`, `sex`, `email`, `avatar`, `password`, `dept_id`, `post_id`, `role_id`, `enable`, `creator`, `updater`, `create_time`, `update_time`, `deleted`, `tenant_id`) VALUES (1364882862733643778, '你好啊', '18267171821', '男', 'asdfaffasdf', NULL, 'e9c225055678356fb83992415f92ba7b', 1, NULL, 1, b'0', 0, NULL, '2021-02-25 04:20:07', NULL, b'0', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `tenant_id` bigint NOT NULL AUTO_INCREMENT COMMENT '加盟商id',
  `tenant_name` varchar(255) DEFAULT '' COMMENT '加盟商名称',
  `tenant_type` varchar(2) DEFAULT '0' COMMENT '加盟商类型（0普通加盟商）',
  `tenant_level` varchar(2) DEFAULT '0' COMMENT '加盟商等级（0普通加盟商）',
  `tenant_default_img` varchar(500) DEFAULT '' COMMENT '默认图片',
  `tenant_desc` varchar(1000) DEFAULT '' COMMENT '加盟商描述',
  `tenant_catalog_ids` varchar(1000) DEFAULT NULL COMMENT '加盟商分类id集合',
  `tenant_catalog_name` varchar(1000) DEFAULT NULL COMMENT '加盟商分类名称集合',
  `tenant_score` decimal(10,2) DEFAULT NULL COMMENT '加盟商评分',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '加盟商状态（0正常 1停用）',
  `tenant_location` varchar(1000) DEFAULT '' COMMENT '加盟商位置',
  `tenant_longitude` varchar(1000) DEFAULT '' COMMENT '加盟商位置经度',
  `tenant_latitude` varchar(1000) DEFAULT '' COMMENT '加盟商位置纬度',
  `tenant_trade_area` varchar(1000) DEFAULT '' COMMENT '加盟商所属商圈',
  `tenant_contact` varchar(1000) DEFAULT '' COMMENT '加盟商联系人',
  `tenant_phone` varchar(1000) DEFAULT '' COMMENT '加盟商联系电话',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='加盟商信息';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址id ',
  `province` varchar(60) DEFAULT '' COMMENT '省 ',
  `city` varchar(600) DEFAULT '' COMMENT '市 ',
  `county` varchar(600) DEFAULT '' COMMENT '县区 ',
  `street` varchar(600) DEFAULT '' COMMENT '街道 ',
  `tenant_id` bigint DEFAULT '0' COMMENT '高校id 分销商',
  `tenant_name` varchar(500) DEFAULT '' COMMENT '高校名称 分销商',
  `address_name` varchar(500) DEFAULT '' COMMENT '地址名称',
  `address_longitude` varchar(500) DEFAULT '' COMMENT '经度',
  `address_latitude` varchar(500) DEFAULT '' COMMENT '纬度',
  `address_details` varchar(1000) DEFAULT '' COMMENT '详细地址 ',
  `default_address` varchar(2) NOT NULL DEFAULT '0' COMMENT '是否默认地址 0否 1是 ',
  `user_id` bigint NOT NULL COMMENT '用户id ',
  `phone` varchar(20) NOT NULL COMMENT '用户电话 ',
  `contact` varchar(100) NOT NULL COMMENT '联系人 ',
  `create_by` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单收货地址表';

-- ----------------------------
-- Records of user_address
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
