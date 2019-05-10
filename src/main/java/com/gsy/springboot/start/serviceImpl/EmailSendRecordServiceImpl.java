package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.mapper.auto.TEmailSendMapper;
import com.gsy.springboot.start.pojo.TEmailSend;
import com.gsy.springboot.start.service.EmailSendRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created By Gsy on 2019/5/5
 */
@Service
public class EmailSendRecordServiceImpl implements EmailSendRecordService {
    @Autowired
    TEmailSendMapper tEmailSendMapper;
    @Override
    public void recordEmailSend(TEmailSend tEmailSend) {
        tEmailSendMapper.insertSelective(tEmailSend);
    }
}
