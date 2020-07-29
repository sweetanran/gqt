package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店表
 * 
 * @author cheer
 */
@Data
@TableName("mall_salesroom")
public class MallSalesroomEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 商户id
	 */
	private Long shopId;
	/**
	 * 门店名称
	 */
	private String name;
	/**
	 * 口令
	 */
	private String word;
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
