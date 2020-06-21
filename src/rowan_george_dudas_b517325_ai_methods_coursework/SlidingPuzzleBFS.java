/*
 *  Author: Rowan George Dudas (B517325)
 */

package rowan_george_dudas_b517325_ai_methods_coursework;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SlidingPuzzleBFS {
    // Queue used to store all of the nodes when solving the puzzle
    Queue<String> queue = new LinkedList<>();   
    // Hashmap used to store non-repeated nodes and to also store the depth of visited levels of the tree 
    Map<String,Integer> depth = new HashMap<>(); 
    // Hashmap used to store visited states 
    Map<String,String> pastStates = new HashMap<>(); 
    
    // Add method to add the new state to the hashmap and queue
    public void add(String newState, String prevState) {
      int newElement;

      if(!depth.containsKey(newState)) {                
        if (prevState == null) {                        
          newElement = 0;
        }
        else {
          newElement = depth.get(prevState) + 1;        
        }
        
        depth.put(newState, newElement);        // Give the newState a key and associate it with the value of newElement
        queue.add(newState);                    // Add the newState to the linkedlist called queue
        pastStates.put(newState, prevState);    // Give the newState a key and associate it with the value of prevState
      }
    }
    
    /* The four methods below take the present state, look for i and move it around to look for the goal state. 
     * After each individual method is run, the presentState and nextState is then put through the goalChecker
     * to see whether the goal state has been met.
     */
    
    // Method to move the blank character '0' up
    public void blank_char_up(String presentState) {
      int i = presentState.indexOf("0");

      if(i > 2) {
        String nextState = presentState.substring(0, i - 3) + "0" + presentState.substring(i - 2, i) + presentState.charAt(i - 3) + presentState.substring(i + 1);
        goalCheck(presentState, nextState);
      }
    }
    
    // Method to move the blank character '0' left
    public void blank_char_left(String presentState) {
      int j = presentState.indexOf("0");

      if(j != 0 && j != 3 && j != 6) {
        String nextState = presentState.substring(0, j - 1) + "0" + presentState.charAt(j - 1) + presentState.substring(j + 1);
        goalCheck(presentState, nextState);
      }
    }
    
    // Method to move the blank character '0' right
    public void blank_char_right(String presentState) {
      int k = presentState.indexOf("0");

      if(k !=2 && k != 5 && k != 8) {
        String nextState = presentState.substring(0, k) + presentState.charAt(k + 1) + "0" + presentState.substring(k + 2);
        goalCheck(presentState, nextState);
      }
    }
 
    // Method to move the blank character '0' down
    public void blank_char_down(String presentState) {
      int l = presentState.indexOf("0");

      if(l < 6) {
        String nextState = presentState.substring(0, l) + presentState.substring(l + 3, l + 4) + presentState.substring(l + 1, l + 3) + "0" + presentState.substring(l + 4);
        goalCheck(presentState, nextState);
      }
    }

    /*
     *  Method to check whether the inital state has met the goal state. If the inital state has met the goal state,
     *  every move that has been made will be printed along with information on what level in the tree that that move was made.
     *  The format of each state at each level will be printed in a 3x3 matrix style to represent the puzzle.
     */
    public void goalCheck(String prevState, String newState) {
      add(newState, prevState);
        
      if(newState.equals("123405678")) {
        System.out.println("A solution exists in the tree at level " + depth.get(newState) + "\n");
        String traceState = newState;
        
        while (traceState != null) {
          System.out.println(traceState.substring(0, 3) + "\n" + traceState.substring(3, 6) + "\n" + traceState.substring(6, 9) + "   at depth " + depth.get(traceState) + "\n");
          traceState = pastStates.get(traceState);
        }
        System.exit(0);
      }
    }

    public static void main(String args[]) {
        // Instructions for the user and initial state prompt
        System.out.println("Instructions:"+"\n"+"\n"+"Please enter a 9 character long string when prompted."+"\n"+"Make sure that the string is just numbers, and ranges from 0-8 (0 represents the blank space in the puzzle)"+"\n"+"The first three characters are the three values on row one."+"\n"+"The next three characters are the three values for row two of the puzzle"+"\n"+ "The last three values are the three values for row three of the puzzle."+"\n"+"\n"+"Please enter the initial state of the puzzle:");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        // Goal state prompt
        System.out.println("Plese enter the goal state:");
        Scanner scan = new Scanner(System.in);
        String goalState = scan.nextLine();
        scan.close();
        
        // Creating the new instance of the puzzle
        SlidingPuzzleBFS puzzle = new SlidingPuzzleBFS();

        // Adding the initial state to the method called add
        puzzle.add(str, null);
        
        /* While the list 'queue' is not equal to null, retrieve and remove the head of the list 'queue'.
         * Then run the methods to move the '0' character around the puzzle to test different combinations until the goal
         * state is met.
         */
        while(puzzle.queue != null) {
            String presentState = puzzle.queue.remove();
            puzzle.blank_char_up(presentState);                                       
            puzzle.blank_char_left(presentState);                                     
            puzzle.blank_char_right(presentState);
            puzzle.blank_char_down(presentState);                   
        }
        
       // If the goal state is not met, then print that a solution does not exist.
        if(puzzle.queue == null) {
            System.out.println("Sorry, a solution does not exist.");
        }
    }
}