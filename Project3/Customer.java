package Project3;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal moneyAvailable;
    private BigDecimal moneySaved;
    private int concertsPurchased;
    private boolean membership;
    private String username;
    private String password;
    private ArrayList<Event> tickets = new ArrayList<Event>();

    public Customer(int id, String firstName, String lastName, BigDecimal moneyAvailable, int concertsPurchased, boolean membership, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneyAvailable = moneyAvailable;
        this.concertsPurchased = concertsPurchased;
        this.membership = membership;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getMoneyAvailable() {
        return moneyAvailable;
    }

    public void setMoneyAvailable(BigDecimal moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public int getConcertsPurchased() {
        return concertsPurchased;
    }

    public void setConcertsPurchased(int concertsPurchased) {
        this.concertsPurchased = concertsPurchased;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Event> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Event> tickets) {
        this.tickets = tickets;
    }

    //OtherMethods

    public void buyVipTicket(Event event){
        if(this.membership){
            moneyAvailable = moneyAvailable.subtract(event.getVipPrice().multiply(new BigDecimal(0.9)));
            moneyAvailable = moneyAvailable.subtract(event.getVipPrice().multiply(new BigDecimal(0.9)).multiply(new BigDecimal(0.0875)));
            moneySaved = moneySaved.add(event.getVipPrice().multiply(new BigDecimal(0.1)));
        } else {
            moneyAvailable = moneyAvailable.subtract(event.getVipPrice());
            moneyAvailable = moneyAvailable.subtract(event.getVipPrice().multiply(new BigDecimal(0.0875)));
        }
        tickets.add(event);
        concertsPurchased++;

    }
    public void buyGoldTicket(Event event){
        if(this.membership){
            moneyAvailable = moneyAvailable.subtract(event.getGoldPrice().multiply(new BigDecimal(0.9)));
            moneyAvailable = moneyAvailable.subtract(event.getGoldPrice().multiply(new BigDecimal(0.9)).multiply(new BigDecimal(0.0875)));
            moneySaved = moneySaved.add(event.getGoldPrice().multiply(new BigDecimal(0.1)));
        } else {
            moneyAvailable = moneyAvailable.subtract(event.getGoldPrice());
            moneyAvailable = moneyAvailable.subtract(event.getGoldPrice().multiply(new BigDecimal(0.0875)));
        }
        tickets.add(event);
        concertsPurchased++;
    }
    public void buySilverTicket(Event event){
        if(this.membership){
            moneyAvailable = moneyAvailable.subtract(event.getSilverPrice().multiply(new BigDecimal(0.9)));
            moneyAvailable = moneyAvailable.subtract(event.getSilverPrice().multiply(new BigDecimal(0.9)).multiply(new BigDecimal(0.0875)));
            moneySaved = moneySaved.add(event.getSilverPrice().multiply(new BigDecimal(0.1)));
        } else {
            moneyAvailable = moneyAvailable.subtract(event.getSilverPrice());
            moneyAvailable = moneyAvailable.subtract(event.getSilverPrice().multiply(new BigDecimal(0.0875)));
        }
        tickets.add(event);
        concertsPurchased++;
    }
    public void buyBronzeTicket(Event event){
        if(this.membership){
            moneyAvailable = moneyAvailable.subtract(event.getBronzePrice().multiply(new BigDecimal(0.9)));
            moneyAvailable = moneyAvailable.subtract(event.getBronzePrice().multiply(new BigDecimal(0.9)).multiply(new BigDecimal(0.0875)));
            moneySaved = moneySaved.add(event.getBronzePrice().multiply(new BigDecimal(0.1)));
        } else {
            moneyAvailable = moneyAvailable.subtract(event.getBronzePrice());
            moneyAvailable = moneyAvailable.subtract(event.getBronzePrice().multiply(new BigDecimal(0.0875)));
        }
        tickets.add(event);
        concertsPurchased++;
    }
    public void buyGeneralTicket(Event event){
        if(this.membership){
            moneyAvailable = moneyAvailable.subtract(event.getGeneralPrice().multiply(new BigDecimal(0.9)));
            moneyAvailable = moneyAvailable.subtract(event.getGeneralPrice().multiply(new BigDecimal(0.9)).multiply(new BigDecimal(0.0875)));
            moneySaved = moneySaved.add(event.getGeneralPrice().multiply(new BigDecimal(0.1)));
        } else {
            moneyAvailable = moneyAvailable.subtract(event.getGeneralPrice());
            moneyAvailable = moneyAvailable.subtract(event.getGeneralPrice().multiply(new BigDecimal(0.0875)));
        }
        tickets.add(event);
        concertsPurchased++;
    }
}
