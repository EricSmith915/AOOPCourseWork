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

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


public class RunTicketMiner {
    public static void main(String[] args){

        //String that will ask for the users choice
        String choice = "";

        //Creates scanner and asks user for name of CSV
        Scanner input = new Scanner(System.in);

        System.out.println("Hello! Welcome to TicketMiner! \nBefore we start," +
                " we will need to know a little more about you. \nPlease enter the " +
                "name of your event csv file. Make sure to include .csv at the end. This entry is case sensitive\n");

        String csvName = input.nextLine();

        System.out.println("\nThank you! Now please enter the name of the customer list. This is also case sensitive." +
                "Make sure to include .csv at the end\n");

        String customerList = input.nextLine();


        //Asks the user for directory
        System.out.println("\nPlease pick the folder which contains your files. \n" +
                "On Windows this might look like C:\\Users\\Eric\\Documents\\CS\\Advanced Object Oriented\\src\\Project2\\ \n" +
                "On Mac this might look like /Users/Eric/Documents/CS/Advanced Object Oriented/src/Project2\n");

        String path = input.nextLine();

        //Checks if there is already a log file in the specified directory
        //and deletes it if there already is one

        File alreadyLog = new File(path + "log.txt");
        alreadyLog.delete();

        //Tries to create a log file and posts an error if the file is already found
        try{
            File logFile = new File(path + "log.txt");
            if(!(logFile.createNewFile())){
                System.out.println("\nCould not create new file. You probably already have a file called log.txt already." +
                        " Please delete the old log.txt if possible\n");
            }
        }catch(IOException e){
            System.out.println("\nCould not initialize a new file. Directory likely invalid\n");
            System.exit(0);
        }catch(Exception e){
            System.out.println("\nError in the program\n");
        }

        //Calls the readCSV method, and stores the values of the csv
        //into an event ArrayList
        ArrayList<Event> events = readCSV(csvName, path);

        System.out.println("\nThank you! Please enter what you are: \n[1]: An Individual User \n[2]: A System Administrator\n");

        choice = input.nextLine();

        switch (choice){
            case "1":
                individualUser(events);
                break;
            case "2":
                systemAdministrator(events, path + csvName);
                break;
            default:
                System.out.println("\nPick a valid option\n");
        }


    }

    public static ArrayList<Event> readCSV(String csvName, String path){
        String line = "";
        boolean first = true;
        ArrayList<Event> events = new ArrayList<Event>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm aa");

        try {
            BufferedReader scan = new BufferedReader(new FileReader(path + csvName));
            while ((line = scan.readLine()) != null) {

                Event event = new Event();
                Venue venue = new Venue();

                if(!first){
                    String[] currentLine = line.split(",");

                    switch (currentLine[12]){
                        case "Stadium":
                            venue = new Stadium(currentLine[10], Integer.parseInt(currentLine[11]), Integer.parseInt(currentLine[15]),
                                    Integer.parseInt(currentLine[16]), Integer.parseInt(currentLine[17]), Integer.parseInt(currentLine[18]),
                                    Integer.parseInt(currentLine[19]), Integer.parseInt(currentLine[20]), Integer.parseInt(currentLine[13]));
                            break;
                        case "Arena":
                            venue = new Arena(currentLine[10], Integer.parseInt(currentLine[11]), Integer.parseInt(currentLine[15]),
                                    Integer.parseInt(currentLine[16]), Integer.parseInt(currentLine[17]), Integer.parseInt(currentLine[18]),
                                    Integer.parseInt(currentLine[19]), Integer.parseInt(currentLine[20]), Integer.parseInt(currentLine[13]));
                            break;
                        case "Auditorium":
                            venue = new Auditorium(currentLine[10], Integer.parseInt(currentLine[11]), Integer.parseInt(currentLine[15]),
                                    Integer.parseInt(currentLine[16]), Integer.parseInt(currentLine[17]), Integer.parseInt(currentLine[18]),
                                    Integer.parseInt(currentLine[19]), Integer.parseInt(currentLine[20]), Integer.parseInt(currentLine[13]));
                            break;
                        case "Open Air":
                            venue = new OpenAir(currentLine[10], Integer.parseInt(currentLine[11]), Integer.parseInt(currentLine[15]),
                                    Integer.parseInt(currentLine[16]), Integer.parseInt(currentLine[17]), Integer.parseInt(currentLine[18]),
                                    Integer.parseInt(currentLine[19]), Integer.parseInt(currentLine[20]), Integer.parseInt(currentLine[13]));
                            break;
                    }

                    String[] date = currentLine[3].split("/");

                    if(date[0].length() == 1){
                        date[0] = "0" + date[0];
                    }
                    if(date[1].length() == 1){
                        date[1] = "0" + date[1];
                    }
                    if(date[2].length() == 1){
                        date[2] = "0" + date[2];
                    }

                    String[] hour = currentLine[4].split(":");

                    if(hour[0].length() == 1){
                        hour[0] = "0" + hour[0];
                    }

                    switch(currentLine[1]){
                        case "Sport":
                            event = new Sport(Integer.parseInt(currentLine[0]), currentLine[2], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[5]), new BigDecimal(currentLine[6]),
                                    new BigDecimal(currentLine[7]), new BigDecimal(currentLine[8]), new BigDecimal(currentLine[9]), venue, new BigDecimal(currentLine[14]));
                            break;
                        case "Concert":
                            event = new Concert(Integer.parseInt(currentLine[0]), currentLine[2], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[5]), new BigDecimal(currentLine[6]),
                                    new BigDecimal(currentLine[7]), new BigDecimal(currentLine[8]), new BigDecimal(currentLine[9]), venue, new BigDecimal(currentLine[14]));
                            break;
                        case "Special":
                            event = new Special(Integer.parseInt(currentLine[0]), currentLine[2], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[5]), new BigDecimal(currentLine[6]),
                                    new BigDecimal(currentLine[7]), new BigDecimal(currentLine[8]), new BigDecimal(currentLine[9]), venue, new BigDecimal(currentLine[14]));
                            break;
                    }

                }

