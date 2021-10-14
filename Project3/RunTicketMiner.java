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

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class RunTicketMiner {
    public static void main(String[] args){

        //String that will ask for the users choice
        String choice = "";

        //Creates scanner and asks user for name of CSV
        Scanner input = new Scanner(System.in);

        System.out.println("Hello! Welcome to TicketMiner! \nBefore we start," +
                " we will need to know a little more about you. \nPlease enter the " +
                "name of your event csv file. Make sure to include .csv at the end. This entry is case sensitive\n");

        String eventsName = input.nextLine();

        System.out.println("\nThank you! Now please enter the name of the customer list. This is also case sensitive." +
                " Make sure to include .csv at the end\n");

        String customersName = input.nextLine();


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
            File newFile = new File(path + "log.txt");
            if(!(newFile.createNewFile())){
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
        //into an event ArrayList. The same is done for customers
        ArrayList<Event> events = readEvents(eventsName, path);
        ArrayList<Customer> customers = readCustomers(customersName, path);


        //Loops through the program, allowing the user to log on and off or commit actions until they wish to quit
        while(true){
            System.out.println("\nThank you! Please enter what you are or want to do: \n[1]: An Individual User " +
                    "\n[2]: A System Administrator\n[3]: An Event Manager\n[3]: Exit the TicketMiner System\n");

            choice = input.nextLine();
            switch (choice) {
                case "1":
                    individualUser(events, customers, path);
                    break;
                case "2":
                    systemAdministrator(events, path);
                    break;
                case "3":
                    eventManager(events, path);
                case "4":
                    exitProgram(path, events, customers);
                    break;
                default:
                    System.out.println("\nPick a valid option\n");
            }
        }
    }

    /**
     *
     * @param csvName Name of the csv file which contains all the events
     * @param path The name of the directory in which the input files are located
     * @return An arraylist containing event objects
     */
    public static ArrayList<Event> readEvents(String csvName, String path){
        String line = "";
        boolean first = true;
        ArrayList<Event> events = new ArrayList<Event>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm aa");

        int idIndex = 0, eventTypeIndex = 0, nameIndex = 0, dateIndex = 0, timeIndex = 0, vipPriceIndex = 0,
                goldPriceIndex = 0, silverPriceIndex = 0, bronzePriceIndex = 0, generalPriceIndex = 0, venueNameIndex = 0,
                pctSeatsUnavailableIndex = 0, venueTypeIndex = 0, capacityIndex = 0, costIndex = 0, vipPctIndex = 0,
                goldPctIndex = 0, silverPctIndex = 0, bronzePctIndex = 0, generalAdmissionPct = 0, reservedExtraPct = 0,
                fireworksPlannedIndex = 0, fireworksCostIndex = 0;

        try {
            BufferedReader scan = new BufferedReader(new FileReader(path + csvName));
            while ((line = scan.readLine()) != null) {

                Event event = new Event();
                Venue venue = new Venue();

                String[] currentLine = line.split(",");
                if(first){
                    first = false;

                    for(int i = 0; i < currentLine.length; i++){
                        switch(currentLine[i]){
                            case "Event ID":
                                idIndex = i;
                            case "Event Type":
                                eventTypeIndex = i;
                            case "Name":
                                nameIndex = i;
                            case "Date":
                                dateIndex = i;
                            case "Time":
                                timeIndex = i;
                            case "VIP Price":
                                vipPriceIndex = i;
                            case "Gold Price":
                                goldPriceIndex = i;
                            case "Silver Price":
                                silverPriceIndex = i;
                            case "Bronze Price":
                                bronzePriceIndex = i;
                            case "General Admission Price":
                                generalPriceIndex = i;
                            case "Venue Name":
                                venueNameIndex = i;
                            case "Pct Seats Unavailable":
                                pctSeatsUnavailableIndex = i;
                            case "Venue Type":
                                venueTypeIndex = i;
                            case "Capacity":
                                capacityIndex = i;
                            case "Cost":
                                costIndex = i;
                            case "VIP Pct":
                                vipPctIndex = i;
                            case "Gold Pct":
                                goldPctIndex = i;
                            case "Silver Pct":
                                silverPctIndex = i;
                            case "Bronze Pct":
                                bronzePctIndex = i;
                            case "General Admission Pct":
                                generalAdmissionPct = i;
                            case "Reserved Extra Pct":
                                reservedExtraPct = i;
                            case "Fireworks Planned":
                                fireworksPlannedIndex = i;
                            case "Fireworks Cost":
                                fireworksCostIndex = i;
                        }
                    }
                } else {
                    switch (currentLine[venueTypeIndex]){
                        case "Stadium":
                            venue = new Stadium(currentLine[venueNameIndex], Integer.parseInt(currentLine[pctSeatsUnavailableIndex]),
                                    Integer.parseInt(currentLine[vipPctIndex]), Integer.parseInt(currentLine[goldPctIndex]),
                                    Integer.parseInt(currentLine[silverPctIndex]), Integer.parseInt(currentLine[bronzePctIndex]),
                                    Integer.parseInt(currentLine[generalAdmissionPct]), Integer.parseInt(currentLine[reservedExtraPct]),
                                    Integer.parseInt(currentLine[capacityIndex]));
                            break;
                        case "Arena":
                            venue = new Arena(currentLine[venueNameIndex], Integer.parseInt(currentLine[pctSeatsUnavailableIndex]),
                                    Integer.parseInt(currentLine[vipPctIndex]), Integer.parseInt(currentLine[goldPctIndex]),
                                    Integer.parseInt(currentLine[silverPctIndex]), Integer.parseInt(currentLine[bronzePctIndex]),
                                    Integer.parseInt(currentLine[generalAdmissionPct]), Integer.parseInt(currentLine[reservedExtraPct]),
                                    Integer.parseInt(currentLine[capacityIndex]));
                            break;
                        case "Auditorium":
                            venue = new Auditorium(currentLine[venueNameIndex], Integer.parseInt(currentLine[pctSeatsUnavailableIndex]),
                                    Integer.parseInt(currentLine[vipPctIndex]), Integer.parseInt(currentLine[goldPctIndex]),
                                    Integer.parseInt(currentLine[silverPctIndex]), Integer.parseInt(currentLine[bronzePctIndex]),
                                    Integer.parseInt(currentLine[generalAdmissionPct]), Integer.parseInt(currentLine[reservedExtraPct]),
                                    Integer.parseInt(currentLine[capacityIndex]));
                            break;
                        case "Open Air":
                            venue = new OpenAir(currentLine[venueNameIndex], Integer.parseInt(currentLine[pctSeatsUnavailableIndex]),
                                    Integer.parseInt(currentLine[vipPctIndex]), Integer.parseInt(currentLine[goldPctIndex]),
                                    Integer.parseInt(currentLine[silverPctIndex]), Integer.parseInt(currentLine[bronzePctIndex]),
                                    Integer.parseInt(currentLine[generalAdmissionPct]), Integer.parseInt(currentLine[reservedExtraPct]),
                                    Integer.parseInt(currentLine[capacityIndex]));
                            break;
                    }

                    if(currentLine[fireworksPlannedIndex].equals("True")){
                        venue.setFireworks(true);
                        venue.setFireworksCost(Integer.parseInt(currentLine[fireworksCostIndex]));
                    }

                    String[] date = currentLine[dateIndex].split("/");

                    if(date[0].length() == 1){
                        date[0] = "0" + date[0];
                    }
                    if(date[1].length() == 1){
                        date[1] = "0" + date[1];
                    }
                    if(date[2].length() == 1){
                        date[2] = "0" + date[2];
                    }

                    String[] hour = currentLine[timeIndex].split(":");

                    if(hour[0].length() == 1){
                        hour[0] = "0" + hour[0];
                    }

                    switch(currentLine[eventTypeIndex]){
                        case "Sport":
                            event = new Sport(Integer.parseInt(currentLine[idIndex]), currentLine[nameIndex], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[vipPriceIndex]), new BigDecimal(currentLine[goldPriceIndex]),
                                    new BigDecimal(currentLine[silverPriceIndex]), new BigDecimal(currentLine[bronzePriceIndex]), new BigDecimal(currentLine[generalPriceIndex]),
                                    venue, new BigDecimal(Integer.parseInt(currentLine[costIndex])));
                            break;
                        case "Concert":
                            event = new Concert(Integer.parseInt(currentLine[idIndex]), currentLine[nameIndex], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[vipPriceIndex]), new BigDecimal(currentLine[goldPriceIndex]),
                                    new BigDecimal(currentLine[silverPriceIndex]), new BigDecimal(currentLine[bronzePriceIndex]), new BigDecimal(currentLine[generalPriceIndex]),
                                    venue, new BigDecimal(Integer.parseInt(currentLine[costIndex])));
                            break;
                        case "Special":
                            event = new Special(Integer.parseInt(currentLine[idIndex]), currentLine[nameIndex], format.parse(date[0] + "/" + date[1] + "/" + date[2]),
                                    hourFormat.parse(hour[0] + ":" + hour[1]), new BigDecimal(currentLine[vipPriceIndex]), new BigDecimal(currentLine[goldPriceIndex]),
                                    new BigDecimal(currentLine[silverPriceIndex]), new BigDecimal(currentLine[bronzePriceIndex]), new BigDecimal(currentLine[generalPriceIndex]),
                                    venue, new BigDecimal(Integer.parseInt(currentLine[costIndex])));
                            break;
                    }
                }
                events.add(event);
            }
        } catch (IOException e) {
            System.out.println("\nCould not find csv file. Please try running program again");
            printLog(path, "Program failed due to IOException");
            System.exit(1);
        } catch (ParseException e){
            System.out.println("\nCould not parse the date from the csv");
            printLog(path, "Program failed due to parse exception");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\nReading csv error. You likely mixed up customer list and event list. Try running program again");
            printLog(path, "Program failed due to ArrayIndexOutOfBoundsException");
            System.exit(1);
        }
        return events;
    }

    /**
     *
     * @param customersName Name of the file of the customers csv
     * @param path Directory in which the csv file is located
     * @return Returns an ArrayList of customers
     */
    public static ArrayList<Customer> readCustomers(String customersName, String path){
        String line = "";
        boolean first = true;
        ArrayList<Customer> customers = new ArrayList<Customer>();

        //Creates a buffered reader which will read the customer csv line by line
        //Every line will create a customer object and add that object into a customer ArrayList
        try{
            BufferedReader scan = new BufferedReader(new FileReader(path + customersName));

            while((line = scan.readLine()) != null) {
                if(!first){
                    String[] currentLine = line.split(",");

                    Customer newCustomer = new Customer(Integer.parseInt(currentLine[0]), currentLine[1],
                            currentLine[2], new BigDecimal(currentLine[3]), Integer.parseInt(currentLine[4]),
                            currentLine[5].equalsIgnoreCase("true"), currentLine[6], currentLine[7]);

                    customers.add(newCustomer);
                }
                first = false;
            }

        }catch (IOException e){
            System.out.println("\nCould not read from csv\n");
            printLog(path, "Could not read from csv file");
        }

        return customers;
    }

    /**
     *
     * @param events An arraylist of event object
     * @param customers An arraylist of customer objects
     * @param path The directory in which files will be created
     */
    public static void individualUser(ArrayList<Event> events, ArrayList<Customer> customers, String path){
        int customerID = 0;
        boolean on = true;
        boolean validLogin = false;
        Scanner input = new Scanner(System.in);

        //Asks the user for credentials
        System.out.println("\nPlease type in your first and last name\n");

        String name = input.nextLine();
        String[] firstLast = name.split(" ");

        System.out.println("\nPlease enter your username\n");

        String username = input.nextLine();

        System.out.println("\nPlease type in your password\n");

        String password = input.nextLine();

        //Checks if the user has valid credentials
        try {
            for (int i = 0; i < customers.size(); i++) {
                if (firstLast[0].equalsIgnoreCase(customers.get(i).getFirstName()) && firstLast[1].equalsIgnoreCase(customers.get(i).getLastName())
                        && username.equalsIgnoreCase(customers.get(i).getUsername()) && password.equals(customers.get(i).getPassword())) {

                    System.out.println("\nLog in Authenticated\n");
                    validLogin = true;
                    printLog(path, name + " logged on as a customer");
                    customerID = i;

                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("\nLogin failed. Make sure to write a First name and a last name like 'John Doe'\n");
            printLog(path, "User failed to log in as customer");
        }


        //Main logic of the program which is executed only if user is able to log in
        if(validLogin){

            //Loop that will keep on going until the user wishes to log off
            while(on) {
                System.out.println("\nWhat would you like to do?\n[1]: Print out events\n[2]: Buy tickets to events" +
                        "\n[3]: View concerts Purchased and balance\n[4]: Log out\n");

                String choice = input.nextLine();

                switch (choice) {

                    //Loops through the events object and prints out their variables
                    case "1":
                        printLog(path, name + " printed out events");
                        for (int i = 1; i < events.size(); i++) {
                            System.out.println("\nID: " + events.get(i).getId() + " Name: " + events.get(i).getName() +
                                    " VIP Price: " + events.get(i).getVipPrice() + " Gold Price: " + events.get(i).getGoldPrice() +
                                    " Silver Price: " + events.get(i).getSilverPrice() + " Bronze Price: " + events.get(i).getBronzePrice() +
                                    " General Price: " + events.get(i).getGeneralPrice());
                        }
                        break;
                    case "2":
                        //Asks the user what they want to purchase and checks if the id is valid
                        System.out.println("\nEnter the id of the event you wish to purchase a ticket to\n");
                        choice = input.nextLine();

                        while(Integer.parseInt(choice) <= 0 || Integer.parseInt(choice) > events.size()){
                            System.out.println("\nYou did not enter a valid ID. Try again\n");
                            choice = input.nextLine();
                        }

                        int i = Integer.parseInt(choice);

                        //Prints out the prices for the user and asks them what they want to buy
                        System.out.println("\nName: " + events.get(i).getName() +
                                ", VIP Price: " + events.get(i).getVipPrice() + ", Gold Price: " + events.get(i).getGoldPrice() +
                                ", Silver Price: " + events.get(i).getSilverPrice() + ", Bronze Price: " + events.get(i).getBronzePrice() +
                                ", General Price: " + events.get(i).getGeneralPrice() + "\n");


                        System.out.println("\nMoney available: " + customers.get(customerID).getMoneyAvailable() +
                                "\nTickets purchased: " + customers.get(customerID).getConcertsPurchased());

                        System.out.println("\nWhat would you like to purchase?\n[1]: Vip Ticket\n[2]: Gold Ticket" +
                                "\n[3]: Silver Ticket \n[4]: Bronze ticket \n[5]: General Ticket\n[6]: No ticket\n");

                        String choice2 = input.nextLine();

                        //Switch statement which takes in the choice of the user
                        switch (choice2){

                            //Based on what the user chooses, the system will display the amount of tickets available.
                            //Checks if the user has enough money, and lets them buy a ticket if their balance is high enough
                            case "1":
                                int vipAvailable = ((events.get(i).getVenue().getPctVIP() * events.get(i).getVenue().getCapacity()) / 100) - events.get(i).getVipSold();
                                System.out.println("\nVip tickets available: " + vipAvailable + "\nDo you want to confirm purchase?" +
                                        "\n[1]: Yes \n[2]: No\n");

                                String choice3 = input.nextLine();
                                if(choice3.equals("1") && vipAvailable > 1 && customers.get(customerID).getMoneyAvailable().compareTo(events.get(i).getVipPrice()) == 1){
                                    events.get(i).sellVip();
                                    customers.get(customerID).buyVipTicket(events.get(i));
                                    printLog(path, name + " purchased a vip ticket for event ID " + events.get(i).getId());
                                }else if(choice3.equals("2")){
                                    System.out.println("\nThank you for choosing TicketMiner!\n");
                                }else{
                                    System.out.println("\nCould not buy ticket. There are likely no more tickets " +
                                            "available or your balance is too low\n");
                                }
                                break;

                            case "2":
                                int goldAvailable = ((events.get(i).getVenue().getPctGold() * events.get(i).getVenue().getCapacity()) / 100) - events.get(i).getGoldSold();
                                System.out.println("\nGold tickets available: " + goldAvailable + "\nDo you want to confirm purchase?" +
                                        "\n[1]: Yes \n[2]: No\n");

                                choice3 = input.nextLine();
                                if(choice3.equals("1") && goldAvailable > 1 && customers.get(customerID).getMoneyAvailable().compareTo(events.get(i).getGoldPrice()) == 1){
                                    events.get(i).sellGold();
                                    customers.get(customerID).buyGoldTicket(events.get(i));
                                    printLog(path, name + " purchased a gold ticket for event ID " + events.get(i).getId());
                                }else if(choice3.equals("2")){
                                    System.out.println("\nThank you for choosing TicketMiner!\n");
                                }else{
                                    System.out.println("\nCould not buy ticket. There are likely no more tickets " +
                                            "available or your balance is too low\n");
                                }
                                break;

                            case "3":
                                int silverAvailable = ((events.get(i).getVenue().getPctSilver() * events.get(i).getVenue().getCapacity()) / 100) - events.get(i).getSilverSold();
                                System.out.println("\nSilver tickets available: " + silverAvailable + "\nDo you want to confirm purchase?" +
                                        "\n[1]: Yes \n[2]: No\n");

                                choice3 = input.nextLine();
                                if(choice3.equals("1") && silverAvailable > 1 && customers.get(customerID).getMoneyAvailable().compareTo(events.get(i).getSilverPrice()) == 1){
                                    events.get(i).sellSilver();
                                    customers.get(customerID).buySilverTicket(events.get(i));
                                    printLog(path, name + " purchased a silver ticket for event ID " + events.get(i).getId());
                                }else if(choice3.equals("2")){
                                    System.out.println("\nThank you for choosing TicketMiner!\n");
                                }else{
                                    System.out.println("\nCould not buy ticket. There are likely no more tickets " +
                                            "available or your balance is too low\n");
                                }
                                break;

                            case "4":
                                int bronzeAvailable = ((events.get(i).getVenue().getPctBronze() * events.get(i).getVenue().getCapacity()) / 100) - events.get(i).getBronzeSold();
                                System.out.println("\nBronze tickets available: " + bronzeAvailable + "\nDo you want to confirm purchase?" +
                                        "\n[1]: Yes \n[2]: No\n");

                                choice3 = input.nextLine();
                                if(choice3.equals("1") && bronzeAvailable > 1 && customers.get(customerID).getMoneyAvailable().compareTo(events.get(i).getBronzePrice()) == 1){
                                    events.get(i).sellBronze();
                                    customers.get(customerID).buyBronzeTicket(events.get(i));
                                    printLog(path, name + " purchased a bronze ticket for event ID " + events.get(i).getId());
                                }else if(choice3.equals("2")){
                                    System.out.println("\nThank you for choosing TicketMiner!\n");
                                }else{
                                    System.out.println("\nCould not buy ticket. There are likely no more tickets " +
                                            "available or your balance is too low\n");
                                }
                                break;

                            case "5":
                                int generalAvailable = ((events.get(i).getVenue().getPctGeneral() * events.get(i).getVenue().getCapacity()) / 100) - events.get(i).getGeneralSold();
                                System.out.println("\nGeneral tickets available: " + generalAvailable + "\nDo you want to confirm purchase?" +
                                        "\n[1]: Yes \n[2]: No\n");

                                choice3 = input.nextLine();
                                if(choice3.equals("1") && generalAvailable > 1 && customers.get(customerID).getMoneyAvailable().compareTo(events.get(i).getGeneralPrice()) == 1){
                                    events.get(i).sellGeneral();
                                    customers.get(customerID).buyGeneralTicket(events.get(i));
                                    printLog(path, name + " purchased a general ticket for event ID " + events.get(i).getId());
                                }else if(choice3.equals("2")){
                                    System.out.println("\nThank you for choosing TicketMiner!\n");
                                }else{
                                    System.out.println("\nCould not buy ticket. There are likely no more tickets " +
                                            "available or your balance is too low\n");
                                }
                                break;

                            case "6":
                                System.out.println("\nThank you for choosing TicketMiner\n");
                                break;

                            default:
                                System.out.println("\nYou did not choose a valid option\n");
                        }
                        break;
                    case "3":
                        printLog(path, name + " viewed purchased events and balance");

                        //Prints all the events that the user has purchased
                        for(int j = 0; j < customers.get(customerID).getTickets().size(); j++){
                            System.out.println("\nEvent ID: " + customers.get(customerID).getTickets().get(j).getId() +
                                    " Event Name: " + customers.get(customerID).getTickets().get(j).getName());
                        }

                        //Prints the amount of money available to the customer
                        System.out.println("\nBalance: " + customers.get(customerID).getMoneyAvailable() + "\n");
                        break;

                    case "4":
                        //Logs off the user

                        //Calculates reserved pct for the events
                        for(int j = 1; j < events.size(); j++){
                            events.get(j).calculatePCT();
                        }
                        printLog(path, name + " logged off as customer");
                        on = false;
                        break;

                    default:
                        //Default option for if the customer does not select a valid option
                        System.out.println("\nYou did not pick a valid option\n");
                }
            }

        }else{
            //Print statement that lets user know if the log-in credentials are valid
            System.out.println("\nLogin in credentials are not valid\n");
            printLog(path, name + " failed to log in as customer");
        }

    }

    /**
     *
     * @param event Event arraylist which contains items read from the EventList file
     * @param path Directory in which new files will be created
     */
    public static void systemAdministrator(ArrayList<Event> event, String path){
        printLog(path, "User logs in as System Admin");
        Scanner input = new Scanner(System.in);
        String choice = "";
        boolean on = true;

        //Loop that will keep on iterating until the user wishes to exit the system
        while(on) {
            System.out.println("\nWould you like to \n[1]: Inquire event by ID\n[2]: Inquire event by name\n[3]: " +
                            "Log out of admin\n");

            choice = input.nextLine();

            switch(choice){
                //Prints out events by ID
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
                //Prints out events by name
                case "2":
                    System.out.println("\nEnter the name of the event you wish to view\n");

                    choice = input.nextLine();
                    printLog(path, "Admin prints event " + choice);

                    for(int i = 1; i < event.size(); i++){
                        if(event.get(i).getName().equalsIgnoreCase(choice)){
                            event.get(i).printSysAdmin();
                            break;
                        }
                    }
                    break;
                //Logs out of admin
                case "3":
                    printLog(path, "Admin logs off");
                    on = false;
                    break;
            }
        }
    }

    public static void ticketAutomaton(){

    }

    public static void eventManager(ArrayList<Event> events, String path){
        Scanner input = new Scanner(System.in);

        System.out.println("\nThank you for logging in as an Event Manager\n " +
                "Enter the name of your event\n");

        String name = input.nextLine();

        System.out.println("\nEnter the date of your event. Make sure you use the format MM/DD/YYYY\n");

        String date = input.nextLine();

        String finalDate[] = date.split("/");

        System.out.println("\nEnter the time of your event. Make sure to use the format XX:XX AM (or PM)");

        String time = input.nextLine();

        System.out.println("\nWhat venue would you like to use?\n[1]: Sun Bowl Stadium\n[2]: Don Haskins Center\n " +
                "[3]: Magoffin Auditorium\n[4]: San Jacinto Plaza\n[5]: Centennial Plaza");

        String venue = input.nextLine();

        System.out.println("Enter the price of general admission. This ticket can not be over 500, and will automatically" +
                "be reduced to 500.\nMake sure that your input is also a positive integer.\n");

        String price = input.nextLine();


    }

    /**
     *
     * @param path The file directory which stores where new csv files will be stored
     * @param events An events ArrayList which contains all the events
     * @param customers A customers ArrayList which will contain all the customers
     */
    //Custom method to quit the program while writing to log
    public static void exitProgram(String path, ArrayList<Event> events, ArrayList<Customer> customers) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        printLog(path, "User exits the system");

        //Checks if there is already a log file in the specified directory
        //and deletes it if there already is one

        File alreadyEvent = new File(path + "NewEventList.csv");
        alreadyEvent.delete();

        File alreadyCustomer = new File(path + "NewCustomerList.csv");
        alreadyCustomer.delete();

        //Tries to create a New Event List file and posts an error if the file is already found
        try{
            File newFile = new File(path + "NewEventList.csv");
            if(!(newFile.createNewFile())){
                System.out.println("\nCould not create new file. You probably already have a file called log.txt already." +
                        " Please delete the old NewEventList.csv if possible\n");
            }
        }catch(IOException e){
            System.out.println("\nCould not initialize a new file. Directory likely invalid\n");
            printLog(path, "Could not create New EventList");
            System.exit(0);
        }catch(Exception e){
            System.out.println("\nError in the program\n");
            printLog(path, "Could not create new EventList");
        }


        //Prints to the NewEventsList csv
        for(int i = 0; i < events.size(); i++){
            if(i == 0) {
                printCSV(path, "NewEventList", "ID,Event Type,Name,Date,Time,VIP Price,Gold Price,Silver Price,Bronze Price," +
                        "General Admission Price,Venue Name,Pct Seats Unavailable,Venue Type,Capacity,Cost,Vip Pct," +
                        "Gold Pct,Silver Pct,Bronze Pct,General Admission Pct,Reserved Extra Pct");
            }else{
                printCSV(path, "NewEventList", events.get(i).getId() +","+ events.get(i).getType() + "," +events.get(i).getName() +"," +
                        dateFormat.format(events.get(i).getDate()) + ","+ timeFormat.format(events.get(i).getTime()) +
                        "," + events.get(i).getVipPrice() +"," +events.get(i).getGoldPrice() +"," +
                        events.get(i).getSilverPrice() + "," + events.get(i).getBronzePrice() + "" +
                        events.get(i).getGeneralPrice() + "," + events.get(i).getVenue().getName() + "," +
                        events.get(i).getVenue().getPctUnavailableSeat() + "," + events.get(i).getType() + "," +
                        events.get(i).getVenue().getCapacity() + "," + events.get(i).getCost() + "," +
                        events.get(i).getVenue().getPctVIP() + "," + events.get(i).getVenue().getPctGold() + "," +
                        events.get(i).getVenue().getPctSilver() + "," + events.get(i).getVenue().getPctBronze() + "," +
                        events.get(i).getVenue().getPctGeneral() + "," +events.get(i).getVenue().getPctReserved());
            }
        }

        //Tries to create a new customer list
        try{
            File newFile = new File(path + "NewCustomerList.csv");
            if(!(newFile.createNewFile())){
                System.out.println("\nCould not create new file. You probably already have a file called log.txt already." +
                        " Please delete the old NewEventList.csv if possible\n");
            }
        }catch(IOException e){
            System.out.println("\nCould not initialize a new file. Directory likely invalid\n");
            printLog(path, "Could not create New CustomerList");
            System.exit(0);
        }catch(Exception e){
            System.out.println("\nError in the program\n");
            printLog(path, "Could not create new CustomerList");
        }


        //Prints to the NewCustomersList csv
        for(int i = -1; i < customers.size(); i++){
            if(i == -1) {
                printCSV(path, "NewCustomerList" ,"ID,First Name,Last Name,Money Available,Concerts Purchased,TicketMiner Membership,Username," +
                        "Password");
            }else{
                printCSV(path, "NewCustomerList",customers.get(i).getId() + "," + customers.get(i).getFirstName() + "," +
                        customers.get(i).getLastName() + "," + customers.get(i).getMoneyAvailable() + "," +
                        customers.get(i).getConcertsPurchased() + "," + customers.get(i).isMembership() + "," +
                        customers.get(i).getUsername() + "," + customers.get(i).getPassword());
            }
        }

        System.exit(0);
    }

    /**
     *
     * @param path The path in which the log file is in
     * @param message The message which will be logged for the user
     */
    //Method that prints to the log.
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

    /**
     *
     * @param path The path that the new file will be created in
     * @param name The name of the file that will be printed to
     * @param message The message which will be printed into the csv
     */
    public static void printCSV(String path,String name, String message){
        try {
            //Creates a variety of writers which will print to the end of the log file
            FileWriter x = new FileWriter(path + name +".csv", true);
            BufferedWriter y = new BufferedWriter(x);
            PrintWriter writer = new PrintWriter(y);

            writer.println(message);
            writer.close();

        } catch (IOException E) {
            System.out.println("\nCould not write to log\n");
        }
    }
}
