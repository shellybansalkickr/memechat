package com.kickr.memechat.user.repo;

import com.kickr.memechat.user.entity.MemeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeUserRepo extends JpaRepository<MemeUser,Long> {
    @Query("SELECT u FROM MemeUser  u WHERE  u.emailId= ?1 and  u.password= ?2 and  u.userType= ?3")
    public MemeUser findByManager(String emailId,String password, MemeUser.UserCategory userCategory);
}
