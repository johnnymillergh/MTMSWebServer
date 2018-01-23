package entity;

import java.sql.Timestamp;

public class CustomerOrderEntity {
    private int id;
    private int userId;
    private Timestamp orderDatetime;
    private int movieScheduleId;
    private boolean isPaid;
    private boolean isUsed;
    private int ticketAmount;
    private float totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Timestamp orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public int getMovieScheduleId() {
        return movieScheduleId;
    }

    public void setMovieScheduleId(int movieScheduleId) {
        this.movieScheduleId = movieScheduleId;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CustomerOrderEntity: " + id + ", " + userId + ", " + orderDatetime + ", " + movieScheduleId;
    }
}
