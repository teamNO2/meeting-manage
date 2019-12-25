package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.repository.SignRepository;
import com.suixingpay.repository.UsersRepository;
import com.suixingpay.service.MeetingHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MeetingHomeServiceImpl
 * @Description TODO
 * @Author ShiMengyao
 * @Date 2019 年 12 月 19 日 0019 11:19:45
 * @Version 1.0
 */
@Service
public class MeetingHomeServiceImpl implements MeetingHomeService {
    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SignRepository signRepository;

    @Override
    public List<Meeting> selectisapply(String userId) {
        return meetingRepository.selectisapply(userId);
    }

    //更改状态
    @Override
    public int updatemeetingStatus(int meetingId) {

        return meetingRepository.updatemeetingStatus(meetingId);
    }

    //查询未报名的
    @Override
    public List<List<Meeting>> selectmeetings(String userId) {
        String a = userId;
        List<String > list = new ArrayList<>();
        List<List<Meeting>> meetingList = new ArrayList<>();
        list.add(meetingRepository.selectRootId(userId));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0 ;i < usersRepository.countUser()+signRepository.countSign(); i++ ){
            userId = meetingRepository.selectRootId(userId);
            list.add(meetingRepository.selectRootId(userId));
            //System.out.println(list);
            if((list.get(i)!=null)&&(list.get(i)!="")) {
                meetingList.add(meetingRepository.selectMeetings(list.get(i), a));
                meetingList.add(meetingRepository.selectMeetings2(a));
                meetingList.add(meetingRepository.selectMeetings3(list.get(i),sdf.format(date)));
                meetingList.add(meetingRepository.selectMeetings4(list.get(i),sdf.format(date)));
            }
        }
        for (int i = 0 ;i<meetingList.size();i++){
            if(meetingList.get(i).isEmpty()){
                meetingList.remove(i);
                i--;
            }
        }



        //System.out.println(meetingList);
        return meetingList;
    }

    @Override
    public Meeting selectByIddesc(String meetingId) {
        return meetingRepository.selectByIddesc(meetingId);
    }

    @Override
    public List<Meeting> selectbyuserId(String userId) {
        return meetingRepository.selectbyuserId(userId);
    }


}
