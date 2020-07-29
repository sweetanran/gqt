package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分记录表
 * 
 * @author cheer
 */
@Data
@TableName("mall_credit_record")
public class MallCreditRecordEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 操作
	 */
	private String name;
	/**
	 * 赢取或消耗的积分
	 */
	private Integer credit;
	/**
	 * 日期
	 */
	private String date;
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
