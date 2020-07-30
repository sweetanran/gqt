package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 * 
 * @author cheer
 */
@Data
@TableName("mall_product_image")
@Accessors(chain = true)
public class MallProductImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 商品id
	 */
	private Long productId;
	/**
	 * 图片地址
	 */
	private String imageUrl;
	/**
	 * 图片类型
	 */
	private Integer imageType;
	/**
	 * 排序
	 */
	private Long sort;
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
