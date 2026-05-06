
-- 创建数据库
CREATE DATABASE IF NOT EXISTS `notmaker_mall` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `notmaker_mall`;

-- ----------------------------
-- 1. 用户表
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键自增',
  `username` varchar(50) NOT NULL COMMENT '用户名，用于登录系统',
  `password` varchar(100) NOT NULL COMMENT '密码，经过MD5加密存储',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像URL地址',
  `gender` tinyint DEFAULT '0' COMMENT '性别：0-未知，1-男，2-女',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `birthday` varchar(20) DEFAULT NULL COMMENT '出生日期',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '用户状态：0-禁用，1-启用',
  `score` int DEFAULT '0' COMMENT '用户积分',
  `level` int DEFAULT '1' COMMENT '用户等级',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `city` varchar(50) DEFAULT NULL COMMENT '所在城市',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记：0-未删除，1-已删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_phone` (`phone`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- 2. 角色表
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID，主键自增',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码，用于代码中权限判断',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '角色状态：0-禁用，1-启用',
  `sort_order` int DEFAULT '0' COMMENT '排序号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- ----------------------------
-- 3. 权限表
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID，主键自增',
  `permission_name` varchar(50) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限编码，用于代码中权限判断',
  `permission_type` tinyint NOT NULL DEFAULT '1' COMMENT '权限类型：1-菜单，2-按钮，3-接口',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父权限ID，顶级权限为0',
  `path` varchar(255) DEFAULT NULL COMMENT '权限路径或URL',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径（前端）',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标标识',
  `sort_order` int DEFAULT '0' COMMENT '排序号',
  `hidden` tinyint NOT NULL DEFAULT '0' COMMENT '是否隐藏：0-显示，1-隐藏',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '权限状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

-- ----------------------------
-- 4. 用户角色关联表
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- ----------------------------
-- 5. 角色权限关联表
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';

-- ----------------------------
-- 6. 商品分类表
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID，主键自增',
  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父分类ID，顶级分类为0',
  `level` int NOT NULL DEFAULT '1' COMMENT '分类层级深度',
  `icon` varchar(255) DEFAULT NULL COMMENT '分类图标',
  `sort_order` int DEFAULT '0' COMMENT '分类排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '分类状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类表';

-- ----------------------------
-- 7. 商品表
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID，主键自增',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `product_code` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  `main_image` varchar(500) DEFAULT NULL COMMENT '商品主图URL',
  `images` text COMMENT '商品图片列表，JSON格式存储',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '商品原价',
  `stock` int NOT NULL DEFAULT '0' COMMENT '商品库存数量',
  `sales_count` int NOT NULL DEFAULT '0' COMMENT '已售数量',
  `unit` varchar(20) DEFAULT '件' COMMENT '商品单位，如：件、个、箱',
  `weight` double DEFAULT NULL COMMENT '商品重量（克）',
  `description` text COMMENT '商品详情描述',
  `specifications` text COMMENT '商品规格，JSON格式存储',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '商品状态：0-下架，1-上架，2-售罄',
  `is_recommend` tinyint NOT NULL DEFAULT '0' COMMENT '是否推荐：0-否，1-是',
  `is_hot` tinyint NOT NULL DEFAULT '0' COMMENT '是否热卖：0-否，1-是',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核，1-已通过，2-已拒绝',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_status` (`status`),
  KEY `idx_product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品表';

-- ----------------------------
-- 8. 订单表
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键自增',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `freight_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `discount_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `coupon_id` bigint DEFAULT NULL COMMENT '优惠券ID',
  `point_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '积分抵扣金额',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消，5-已退款',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态：0-未支付，1-已支付，2-已退款',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付方式：1-微信支付，2-支付宝，3-银行卡',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `receiver_name` varchar(50) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) NOT NULL COMMENT '收货人电话',
  `receiver_province` varchar(50) NOT NULL COMMENT '收货地址省份',
  `receiver_city` varchar(50) NOT NULL COMMENT '收货地址城市',
  `receiver_district` varchar(50) NOT NULL COMMENT '收货地址区县',
  `receiver_address` varchar(255) NOT NULL COMMENT '详细收货地址',
  `receiver_zip` varchar(10) DEFAULT NULL COMMENT '收货邮编',
  `user_remark` varchar(255) DEFAULT NULL COMMENT '用户备注',
  `admin_remark` varchar(255) DEFAULT NULL COMMENT '管理员备注',
  `ship_time` datetime DEFAULT NULL COMMENT '发货时间',
  `express_company` varchar(50) DEFAULT NULL COMMENT '物流公司',
  `express_no` varchar(50) DEFAULT NULL COMMENT '物流单号',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '取消原因',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_status` (`order_status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- ----------------------------
