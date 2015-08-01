//import testPackage.NimPlayer;
import java.io.*;
import java.util.Scanner;

/*Nimsys.java
 *This class controls the overall Nim game process
 *Author: Xixiang Chen
 *Created on 05/05/2015
 */

public class Nimsys {
/*The Nimsys class: This class is the main controlling system for the NimGame. It provides people
 *a command line for interaction. It has 8 kinds of functions. They are: addplayer, removeplayer,
 *editplayer, resetstats, displayplayer, startgame, rankings and exit. The system can react well 
 *according what command does poeple input.
 */

	public static NimPlayer[] playerRecord = new NimPlayer[100];//A Nimsys has up to 100 players
	public static NimGame game = new NimGame(); //A Nimsys has a NimGame when it starts
	
	
	/*The startgame method: It input total stone, upperbound of the stone remove, and two player
	 *as parameters to run the game(change the game status from 'idle' to 'in process'). After 
	 *finish playing the game, the game change the players' record.
	 */
	private static void startGame(int totalStone, int upbound, String user1, String user2){
		boolean checkUser1 = false;
		boolean checkUser2 = false;
		int index1 = 0;
		int index2 = 0;
		for (int i=0; i<100; i++){
			if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(user1))){
				checkUser1 = true;
				index1=i;
			}

