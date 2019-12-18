package com.suixingpay.repository;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BackgroundRepository {

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    List<Meeting> backgroundSelectAll();

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */

    List<Meeting> backgroundSelectById1(Integer meetingId);

    List<Apply> backgroundSelectById2(Integer meetingId);

    List<Sign> backgroundSelectById3(Integer meetingId);

    /*
     * 张佳鑫
     * 后台管理审核会议
     */

    Integer backgroundUpdateStatus0(Integer meetingId);

    Integer backgroundUpdateStatus1(Integer meetingId);

    Integer backgroundUpdateStatus2(Integer meetingId);

}
