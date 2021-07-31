package TrafficControl;

import java.util.ArrayList;

// Junction class
public class Junction extends Point {

	// Junction variable definitions
	private String name;
	public static int junctionID=1;
	public int thisJunctionID;
	private ArrayList<Road> EnterRoads;
	private ArrayList<Road> ExitRoads;
	public int LightTypeNumVal= 1 + (int)(Math.random() * ((8 - 1) + 1));
	private int junctionLightType;
	private Light thisJunctionLight;

	// This function is used to create a junction object
	public Junction(double x, double y) {
		super(x, y);		
		thisJunctionID = junctionID++;
		EnterRoads = new ArrayList<Road>();
		ExitRoads = new ArrayList<Road>();
		thisJunctionLight = null;
		junctionLightType = LightTypeNumVal;
	}
	
	// This function returns the name of the junction 
	public String getName(){
		this.name = "Junction " + thisJunctionID;
		return this.name;
	}
	
	// This returns the junction ID number
	public int getJuncID() {
		return thisJunctionID;
	}
	
	// This function prints the Junction
	public void printJunction(){
		System.out.println( getName() + " at point " + "("+ df.format(x) +","+ df.format(y) +")");
	}

	// Gets the entering roads 
	public ArrayList<Road> getEnter(){
		return EnterRoads;
	}
	
	// Sets the entering roads list
	public void setEnterroad(ArrayList<Road> EnterRoads){
		this.EnterRoads = EnterRoads;
	}
	
	// Gets the exiting roads 
	public ArrayList<Road> getExit(){
		return ExitRoads;
	}
	
	// Sets the exiting roads list
	public void setExitroad(ArrayList<Road> ExitRoads){
		this.ExitRoads = ExitRoads;
	}	
	
	// Gets the junction light
	public Light getLight(){
		return thisJunctionLight;
	}
	
	// Sets the junction light to a value of: Random, Permanent or Null.
	// The decision of which value the junction would get, is decided randomly
	// with only 25% chance of getting a value other than null,
	// out of the lights that are not null there is 50% chance of getting random, and 50% chance of getting Permanent
	public void setLight(Light thisJunctionLight){
		
		// Sets the light type decider number value to a random value in range 1-8 
		junctionLightType = LightTypeNumVal;
		
		// Got any number in range 1-6 -> Junction's light gets null type
		if ((1 <= junctionLightType && junctionLightType <= 6) || (this.getEnter().size()== 0)) {
			thisJunctionLight = null;
		}
		
		// Got 7 -> Junction's light gets permanent type
		else if (junctionLightType == 7 && this.getEnter().size()> 0) {
			thisJunctionLight = new PermanentLight(this);
			System.out.println("Permanent light created at - "+ getName() + " , Delay = " + thisJunctionLight.getDelay()
			+ " , " + "\r\n" + "Current green road : " + thisJunctionLight.getCurrentGreen().getName());
		}
		
		// Got 8 -> Junction's light gets random type
		else if (junctionLightType == 8 && this.getEnter().size()> 0) {
			thisJunctionLight = new RandomLight(this);
			System.out.println("Random light created at - "+ getName() + " , Delay = " + thisJunctionLight.getDelay()
			+ " , " +  "\r\n" + "Current green road : " + thisJunctionLight.getCurrentGreen().getName());
		}
		this.thisJunctionLight = thisJunctionLight;
	}	
}