package deneme;
public class CateringScheduling {
    static int[] trays = {5,5,5};// börek, cake, drink
    static int[] total = {25,10,25};// total number of börek, cake, drink
    static int[] borek = {0,0,0,0,0,0,0,0,0,0};// for holding börek numbers of every geusts
    static int[] cake = {0,0,0,0,0,0,0,0,0,0};// for holding cake numbers of every geusts
    static int[] drink = {0,0,0,0,0,0,0,0,0,0};// for holding drink numbers of every geusts
    
    public static void main(String[] args) {
        //Creating thread objects
        new Thread(new Waiter(trays,total)).start();
        new Thread(new Guests(0,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(1,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(2,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(3,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(4,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(5,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(6,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(7,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(8,trays,total,borek,cake,drink)).start();
        new Thread(new Guests(9,trays,total,borek,cake,drink)).start();
    }   
}