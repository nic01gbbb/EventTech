package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.domain.coupon.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {




}
