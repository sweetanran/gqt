package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收货地址表
 * 
 * @author cheer
 */
@Data
@TableName("mall_address")
public class MallAddressEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 购买人id
	 */
	private Long userId;
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区/县
	 */
	private String region;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 编码
	 */
	private String areaCode;
	/**
	 * 
	 */
	private Integer isDefault;
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
