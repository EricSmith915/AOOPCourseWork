package Project1;

import java.math.BigDecimal;
import java.util.Date;


public class Sport {
    private int id;
    private String type;
    private String name;
    private String date;
    private String time;
    private BigDecimal vipPrice;
    private BigDecimal goldPrice;
    private BigDecimal silverPrice;
    private BigDecimal bronzePrice;
    private BigDecimal generalPrice;

    //Constructor

    public Sport(int id, String type, String name, String date, String time, BigDecimal vipPrice, BigDecimal goldPrice, BigDecimal silverPrice, BigDecimal bronzePrice, BigDecimal generalPrice) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.vipPrice = vipPrice;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        this.generalPrice = generalPrice;
    }


    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public void print(){
        System.out.println("ID: " + id + ", Type: " + type + ", Date :" + date + ", Time: " + time + ", Vip Price: " + vipPrice + ", Gold Price: " + goldPrice
                + ", Silver Price: " + silverPrice + ", Bronze Price: " + bronzePrice + ", General Admission Price: " + generalPrice);
    }

    public static void main(String[] args){

    }
}