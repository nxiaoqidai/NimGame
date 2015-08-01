/* NimPlayer.java
 * This class stores some information of players and its method
 * Author: Xixiang Chen
 * Created on 05/05/2015
 */
import java.util.Scanner;
public class NimPlayer 
{
/*The Nimplayer class: this class have several functions: it can receive and stored the players
 *from Nimsys. It can just mainly store some instance variables and its "getMethod".
 * 
 */
	private String username;      //player's username which should be set before using.
	private String givenName;     //Player's givenName
	private String familyName;    //player's familyName
	private int gamePlayed;       //player's total played games number
	private int gameWon;  		  //player's total win games number
	private int numOfRemove;
	private String type="NimPlayer"; //father class type
	
	public String getType(){
		return this.type;
	}
	
	public NimPlayer(String username, String givenName,String familyName){
		this.username=username;
		this.givenName=givenName;
		this.familyName=familyName;
	}
	public NimPlayer(NimPlayer playerA){
		
	}

	public NimPlayer() {
		username="";
		givenName="";
		familyName="";
		gamePlayed=0;
		gameWon=0;// TODO Auto-generated constructor stub
	}

	public void setNumOfRemove(int number){
		this.numOfRemove=number;
	}
	
	public int getNumOfRemove(){
		return this.numOfRemove;
	}
	
	public void setGamePlayed(int num){
		this.gamePlayed=num;
	}
	
	public void setGameWon(int num){
		this.gameWon=num;
	}
	
	//set GivenName method
	public void setGivenName(String givenName){
		this.givenName=givenName;
	}
	
	//set Username method
	public void setUsername(String username){
		this.username = username;
	}
	
	//set family name method	
	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}
	
	//get username method
	public String getUsername(){
		return this.username;
	}
	
	//get given name method
	public String getGivenName(){
		return this.givenName;
	}
	
	//get family name method
	public String getFamilyName(){
		return this.familyName;
	}
	
	//get the total games number method
	public int getGamePlayed(){
		return this.gamePlayed;
	}
	
	//get the total games had won method
	public int getGameWon(){
		return this.gameWon;
	}
	
	//reset user game record method
	public void resetStats(){
		this.gamePlayed = 0;
		this.gameWon = 0;
	}
	
	//when a player play a game, it game record + 1
	public void addGamePlayed(){
		this.gamePlayed+=1;
	}
	
	
	//when a player play and win a game, its game won record +1
	public void addGameWon(){
		this.gameWon+=1;
	}
	

}