			if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(user2))){
				checkUser2 = true;
				index2=i;
			}	
		}
		if (checkUser1&&checkUser2){
			game.runGame(totalStone, upbound, playerRecord[index1], playerRecord[index2]);
		}
		else{
			System.out.println("One of the players does not exist.");
		}
	}
	
	/*The addplayer method: It can initial a player record by inputing its username,familyname 
	 * and givenname. 
	 */
	private static void addPlayer(String username, String familyName, String givenName){
		for (int i=0; i<100; i++){
			if ((playerRecord[i] != null)&&(playerRecord[i].getUsername().equals(username))){
				System.out.println("The player already exists.");
				break;	
			}
			else if (playerRecord[i] == null){
				playerRecord[i]=new NimPlayer();
				playerRecord[i].setUsername(username);
				playerRecord[i].setFamilyName(familyName);
				playerRecord[i].setGivenName(givenName);
				
		        break;
			}    
	    }
	}
	
	//The addaiplayer method: It can initial a AI player
	public static void addaiPlayer(String username, String familyName, String givenName){
		for (int i=0; i<100; i++){
			if ((playerRecord[i] != null)&&(playerRecord[i].getUsername().equals(username))){
				System.out.println("The player already exists.");
				break;	
			}
			else if (playerRecord[i] == null){
				playerRecord[i]=new NimAIPlayer();
				playerRecord[i].setUsername(username);
				playerRecord[i].setFamilyName(familyName);
				playerRecord[i].setGivenName(givenName);
				
		        break;
			}    
	    }
	}
	
	/*The removeplayer method: It can remove a player record by inputing its username. When people
	 * does not input username, it can remove all the player records. If a username does not exist
	 * the system will print a "non-exist" message. 
	 */
	private static void removePlayer(String username){
		if (username.length() ==0){
			System.out.println("Are you sure you want to remove all players? (y/n)");
			username = game.keyboard.nextLine();
			if (username.equals("y")){
				for (int i=0; i<100; i++){
					playerRecord[i]=null;
					if (i==99)
						break;
				}
			}
		}
		else {
			username = username.substring(1);
		    //System.out.println(username);
			for (int i=0; i<100; i++){ 
				if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(username))){
					for (int j=i; j<99; j++){
						playerRecord[j]=playerRecord[j+1];
					}
					playerRecord[99]=null;
				    break;    
				}
				if (i==99)
				    System.out.println("The player does not exist.");
			}
		}	
	}
	
	/*The editPlayer method: It can edit a player's family name and given name. But people can not 
	 * change it's username. When people trys to edit a non-exist player the system will print a 
	 * message. 
	 */
	private static void editPlayer(String username, String familyName, String givenName){
		for (int i=0; i<100; i++){
			if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(username))){
				playerRecord[i].setFamilyName(familyName);
				playerRecord[i].setGivenName(givenName);
				break;
			}
			else if((playerRecord[99]==null)||(playerRecord[99]!=null)
					&&(!(playerRecord[99].getUsername().equals(username)))){
				System.out.println("The player does not exist.");
				break;
			}
		}
	}
	
	/*The resetstats method: It can reset someone's playing record(The gamePlayed and the gameWon
	 * reset to zero). If people try to input a non-exist user it will print a message.
	 */
	private static void resetStats(String username){
		if (username.length()==0){
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			username = game.keyboard.nextLine();
			if (username.equals("y")){
				for (int i=0; i<100; i++){
					if (playerRecord[i]!=null)
						playerRecord[i].resetStats();
					else
						break;
				}
			}
		}
		else{
			username = username.substring(1);
			for (int i=0; i<100; i++){
				if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(username))){
					playerRecord[i].resetStats();
					break;
				}
			}
		}	
	}

	/*The displayplayer method: It can display one player with all its information by ASCii of 
	 * username sorting.
	 * 
	 */
	private static void displayPlayer(String username){
		if (username.length()==0){
			if (playerRecord[0]==null){
				System.out.println("");
				System.out.println(">");
				System.exit(0);
			}	
			int m = 0;
			for(int i=0;i<100;i++){
				if (playerRecord[i]!=null){				
					m+=1;				
				}
				else{	
					break;
				}		
			}
			
			for (int i=0;i!=m-1;i++){
				for (int j=i+1;j!=m;j++){
					if (playerRecord[i].getUsername().compareTo(playerRecord[j].getUsername())>0){
						NimPlayer temp=playerRecord[i];
						playerRecord[i]=playerRecord[j];
						playerRecord[j]=temp;
					}
				}
			}
			
			for (int i=0; i<100; i++){
				if (playerRecord[i]!=null){
					System.out.println(playerRecord[i].getUsername()+","
							+playerRecord[i].getGivenName()+","+playerRecord[i].getFamilyName()+","
							+playerRecord[i].getGamePlayed()+" games,"
							+playerRecord[i].getGameWon()+" wins");
				}
				else
					break;
			}
		}
		else{
			username = username.substring(1);
			for (int i=0; i<100; i++){
				if ((playerRecord[i]!=null)&&(playerRecord[i].getUsername().equals(username))){
					System.out.println(playerRecord[i].getUsername()+","
							+playerRecord[i].getGivenName()+","+playerRecord[i].getFamilyName()+","
							+playerRecord[i].getGamePlayed()+" games,"
							+playerRecord[i].getGameWon()+" wins");
					break;
				}	
				if (i==99)
					System.out.println("The player does not exist.");
			}
		}
	}
	
	/*The rankings method. This method can display up to 10 players information sorted
	 * by the ratio of its winning record.
	 * Using selection sort.
	 */
	private static void rankings(){
		//fistly read how many users
		int m=0;
		for(int i=0;i<100;i++){
			if (playerRecord[i]!=null){				
				m+=1;				
			}
			else{	
				break;
			}		
		}
		
		//selection sort by winning rate.
		for (int i=0;i!=m-1;i++){
			double ir ;
			double jr ;
			if (playerRecord[i].getGamePlayed()==0){
				ir =0f;
			}
			else{
				ir = (double)playerRecord[i].getGameWon()/playerRecord[i].getGamePlayed();
			}
			for (int j=i+1;j!=m;j++){
				if (playerRecord[j].getGamePlayed()==0){
					jr =0f;
				}
				else{
					jr =(double) playerRecord[j].getGameWon()/playerRecord[j].getGamePlayed();
				}			
					 
				if (ir<jr)
				{
					NimPlayer temp=playerRecord[i];
					playerRecord[i]=playerRecord[j];
					playerRecord[j]=temp;
					double temp1;
					temp1=ir;
					ir=jr;
					jr=temp1;
				}
				else if(ir==jr){
					if (playerRecord[i].getUsername().compareTo(playerRecord[j].getUsername())>0){
						NimPlayer temp=playerRecord[i];
						playerRecord[i]=playerRecord[j];
						playerRecord[j]=temp;
					}
				}
				
			}
		}
		
		//now display players
		for (int i=0; i<100; i++){
			if (playerRecord[i]!=null){
				long c;
				if (playerRecord[i].getGamePlayed()==0){
					c =0;
				}
				else {
					c= Math.round(((double)playerRecord[i].getGameWon()
							/playerRecord[i].getGamePlayed()*100));
				}
				if (playerRecord[i].getGamePlayed()>=10)
				    System.out.printf("%-5s| %-2d",c+"%",(playerRecord[i].getGamePlayed()));
				else
					System.out.printf("%-5s| 0%-1d",c+"%",(playerRecord[i].getGamePlayed()));
				System.out.println(" games | "+playerRecord[i].getGivenName()+" "
						+playerRecord[i].getFamilyName());
		    }
			
		}
	}
	
	/*This is the main method of the nimgame system. When this system started, the command line 
	 * should always willing to read the command of the system user and react appropriately. When
	 * the user type exit. The system stop. 
	 */
	public static void main(String[] args){
		
		//welcome massage and the command line
		System.out.println("Welcome to Nim");
		System.out.println();
		
		
		//create file
		File datafile = new File("players.txt");
		try{
			if(!datafile.exists())
				datafile.createNewFile();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}	
		
		//read the data file before the system run
		try{
			game.keyboard = new Scanner (new FileInputStream("players.txt"));
			while (game.keyboard.hasNextLine()){
				String playerInfo = game.keyboard.nextLine();
				
				String playerType=playerInfo.substring(0,playerInfo.indexOf(","));
				playerInfo = playerInfo.substring(playerInfo.indexOf(",")+1);
				String usernameData = playerInfo.substring(0,playerInfo.indexOf(","));
				playerInfo = playerInfo.substring(playerInfo.indexOf(",")+1);
				String givennameData=playerInfo.substring(0,playerInfo.indexOf(","));
				playerInfo = playerInfo.substring(playerInfo.indexOf(",")+1);
				String familynameData=playerInfo.substring(0,playerInfo.indexOf(","));
				playerInfo=playerInfo.substring(playerInfo.indexOf(",")+1);
			    int gamesData=Integer.parseInt(playerInfo.substring(0,playerInfo.indexOf(",")));
			    playerInfo=playerInfo.substring(playerInfo.indexOf(",")+1);
			    int winsData=Integer.parseInt(playerInfo);
			    
			    if (playerType.equals("NimPlayer")){
			    	addPlayer(usernameData,familynameData,givennameData);
			    	for (int i=0; i<100; i++){
						if ((playerRecord[i]!=null)&&
								(playerRecord[i].getUsername().equals(usernameData))){
							playerRecord[i].setGamePlayed(gamesData);
							playerRecord[i].setGameWon(winsData);
							break;
						}
			    	}
			    }
			    else if (playerType.equals("NimAIPlayer")){
			    	addaiPlayer(usernameData,givennameData,familynameData);
			    	for (int i=0; i<100; i++){
						if ((playerRecord[i]!=null)&&
								(playerRecord[i].getUsername().equals(usernameData))){
							playerRecord[i].setGamePlayed(gamesData);
							playerRecord[i].setGameWon(winsData);
							break;
						}
			    	}
			    }    
			}
			game.keyboard.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			//System.exit(0);
		}
		
		game.keyboard = new Scanner(System.in);
		System.out.print(">");
		String command = game.keyboard.nextLine();
		//start a infinite loop until user input "exit"
		while(true){
			try{
				String[] check = command.split("\\ ");
				if (!((check[0].equals("addplayer")) || (check[0].equals("removeplayer")) 
					||(check[0].equals("editplayer")) || (check[0].equals("resetstats")) 
					||(check[0].equals("displayplayer"))||(check[0].equals("startgame"))
					||(check[0].equals("rankings"))||(check[0].equals("exit"))
					||(check[0].equals("addaiplayer"))))
				
						throw new Exception("'"+check[0]+"' is not a valid command.");	
				else if (check[0].equals("addplayer")||check[0].equals("editplayer")){
					String tempCommand;
					tempCommand = command.substring(10);
					check = tempCommand.split("\\,");
					if (check.length!=3)
						throw new Exception("Incorrect number of arguments supplied to command.");
				}
				else if (check[0].equals("addaiplayer")){
					String tempCommand;
					tempCommand = command.substring(12);
					check=tempCommand.split("\\,");
					if (check.length!=3)
						throw new Exception("Incorrect number of arguments supplied to command.");
				}
				else if (check[0].equals("editplayer")){
					String tempCommand;
					tempCommand = command.substring(11);
					check = tempCommand.split("\\,");
					if (check.length!=3)
						throw new Exception("Incorrect number of arguments supplied to command.");
				}
				else if (check[0].equals("startgame")){
					String tempCommand;
					tempCommand = command.substring(10);
					check = tempCommand.split("\\,");
					if (check.length!=4)
						throw new Exception("Incorrect number of arguments supplied to command.");
				}
			}
			
			catch(Exception e){
				String message =e.getMessage();
				System.out.println(message);
			}
			
			if ((command.length()>10)&&(command.substring(0, 10).equals("addplayer "))){
				command = command.substring(10);
				String[] value = command.split("\\,");
				if (value.length==3)
					addPlayer(value[0],value[1],value[2]);	
			}
			
			else if ((command.length()>12)&&(command.substring(0, 12).equals("addaiplayer "))){
				command = command.substring(12);
				String[] value = command.split("\\,");
				//System.out.println("IN");
				if (value.length==3)
					addaiPlayer(value[0],value[1],value[2]);	
			}
			
			else if ((command.length()>11)&&(command.substring(0,12).equals("removeplayer"))){
				command = command.substring(12);
				removePlayer(command);
			}
			
			else if ((command.length()>11)&&(command.substring(0,11).equals("editplayer "))){
				command = command.substring(11);
				String[] value = command.split("\\,");
				if (value.length==3)
					editPlayer(value[0], value[1], value[2]);
			}
			
			else if ((command.length()>9)&&(command.substring(0,10).equals("resetstats"))){
				command = command.substring(10);
				resetStats(command);
			}
			
			else if ((command.length()>12)&&(command.substring(0,13).equals("displayplayer"))){
				command = command.substring(13);
				displayPlayer(command);
			}
			
			else if ((command.length()>10)&&(command.substring(0,10).equals("startgame "))){
				command = command.substring(10);
				String[] value = command.split("\\,");
				if (value.length==4){
					int totalStone = Integer.parseInt(value[0]);
					int upbound = Integer.parseInt(value[1]);
					startGame(totalStone,upbound,value[2],value[3]);
				}	
			}
			
			else if (command.equals("exit")){
				System.out.println();
				PrintWriter output;
				try{
					output=new PrintWriter(new FileOutputStream("players.txt"));
					for (int i=0; i<100; i++){
						if (playerRecord[i]!=null){
							output.println(playerRecord[i].getType()+","
									+playerRecord[i].getUsername()+","
									+playerRecord[i].getGivenName()+","
									+playerRecord[i].getFamilyName()+","
									+playerRecord[i].getGamePlayed()+","
									+playerRecord[i].getGameWon());
						}
						else
							break;
					}
					output.close();
				}
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				System.exit(0);
			}	
			
			else if (command.equals("rankings"))
				rankings();
			
			System.out.println();
			System.out.print(">");
			command = game.keyboard.nextLine();
		}
		
	}
		
}
