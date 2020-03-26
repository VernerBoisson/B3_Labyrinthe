
/*
* Program Maze tracer
* Description: GUI, Recursively find the solution to a maze
* @author: Fernando Simon
* date: November 11, 2014
*/

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

/*Class Header: MazePanel*/
/*Description: its got the run, paint, and other methods used for recursion
 I created a 2D array and basically the movefrom and paint methods do most
of the work calling the other methods to change stuff up.
*/
class MazePanel extends JPanel implements Runnable {
   public MazePanel() {
   }

   final private int SquareSize = 25;
   final private int BoardSize = 25;
   private boolean free = false;
   int locX = 1, locY = 1;


	public char [][] board = setBoard();

//		   {"bbbbbbbbbb".toCharArray(),
//		    "broooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooo".toCharArray(),
//			"booooooooG".toCharArray(),
//			"bbbbbbbbbb".toCharArray() };


//	public String [][] board =
//   { {"b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b"},
//     {"b","r","o","o","b","b","o","o","o","o","o","o","o","o","o","o","b"},
//     {"b","b","b","o","o","o","o","b","b","b","o","b","b","b","b","o","b"},
//     {"b","o","o","b","o","b","b","o","b","o","o","b","o","o","o","o","b"},
//     {"b","b","o","b","o","b","o","o","b","o","b","b","o","b","b","b","b"},
//     {"b","b","o","o","o","o","o","b","o","o","b","o","o","b","o","o","b"},
//     {"b","o","o","b","b","o","b","o","o","b","b","o","b","o","o","b","b"},
//     {"b","o","b","o","o","o","b","o","b","o","o","o","b","o","o","o","b"},
//     {"b","o","o","o","b","b","o","o","b","o","b","b","b","b","b","o","b"},
//     {"b","b","b","o","b","o","o","b","o","o","b","o","o","o","o","o","b"},
//     {"b","o","o","o","b","o","o","b","o","o","b","b","b","o","b","b","b"},
//     {"b","o","b","b","o","b","o","o","b","o","o","o","b","o","o","o","b"},
//     {"b","o","b","o","o","o","b","o","b","b","b","o","o","b","b","o","b"},
//     {"b","o","b","o","b","o","b","o","o","o","o","b","o","o","o","o","b"},
//     {"b","o","b","o","b","o","b","b","b","b","o","b","o","b","b","b","b"},
//     {"b","o","o","o","b","o","b","G","o","o","o","b","o","o","o","o","b"},
//     {"b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b"}};

    
	/*
		Method Header: paintComponent
		@param Graphics g
		description: This goes throughout the whole array checking
		the char and if they are certain chars they are painted accordingly.
		No real logic in this pretty obvious.
    */

	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for(int i = 0;  i < board.length; i++){
    		for(int j = 0; j< board.length; j++){
  				if(i==locX && j==locY){
  					g.setColor(Color.red);
      				g.fillOval(j*25,i*25,25,25);
  				}
      			else if(board[i][j]=='W'){
      				g.setColor(Color.black);
      				g.fillRect(j*SquareSize,i*SquareSize,BoardSize,BoardSize);
      			}
      			else if(board[i][j]=='G'){
      				g.setColor(Color.blue);
      				g.fillOval(j*25,i*25,25,25);
      			}
      			else if(board[i][j]=='r'){
      				g.setColor(Color.red);
      				g.fillOval(j*25,i*25,25,25);
      			}
      			else if(board[i][j]=='y'){
      				g.setColor(Color.yellow);
      				g.fillOval(j*25,i*25,25,25);
      			}
      			else{
      				g.setColor(Color.white);
      				g.fillRect(j*SquareSize,i*SquareSize,BoardSize,BoardSize);
      			}
      		}
      	}
   	}

   	public char [][] setBoard(){
		try {
			URL yahoo = new URL("http://localhost:8080/42");
			HttpURLConnection con = (HttpURLConnection) yahoo.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject myresponse=new JSONObject(response.toString());

			ArrayList<String> listdata = new ArrayList<String>();
			board = new char[10][10];

			JSONArray jArray = myresponse.getJSONArray("maze");
			if (jArray != null) {
				for (int i = 0; i < jArray.length(); i++){

					char tab[] = jArray.getString(i).toCharArray();

					for (int j = 0; j < jArray.length(); j++ ) {
						board[i][j] = tab[j];
					}
					listdata.add(jArray.getString(i));
				}
			}

			//public char [][] board = {"eee".toCharArray(),"oooo".toCharArray()};

			System.out.println(myresponse.getJSONArray("maze"));
			String mama = "mama";
			System.out.println(mama + board[1][1]);
			return board;

		} catch (Exception e){
			System.out.println(e);
			return board;

		}
	}
   /*
		Method Header: run
		description: starts it at 1,1
   */
   public void run () {
      moveFrom(1,1);
   }
   
   /*
		Method Header: isGoal
		@param int x, int y
		@return boolean
		description: checks to see if its the Goal
   */
   public boolean isGoal(int x, int y){
   		if(board[x][y]=='G')
   			return true;
   		else 
   			return false;
   }
   /*
		Method Header: setVisited
		@param int x, int y
		description: sets the board to a y wherever its at to be visited
   */
   public void setVisited(int x, int y){	
   			board[x][y] = 'y';
   			locX = x;
   			locY = y;
   }
   
   /*
		Method Header: isWall
		@param int x, int y
		@return boolean
		description: checks to see if there is a wall
   */
   public boolean isWall(int x, int y){
   		if(board[x][y]=='b')
   			return true;
   		else 
   			return false;
   }
   /*
		Method Header: isVisited
		@param int x, int y
		@return boolean
		description:checks the array to see if it has been visited
   */
   public boolean isVisited(int x, int y){
   		if(board[x][y]=='y')
   				return true;
   			else 
   				return false;
   }
   /*
		Method Header: moveFrom
		@param int x, int y
		description: This is a recursive function, this is what makes this work.  The
		base case has three parts, first it returns if there is a wall or has been visited
		before.  Next if it hits the goalit brings up a message that says that you found it
		and then exits.  For the recursive part it sets where its at as visited, repaints,
		sleeps and then calls itself to move in every direction.
	*/
   private void moveFrom(int x, int y) {
      	if(isWall(x,y))
      		return;
      	if(isVisited(x,y))
      		return;
      	if(isGoal(x,y)){
      		free = true;
      		JOptionPane.showMessageDialog(this, "Good job dog!!");
  			System.exit(0);
      	}
      	if(!free){
      		setVisited(x,y);
      		repaint();
      		try {Thread.sleep(300); } catch (Exception e) { }
    		
			moveFrom(x-1,y);
			moveFrom(x+1,y);
			moveFrom(x,y-1);
			moveFrom(x,y+1);
		}		
   }
}