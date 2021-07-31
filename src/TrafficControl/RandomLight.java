package TrafficControl;

import java.util.Random;

// Random Light class
public class RandomLight extends Light{

	// Parent definitions
	public RandomLight(Junction junction) {
		super(junction);
	}
	
	// Green light function at random light
	public void greenLight() {
		int size=getJunction().getEnter().size();
		Random r = new Random();
		int randIndex= r.nextInt(size);
		Road myRoad =getJunction().getEnter().get(randIndex);
		setCurrentGreen(myRoad);
	}
	
	// Converts to string
	@Override
	public String toString() {
		return "Random light at "+ this.getJunction().getName() + " , ID : "+super.toString();
	}
}