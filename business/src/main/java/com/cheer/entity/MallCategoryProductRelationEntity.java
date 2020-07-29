package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类与商品关系表
 * 
 * @author cheer
 */
@Data
@TableName("mall_category_product_relation")
public class MallCategoryProductRelationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 商品id
	 */
	private Long productId;
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
