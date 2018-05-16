package model.user;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


@Data
public class Auction {

    private long id;
    private Date startDate;
    private Date endDate;
    private Double minimumPrice;

    private long bidOwner;
    private boolean isRunning;

    private long product;

    private long currentWinner;
    private long currentWinningBid;
    private long winner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public long getBidOwner() {
        return bidOwner;
    }

    public void setBidOwner(long bidOwner) {
        this.bidOwner = bidOwner;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    public long getCurrentWinner() {
        return currentWinner;
    }

    public void setCurrentWinner(long currentWinner) {
        this.currentWinner = currentWinner;
    }

    public long getCurrentWinningBid() {
        return currentWinningBid;
    }

    public void setCurrentWinningBid(long currentWinningBid) {
        this.currentWinningBid = currentWinningBid;
    }

    public long getWinner() {
        return winner;
    }

    public void setWinner(long winner) {
        this.winner = winner;
    }

    // optional
    public Product current_product;
}

