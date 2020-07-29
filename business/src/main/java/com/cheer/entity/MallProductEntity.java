package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 * 
 * @author cheer
 */
@Data
@TableName("mall_product")
public class MallProductEntity implements Serializable {

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
	 * 
	 */
	private Integer type;
	/**
	 * 主标题
	 */
	private String mainTitle;
	/**
	 * 简略标题
	 */
	private String shortTitle;
	/**
	 * 商户id
	 */
	private Long shopId;
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;
	/**
	 * 展示价格
	 */
	private BigDecimal displayPrice;
	/**
	 * 积分
	 */
	private Integer credit;
	/**
	 * 库存数量
	 */
	private Integer stock;
	/**
	 * 关键词，以逗号分开，最多3个，最多6个汉字
	 */
	private String keywords;
	/**
	 * 商品上架状态
	 */
	private Integer publishStatus;
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
