/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:47
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.repository;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    public Meeting selectById(String meetingId);

    //新建鑫管家发起的会议
    public int insert(Meeting meeting);

    //插入会议
    int insertSelective(Meeting meeting);

    //模糊查询
    List<Meeting> findMeetingWithLike(Meeting meeting);
    //根据日期查询


    //查询已经报名的 石梦瑶 0 1
    List<Meeting> selectisapply(String userId);


    //查询父辈的userID
    String selectRootId(String userId);

    //查询所有会议详细信息
    Meeting selectByIddesc(String meeting);


    //用userID查询meetingID
    List<Meeting> selectbyuserId(String userId);

    List<Meeting> selectMeetings(String rootId,String userId);
    List<Meeting> selectMeetings2(String userId);
    List<Meeting> selectMeetings3(String userId,String date);
    List<Meeting> selectMeetings4(String userId,String date);

    List<Meeting> limitDate(@Param("beginDate") Date beginDate,@Param("endDate") Date endDate);

    //根据会议id修改会议
    int updateByPrimaryKey(Meeting meeting);

    int updatemeetingStatus(int meetingId);
    //myUpdateMeetingById
    int myUpdateMeetingById(Meeting meeting);

    /**
     * 柴宇航
     * 查询出会议开始时间和会议时长
     */
    Meeting selectstartTimeandtimeLong(int meetingId);

    /**
     * 柴宇航
     * 查询出谁创建的会议
     */
    Meeting selectuserId(int meetingId);

    //查看报名人数
    int selectApplyNumberByMeetingId(int meetingId);

    //查看会议报名信息
    List<Apply> selectApplyByMeetingId(int meetingId);

    //查看会议签到信息
    List<Sign> selectSignByMeetingId(int meetingId);
}
