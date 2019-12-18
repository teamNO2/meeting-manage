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
    public Meeting selectById(String meetingId);

    //新建鑫管家发起的会议
    public int insert(Meeting meeting);
}
