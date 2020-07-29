package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类表
 * 
 * @author cheer
 */
@Data
@TableName("mall_category")
public class MallCategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 是否显示
	 */
	private Integer showStatus;
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
