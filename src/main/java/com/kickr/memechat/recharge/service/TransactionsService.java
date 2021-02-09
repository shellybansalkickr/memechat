package com.kickr.memechat.recharge.service;

import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.repository.RechargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    private RechargeRepo rechargeRepo;

    public List<RechargeParams> findByDate(java.util.Date startDate, Date endDate) {
        List<RechargeParams> trans = (List<RechargeParams>)rechargeRepo.findByDate(startDate,endDate);
        return trans;
    }
}
