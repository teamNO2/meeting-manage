package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;
import com.suixingpay.entity.Sign;
import com.suixingpay.repository.BackgroundRepository;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.repository.UsersRepository;
import com.suixingpay.service.BackgroundService;
import io.swagger.models.auth.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    @Override
    public List<Meeting> backgroundSelectAll() {
        return backgroundRepository.backgroundSelectAll();
    }
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    /**
     * @Description: 创建会议
     * @Param: [meeting]
     * @return: int
     * @Author: lichanghao
     * @Date: 2019/12/18
     */
    @Override
    public int createMeeting(Meeting meeting) {
        return meetingRepository.insertSelective(meeting);
    }
    //根据name查询

    @Override
    public Users selectByName(String userName) {
        return usersRepository.selectByName(userName);
    }

    @Override
    public Users selectByTwo(String userName, String referralCode) {
        return usersRepository.selectByTwo(userName,referralCode);
    }

    //根据ID查询
    @Override
    public Meeting selectById(String meetingId) {
        return meetingRepository.selectById(meetingId);
    }
    //模糊查询会议
    @Override
    public List<Meeting> likeMeeting(List<Meeting> meetings, List<Users> users) {
        if (meetings==null||meetings.isEmpty()||users==null||users.isEmpty()){
            return new ArrayList<>();
        }else {
            List<Meeting> meetingList = new ArrayList<>();
            for (Meeting meetings1 : meetings) {
                if (meetings1.getUserId() == -1) {
                    List<Users> usersList = new ArrayList<>();
                    for (Users users1 : users) {
                        usersList.add(users1);
                    }
                    meetings1.setUsersList(usersList);
                    meetingList.add(meetings1);
                } else {
                    for (Users users1 : users) {
                        System.out.println(meetings1.getUserId());
                        if (users1.getId() == meetings1.getUserId()) {
                            System.out.println(users1.getId());
                            List<Users> usersList = new ArrayList<>();
                            usersList.add(users1);
                            meetings1.setUsersList(usersList);
                            meetingList.add(meetings1);
                            break;
                        }
                    }
                }
            }
            return meetingList;
        }
    }
    //模糊查询鑫管家
    @Override
    public List<Users> findPageWithResultLike(Users users) {
        return usersRepository.findPageWithResultLike(users);
    }

    @Override
    public List<Users> selectAll() {
        return usersRepository.selectAll();
    }

    //模糊查询
    @Override
    public List<Meeting> findPageWithResultLike(Meeting meeting) {
        return meetingRepository.findMeetingWithLike(meeting);
    }
    //根据日期查询
    @Override
    public List<Meeting> limitDate(Date beginDate,Date endDate) {
        return meetingRepository.limitDate(beginDate, endDate);
    }

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */

    @Override
    public Meeting backgroundSelectById1(Integer meetingId) {
        return backgroundRepository.backgroundSelectById1(meetingId);
    }

    @Override
    public List<Apply> backgroundSelectById2(Integer meetingId) {
        return backgroundRepository.backgroundSelectById2(meetingId);
    }

    @Override
    public List<Sign> backgroundSelectById3(Integer meetingId) {
        return backgroundRepository.backgroundSelectById3(meetingId);
    }

    /*
     * 张佳鑫
     * 后台管理审核会议
     */

    @Override
    public Integer backgroundUpdateStatus(Integer meetingId, Integer check) {
        if(check==0){
            //System.out.println(backgroundRepository.backgroundUpdateStatus0(meetingId));
            backgroundRepository.backgroundUpdateStatus3(meetingId);
            return backgroundRepository.backgroundUpdateStatus0(meetingId);
        }else if(check==1){
            backgroundRepository.backgroundUpdateStatus3(meetingId);
            //System.out.println(backgroundRepository.backgroundUpdateStatus1(meetingId));
            return backgroundRepository.backgroundUpdateStatus1(meetingId);
        }else{
            backgroundRepository.backgroundUpdateStatus4(meetingId);
            //System.out.println(backgroundRepository.backgroundUpdateStatus2(meetingId));
            return backgroundRepository.backgroundUpdateStatus2(meetingId);
        }
    }
}
