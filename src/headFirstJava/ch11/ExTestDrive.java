package headFirstJava.ch11;

// tried but not 100% on 5/7/2021
// use catch block to create the word for the time when "yes" is selected.

class MyEx extends Exception{ }

public class ExTestDrive{

    /* Nice try but wrong answer
    public static void main(String[] args) {
        //String test = args[0];
        String test = "yes";
        System.out.print("t");
        try {
            doRisky(test);
        } catch(MyEx e){ // when "no" throws an exception
            System.out.print("r");
            System.out.print("o");
        } finally{
            System.out.print("w");
            System.out.println("s");
        }

    }

    static void doRisky(String t) throws MyEx{
        System.out.print("h");
        if("yes".equals(t)){
            System.out.print("a");
        }
        throw new MyEx();
    }
     */

    public static void main(String[] args) {
        //String test = args[0];
        String test = "no";
        // test = "yes";
        System.out.print("t");
        try {
            doRisky(test);
        } catch(MyEx e){
            System.out.print("a");
        } finally{
            System.out.print("w");
            System.out.println("s");
        }

    }

    static void doRisky(String t) throws MyEx{
        System.out.print("h");
        if("yes".equals(t)){
            throw new MyEx();
        }
        System.out.print("r");
        System.out.print("o");
    }




}

