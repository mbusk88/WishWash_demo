package com.example.wishwash_demo;

import java.util.Date;

public class Booking {
    private Date date;
    private Date timeInterval;

    Booking(Date date, Date timeInterval) {
        this.date = date;
        this.timeInterval = timeInterval;
    }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Date getTimeInterval() { return timeInterval; }
    public void setTimeInterval(Date timeInterval) { this.timeInterval = timeInterval; }
}
