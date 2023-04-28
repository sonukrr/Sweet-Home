package com.upgrad.booking.services;

import com.upgrad.booking.entity.BookingInfoEntity;

import java.util.List;

public interface BookingService {
    BookingInfoEntity saveBooking(BookingInfoEntity booking);

    BookingInfoEntity getBooking(int id);

    List<BookingInfoEntity> getAllBookings();
}
