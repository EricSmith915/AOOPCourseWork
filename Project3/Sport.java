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
import java.util.Date;

public class Sport extends Event {

    String type = "Sport";

    //Constructor
    public Sport() {
    }

    public Sport(int id, String name, Date date, Date time, BigDecimal vipPrice, BigDecimal goldPrice, BigDecimal silverPrice, BigDecimal bronzePrice, BigDecimal generalPrice, Venue venue, BigDecimal cost) {
        super(id, name, date, time, vipPrice, goldPrice, silverPrice, bronzePrice, generalPrice, venue, cost);
        super.setType(type);
    }

    //Getters and Setters


    public String getType() {
        return type;
    }
}
