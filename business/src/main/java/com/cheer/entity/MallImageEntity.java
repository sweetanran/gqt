package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片表
 * 
 * @author cheer
 */
@Data
@TableName("mall_image")
public class MallImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 图片地址
	 */
	private String imageUrl;
	/**
	 * 图片类型
	 */
	private Integer imageType;
	/**
	 * 是否显示
	 */
	private Integer showStatus;
	/**
	 * 商品id
	 */
	private Long productId;
	/**
	 * 图片宽度
	 */
	private Double width;
	/**
	 * 图片高度
	 */
	private Double height;
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
