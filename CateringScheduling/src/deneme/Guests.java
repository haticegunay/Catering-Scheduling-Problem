package deneme;
import java.util.Arrays;
import java.util.Random;
class Guests implements Runnable{ //For objects to speak, the run method is overridden.
    int[] trays;
    int[] total;
    int[] borek;
    int[] cake;
    int[] drink;
    int[] array;
    int number, num;// each thread has a number, these numbers will be used later as index of an array.
    String name;
    Random r=new Random(); //random class
    
    public Guests(int number ,int[] trays, int[] total, int[] borek, int[] cake, int[] drink){
        this.number = number;
        this.trays = trays;
        this.total = total;
        this.borek = borek;
        this.cake = cake;
        this.drink = drink;
    }
    public synchronized void decrement(int[] array, String name, int num){ // critical section
       // "synchronized " word provide us that if there is any thread working on this function
       //another thread does not working on the this function.
        trays[num]--;
        System.out.println("Guest" + number + " take a "+ name + ", number of " + name + "s in trays = " + trays[num]);
        array[number]++; // Which thread decrease the tray, it should increase own value by one.
        
    }
    @Override
    public void run(){
        while(true){
            drowse();// initially every thread sleep a random time.
            int choise=r.nextInt(3);// each thread decide which item to eat.
            switch (choise) {
                case 0 :
                    if(borek[number]<5){
                        if(trays[0] == 0 && total[0] != 0)// waits for the waiter to put the items on the tray.
                            drowse();
                        if(total[0]<=10){//every gusest must eat at least 1 at most 5 böreks.
                            //So, if there are 10 böreks in börektray, attention should be paid.
                            if ((contains(borek,0)) && borek[number]==0 ) // if there is a thread that does not eat,
                                //and current thread didn't eat, cuurent thread will eat.
                                decrement(borek,"borek",0);
                            if((contains(borek,0)) && borek[number]!=0)// if there is a thread that does not eat, it will eat. 
                                drowse();
                            if((contains(borek,0) == false) && trays[0] > 0)// if every thread ate 1 items, you can eat.
                                decrement(borek,"borek",0);   
                        }
                        if(trays[0] != 0 && total[0]>10)
                            decrement(borek,"borek",0);
                    drowse();
                    }
                    else if(borek[number]>5)// threads can eat max 5 börek. so, if thread ate 5 böreks, it can not eat again.
                        drowse();
                    break;
                case 1 :
                    if(cake[number]<2){
                        if(trays[1] == 0 && total[1] != 0)// waits for the waiter to put the items on the tray.
                            drowse(); 
                        if(total[1]<=8){
                            if ((contains(cake,0)) && cake[number]==0 )// if any guest havent eat 
                                decrement(cake,"cake",1);
                            if((contains(cake,0)) && cake[number]!=0){
                                drowse();
                            }
                            if((contains(cake,0) == false) && trays[1]>0)
                                decrement(cake,"cake",1);  
                        }
                        if(trays[1] != 0 && total[1]>8)
                            decrement(cake,"cake",1);
                        drowse();
                    }
                    else if(cake[number]>1)
                        drowse();
                    break;
                case 2 :
                    if(drink[number]<5){
                        if(trays[2] == 0 && total[2] != 0)// waits for the waiter to put the items on the tray.
                            drowse();
                        if(total[2]<=10){
                            if ((contains(drink,0)) && drink[number]==0)
                                decrement(drink,"drink",2);
                            if((contains(drink,0)) && drink[number]!=0)
                                drowse();
                            if((contains(drink,0) == false) && trays[2] > 0)
                                decrement(drink,"drink",2);  
                        }
                        if(trays[2] != 0 && total[2]>10)
                            decrement(drink,"drink",2);
                        drowse();
                    }    
                    else if(drink[number]>4)   
                        drowse();
                    break;
            }
            if(total[0]==0 && trays[0] == 0){ // if all trays are empty, exit the program.
                if(total[1]==0 && trays[1] == 0){
                    if(total[2]==0 && trays[2] == 0){ //the program prints on the screen that each thread eats which and how many items.
                        System.out.println("Array of borek = " + Arrays.toString(borek));// 0 index equals to guest 1, index 1 equals to guest 2 ... index 9 equals to guest 10.
                        System.out.println("Array of cake = " + Arrays.toString(cake));
                        System.out.println("Array of drink = " + Arrays.toString(drink));
                        System.exit(0);  
                    }
                }  
            }        
        }    
    }
        public static boolean contains(final int[] array, final int v) {// it controls is there a thread does not eat the item.
            boolean result = false;
            for(int i : array){
                if(i == v){
                    result = true;
                    break;
                }
            } 
            return result;
        }

    public void drowse(){
        try {
            Thread.sleep((long)(Math.random() * 2000)); //random second.
            } 
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }    
    }
}
