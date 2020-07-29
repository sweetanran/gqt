DROP TABLE IF EXISTS `mall_category`;
CREATE TABLE `mall_category`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50)         NOT NULL COMMENT '分类名称',
    `show_status` TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
    `sort`        BIGINT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='分类表';

DROP TABLE IF EXISTS `mall_product`;
CREATE TABLE `mall_product`
(
    `id`             BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `number`         VARCHAR(50)         NOT NULL COMMENT '编号',
    `type`           INT(11) UNSIGNED    NOT NULL COMMENT '类型，1是普通商品，2是电子商品',
    `main_title`     VARCHAR(255)        NOT NULL COMMENT '主标题',
    `short_title`    VARCHAR(255)        NOT NULL COMMENT '简略标题',
    `shop_id`        BIGINT(11) UNSIGNED NOT NULL COMMENT '商户id',
    `market_price`   DECIMAL(18, 4) COMMENT '市场价格',
    `display_price`  DECIMAL(18, 4) COMMENT '展示价格',
    `credit`         INT(11) COMMENT '积分',
    `stock`          INT(11)             NOT NULL COMMENT '库存数量',
    `keywords`       VARCHAR(50) COMMENT '关键词，以逗号分开，最多3个，最多6个汉字',
    `publish_status` INT(3)              NOT NULL COMMENT '商品上架状态',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time`    DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_shop_id` (`shop_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

DROP TABLE IF EXISTS `mall_category_product_relation`;
CREATE TABLE `mall_category_product_relation`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `category_id` BIGINT(11) UNSIGNED NOT NULL COMMENT '分类id',
    `product_id`  BIGINT(11) UNSIGNED NOT NULL COMMENT '商品id',
    `sort`        BIGINT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='分类与商品关系表';

DROP TABLE IF EXISTS `mall_product_desc`;
CREATE TABLE `mall_product_desc`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id`  BIGINT(11) UNSIGNED NOT NULL COMMENT '商品id',
    `description` LONGTEXT            NOT NULL COMMENT '商品介绍',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (product_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品介绍表';

DROP TABLE IF EXISTS `mall_product_attr`;
CREATE TABLE `mall_product_attr`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id`  BIGINT(11) UNSIGNED NOT NULL COMMENT '商品id',
    `attr_value`  VARCHAR(200)        NOT NULL COMMENT '商品属性',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (product_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品属性表';

DROP TABLE IF EXISTS `mall_product_image`;
CREATE TABLE `mall_product_image`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id`  BIGINT(11) UNSIGNED NOT NULL COMMENT '商品id',
    `image_url`   VARCHAR(255)        NOT NULL COMMENT '图片地址',
    `image_type`  INT(3) UNSIGNED     NOT NULL COMMENT '图片类型',
    `sort`        BIGINT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (product_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品图片表';

DROP TABLE IF EXISTS `mall_shop`;
CREATE TABLE `mall_shop`
(
    `id`             BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `number`         VARCHAR(50)         NOT NULL COMMENT '编号',
    `name`           VARCHAR(50)         NOT NULL COMMENT '商户名称',
    `contact_person` VARCHAR(50)         NOT NULL COMMENT '联系人',
    `phone`          VARCHAR(50)         NOT NULL COMMENT '联系方式',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time`    DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='商户表';

DROP TABLE IF EXISTS `mall_image`;
CREATE TABLE `mall_image`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `image_url`   VARCHAR(255)        NOT NULL COMMENT '图片地址',
    `image_type`  INT(3) UNSIGNED     NOT NULL COMMENT '图片类型',
    `show_status` TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
    `product_id`  BIGINT(11) UNSIGNED COMMENT '商品id',
    `width`       DOUBLE(6, 2) COMMENT '图片宽度',
    `height`      DOUBLE(6, 2) COMMENT '图片高度',
    `sort`        BIGINT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='图片表';

DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order`
(
    `id`            BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `number`        VARCHAR(50)         NOT NULL COMMENT '编号',
    `product_id`    BIGINT(11)          NOT NULL COMMENT '商品id',
    `product_name`  VARCHAR(255)        NOT NULL COMMENT '商品名称',
    `type`          INT(11) UNSIGNED    NOT NULL COMMENT '类型，1是普通商品，2是电子商品',
    `attr_value`    VARCHAR(200)        NOT NULL COMMENT '商品属性',
    `thumbnail_url` VARCHAR(255) COMMENT '商品缩略图',
    `user_id`       INT(25)             NOT NULL COMMENT '用户id',
    `receiver`      VARCHAR(20)         NOT NULL COMMENT '收货人',
    `phone`         VARCHAR(20)         NOT NULL COMMENT '联系电话',
    `address`       VARCHAR(255) COMMENT '收货地址',
    `shop_id`       BIGINT(11) UNSIGNED NOT NULL COMMENT '商户id',
    `price`         DECIMAL(18, 4) COMMENT '价格',
    `credit`        DECIMAL(18, 4) COMMENT '积分',
    `remark`        VARCHAR(255) COMMENT '备注',
    `quantity`      INT(11)             NOT NULL COMMENT '数量',
    `express_info`  VARCHAR(255) COMMENT '快递信息',
    `status`        INT(3)              NOT NULL COMMENT '订单状态',
    `vir_number`    VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '电子编号',
    `hexiao_number` VARCHAR(50)         COMMENT '核销号',
    `salesroom_id`  BIGINT(11)          NOT NULL DEFAULT 0,
    `is_deleted`    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`   TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_shop_id` (`shop_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

DROP TABLE IF EXISTS `mall_complaint`;
CREATE TABLE `mall_complaint`
(
    `id`           BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`      INT(25)             NOT NULL COMMENT '微信openid',
    `order_number` VARCHAR(50) COMMENT '订单号',
    `phone`        VARCHAR(20)         NOT NULL COMMENT '联系方式',
    `content`      VARCHAR(255)        NOT NULL COMMENT '投诉内容',
    `status`       INT(3)              NOT NULL DEFAULT 1 COMMENT '处理状态',
    `reply`        VARCHAR(255) COMMENT '答复内容',
    `remark`       VARCHAR(255) COMMENT '备注',
    `is_deleted`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='投诉表';

DROP TABLE IF EXISTS `mall_address`;
CREATE TABLE `mall_address`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(11) UNSIGNED NOT NULL COMMENT '购买人id',
    `receiver`    VARCHAR(20)         NOT NULL COMMENT '收货人',
    `phone`       VARCHAR(20)         NOT NULL COMMENT '联系电话',
    `province`    VARCHAR(20) COMMENT '省',
    `city`        VARCHAR(20) COMMENT '市',
    `region`      VARCHAR(20) COMMENT '区/县',
    `address`     VARCHAR(255) COMMENT '详细地址',
    `area_code`   VARCHAR(255) COMMENT '编码',
    `is_default`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='收货地址表';

DROP TABLE IF EXISTS `mall_admin`;
CREATE TABLE `mall_admin`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(50)         NOT NULL COMMENT '用户名',
    `password`    VARCHAR(255)        NOT NULL COMMENT '密码',
    `role`        INT(3)              NOT NULL COMMENT '角色',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='管理员表';

DROP TABLE IF EXISTS `mall_credit_record`;
CREATE TABLE `mall_credit_record`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(11) UNSIGNED NOT NULL COMMENT '用户id',
    `name`        VARCHAR(20)         NOT NULL COMMENT '操作',
    `credit`      INT(11)             NOT NULL COMMENT '赢取或消耗的积分',
    `date`        VARCHAR(20)         NOT NULL COMMENT '日期',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='积分记录表';

DROP TABLE IF EXISTS `mall_salesroom`;
CREATE TABLE `mall_salesroom`
(
    `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`     BIGINT(11) UNSIGNED NOT NULL COMMENT '商户id',
    `name`        VARCHAR(50)         NOT NULL COMMENT '门店名称',
    `word`        VARCHAR(20)         NOT NULL COMMENT '口令',
    `is_deleted`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='门店表';