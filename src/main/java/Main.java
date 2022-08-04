import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("enter time for work and relax");
        String[] userInput = new Scanner(System.in).nextLine().split(" ");

        // в минутах
        int workTime = 1;
        int breakTime = 1;
        int sizebreak = 30;
        int sizework = 30;


        for (int i = 0; i < userInput.length; i++){
            switch (userInput[i]) {
                case "--help" -> System.out.println("""
                        \n Pomodoro - это приложение
                        -w - сколько работать
                        -b  - сколько отдыхать
                        --help - вызвать помощь
                        """);
                case "-w" -> workTime = Integer.parseInt(userInput[++i]);
                case "-b" -> breakTime = Integer.parseInt(userInput[++i]);
            }
        }
        System.out.printf("workTime = %d, breakTime = %d", workTime, breakTime);

        long startTime = System.currentTimeMillis();
        timer(workTime , breakTime, sizebreak, sizework);
        long endTime = System.currentTimeMillis();
        System.out.println("Timer work " + (endTime - startTime)/(1000*60) + " min");
    }

    public static void timer(int workTimeMin, int breakTimeMin, int sizeBreak, int sizeWork) throws InterruptedException {
        printProgress("Work progress:: ", workTimeMin, sizeWork);
        printProgress("Break progress:: ", breakTimeMin, sizeBreak);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60* time / size;
        rep = 60* time /length;
        int stretchb = size /(3* time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(true){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

}
