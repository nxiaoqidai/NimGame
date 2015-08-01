/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
	
	Coded by: Jin Huang
	Modified by: Jianzhong Qi, 29/04/2015
*/
import java.math.*;
public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the task in Section 2.3	
	private String type = "NimAIPlayer";  //override the father class type
	
	public String getType(){
		return this.type;
	}
		
	public NimAIPlayer(String username, String givenName,String familyName){
		super(username,givenName,familyName);
	}
	public NimAIPlayer(){
		super();
	}
	
	//remove method for ai player
	public int removeStone(int stone, int upperBound){
		if ((stone-1)%(upperBound+1)!=0)
			return (stone-1)%(upperBound+1);
		else
			return ((int)(Math.random()*((stone>upperBound)?upperBound:stone))+1);
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