                first = false;
                events.add(event);
            }
        } catch (IOException e) {
            System.out.println("\nCould not find csv file. Please try running program again");
            System.exit(0);
        } catch (ParseException e){
            System.out.println("Could not parse the date from the csv");
        }
        return events;
    }

    public static void individualUser(ArrayList<Event> event){
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease type in your first and last name\n");

        String name = input.nextLine();

        System.out.println("\nPlease type in your password\n");

        String password = input.nextLine();


    }

    public static void systemAdministrator(ArrayList<Event> event, String path){
        printLog(path, "User logs in as System Admin");
        Scanner input = new Scanner(System.in);
        String choice = "";

        while(true) {
            System.out.println("\nWould you like to \n[1]: Inquire event by ID\n[2]: Inquire event by name\n[Exit]: Exit the Program\n");

            choice = input.nextLine();

            switch(choice){
                case "1":
                    System.out.println("\nEnter the ID of the event you wish to view\n");

                    choice = input.nextLine();
                    try {
                        event.get(Integer.parseInt(choice)).printSysAdmin();
                        printLog(path, "Admin prints out event " + choice);
                    } catch (NullPointerException e){
                        System.out.println("\nYou did not enter a valid ID\n");
                        printLog(path, "Admin searches ID that is out of bounds");
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("\nYou did not enter a valid ID\n");
                        printLog(path, "Admin searches ID that is out of bounds");
                    }
                    break;
                case "2":
                    System.out.println("\nEnter the name of the event you wish to view\n");

                    choice = input.nextLine();
                    for(int i = 1; i < event.size(); i++){
                        if(event.get(i).getName().equalsIgnoreCase(choice)){
                            event.get(i).printSysAdmin();
                            break;
                        }
                    }
                    System.out.println("\nCould not find an event with that name\n");
                case "Exit":
                    exitProgram(path);
            }
        }
    }

    public static void exitProgram(String path) {
        printLog(path, "User exits the system");
        System.exit(0);
    }

    public static void printLog(String path, String message){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            //Creates a variety of writers which will print to the end of the log file
            FileWriter x = new FileWriter(path + "log.txt", true);
            BufferedWriter y = new BufferedWriter(x);
            PrintWriter writer = new PrintWriter(y);

            writer.println(formatter.format(date) + ": " + message);
            writer.close();

        } catch (IOException E) {
            System.out.println("\nCould not write to log\n");
        }
    }
}
