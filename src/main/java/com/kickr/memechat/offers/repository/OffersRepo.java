package com.kickr.memechat.offers.repository;

import com.kickr.memechat.offers.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OffersRepo extends JpaRepository<Offers,Long> {
}
