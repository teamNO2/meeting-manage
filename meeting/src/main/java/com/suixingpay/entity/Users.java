/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:50
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description:
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:50
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Users {
    /**  **/
    private Integer id;
    /** 用户编号 **/
    private String userId;
    /** 用户姓名 **/
    private String userName;
    /** 手机号 **/
    private String telephone;
    /** 根用户编号 **/
    private String rootUserId;
    /** 上级用户编号 **/
    private String pUserId;
    /** 推荐码 **/
    private String referralCode;
    /** 用户等级(1~9) **/
    private String levelNo;
    /** 落地省 **/
    private String userProvince;
    /** 落地市 **/
    private String userCity;
    /** 创建时间 **/
    private Date createDate;
    /** 修改时间 **/
    private Date updateDate;
    /** 状态（0:正常，1删除） **/
    private String status;
    /**  **/
    private String USER;
    /**  **/
    private Long CURRENTCONNECTIONS;
    /**  **/
    private Long TOTALCONNECTIONS;
}
