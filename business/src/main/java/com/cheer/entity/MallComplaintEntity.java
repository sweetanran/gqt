package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 投诉表
 * 
 * @author cheer
 */
@Data
@TableName("mall_complaint")
public class MallComplaintEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 微信openid
	 */
	private Integer userId;
	/**
	 * 订单号
	 */
	private String orderNumber;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 投诉内容
	 */
	private String content;
	/**
	 * 处理状态
	 */
	private Integer status;
	/**
	 * 答复内容
	 */
	private String reply;
	/**
	 * 备注
	 */
	private String remark;
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
