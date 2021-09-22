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

public class Stadium extends Venue{

    //Constructors


    public Stadium() {
    }

    public Stadium(String name, int pctUnavailableSeat, int pctVIP, int pctGold, int pctSilver, int pctBronze, int pctGeneral, int pctReserved, int capacity) {
        super(name, pctUnavailableSeat, pctVIP, pctGold, pctSilver, pctBronze, pctGeneral, pctReserved, capacity);
    }
}
