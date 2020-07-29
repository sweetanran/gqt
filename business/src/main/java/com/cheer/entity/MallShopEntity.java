package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户表
 * 
 * @author cheer
 */
@Data
@TableName("mall_shop")
public class MallShopEntity implements Serializable {

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
	 * 商户名称
	 */
	private String name;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系方式
	 */
	private String phone;
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
