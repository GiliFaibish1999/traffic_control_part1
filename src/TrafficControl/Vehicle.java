package TrafficControl;

import java.util.ArrayList;

// Vehicle class
public class Vehicle {
	
	// Vehicle variables definitions
	public int speedVal=  30 + (int)(Math.random() * ((120 - 30) + 1));
	protected int vehicleSpeed;
	private String name;
	public static int VehicleID=1;
	public int thisVehicleID;
	private int counter;
	private int lastRoadIndex;
	private int currentRoadIndex;
	private ArrayList<Junction> trackJunctions;
	private ArrayList<Road> trackRoads;
	private Road currentRoad;
	
	// This function creates a vehicle according to the map
	public Vehicle(Map map) {
		thisVehicleID = VehicleID++;
		
		// Sets vehicle speed
		vehicleSpeed = speedVal;
		
		// Prints vehicle name and speed
		System.out.println("\r\n" + this.getName() + " was created, Speed of " + this.getName() + " is " + vehicleSpeed);
		
		// Creates the vehicle's track and collects that track's necessary data
		thisVehicleTrack(map, this);
		setTrackJunctions(map);
		trackJunctions = getTrackJunctions();
		setTrackRoads(map);
		trackRoads = getTrackRoads();
		
		// Settings for the current and last road indexes that are later used for the "move" function 
		currentRoadIndex =0;
		lastRoadIndex = trackRoads.size()-1;
	}
	
	// This function creates a track for the vehicle
	public void thisVehicleTrack(Map map, Vehicle vehicle) {
		map.createTrack(vehicle);
	}
	
	// Gets the list of junctions in this vehicle's track
	public ArrayList<Junction> getTrackJunctions(){
		return trackJunctions;
	}
	
	// Sets the list list of junctions in this vehicle's track
	public void setTrackJunctions(Map map){
		this.trackJunctions = map.getTrackJunctions();
	}	
	
	// Gets the list of roads in this vehicle's track
	public ArrayList<Road> getTrackRoads(){
		return trackRoads;
	}
	
	// Sets the list of roads in this vehicle's track
	public void setTrackRoads(Map map){
		this.trackRoads = map.getTrackRoads();
	}	

	
	// This function returns the name of the vehicle 
	public String getName(){
		this.name = "Vehicle " + thisVehicleID;
		return this.name;
	}
	
	// This function moves the vehicle on the track, and prints the vehicle's current position
	// in the track each time it is used
	public void move() {
		
		// Counter for progress in the road is up by one
		counter++;
		
		// Sets the current road the vehicle is on
		currentRoad = trackRoads.get(currentRoadIndex);
		
		// Acts according to the vehicle position in the track, according to 4 possible cases
		
		// Case 1 - vehicle is in the middle of a road that is not the last road on it's track
		if ((((currentRoad.RoadLength)-(counter*vehicleSpeed))>0) && (currentRoadIndex < lastRoadIndex)) {
			
			// Prints the vehicle current position
			System.out.println("\r\n" + this.getName() + " is now on " + currentRoad.getName() + "\r\n" + 
					"Which is road number " + (currentRoadIndex+1) + " on it's track." + "\r\n" + 
					"The vehicle passed " + (counter*vehicleSpeed) + " of this road already, " + "\r\n" + 
					"And has " + ((currentRoad.RoadLength)-(counter*vehicleSpeed)) + " left to finish the road");
		}
		
		// Case 2 - vehicle finished a road that is not the last road on it's track
		else if ((((currentRoad.RoadLength)-(counter*vehicleSpeed))<=0) && (currentRoadIndex < lastRoadIndex)) {
			
			// Prints the vehicle current position
			System.out.println("\r\n" + this.getName() + " just finished " + currentRoad.getName() + "\r\n" + 
					"Which was road number " + (currentRoadIndex+1) + " on it's track." + "\r\n" +
					 "On the next turn, the vehicle would continue to road number "
					 + (currentRoadIndex+1) + " on it's track");
			
			// Sets the current road index for the next road
			currentRoadIndex++;
			
			// Sets the counter for progress in the road to zero
			counter=0;
		}
		
		// Case 3 - vehicle is in the middle of the last road on it's track
		else if ((((currentRoad.RoadLength)-(counter*vehicleSpeed))>0) && (currentRoadIndex == lastRoadIndex)) {
			
			// Prints the vehicle current position
			System.out.println("\r\n" + this.getName() + " is now on " + currentRoad.getName() + "\r\n" + 
					"Which is the last road on it's track." + "\r\n" + 
					"The vehicle passed " + (counter*vehicleSpeed) + " of this road already, " + "\r\n" + 
					"And has " + ((currentRoad.RoadLength)-(counter*vehicleSpeed)) + " left to finish the road");
		}
		
		// Case 4 - vehicle finished it's track
		else if ((((currentRoad.RoadLength)-(counter*vehicleSpeed))<=0) && (currentRoadIndex == lastRoadIndex)) {
			
			// Prints the vehicle current position
			System.out.println("\r\n" + this.getName() + " Finished it's track!");
		}
	}
}