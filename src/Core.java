
import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Core {
    private static Stack<String[]> sortedArr = new Stack<>(); // container for sorted arrays
    private static Stack<String[]> memory = new Stack<>(); //for Undo/Redo functions 
    private static Stack<String[]> toDisplay = new Stack<>(); // container for data to be displayed
    private static ArrayList<String> toSort = new ArrayList<String>(); // for sorting
    private static LinkedList<String[]> toStore = new LinkedList<>(); // for storing
    
    public static void addData(String incomeLeft, String income, String price, String item){
        toStore.add(new String[] {incomeLeft, income, price, item}); // stores data
        ToSort(); // executes "ToSort" method
    }
    
    public static void ToSort(){
        
            System.out.println("to Sorter: ");
            toSort.clear(); // to prevent duplicates
            for(String[] element: toStore){
                System.out.println(Arrays.toString(element));
                //toDisplay.add(element);
                toSort.add(element[2]); // sends only the "price" values.
                }
            System.out.println("****");
        
        sorter();
    }

    public static String addIncome(String input, String income){
        
        LinkedList<String[]> response = new LinkedList<>();
        
        int x, y, z;
        // converts String numerics into Integers.
        x = Integer.parseInt(input);
        y = Integer.parseInt(income);
        
        z = x + y;
        String newIncome = Integer.toString(z);
        return newIncome;
    }
    
     public static String getExpenses(String price, String income){
        
        int x, y, z;
        
        // Converts String to int for calculation.
        x = Integer.parseInt(price);
        y = Integer.parseInt(income);
        
        if(x > y){ // Prevents user from withdrawing if input > income.
            return "INSUFFICIENT INCOME!";
        }else{
            z = y - x;
            String newIncome = Integer.toString(z); //converts int to String.
            return newIncome; // returns difference.
        }
    }
    
    public static void sorter() {
        
        System.out.println("at Sorter: ");
        for (String element : toSort) { // Using enhanced for loop on ArrayList
            System.out.println(element);
        }
        System.out.println("****");

        if (toSort.size() > 1) { // only activates the bubble sorter if its size is greater than 1.
            System.out.println("Not sorted: ");
            for (String element : toSort) {
                System.out.println(element); // Prints list before sorted.
            }
            System.out.println("****");

            for (int i = 0; i < toSort.size() - 1; i++) {
                for (int j = 0; j < toSort.size() - 1 - i; j++) { // Prevent out-of-bounds
                    int x = Integer.parseInt(toSort.get(j)); // Access elements with get()
                    System.out.println(x);
                    int y = Integer.parseInt(toSort.get(j + 1));
                    System.out.println(y);


                    if (x > y) {

                        String temp = toSort.get(j);
                        toSort.set(j, toSort.get(j + 1)); // Set elements using set()
                        toSort.set(j + 1, temp);
                    }
                }
            }

            System.out.println("Sorted: ");
            for (String element : toSort) {
                System.out.println(element); // Prints list after sorted to console.
            }
            System.out.println("****");
            
            // Sorts "toStore" by index-value macthing and using "toSort" as the order basis.
            for(int i = 0; i < toSort.size(); i++){ // to iterate through toSort.
                for(int j = 0; j < toStore.size(); j++){ // to iterate through toStore.
                    if(toSort.get(i).equals(toStore.get(j)[2])){ // to identify whether prevent "i" value of toSort matches with the 3rd index of "toStore."
                        String[] temp1 = toStore.get(j); // gets and stores values in temp holders.
                        String[] temp2 = toStore.get(i);
                        toStore.set(i, temp1); // swaps values.
                        toStore.set(j, temp2);
                        break; // breaks the loop so the outer can proceed.
                    }
                }
            }
            System.out.println("Sorted Arrays: ");
            for (String[] element : toStore) {
                System.out.println(Arrays.toString(element)); // Prints list after sorted to console.
                sortedArr.add(element);
            }
            System.out.println("****");
        }
    }
    
    public static Stack<String[]> SortedArr(){
        Stack<String[]> response = new Stack<>();
        
        sortedArr.clear();
        ToSort();
          
        for(String[] element : sortedArr){
            toDisplay.add(element);
        }
        
        // checks Stack is empty or not.
        if(sortedArr.size() < 0){
            String[] container = {"EMPTY STACK"};
            response.add(container);
            return response;
        }else{
            return sortedArr; // returns sorted array.
        }
    }

    
    public static Stack<String[]> Memory(String command){
        Stack<String[]> response = new Stack<>();
        
        for(String[] element :  toDisplay){
            System.out.println(Arrays.toString(element));
        }
        
        if(command.equals("undo")){
            if(toDisplay.size() > 0){
                memory.add(toDisplay.pop());
            }else{
                String [] container = {"EMPTY STACK"};
                response.add(container);
                return response;
            }
        }else{
            if(memory.size() > 0){
                toDisplay.add(memory.pop()); 
            }else{
                String [] container = {"EMPTY STACK"};
                response.add(container);
                return response;
            }
        }
        return toDisplay;
    }
}

/*
System.out.println("Rmemory size after removing: " + Rmemory.size() + "\n Redo Memory: "); 
        for(LinkedList<String[]> element: Rmemory){
            for(String[] arr: element){
                System.out.println(" " + Arrays.toString(arr));
            }
        }
        System.out.println("****");
        System.out.println("Umemory size after adding: " + Rmemory.size() + "\n Undo Memory: "); 
        for(LinkedList<String[]> element: Rmemory){
            for(String[] arr: element){
                System.out.println(" " + Arrays.toString(arr));
            }
        }

System.out.println("Umemory size after removing: " + Umemory.size() + "\n Undo Memory: "); 
        for(LinkedList<String[]> element: Umemory){
            for(String[] arr: element){
                System.out.println(" " + Arrays.toString(arr));
            }
        }
        System.out.println("****");
        System.out.println("Rmemory size after adding: " + Umemory.size() + "\n Redo Memory: "); 
        for(LinkedList<String[]> element: Umemory){
            for(String[] arr: element){
                System.out.println(" " + Arrays.toString(arr));
            }
        }
if(x > y){
            String[] newIncome = {"YOU ARE TOO BROKE FOR THAT!"};
            response.add(newIncome);
            return response;
        }else{
            z = y - x;
            String newIncome = Integer.toString(z);
            String [] container = {newIncome, income, input, item};
            
            response.add(new String[]{newIncome, income, input, item});
            
            sortMem.add(new String[]{newIncome, income, input, item});
            
            sorter();
            
            // to track Memory.
            System.out.println("Memory: ");
            for(String[] element: response){
                System.out.println(Arrays.toString(element));
                }
            System.out.println("****");
            
            return response;
        }
z = x + y;
        String newIncome = Integer.toString(z);
        String[] container = {newIncome}; // makes array container String conversion of z.
        response.add(container);
        return response; // returns response after obtaining array.
*/
