package com.kickr.memechat.user.controller;

import com.kickr.memechat.user.entity.MemeUser;
import com.kickr.memechat.user.service.MemeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeUserController {
    @Autowired
    private MemeUserService memeUserService;

    private static final Logger logger = LoggerFactory.getLogger(MemeUserController.class);

    @GetMapping(value = "/api/verifyuser/manager")
    public ResponseEntity<MemeUser> findManager(@RequestParam String userId,@RequestParam String password){
        try{
            if(userId.isEmpty() || password.isEmpty()){
                throw new NullPointerException("userId and password Incorrect");
            }
            MemeUser memeUser = memeUserService.findManager(userId,password);
            if(memeUser.equals(null)){
               throw new NullPointerException();

            }
            return new ResponseEntity<>(memeUser, HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
}