-- 9. 订单项表
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单项ID，主键自增',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称（冗余存储）',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品主图（冗余存储）',
  `specifications` varchar(500) DEFAULT NULL COMMENT '商品规格，JSON格式存储',
  `price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `quantity` int NOT NULL COMMENT '购买数量',
  `subtotal` decimal(10,2) NOT NULL COMMENT '小计金额',
  `merchant_id` bigint DEFAULT NULL COMMENT '商家ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单项表';

-- ----------------------------
-- 10. 购物车表
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称（冗余存储）',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品主图',
  `specifications` varchar(500) DEFAULT NULL COMMENT '商品规格，JSON格式存储',
  `price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `quantity` int NOT NULL COMMENT '购买数量',
  `selected` tinyint NOT NULL DEFAULT '1' COMMENT '是否选中：0-未选中，1-已选中',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='购物车表';

-- ----------------------------
-- 11. 收货地址表
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `consignee_name` varchar(50) NOT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(20) NOT NULL COMMENT '收货人手机号',
  `province` varchar(50) NOT NULL COMMENT '省份',
  `city` varchar(50) NOT NULL COMMENT '城市',
  `district` varchar(50) NOT NULL COMMENT '区县',
  `detail_address` varchar(255) NOT NULL COMMENT '详细地址',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `is_default` tinyint NOT NULL DEFAULT '0' COMMENT '是否为默认地址：0-否，1-是',
  `address_tag` tinyint DEFAULT NULL COMMENT '地址标签：1-家，2-公司，3-学校',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_is_default` (`is_default`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收货地址表';

-- ----------------------------
-- 12. 收藏表
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '收藏ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `collect_type` tinyint NOT NULL DEFAULT '1' COMMENT '收藏类型：1-商品，2-文章，3-店铺',
  `object_id` bigint NOT NULL COMMENT '收藏对象ID',
  `object_name` varchar(200) DEFAULT NULL COMMENT '收藏对象名称',
  `object_image` varchar(500) DEFAULT NULL COMMENT '收藏对象图片',
  `remark` varchar(255) DEFAULT NULL COMMENT '收藏备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_object_id` (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';

-- ----------------------------
-- 13. 优惠券表
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '优惠券ID，主键自增',
  `coupon_name` varchar(100) NOT NULL COMMENT '优惠券名称',
  `coupon_code` varchar(50) DEFAULT NULL COMMENT '优惠券编码',
  `coupon_type` tinyint NOT NULL DEFAULT '1' COMMENT '优惠券类型：1-满减券，2-折扣券，3-无门槛券',
  `value` decimal(10,2) NOT NULL COMMENT '优惠券面额或折扣',
  `min_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '使用门槛：满X元可用',
  `max_discount` decimal(10,2) DEFAULT NULL COMMENT '最大优惠金额',
  `total_count` int NOT NULL DEFAULT '0' COMMENT '发行总量',
  `received_count` int NOT NULL DEFAULT '0' COMMENT '已领取数量',
  `used_count` int NOT NULL DEFAULT '0' COMMENT '已使用数量',
  `per_limit` int NOT NULL DEFAULT '1' COMMENT '每人限领数量',
  `start_time` datetime NOT NULL COMMENT '有效期开始时间',
  `end_time` datetime NOT NULL COMMENT '有效期结束时间',
  `applicable_scope` tinyint NOT NULL DEFAULT '1' COMMENT '适用商品范围：1-全部商品，2-指定分类，3-指定商品',
  `category_ids` text COMMENT '适用分类ID列表，JSON格式',
  `product_ids` text COMMENT '适用商品ID列表，JSON格式',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '优惠券状态：0-未发布，1-进行中，2-已结束',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='优惠券表';

-- ----------------------------
-- 14. 用户优惠券表
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_coupon`;
CREATE TABLE `tb_user_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
  `coupon_code` varchar(50) DEFAULT NULL COMMENT '优惠券码',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未使用，1-已使用，2-已过期',
  `receive_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint DEFAULT NULL COMMENT '使用的订单ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_coupon_id` (`coupon_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户优惠券表';

-- ----------------------------
-- 15. 评论表
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID，主键自增',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称（冗余存储）',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像（冗余存储）',
  `rating` tinyint NOT NULL DEFAULT '5' COMMENT '评分，1-5星',
  `content` text NOT NULL COMMENT '评论内容',
  `images` text COMMENT '评论图片，JSON数组格式',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '商家回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '商家回复时间',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `reply_count` int NOT NULL DEFAULT '0' COMMENT '回复数量',
  `is_show` tinyint NOT NULL DEFAULT '1' COMMENT '是否显示：0-隐藏，1-显示',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ----------------------------
-- 16. 通知公告表
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知ID，主键自增',
  `title` varchar(200) NOT NULL COMMENT '公告标题',
  `content` text NOT NULL COMMENT '公告内容',
  `notice_type` tinyint NOT NULL DEFAULT '1' COMMENT '公告类型：1-系统通知，2-活动公告，3-物流通知，4-订单通知',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '公告封面图',
  `link_url` varchar(500) DEFAULT NULL COMMENT '公告链接',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序号',
  `is_top` tinyint NOT NULL DEFAULT '0' COMMENT '是否置顶：0-否，1-是',
  `is_urgent` tinyint NOT NULL DEFAULT '0' COMMENT '是否紧急：0-否，1-是',
  `read_count` int NOT NULL DEFAULT '0' COMMENT '阅读量',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '公告状态：0-草稿，1-发布，2-下架',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_notice_type` (`notice_type`),
  KEY `idx_status` (`status`),
  KEY `idx_is_top` (`is_top`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知公告表';

-- ----------------------------
-- 17. 轮播图表
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '轮播图ID，主键自增',
  `title` varchar(100) NOT NULL COMMENT '轮播图标题',
  `image_url` varchar(500) NOT NULL COMMENT '轮播图片URL',
  `link_url` varchar(500) DEFAULT NULL COMMENT '跳转链接',
  `link_type` tinyint NOT NULL DEFAULT '1' COMMENT '跳转类型：1-无链接，2-内部页面，3-外部链接',
  `business_id` bigint DEFAULT NULL COMMENT '关联业务ID（如商品ID、文章ID）',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序号，数字越小越靠前',
  `position` tinyint NOT NULL DEFAULT '1' COMMENT '轮播位置：1-首页，2-商品详情，3-活动页',
  `background_color` varchar(20) DEFAULT '#ffffff' COMMENT '背景色',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '轮播图状态：0-禁用，1-启用',
  `start_time` datetime DEFAULT NULL COMMENT '开始展示时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束展示时间',
  `click_count` int NOT NULL DEFAULT '0' COMMENT '点击量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_position` (`position`),
  KEY `idx_status` (`status`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='轮播图表';

-- ----------------------------
-- 18. 文章表
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章ID，主键自增',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '文章封面图',
  `content` text NOT NULL COMMENT '文章内容',
  `summary` varchar(500) DEFAULT NULL COMMENT '文章摘要',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签，逗号分隔',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '浏览量',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数',
  `comment_count` int NOT NULL DEFAULT '0' COMMENT '评论数',
  `share_count` int NOT NULL DEFAULT '0' COMMENT '分享数',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-草稿，1-已发布，2-已下架',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_status` (`status`),
  KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- ----------------------------
-- 19. 反馈表
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '反馈ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `feedback_type` tinyint NOT NULL DEFAULT '1' COMMENT '反馈类型：1-功能建议，2-bug反馈，3-体验问题，4-其他',
  `content` text NOT NULL COMMENT '反馈内容',
  `images` text COMMENT '反馈图片，JSON数组',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '处理状态：0-待处理，1-处理中，2-已处理，3-已拒绝',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='反馈表';

-- ----------------------------
-- 20. 系统配置表
-- ----------------------------
DROP TABLE IF EXISTS `tb_config`;
CREATE TABLE `tb_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置ID，主键自增',
  `config_key` varchar(100) NOT NULL COMMENT '配置键',
  `config_value` text COMMENT '配置值',
  `config_name` varchar(100) NOT NULL COMMENT '配置名称',
  `config_type` varchar(50) DEFAULT 'string' COMMENT '配置类型：string, number, boolean, json',
  `description` varchar(255) DEFAULT NULL COMMENT '配置描述',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序号',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置表';

-- ----------------------------
-- 21. 数据字典表
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典ID，主键自增',
  `dict_type` varchar(50) NOT NULL COMMENT '字典类型',
  `dict_label` varchar(100) NOT NULL COMMENT '字典标签',
  `dict_value` varchar(100) NOT NULL COMMENT '字典值',
  `dict_sort` int DEFAULT '0' COMMENT '排序号',
  `dict_status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_dict_type` (`dict_type`),
  KEY `idx_dict_status` (`dict_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据字典表';

-- ----------------------------
-- 22. 消息表
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `title` varchar(200) NOT NULL COMMENT '消息标题',
  `content` text NOT NULL COMMENT '消息内容',
  `message_type` tinyint NOT NULL DEFAULT '1' COMMENT '消息类型：1-系统消息，2-订单消息，3-活动消息',
  `is_read` tinyint NOT NULL DEFAULT '0' COMMENT '是否已读：0-未读，1-已读',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `link_url` varchar(500) DEFAULT NULL COMMENT '跳转链接',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_is_read` (`is_read`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';

-- ----------------------------
-- 23. 足迹表
-- ----------------------------
DROP TABLE IF EXISTS `tb_footprint`;
CREATE TABLE `tb_footprint` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '足迹ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='足迹表';

-- ----------------------------
-- 24. 发票表
-- ----------------------------
DROP TABLE IF EXISTS `tb_invoice`;
CREATE TABLE `tb_invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '发票ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `invoice_type` tinyint NOT NULL DEFAULT '1' COMMENT '发票类型：1-普通发票，2-增值税专用发票',
  `invoice_title` varchar(200) NOT NULL COMMENT '发票抬头',
  `tax_no` varchar(50) DEFAULT NULL COMMENT '税号',
  `company_address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `company_phone` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `email` varchar(100) DEFAULT NULL COMMENT '接收邮箱',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待开票，1-已开票，2-已作废',
  `invoice_no` varchar(50) DEFAULT NULL COMMENT '发票号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='发票表';

-- ----------------------------
-- 25. 地区表
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地区ID，主键自增',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父地区ID',
  `area_name` varchar(100) NOT NULL COMMENT '地区名称',
  `area_code` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `level` tinyint NOT NULL DEFAULT '1' COMMENT '地区级别：1-省，2-市，3-区/县',
  `sort_order` int DEFAULT '0' COMMENT '排序号',
  `lng` double DEFAULT NULL COMMENT '经度',
  `lat` double DEFAULT NULL COMMENT '纬度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_area_code` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地区表';

-- ----------------------------
-- 26. 系统日志表
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID，主键自增',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `operation_type` tinyint DEFAULT NULL COMMENT '操作类型：1-登录，2-登出，3-新增，4-修改，5-删除，6-查询',
  `module` varchar(100) DEFAULT NULL COMMENT '请求模块',
  `method` varchar(100) DEFAULT NULL COMMENT '请求方法',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求URL',
  `request_param` text COMMENT '请求参数',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式：GET/POST/PUT/DELETE',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求IP地址',
  `location` varchar(100) DEFAULT NULL COMMENT '操作地点',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  `response_code` int DEFAULT NULL COMMENT '响应状态码',
  `response_msg` varchar(255) DEFAULT NULL COMMENT '响应消息',
  `execution_time` bigint DEFAULT NULL COMMENT '执行时长（毫秒）',
  `status` tinyint DEFAULT '1' COMMENT '操作状态：0-失败，1-成功',
  `error_msg` text COMMENT '错误信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_module` (`module`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统日志表';

-- ----------------------------
-- 27. 秒杀活动表
-- ----------------------------
DROP TABLE IF EXISTS `tb_seckill`;
CREATE TABLE `tb_seckill` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀ID，主键自增',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价',
  `seckill_price` decimal(10,2) NOT NULL COMMENT '秒杀价',
  `stock` int NOT NULL DEFAULT '0' COMMENT '秒杀库存',
  `sold_count` int NOT NULL DEFAULT '0' COMMENT '已售数量',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未开始，1-进行中，2-已结束',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_status` (`status`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='秒杀活动表';

-- ----------------------------
-- 28. 积分记录表
-- ----------------------------
DROP TABLE IF EXISTS `tb_points`;
CREATE TABLE `tb_points` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `points` int NOT NULL COMMENT '积分变动数量',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '类型：1-获取，2-使用',
  `source` tinyint NOT NULL COMMENT '来源：1-注册，2-登录，3-订单，4-签到，5-兑换，6-退款',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `order_id` bigint DEFAULT NULL COMMENT '关联订单ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_type` (`type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='积分记录表';

-- ----------------------------
-- 29. 签到记录表
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign`;
CREATE TABLE `tb_sign` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '签到ID，主键自增',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `sign_date` date NOT NULL COMMENT '签到日期',
  `points` int NOT NULL DEFAULT '0' COMMENT '获得积分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '签到时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_date` (`user_id`, `sign_date`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_sign_date` (`sign_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='签到记录表';

-- ----------------------------
-- 30. 版本管理表
-- ----------------------------
DROP TABLE IF EXISTS `tb_version`;
CREATE TABLE `tb_version` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本ID，主键自增',
  `version_code` varchar(20) NOT NULL COMMENT '版本号',
  `version_name` varchar(50) NOT NULL COMMENT '版本名称',
  `platform` varchar(20) NOT NULL DEFAULT 'ios' COMMENT '平台：ios, android, miniapp',
  `update_content` text COMMENT '更新内容',
  `download_url` varchar(500) DEFAULT NULL COMMENT '下载链接',
  `is_force` tinyint NOT NULL DEFAULT '0' COMMENT '是否强制更新：0-否，1-是',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_version_platform` (`version_code`, `platform`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='版本管理表';

-- ----------------------------
-- 初始化数据
-- ----------------------------

-- 插入管理员用户 (密码: admin123)
INSERT INTO `tb_user` (`username`, `password`, `real_name`, `phone`, `email`, `status`, `score`, `level`, `remark`) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', '13800138000', 'admin@notmaker.com', 1, 1000, 10, '超级管理员账号');

-- 插入角色
INSERT INTO `tb_role` (`role_name`, `role_code`, `description`, `status`, `sort_order`) VALUES
('超级管理员', 'SUPER_ADMIN', '系统超级管理员，拥有所有权限', 1, 1),
('普通管理员', 'ADMIN', '普通管理员，拥有部分管理权限', 1, 2),
('普通用户', 'USER', '普通用户，拥有基本操作权限', 1, 3);

-- 插入用户角色关联
INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES
(1, 1);

-- 插入权限
INSERT INTO `tb_permission` (`permission_name`, `permission_code`, `permission_type`, `parent_id`, `path`, `sort_order`, `status`) VALUES
('系统管理', 'system', 1, 0, '/system', 1, 1),
('用户管理', 'system:user', 1, 1, '/system/user', 1, 1),
('角色管理', 'system:role', 1, 1, '/system/role', 2, 1),
('权限管理', 'system:permission', 1, 1, '/system/permission', 3, 1),
('商品管理', 'product', 1, 0, '/product', 2, 1),
('商品列表', 'product:list', 1, 5, '/product/list', 1, 1),
('商品分类', 'product:category', 1, 5, '/product/category', 2, 1),
('订单管理', 'order', 1, 0, '/order', 3, 1),
('订单列表', 'order:list', 1, 8, '/order/list', 1, 1),
('会员管理', 'member', 1, 0, '/member', 4, 1),
('评论管理', 'member:comment', 1, 10, '/member/comment', 1, 1);

-- 插入角色权限关联
INSERT INTO `tb_role_permission` (`role_id`, `permission_id`)
SELECT 1, `id` FROM `tb_permission`;

-- 插入商品分类
INSERT INTO `tb_category` (`category_name`, `parent_id`, `level`, `icon`, `sort_order`, `description`, `status`) VALUES
('数码电子', 0, 1, 'icon-phone', 1, '数码电子产品分类', 1),
('手机通讯', 1, 2, 'icon-mobile', 1, '手机通讯设备', 1),
('电脑办公', 1, 2, 'icon-laptop', 2, '电脑及办公设备', 1),
('智能穿戴', 1, 2, 'icon-watch', 3, '智能穿戴设备', 1),
('服装鞋帽', 0, 1, 'icon-shirt', 2, '服装鞋帽分类', 1),
('男装', 5, 2, 'icon-male', 1, '男装', 1),
('女装', 5, 2, 'icon-female', 2, '女装', 1),
('鞋靴', 5, 2, 'icon-shoe', 3, '鞋靴', 1),
('家用电器', 0, 1, 'icon-electrical', 3, '家用电器分类', 1),
('生活用品', 0, 1, 'icon-daily', 4, '生活用品分类', 1);

-- 插入商品
INSERT INTO `tb_product` (`product_name`, `product_code`, `category_id`, `main_image`, `images`, `price`, `original_price`, `stock`, `sales_count`, `unit`, `weight`, `description`, `status`, `is_recommend`, `is_hot`, `audit_status`) VALUES
('iPhone 15 Pro Max 256GB', 'IPHONE15PM256', 2, 'https://img.yzcdn.cn/vant/ipad.jpeg', '["https://img.yzcdn.cn/vant/ipad.jpeg"]', 9999.00, 10999.00, 100, 50, '件', 221, 'iPhone 15 Pro Max 256GB 钛金属边框 A17 Pro芯片', 1, 1, 1, 1),
('MacBook Pro 14英寸 M3', 'MACBOOKPRO14M3', 3, 'https://img.yzcdn.cn/vant/ipad.jpeg', '["https://img.yzcdn.cn/vant/ipad.jpeg"]', 16999.00, 18999.00, 50, 20, '件', 1550, 'MacBook Pro 14英寸 M3 Pro芯片 18+512GB', 1, 1, 0, 1),
('AirPods Pro 第二代', 'AIRPODSPRO2', 2, 'https://img.yzcdn.cn/vant/ipad.jpeg', '["https://img.yzcdn.cn/vant/ipad.jpeg"]', 1899.00, 1999.00, 200, 100, '件', 51, 'AirPods Pro 第二代 主动降噪 自适应音频', 1, 0, 1, 1),
('Apple Watch Series 9', 'APPLEWATCHS9', 4, 'https://img.yzcdn.cn/vant/ipad.jpeg', '["https://img.yzcdn.cn/vant/ipad.jpeg"]', 3999.00, 4299.00, 150, 80, '件', 32, 'Apple Watch Series 9 45mm 蜂窝版', 1, 1, 0, 1),
('iPad Pro 12.9英寸', 'IPADPRO129', 3, 'https://img.yzcdn.cn/vant/ipad.jpeg', '["https://img.yzcdn.cn/vant/ipad.jpeg"]', 9299.00, 9999.00, 80, 30, '件', 682, 'iPad Pro 12.9英寸 M2芯片 256GB', 1, 0, 0, 1);

-- 插入轮播图
INSERT INTO `tb_banner` (`title`, `image_url`, `link_type`, `business_id`, `sort_order`, `position`, `status`) VALUES
('iPhone 15 Pro Max 新品上市', 'https://img.yzcdn.cn/vant/apple-1.jpg', 2, 1, 1, 1, 1),
('MacBook Pro M3 全新发布', 'https://img.yzcdn.cn/vant/apple-2.jpg', 2, 2, 2, 1, 1),
('AirPods Pro 年终特惠', 'https://img.yzcdn.cn/vant/apple-3.jpg', 2, 3, 3, 1, 1);

-- 插入通知公告
INSERT INTO `tb_notice` (`title`, `content`, `notice_type`, `sort_order`, `is_top`, `status`, `publish_time`) VALUES
('平台上线公告', '欢迎使用我们的电商平台，祝您购物愉快！', 1, 1, 1, 1, NOW()),
('双十一活动预告', '双十一购物狂欢节即将到来，敬请期待！', 2, 2, 0, 1, NOW()),
('物流延迟通知', '因双十一期间物流高峰，部分地区配送可能延迟，给您带来不便敬请谅解。', 3, 3, 0, 1, NOW());

-- 插入优惠券
INSERT INTO `tb_coupon` (`coupon_name`, `coupon_type`, `value`, `min_amount`, `max_discount`, `total_count`, `received_count`, `per_limit`, `start_time`, `end_time`, `status`) VALUES
('新人专享券', 1, 50.00, 200.00, 50.00, 1000, 100, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1),
('满减优惠券', 1, 100.00, 500.00, 100.00, 500, 50, 2, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), 1),
('无门槛折扣券', 3, 10.00, 0.00, NULL, 200, 20, 1, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1);

-- 插入系统配置
INSERT INTO `tb_config` (`config_key`, `config_value`, `config_name`, `config_type`, `description`, `status`) VALUES
('mall_name', '电商小程序', '商城名称', 'string', '商城名称设置', 1),
('mall_logo', 'https://img.yzcdn.cn/vant/logo.png', '商城Logo', 'string', '商城Logo URL', 1),
('freight_template', '99', '免运费门槛', 'number', '满X元免运费', 1),
('points_ratio', '1', '积分抵扣比例', 'number', '1积分抵1分钱', 1);

-- 插入数据字典
INSERT INTO `tb_dictionary` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `status`, `description`) VALUES
('gender', '未知', '0', 1, 1, '性别-未知'),
('gender', '男', '1', 2, 1, '性别-男'),
('gender', '女', '2', 3, 1, '性别-女'),
('order_status', '待付款', '0', 1, 1, '订单状态-待付款'),
('order_status', '待发货', '1', 2, 1, '订单状态-待发货'),
('order_status', '待收货', '2', 3, 1, '订单状态-待收货'),
('order_status', '已完成', '3', 4, 1, '订单状态-已完成'),
('order_status', '已取消', '4', 5, 1, '订单状态-已取消'),
('order_status', '已退款', '5', 6, 1, '订单状态-已退款'),
('pay_status', '未支付', '0', 1, 1, '支付状态-未支付'),
('pay_status', '已支付', '1', 2, 1, '支付状态-已支付'),
('pay_status', '已退款', '2', 3, 1, '支付状态-已退款');
