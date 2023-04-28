package com.upgrad.booking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(
        name = "booking"
)
public class BookingInfoEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "bookingId"
    )
    private int id;


    @Column(
            name = "fromDate",
            nullable = true
    )
    private String fromDate;


    @Column(
            name = "toDate",
            nullable = true
    )
    private String toDate;

    @Column(
            name = "aadharNumber",
            nullable = true
    )
    private String aadharNumber;

    @Column(
            name = "numOfRooms"
    )
    private int numOfRooms;
    @Column(
            name = "roomNumbers"
    )
    private String roomNumbers;
    @Column(
            name = "roomPrice",
            nullable = false
    )
    private int roomPrice;
    @Column(
            name = "transactionId"
    )
    private int transactionId = 0;
    @Column(
            name = "bookedOn",
            nullable = true
    )
    private String bookedOn;

    public BookingInfoEntity(String fromDate, String toDate, String aadharNumber, int numOfRooms) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
        this.roomNumbers = getRandomRoomNumbers(numOfRooms);
        this.roomPrice = getTotalRoomPrice(numOfRooms, fromDate, toDate);
        this.transactionId = 0;
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.bookedOn = dtf.format(localDateTime);
    }
    public BookingInfoEntity() {

    }
    private String getRandomRoomNumbers(int numOfRooms) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numbersList = new ArrayList<>();

        for (int i = 0; i < numOfRooms; i++) {
            numbersList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return String.join(",", numbersList);
    }

    private int getTotalRoomPrice(int numOfRooms, String fromDate, String toDate) {
        int perDayRate = 1000;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);
        Period period = Period.between(from, to);
        int numDays = period.getDays();

        return perDayRate * numDays * numOfRooms;
    }

    @Override
    public String toString() {
        return "BookingInfoEntity{" +
                "id=" + id +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", numOfRooms=" + numOfRooms +
                ", roomNumbers='" + roomNumbers + '\'' +
                ", roomPrice=" + roomPrice +
                ", transactionId=" + transactionId +
                ", bookedOn='" + bookedOn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingInfoEntity that = (BookingInfoEntity) o;
        return id == that.id && numOfRooms == that.numOfRooms && roomPrice == that.roomPrice && transactionId == that.transactionId && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate) && Objects.equals(aadharNumber, that.aadharNumber) && Objects.equals(roomNumbers, that.roomNumbers) && Objects.equals(bookedOn, that.bookedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, toDate, aadharNumber, numOfRooms, roomNumbers, roomPrice, transactionId, bookedOn);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(String bookedOn) {
        this.bookedOn = bookedOn;
    }
}
