package com.cheer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 * @author cheer
 */
@Data
@TableName("oa_user")
public class OaUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 
	 */
	private String nickname;
	/**
	 * 
	 */
	private String headimgurl;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 
	 */
	private String type;
	/**
	 * 政治面貌
	 */
	private String politicsStatus;
	/**
	 * 出生日期
	 */
	private String birthTime;
	/**
	 * 
	 */
	private String openid;
	/**
	 * 
	 */
	private String path;
	/**
	 * 籍贯
	 */
	private String nativePlace;
	/**
	 * 积分
	 */
	private Integer integral;
	/**
	 * 
	 */
	private Integer useIntegral;
	/**
	 * 职业
	 */
	private String profession;
	/**
	 * 
	 */
	private String department;
	/**
	 * 
	 */
	private String addTime;
	/**
	 * 
	 */
	private String updataTime;

}
