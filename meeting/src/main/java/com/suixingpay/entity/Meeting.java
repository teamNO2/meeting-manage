/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 17:36
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
import java.util.List;

/**
 * @description:
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 17:36
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Meeting {
    /**  **/
    private Integer meetingId;
    /** 会议类型（0培训会、1招商会....） **/
    private Integer meetingType;
    /** 会议名称 **/
    private String meetingName;
    /** 主办方 **/
    private String meetingCreate;
    /** 是否收费（0:不收费，1：收费） **/
    private Integer meetingSalary;
    /** 会议日期 **/
    private Date meetingDate;
    /** 开始时间 **/
    private Date meetingStarttime;
    /** 会议时长（小时为单位） **/
    private Double meetingTimelong;
    /** 报名截止时间 **/
    private Date meetingEndtime;
    /** 会议地点（省市县） **/
    private String meetingAddress;
    /** 详细地址（精确的地址） **/
    private String meetingAlladdress;
    /** 会议描述 **/
    private String meetingDescribe;
    /** 审核状态（0待审核，1驳回，2通过） **/
    private Integer meetingCheckstatus;
    /** 会议状态(0待发布，1报名中，2报名截止，3已完成) **/
    private Integer meetingStatus;
    /** 鑫管家Id，通过这个字段关联到发起管家姓名以及所属分公司 和管家推荐码三个字段 **/
    private Integer userId;
    /** 报名人数 **/
    private Integer meetingApplypersonnumber;
    /** 签到人数 **/
    private Integer meetingSignpersonnumber;
    /** 发起类型（0盟友发起、1公司发起） **/
    private Integer startType;
    /** 用户集合 **/
    private List<Users> usersList;
}
