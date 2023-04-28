package com.upgrad.booking.services;

import com.upgrad.booking.dao.BookingDAO;
import com.upgrad.booking.entity.BookingInfoEntity;
import com.upgrad.booking.exceptions.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    public BookingDAO _bookingDAO;
    public BookingServiceImpl() {
    }

    @Override
    public BookingInfoEntity saveBooking(BookingInfoEntity booking) {
        return (BookingInfoEntity) _bookingDAO.save(booking);
    }

    @Override
    public BookingInfoEntity getBooking(int id) {
        Optional<BookingInfoEntity> bookingObj = this._bookingDAO.findById(id);
        if(bookingObj.isPresent()){
            return bookingObj.get();
        }else{
            throw new PaymentException("Invalid Booking Id ", 400);
        }

    }

    @Override
    public List<BookingInfoEntity> getAllBookings() {
        return this._bookingDAO.findAll();
    }
}
