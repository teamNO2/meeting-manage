/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:47
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.repository;

import com.suixingpay.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:47
 * @version: V1.0
 */
@Mapper
@Repository
public interface MeetingRepository {
    //按meeting-id查询
    Meeting selectById(String meetingId);
    //插入会议
    int insertSelective(Meeting meeting);
    //模糊查询
    List<Meeting> findMeetingWithLike(Meeting meeting);
    //根据日期查询
    List<Meeting> limitDate(Date beginDate,Date endDate);
}
