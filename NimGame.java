/*NimGame.java
 *This class is used to play a Nimgame.
 *Author: Xixiang Chen
 *Created on 05/05/2015
 */
import java.util.Scanner;
public class NimGame {
/*The NimGame class: This class is used to play a NimGame for the Nimsys. When the Nimsys started,
 * a NimGame object is created and be in "idle" status. When the Nimsys want to run a game, the 
 * NimGame will recieve 4 parameters: the initial total stones, the upperbound of a game, and 2 
 * Nimplayers' record. After finishing the game, it will change the played record for these 2 
 * players.	
 */
    private int currentStone; //the number of currently total stones(start from initial stones) 
    private int upperBound;   //the upper bound of stone removal
    
    private NimPlayer playerA= new NimPlayer();//read info of a player(who removes stone firstly)
    private NimPlayer playerB= new NimPlayer();//read info of a player(who removes stone secondly)
    private NimAIPlayer playerC=new NimAIPlayer();
    private NimAIPlayer playerD=new NimAIPlayer();
    Scanner keyboard = new Scanner(System.in);

    public NimGame(NimPlayer playerA, NimPlayer playerB, int stoneNumber,int upperBound) {
    	this.playerA=new NimPlayer(playerA);
		this.playerB=new NimPlayer(playerB);
		this.currentStone=stoneNumber;
		this.upperBound=upperBound;
	}

	public NimGame() {
		currentStone=0;
	}

	//set StoneAmount method (not used now)
    private void setStoneAmount(Scanner keyboard){
    	this.currentStone=keyboard.nextInt();
    }
    
    //set setUpperBoundt method (not used now)
    private void setUpperBound(Scanner keyboard){
    	this.upperBound=keyboard.nextInt();
    }
    
    /*The setStoneRemove method: It can read how many stones a user want to move. And it check
     * if the number is legal. It also read whose turn it is (therefore it can display whose turn
     * when the number check can not pass.
    */
    private void setStoneRemove(Scanner keyboard, NimPlayer playerX){
    	while (true){
	    	String s = keyboard.nextLine();
	    	playerX.setNumOfRemove(Integer.parseInt(s));
	    	if ((playerX.getNumOfRemove()>this.currentStone)||playerX.getNumOfRemove()<=0){
	    		System.out.println();
	    		System.out.println("Invalid move. You must remove between 1 and "+this.currentStone
	            		+" stones.");
	    		System.out.println();
	    		System.out.print(currentStone+ " stones left:");
	    		for (int i=currentStone; i>0; i--)
			    {
				    System.out.print(" *");
			    }
	    		System.out.println();
	    		System.out.print(playerX.getGivenName());
			    System.out.println("'s turn - remove how many?");
	    	}
	    	
	    	else if ((playerX.getNumOfRemove()>this.upperBound)||(playerX.getNumOfRemove()<=0)){
	    		System.out.println();
	            System.out.println("Invalid move. You must remove between 1 and "+this.upperBound
	            		+" stones.");
	            System.out.println();
	    		System.out.print(currentStone+ " stones left:");
	    		for (int i=currentStone; i>0; i--)
			    {
				    System.out.print(" *");
			    }
	    		System.out.println();
	    		System.out.print(playerX.getGivenName());
			    System.out.println("'s turn - remove how many?");
	    	}
	    	else
	    		break;		
    	}	
    }
    
    //Two getNimplayer method.
    public NimPlayer getPlayerA(){
    	return playerA;
    }
    
    public NimPlayer getPlayerB(){
    	return playerB;
    }
    
    /*The runGame method: It encapsulate several small method together as a "runGame" method to 
     * play a game. It can read 2 players information and initial game information, and then it 
     * run and display message while the game is running.After the a game, it change these 2 
     * players information and its status change from "in process" to "idle".
     */
    public void runGame(int totalStone, int upbound, NimPlayer player1, NimPlayer player2){
    	this.currentStone = totalStone;
		this.upperBound = upbound;
		playerA=player1;
		playerB=player2;
		
		System.out.println("");
		System.out.println("Initial stone count: "+totalStone);
		System.out.println("Maximum stone removal: "+upperBound);
		System.out.println("Player 1: "+player1.getGivenName()+" "+player1.getFamilyName());
		System.out.println("Player 2: "+player2.getGivenName()+" "+player2.getFamilyName());
		System.out.println("");
		
		
		while (currentStone>=0)
		{
		    System.out.print(currentStone+ " stones left:");
		    for (int i=currentStone; i>0; i--)
		    {
			    System.out.print(" *");
		    }
		    System.out.println();
		    System.out.print(player1.getGivenName());
		    System.out.println("'s turn - remove how many?");
		    this.setStoneRemove(keyboard,player1);
		    currentStone = currentStone - player1.getNumOfRemove();
		    System.out.println();
		    if (currentStone<=0)        //check if someone win
		    {
		    	System.out.println("Game Over");
		    	System.out.print(player2.getGivenName()+" "+player2.getFamilyName());
		    	System.out.println(" wins!");
		    	player2.addGamePlayed();
		    	player2.addGameWon();
		    	player1.addGamePlayed();
		    	break;
		    }
		    
		    /*switch the turn for another player. */
		    
		    System.out.print(currentStone + " stones left:");
		    for (int i=currentStone; i>0; i--)
		    {
			    System.out.print(" *");
		    }
		    System.out.println();
		    System.out.print(player2.getGivenName());
		    System.out.println("'s turn - remove how many?");
		    this.setStoneRemove(keyboard,player2);
		    currentStone = currentStone - player2.getNumOfRemove();
		    System.out.println();
		    if (currentStone<=0)
		    {
		    	System.out.println("Game Over");
		    	System.out.print(player1.getGivenName()+" "+player1.getFamilyName());
		    	System.out.println(" wins!");
		    	player1.addGamePlayed();
		    	player1.addGameWon();
		    	player2.addGamePlayed();
		    	break;
		    }	
		}
    }
}
