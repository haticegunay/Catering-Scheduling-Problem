package deneme;
import java.util.Random;
class Waiter implements Runnable{
    int[] trays;
    int[] total;
    int remainder;
    int num;
    String name;
    Random r=new Random(); //random sınıfı
    
    public Waiter(int[] trays, int[] total){
        this.trays = trays;
        this.total = total;
    }
    public synchronized void increment(int num, String name){// critical section
        // function that for adding items. synchronized word provide synchronization.
        if(trays[num] == 0 && total[num] >=5){ // if the tray is empty add 5 items.
            trays[num]=+5;
            total[num]= total[num]-5;
            System.out.println("Waiter put 5 " + name + "s to tray, number of " + name+"s in tray are = " + trays[num] + " Number of total " + name+"s = " + total[num]);
        }
        if(trays[num] == 1 && total[num] >=4 ){// There is one item on tray, waiter shoul 4 items to tray.
            trays[num]= trays[num]+4;
            total[num]= total[num]-4;
            System.out.println("Waiter put 4 " + name + "s to tray, number of " + name+"s in tray are = " + trays[num] + " Number of total " + name+"s = " + total[num]);
        }
        if((total[num]<=4 && total[num] >0 ) && trays[num] == 0){// if total <4, add all items to tray. It will be last addition.
            trays[num] = total[num];
            remainder = total[num];
            total[num] = 0;
            System.out.println("Waiter put " + remainder + "  " + name + "s to tray, number of " + name+"s in tray are = " + trays[num] + " Number of total " + name+"s = " + total[num]);
            
        }
        else{
            try {
                Thread.sleep((long)(Math.random()* 100)); //random second.
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }    
    } 
    @Override    
    public void run(){      
        while(true){
            int choise=r.nextInt(3);// Waiter looks the trays randomly.
            switch (choise) {
                case 0 :
                    increment(0,"borek");
                    try {
                        Thread.sleep((long)(Math.random()* 100)); //after filling the tray, the waiter sleeps randomly for seconds.
                    } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    break;

                case 1 :
                    increment(1,"cake");
                    try {
                        Thread.sleep((long)(Math.random()* 100)); //random second.
                    } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    break;

                case 2 :
                    increment(2,"drink");
                    try {
                        Thread.sleep((long)(Math.random()* 100)); //random second.
                    } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    break;
            }
        }    
    }
}


