/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 20:34
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 20:34
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Apply {
    /** 报名表id **/
    private Integer applyId;
    /** 用户id **/
    private String userId;
    /** 会议id **/
    private Integer meetingId;
    /** 是否签到 **/
    private Integer applyIssign;
    /** 报名时间 **/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    /** 鑫管家集合 **/
    private List<Users> usersList;
}
