/*
Student: Eric Smith
Course: Advanced Object Oriented Programming - Fall 2021
Instructor: Daniel Mejia
Assignment: Project2
Date: 9/26/2021

I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance.
Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
 */

package Project2;

import java.util.Date;
import java.math.BigDecimal;

public class Event {

    //Variables

    private int id;
    private String name;
    private Date date;
    private Date time;
    private BigDecimal vipPrice;
    private BigDecimal goldPrice;
    private BigDecimal silverPrice;
    private BigDecimal bronzePrice;
    private BigDecimal generalPrice;
    private Venue venue;

    //Constructors

    public Event(){

    }

    public Event(int id, String name, Date date, Date time, BigDecimal vipPrice, BigDecimal goldPrice, BigDecimal silverPrice, BigDecimal bronzePrice, BigDecimal generalPrice, Venue venue) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.vipPrice = vipPrice;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        this.generalPrice = generalPrice;
        this.venue = venue;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(BigDecimal goldPrice) {
        this.goldPrice = goldPrice;
    }

    public BigDecimal getSilverPrice() {
        return silverPrice;
    }

    public void setSilverPrice(BigDecimal silverPrice) {
        this.silverPrice = silverPrice;
    }

    public BigDecimal getBronzePrice() {
        return bronzePrice;
    }

    public void setBronzePrice(BigDecimal bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    public BigDecimal getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(BigDecimal generalPrice) {
        this.generalPrice = generalPrice;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


    //Other Methods

    public void printSysAdmin(){
        int totalPercent = this.venue.getPctBronze() + this.venue.getPctSilver() + this.venue.getPctGold() +
                this.venue.getPctVIP() + this.venue.getPctGeneral();

        System.out.println("\n==============================================================================" +
                "\nEvent ID: " + this.id + "\n" + this.name + "\n" +this.date + "\n" +this.time + "\nEvent Capacity: " +
                this.venue.getCapacity() + "\nTotal Seats Sold: " + (this.venue.getCapacity() * totalPercent) + "\n" +
                "Total VIP seats sold: " + (this.venue.getCapacity() * this.venue.getPctVIP()) + "\nTotal Gold Seats Sold" +
                (this.venue.getCapacity() * this.venue.getPctGold()) + "\nTotal Silver Seats Sold" +
                (this.venue.getCapacity() * this.venue.getPctSilver()) + "\nTotal Bronze Seats Sold" +
                (this.venue.getCapacity() * this.venue.getPctBronze()) + "\nTotal General Seats Sold" +
                (this.venue.getCapacity() * this.venue.getPctGeneral()) + "\nTotal revenue for VIP tickets:" +
                new BigDecimal(((this.venue.getCapacity() * this.venue.getPctVIP()))) + this.vipPrice + "\nTotal revenue for Gold tickets:" +
                new BigDecimal(((this.venue.getCapacity() * this.venue.getPctGold()))) + this.goldPrice + "\nTotal revenue for Silver tickets:" +
                new BigDecimal(((this.venue.getCapacity() * this.venue.getPctSilver()))) + this.silverPrice +"\nTotal revenue for Bronze tickets:" +
                new BigDecimal(((this.venue.getCapacity() * this.venue.getPctBronze()))) + this.bronzePrice + "\nTotal revenue for General tickets:" +
                new BigDecimal(((this.venue.getCapacity() * this.venue.getPctGeneral()))) + this.generalPrice);

    }
}
