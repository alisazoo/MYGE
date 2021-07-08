package MYGEwithGUI;

// This class is used to generate unique id for an input piece of furniture
// using a Factory-Pattern that is based on:
// https://stackoverflow.com/questions/46354117/auto-increment-id-between-classes-java

public class IdProvider {

    private static IdProvider instance = null;

    public static IdProvider getInstance(){
        if( instance == null ){
            instance = new IdProvider();
        }
        return instance;
    }

    private int nextID = 0;

    public int getUniqueId(){

        if(nextID < 0){
            throw new IllegalStateException("Out of IDs");
        }

        int uniqueId = nextID;
        nextID++;

        return uniqueId;
    }
}
