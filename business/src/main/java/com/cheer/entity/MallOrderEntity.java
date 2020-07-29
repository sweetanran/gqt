package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单表
 * 
 * @author cheer
 */
@Data
@TableName("mall_order")
public class MallOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 编号
	 */
	private String number;
	/**
	 * 商品id
	 */
	private Long productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 类型，1是普通商品，2是电子商品
	 */
	private Integer type;
	/**
	 * 商品属性
	 */
	private String attrValue;
	/**
	 * 商品缩略图
	 */
	private String thumbnailUrl;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 商户id
	 */
	private Long shopId;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 积分
	 */
	private BigDecimal credit;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 数量
	 */
	private Integer quantity;
	/**
	 * 快递信息
	 */
	private String expressInfo;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 电子编号
	 */
	private String virNumber;
	/**
	 * 核销号
	 */
	private String hexiaoNumber;
	/**
	 * 
	 */
	private Long salesroomId;
	/**
	 * 
	 */
	private Integer isDeleted;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
