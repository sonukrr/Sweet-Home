package com.upgrad.booking.controller;

import com.upgrad.booking.dto.BookingInfoDTO;
import com.upgrad.booking.dto.PaymentDTO;
import com.upgrad.booking.entity.BookingInfoEntity;
import com.upgrad.booking.exceptions.PaymentException;
import com.upgrad.booking.services.BookingService;
import com.upgrad.booking.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/"})
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionService transactionService;

    public BookingController() {
    }

    @PostMapping(
            value = {"booking"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ResponseEntity newBooking(@RequestBody BookingInfoDTO inputDTO){
        BookingInfoEntity bookingInfo = new BookingInfoEntity(inputDTO.getFromDate(), inputDTO.getToDate(), inputDTO.getAadharNumber(), inputDTO.getNumOfRooms());
        BookingInfoEntity savedBooking = this.bookingService.saveBooking(bookingInfo);
        BookingInfoDTO savedBookingDTO = this.modelMapper.map(savedBooking, BookingInfoDTO.class);
        return new ResponseEntity(savedBookingDTO, HttpStatus.OK);
    }

    @PostMapping(
            value = {"booking/{bookingId}/transaction"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ResponseEntity completeTransaction(@RequestBody PaymentDTO paymentDTO, @PathVariable(name = "bookingId") int bookingId){
        // check if payment mode is either card or upi
        if(!paymentDTO.getPaymentMode().equalsIgnoreCase("card") && !paymentDTO.getPaymentMode().equalsIgnoreCase("upi")){
            throw new PaymentException("Invalid mode of payment", 400);
        }else{
            // get the booking details by id
            BookingInfoEntity bookingInfoEntity = this.bookingService.getBooking(bookingId);;
            if(bookingInfoEntity == null){
                throw new PaymentException("Invalid Booking Id", 400);
            }else{
                // update fecthed booking with transaction number and save back to Db
                int transactionNumber = this.transactionService.getTransactionNumber(paymentDTO);
                bookingInfoEntity.setTransactionId(transactionNumber);
                BookingInfoEntity updatedBookingInfo = this.bookingService.saveBooking(bookingInfoEntity);
                String message = "Booking is confirmed for user with aadhar number: " + bookingInfoEntity.getAadharNumber() + " |  Here are the booking details: " + bookingInfoEntity.toString();
                BookingInfoDTO bookingInfoDTO = this.modelMapper.map(updatedBookingInfo, BookingInfoDTO.class);
                return new ResponseEntity(bookingInfoDTO, HttpStatus.OK);
            }
        }
    }

    @GetMapping(
            value = {"bookings"},
            produces = {"application/json"}
    )

    public ResponseEntity fetchAllBookings(){
        List<BookingInfoEntity> allBookings = this.bookingService.getAllBookings();
        return new ResponseEntity(allBookings, HttpStatus.OK);
    }

    @GetMapping(
            value = {"booking/{id}"},
            produces = {"application/json"}
    )
    public ResponseEntity fetchBookingInfoById(@PathVariable(name = "id") int id){
        BookingInfoEntity bookingInfo = this.bookingService.getBooking(id);
        BookingInfoDTO bookingInfoDTO = (BookingInfoDTO) this.modelMapper.map(bookingInfo, BookingInfoDTO.class);
        return new ResponseEntity(bookingInfoDTO, HttpStatus.OK);
    }


}
