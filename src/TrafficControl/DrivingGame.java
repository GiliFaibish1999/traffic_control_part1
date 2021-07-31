package TrafficControl;

import java.util.Scanner;

// Driving Game class
public class DrivingGame {
	public static void main(String[] args) {

		// Scanner definition
		Scanner scan = new Scanner(System.in);
		
		// Gets the requested amount of junctions from the user
        System.out.print("Enter the number of junctions you want in your map: ");
        int numJunctions = scan.nextInt();
        
        // Gets the requested amount of vehicles from the user
        System.out.print("Enter the number of vehicles you want in your map: ");
        int numVehicles = scan.nextInt();

        // Creates this game's map according to the amounts of vehicles and junctions from the user's  input
		Map thisGameMap = new Map(numJunctions, numVehicles);
		
		// Gets the requested amount of turns from the user
		System.out.print("Enter the number of turns you would like to play: ");
        int numTurns = scan.nextInt();
        
        // Closing Scanner after the use
        scan.close();
        
        // Plays as many turns as the user requested
        for (int j = 0; j < numTurns; j++) {
        	
        	// Prints announcement for the beginning of this turn
        	System.out.println("\r\n"+"Turn number " + (j+1)  + " : ");
        	
        	// Sets and prints all lights statuses for this turn
        	System.out.println("\r\n"+ "Lights status: ");
        	for (int i = 0; i <= thisGameMap.getMapJunctions().size()-1; i++) {        		
    			if (thisGameMap.getMapJunctions().get(i).getLight() == null) {
    				;
    			}
    			else{
    				System.out.println("\r\n");
    				System.out.println(thisGameMap.getMapJunctions().get(i).getLight().toString());
    				thisGameMap.getMapJunctions().get(i).getLight().check();
    				System.out.println("Delay is " + thisGameMap.getMapJunctions().get(i).getLight().getDelay());
    				System.out.println("Current green road is " + thisGameMap.getMapJunctions().get(i).getLight().getCurrentGreen().getName());
    			}    			
    		}
        	
        	// Sets and prints all vehicles statuses for this turn
        	System.out.println("\r\n"+ "Vehicles status: ");
        	for (int h = 0; h <= thisGameMap.getMapVehicles().size()-1; h++) {
        		thisGameMap.getMapVehicles().get(h).move();
			}
		}
	}
}