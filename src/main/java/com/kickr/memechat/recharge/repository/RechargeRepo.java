package com.kickr.memechat.recharge.repository;

import com.kickr.memechat.recharge.entity.RechargeParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface RechargeRepo extends JpaRepository<RechargeParams,String> {
    @Query("SELECT u FROM RechargeParams u WHERE u.txnTime>= ?1 and u.txnTime< ?2")
    public List<RechargeParams> findByDate(Date startDate, Date endDate);

    @Modifying
    @Transactional
    @Query("UPDATE RechargeParams u SET u.status = ?2 , u.payUMoneyString = ?3 ,u.txnTime = ?4 WHERE u.txnId = ?1")
    public void setStatusByPayU(String txnid, String transactionStatus, String payUString,  Date timestamp);
}
