package com.suixingpay.repository;

import com.suixingpay.entity.Meeting;
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
}
