package com.upgrad.booking.dao;

import com.upgrad.booking.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDAO extends JpaRepository<BookingInfoEntity, Integer> {
}
