package com.kickr.memechat.user.service;

import com.kickr.memechat.user.entity.MemeUser;
import com.kickr.memechat.user.repo.MemeUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeUserService {
    @Autowired
    private  MemeUserRepo memeUserRepo;

    public MemeUser findManager(String emailId,String password) throws Exception{

        MemeUser memeUser = memeUserRepo.findByManager(emailId,password, MemeUser.UserCategory.MANAGER);
        return memeUser;

    }
}
