/*
Student: Eric Smith
Course: Advanced Object Oriented Programming - Fall 2021
Instructor: Daniel Mejia
Assignment: Project2
Date: 9/26/2021

I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance.
Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
 */

package Project3;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {

    //Variables

    private int id;
    private String name;
    private Date date;
    private Date time;
    private BigDecimal vipPrice;
    private int vipSold = 0;
    private BigDecimal goldPrice;
    private int goldSold = 0;
    private BigDecimal silverPrice;
    private int silverSold = 0;
    private BigDecimal bronzePrice;
    private int bronzeSold = 0;
    private BigDecimal generalPrice;
    private int generalSold = 0;
    private BigDecimal cost;
    private Venue venue;
    private String type;

    //Constructors

    public Event(){

    }

    public Event(int id, String name, Date date, Date time, BigDecimal vipPrice, BigDecimal goldPrice, BigDecimal silverPrice, BigDecimal bronzePrice, BigDecimal generalPrice, Venue venue,
    BigDecimal cost) {
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
        this.cost = cost;
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

    public int getVipSold() {
        return vipSold;
    }

    public int getGoldSold() {
        return goldSold;
    }

    public int getSilverSold() {
        return silverSold;
    }

    public int getBronzeSold() {
        return bronzeSold;
    }

    public int getGeneralSold() {
        return generalSold;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    //Other Methods

    /**
     * Method that prints out an event in administrator view
     */
    public void printSysAdmin(){

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm aa");

        BigDecimal vipRevenue = (vipPrice.multiply(new BigDecimal(vipSold)));
        BigDecimal goldRevenue = (goldPrice.multiply(new BigDecimal(goldSold)));
        BigDecimal silverRevenue =  (silverPrice.multiply(new BigDecimal(silverSold)));
        BigDecimal bronzeRevenue = (bronzePrice.multiply(new BigDecimal(bronzeSold)));
        BigDecimal generalRevenue =  (generalPrice.multiply(new BigDecimal(generalSold)));

        System.out.println("\n==============================================================================" +
                "\nEvent ID: " + this.id + "\n" + this.name + "\n" + format.format(this.date) + "\n" + hourFormat.format(this.time) + "\nEvent Capacity: " +
                this.venue.getCapacity() + "\nTotal Seats Sold: " + (vipSold + goldSold + silverSold + bronzeSold + generalSold) + "\n" +
                "Total VIP seats sold: " + vipSold + "\nTotal Gold Seats Sold: " +
                goldSold + "\nTotal Silver Seats Sold: " +
                silverSold + "\nTotal Bronze Seats Sold: " +
                bronzeSold + "\nTotal General Seats Sold: " +
                generalSold + "\nTotal revenue for VIP tickets: $" +
                vipRevenue + "\nTotal revenue for Gold tickets: $" +
                goldRevenue + "\nTotal revenue for Silver tickets: $" +
                silverRevenue +"\nTotal revenue for Bronze tickets: $" +
                bronzeRevenue+ "\nTotal revenue for General tickets: $" +
                generalRevenue + "\nTotal revenue for all events: $" +
                vipRevenue.add(goldRevenue.add(silverRevenue.add(bronzeRevenue.add(generalRevenue)))) + "\nExpected Profit(Sell Out): $" +
                cost.subtract(vipRevenue.add(goldRevenue.add(silverRevenue.add(bronzeRevenue.add(generalRevenue))))) + "\nActual Profit: $" +
                cost.subtract(vipRevenue.add(goldRevenue.add(silverRevenue.add(bronzeRevenue.add(generalRevenue))))) +
                "\n==============================================================================\n");

    }


    public void sellVip(){
        vipSold++;
    }
    public void sellGold() {
        goldSold++;
    }
    public void sellSilver() {
        silverSold++;
    }
    public void sellBronze() {
        bronzeSold++;
    }
    public void sellGeneral() {
        generalSold++;
    }

    public void calculatePCT(){
        this.venue.setPctReserved((vipSold + goldSold + silverSold + bronzeSold + generalSold) / this.venue.getCapacity());
    }

}
