import java.util.Scanner;
import java.time.LocalDate;

public class BookingSystem {
    public static void main(String[] args) {
        Booking booking = new Booking();
    }
}

class Booking {
	//ref?
    private int price;
    //tickets
    //date?
    //film?
    public Booking() {
    	//takes user input for number of tickets
        Tickets tickets = new Tickets(); 
        //Calculates total price of all tickets accounting for discount
        PriceCalculator priceCalc = new PriceCalculator(tickets);
        priceCalc.printTotal();
    }
}

class Tickets {
	//type, price and number of tickets
    private String[] type = {"Standard","OAP","Student","Child"};
    private int[] price = {8,6,6,4};
    private int[] num = new int[type.length];
    //variable getters
    public int getNum(int x) {
    	return num[x];
    }
    
    public int getPrice(int x) {
    	return price[x];
    }
    
    public int ticketTypes() {
    	return type.length;
    }
    //take user input for all ticket types
    public Tickets() {
    	System.out.println("Please enter the number of tickets you would like for each ticket type.");
    	Scanner sc = new Scanner(System.in);
    	//set the number for each ticket type
    	for (int i=0; i<ticketTypes(); i++) {
	    	do { 
	            System.out.println(type[i] + " Tickets: ");
	            //input sanitation
	            while (!sc.hasNext("\\d+")){
	            	System.out.println("Please enter a valid number.");
	            	sc.next();
	            }
	            num[i] = sc.nextInt();
	    	} while (num[i]<0);
    	}
    	sc.close();
    }
}

class PriceCalculator {
	int total = 0;
    public PriceCalculator(Tickets tickets) {	
    	//for each ticket type, see if there is a discount today, and and to running total.
    	for (int i=0; i<tickets.ticketTypes(); i++) {
    		total += (tickets.getNum(i) * Discount.isDay(tickets.getPrice(i)));
    	}	
    }
	public void printTotal() {
        System.out.println("The total price of all tickets for this movie is £" + total);
    }

}

class Discount {
    public static int isDay(int basePrice){
    	//if today = this day, return lowered price 
        if("WEDNESDAY" == LocalDate.now().getDayOfWeek().name()){
            int newPrice = basePrice - 2;
            return newPrice;
        }else{ 
            return basePrice;
        }
    }
}