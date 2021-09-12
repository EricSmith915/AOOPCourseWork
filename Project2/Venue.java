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

public class Venue {
    private String name;
    private int pctUnavailableSeat;
    private int pctVIP;
    private int pctGold;
    private int pctSilver;
    private int pctBronze;
    private int pctGeneral;
    private int pctReserved;
    private boolean fireworks;
    private int fireworksCost;
    private int capacity;
    private int cost;

    //Constructor

    public Venue(){

    }

    public Venue(String name, int pctUnavailableSeat, int pctVIP, int pctGold, int pctSilver, int pctBronze, int pctGeneral, int pctReserved, int capacity, int cost) {
        this.name = name;
        this.pctUnavailableSeat = pctUnavailableSeat;
        this.pctVIP = pctVIP;
        this.pctGold = pctGold;
        this.pctSilver = pctSilver;
        this.pctBronze = pctBronze;
        this.pctGeneral = pctGeneral;
        this.pctReserved = pctReserved;
        this.capacity = capacity;
        this.cost = cost;
    }

    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPctUnavailableSeat() {
        return pctUnavailableSeat;
    }

    public void setPctUnavailableSeat(int pctUnavailableSeat) {
        this.pctUnavailableSeat = pctUnavailableSeat;
    }

    public int getPctVIP() {
        return pctVIP;
    }

    public void setPctVIP(int pctVIP) {
        this.pctVIP = pctVIP;
    }

    public int getPctGold() {
        return pctGold;
    }

    public void setPctGold(int pctGold) {
        this.pctGold = pctGold;
    }

    public int getPctSilver() {
        return pctSilver;
    }

    public void setPctSilver(int pctSilver) {
        this.pctSilver = pctSilver;
    }

    public int getPctBronze() {
        return pctBronze;
    }

    public void setPctBronze(int pctBronze) {
        this.pctBronze = pctBronze;
    }

    public int getPctGeneral() {
        return pctGeneral;
    }

    public void setPctGeneral(int pctGeneral) {
        this.pctGeneral = pctGeneral;
    }

    public int getPctReserved() {
        return pctReserved;
    }

    public void setPctReserved(int pctReserved) {
        this.pctReserved = pctReserved;
    }

    public Boolean getFireworks() {
        return fireworks;
    }

    public void setFireworks(Boolean fireworks) {
        this.fireworks = fireworks;
    }

    public int getFireworksCost() {
        return fireworksCost;
    }

    public void setFireworksCost(int fireworksCost) {
        this.fireworksCost = fireworksCost;
    }

    public boolean isFireworks() {
        return fireworks;
    }

    public void setFireworks(boolean fireworks) {
        this.fireworks = fireworks;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
