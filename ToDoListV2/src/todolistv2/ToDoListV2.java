package todolistv2;

import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.Stack;

public class ToDoListV2 {

    private static LinkedList<String> taskNotDone = new LinkedList<>();
    private static LinkedList<String> taskDone = new LinkedList<>();
    private static Stack<Object[]> memory = new Stack<>();
    
    public static void main(String[] args) {
        MainMenu();
    }
    
    public static void MainMenu(){
        
        String[] options = {"add task", "remove task", "edit task", "mark as done", "undo"};
        
        String notDone = taskGetter("getTaskToDo");
        String Done = taskGetter("getTasksDone");
        
        String message = "Not Done:\n" + notDone + "\nDone: \n" + Done;
        
        int choice = JOptionPane.showOptionDialog(
                null,
                message,
                "To-Do List",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        
        switch(choice){
            case 0:
                addTask();
                break;
            case 1:
                removeTask();
                break;
            case 2:
                editTask();
                break;
            case 3:
                markDone();
                break;
            case 4:
                undo();
                break;
            default:
                System.out.println("User did not choose anything. Exiting....");
                break;
        }
    }
    
    public static void addTask(){
        String task = JOptionPane.showInputDialog(null, "Write down your task...");
        
        if(task != null && task.equals("")){
            JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
            addTask();
        } else if(task == null){
            MainMenu();
        } else {
        memory.add(new Object[]{"addTask",task});
        taskNotDone.add(task);
        System.out.print(taskNotDone);
        MainMenu();
        }
    }

    public static void removeTask(){      
        if(taskNotDone.size() <= 0){
            JOptionPane.showMessageDialog(null, "You have no tasks! Please make at least one and try again.");
            MainMenu();
        }else {
            String tasksAtHand = taskGetter("getTaskToDo");
            String choiceToInt = JOptionPane.showInputDialog(null, "Select Which tasks would you like to remove.\n" + tasksAtHand);
            
            // to catch invalid entries and be able to return to MainMenu.
            if(choiceToInt != null && choiceToInt.equals("")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                removeTask();
            } else if(choiceToInt != null && !choiceToInt.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                removeTask();
            }else if(choiceToInt == null){
                MainMenu();
            } else {
            
                int choicetoIndex = Integer.parseInt(choiceToInt);
                int choice = choicetoIndex - 1;

                // to catch invalid entries and remove desired task.
                if(choice > taskNotDone.size() || choice < 0){
                    JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                    removeTask();
                }else {
                    memory.add(new Object[]{"removeTask", choice, taskNotDone.get(choice)});
                    taskNotDone.remove(choice);
                    MainMenu();
                }
            }
        }
    }
    
    public static void editTask(){
        if(taskNotDone.size() <= 0){
            JOptionPane.showMessageDialog(null, "You have no tasks! Please make at least one and try again.");
            MainMenu();
        }else {
            String tasksAtHand = taskGetter("getTaskToDo");
            String choiceToInt = JOptionPane.showInputDialog(null, "Select Which tasks would you like to Edit.\n" + tasksAtHand);
            
            // to catch invalid entries and be able to return to MainMenu.
            if(choiceToInt != null && choiceToInt.equals("")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                editTask();
            } else if(choiceToInt != null && !choiceToInt.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                editTask();
            }else if(choiceToInt == null){
                MainMenu();
            } else {

                int choicetoIndex = Integer.parseInt(choiceToInt);
                int choice = choicetoIndex - 1;

                // to catch invalid entries and remove desired task.
                if(choice > taskNotDone.size() || choice < 0){
                    JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                    editTask();
                }else {
                    String newTask = JOptionPane.showInputDialog(null, "Write replacement task.");
                    memory.add(new Object[]{"editTask", choice, taskNotDone.get(choice)});
                    taskNotDone.set(choice, newTask);
                    MainMenu();
                }
            }
        }
    }
    
    public static void markDone(){
        if(taskNotDone.size() <= 0){
            JOptionPane.showMessageDialog(null, "You have no tasks! Please make at least one and try again.");
            MainMenu();
        }else {
            String taskToDo = taskGetter("getTaskToDo");
            String choiceToInt = JOptionPane.showInputDialog(null, "Select Which tasks have you completed.\n" + taskToDo);
            
            // to catch invalid entries and be able to return to MainMenu.
            if(choiceToInt != null && choiceToInt.equals("")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                markDone();
            } else if(choiceToInt != null && !choiceToInt.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                markDone();
            }else if(choiceToInt == null){
                MainMenu();
            } else {
            
                int choicetoIndex = Integer.parseInt(choiceToInt);
                int choice = choicetoIndex - 1;

                // to catch invalid entries and remove desired task.
                if(choice > taskNotDone.size() || choice < 0){
                    JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!");
                    markDone();
                }else {
                    String markedDone = taskNotDone.get(choice);
                    memory.add(new Object[]{"markedDone", choice, markedDone});
                    taskDone.add(markedDone);
                    taskNotDone.remove(choice);
                    MainMenu();
                }
            }
        }
    }
    
    public static void undo(){
        
        // taskes last element of stack and first element of the object array.
        Object[] container = memory.pop();
        String command = container[0].toString();
        
        // runs the first element of obj arr into a swicth case method.
        switch(command){
            case "undo":
                break;
            case "addTask":
                taskNotDone.remove(container[1].toString());
                MainMenu();
                break;
            case "removeTask":
                taskNotDone.add(Integer.parseInt(container[1].toString()), container[2].toString());
                MainMenu();
                break;
            case "editTask":
                taskNotDone.set(Integer.parseInt(container[1].toString()), container[2].toString());
                MainMenu();
                break;
            case "markedDone":
                taskNotDone.add(Integer.parseInt(container[1].toString()), container[2].toString());
                taskDone.remove(container[2].toString());
                MainMenu();
                break;
            default:
                System.out.println("Does not have any actions in stack. Exiting....");
                MainMenu();
                break;
        }
    }
    
    public static String taskGetter(String command){
        
        // initializes compounder and counter variables.
        String compounder = "";
        int  counter = 1;
        
        // runs the command value through a switch case.
        switch(command){
            case "getTaskToDo":
        
                for(String element : taskNotDone){
                compounder += counter + ". " + element + "\n";
                counter++;
                }
                break;
            case "getTasksDone":
        
                for(String element : taskDone){
                compounder += counter + ". " + element + "\n";
                counter++;
                }
                break;
            default:
                System.out.println("User did not choose anything. Exiting....");
                break; 
        }
        
        String taskGot = compounder;
        return taskGot;
    }
}