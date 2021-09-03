package Project1;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {

        //Initializes Scanners and Counters

        Scanner input = new Scanner(System.in);
        String line = "";
        ArrayList<Sport> events = new ArrayList<Sport>();
        int counter = 0;

        //Asks user for directory if it is not defaul, and writes the log to that directory.

        String path = "/Users/ericsmith/IdeaProjects/Advanced Object Oriented Programming/src/Project1/";
        System.out.println("[1]: If filepath is " +  path);
        System.out.println("[2]: If filepath is different" );

        String inputIn = input.nextLine();

        switch(inputIn){
            case "1":
                break;
            case "2":
                System.out.println("Enter Filepath:");
                path = input.nextLine();
                break;
            default:
                System.out.println("Not a valid option");
                System.exit(0);
        }

        //Checks if a log file is already written, and deletes it if it is already made

        File alreadyLog = new File(path + "log.txt");
        alreadyLog.delete();

        //Tries to create a log file and posts an error if the file is already found
        try{
            File newFile = new File(path + "log.txt");
            if(!(newFile.createNewFile())){
                System.out.println("Could not create new file. You probably already have a file called log.txt already." +
                        " Please delete the old log.txt if possible");
            }

        }catch(IOException e){
            System.out.println("Could not initialize a new file");
            e.printStackTrace();
        }


        //Creates a buffered reader and reads the csv line per line, creating a sport object
        //for every pass/line of the csv
        try{

            BufferedReader scan = new BufferedReader(new FileReader(path + "EventList PA1.csv"));

            while ((line = scan.readLine()) != null){
                if (counter != 0){
                    //Reads a line of the csv and seperates values by the comma
                    String[] currentLine = line.split(",");

                    //Assigns data types to Each item from a line of the csv
                    int id = Integer.parseInt(currentLine[0]);
                    String type = currentLine[1];
                    String name = currentLine[2];
                    String date = currentLine[3];
                    String time = currentLine[4];
                    BigDecimal vipPrice = new BigDecimal(currentLine[5]);
                    BigDecimal goldPrice = new BigDecimal(currentLine[6]);
                    BigDecimal silverPrice = new BigDecimal(currentLine[7]);
                    BigDecimal bronzePrice = new BigDecimal(currentLine[8]);
                    BigDecimal generalPrice = new BigDecimal(currentLine[9]);

                    //Creats a sports object and adds it to the sports array list
                    Sport event = new Sport(id, type, name, date, time, vipPrice, goldPrice, silverPrice, bronzePrice, generalPrice);

                    events.add(event);
                    counter += 1;
                }

                counter += 1;
            }
        }catch(FileNotFoundException e){
            System.out.println("Not a valid filepath");
        }catch(IOException e){
            e.printStackTrace();
        }

        //Asks the user if they want to view the document, or if they want to manipulate the document.
        //Depending on the response, it will call a print method or a modify method.

        do{
            System.out.println("What would you like to do?");
            System.out.println("[1]: Print out events");
            System.out.println("[2]: Modify an event");
            System.out.println("[Exit]: Exit the program");

            inputIn = input.nextLine();

            switch(inputIn){
                case "1":
                    printCSV(events);
                    break;
                case "2":
                    modifyList(events, path + "log.txt");
                    break;
                case "Exit":
                    System.exit(0);
                default:
                    System.out.println("Enter a valid option");
            }

        }while(inputIn != "Exit");

        System.exit(0);

    }

    //Method that prints the csv
    public static void printCSV(ArrayList<Sport> events){
        for(int i = 0; i < events.size(); i++){
            events.get(i).print();
        }
    }

    //Method that handles the modification of the event array.
    public static ArrayList<Sport> modifyList(ArrayList<Sport> events, String path){
        //Asks user for the ID which they want to modify
        Scanner input = new Scanner(System.in);
        System.out.println("Which ID do you wish to modify?");

        int inputIn = input.nextInt() - 1;

        //Checks if the entered ID is valid, and what value they want to modify
        if(inputIn >= 0 || inputIn < events.size()){
            System.out.println("What would you like to modify?");
            System.out.println("[1]: Type");
            System.out.println("[2]: Name");
            System.out.println("[3]: Date");
            System.out.println("[4]: Time");
            System.out.println("[5]: VIP Price");
            System.out.println("[6]: Gold Price");
            System.out.println("[7]: Silver Price");
            System.out.println("[8]: Bronze Price");
            System.out.println("[9]: General Admission Price");

            int modify = input.nextInt();

            //Asks the user what they want to change the value to
            System.out.println("Enter what you would to change value to");
            String value = input.nextLine();
            value = input.nextLine();
            //Records the time and date that the user entered what value they wanted to change
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            try{
                //Creates a variety of writers which will print to the end of the log file
                FileWriter x = new FileWriter(path, true);
                BufferedWriter y = new BufferedWriter(x);
                PrintWriter writer = new PrintWriter(y);


                switch (modify) {
                    //Checks what value the user selected to change, and changes that value of the sports object.
                    //Also creates a line of text which is written into the Log.txt
                    case 1:
                        events.get(inputIn).setType(value);
                        writer.println(formatter.format(date) + ": Set the Type of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 2:
                        events.get(inputIn).setName(value);
                        writer.println(formatter.format(date) + ": Set the Name of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 3:
                        events.get(inputIn).setDate(value);
                        writer.println(formatter.format(date) + ": Set the Date of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 4:
                        events.get(inputIn).setTime(value);
                        writer.println(formatter.format(date) + ": Set the Time of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 5:
                        events.get(inputIn).setVipPrice(new BigDecimal(value));
                        writer.println(formatter.format(date) + ": Set the Vip Price of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 6:
                        events.get(inputIn).setGoldPrice(new BigDecimal(value));
                        writer.println(formatter.format(date) + ": Set the Gold Price of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 7:
                        events.get(inputIn).setSilverPrice(new BigDecimal(value));
                        writer.println(formatter.format(date) + ": Set the Silver Price of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 8:
                        events.get(inputIn).setBronzePrice((new BigDecimal(value)));
                        writer.println(formatter.format(date) + ": Set the Bronze Price of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    case 9:
                        events.get(inputIn).setGeneralPrice(new BigDecimal(value));
                        writer.println(formatter.format(date) + ": Set the General Admission Price of Event " + (inputIn + 1) + " to " + value);
                        writer.close();
                        break;
                    default:
                        System.out.println("Did not enter a valid option");
                        break;

                }
            }catch(NumberFormatException e){
                System.out.println("You did not format the price correctly. Do not use characters.");
            }catch(IOException e){
                System.out.println("Could not write to file");
            }


        }else{
            System.out.println("Not a valid input");
        }

        //Returns the modified ArrayList
        return events;
    }
}