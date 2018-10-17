import processing.core.PApplet;
/**
 * This class creates a game where the user can click on disks to gain points.
 * The game levels up every 20 seconds, making the disks move faster. 
 * The game ends after 60 seconds, or if all the disks are clicked. 
 * @author YunSeo
 * @version April 25, 2016
 */
public class MyGame extends PApplet {
	
	Disk[] disks = new Disk[25];
	int totalScore = 0;
	int level = 0;
	int fps = 60;
	boolean levelUp = true;
	int count = 0;
	boolean X = true;
	
	/**
	 * This class sets up the canvas to make the game on.
	 */
	public void setup() {
		frameRate(20);
		background(255,255,255); //set background to white
		size(500,500); //set the size of the canvas for game
		
		//creates disks to fill the array of disks that will be displayed on the console when the game starts.
		for (int i = 0; i < disks.length; i++) {
			disks[i] = new Disk(this.random(0, this.height), this.random(0, this.width), this.height, this.width, this.random(-2, 2), this.random(-2, 2));
			//this.random refers to the canvas
		}
	}
	
	/**
	 * This draw class creates the game, or what appears on the canvas.
	 */
	public void draw()
	{
		//if the time is under 60 seconds and the game is not over, then carry this out
		if ((frameCount / 60) < 60 && !gameOver()){
			frameRate(fps); //60 frames per second
			background(255,255,255); //background set to white
			text(); //the text block is printed

			//loop through array of disks to create ellipses
			for(int i = 0; i < disks.length; i++) {
				if (disks[i].active) {
					fill(0,0,0); //make ellipses black
					//creates an ellipse with the given variables
					ellipse(disks[i].x, disks[i].y, disks[i].rx, disks[i].ry);
					textSize(24);
					fill(255,255,255); //number text is white
					textAlign(CENTER); //align text to the center
					text(disks[i].score, disks[i].x, disks[i].y); //each instance of disk prints new number
					disks[i].move(); //calls the move class so the disks move around on screen
				}
			}
			
			//making it so that you can level up again if levelUp has been set to false
			if ((frameCount / 60) % 20 != 0 && levelUp == false){
				levelUp = true;
			}
			
			//increase the level, and consequently the speed every 20 seconds
			if ((frameCount / 60) % 20 == 0 && levelUp) {
				//goes through each disk, and increases the speed of it by 5
				for(int i = 0; i < disks.length; i++){
					if (disks[i].ySpeed < 0) {
						disks[i].ySpeed = disks[i].ySpeed - 5;
					}
					
					else if (disks[i].ySpeed >= 0) {
						disks[i].ySpeed = disks[i].ySpeed + 5;
					}
					
					else if (disks[i].xSpeed < 0) {
						disks[i].xSpeed = disks[i].xSpeed - 5;
					}
					
					else if (disks[i].xSpeed >= 0) {
						disks[i].xSpeed = disks[i].xSpeed + 5;
					}
				}
				level++; //increase level 
				levelUp = false; //set this to false so that this doesn't loop over and over again
			}
			
			//gets rid of a disk every 10 seconds if that disk is not already gone
			if ((frameCount / 60) % 10 == 0 && (frameCount / 60) != 0 && X) {
				//int rand = (int)Math.random() * disks.length;
				//disks[rand].deactivate();
				disks[count].deactivate();
				count++;
				X = false;
				
			}
			else if ((frameCount / 60) % 10 !=0){
				X=true;
			}
		}
		
		//else if the game is still going on (under a minute and all the disks are not gone)
		else{
			background(255,255,255);
			fill(0,0,0);
			text("Game over! \n Your total score is: " + totalScore, 255, 255); //print out the total score
		}
	}
	
	/**
	 * This method prints the text that is initially displayed with the disks.
	 */
	public void text(){
		//print text on the canvas: the font size is 32, the text color is black,
		//the text as centered on its x and y coordinates
		//printing instructions
		textSize(28);
		fill(0,0,0);
		textAlign(CENTER);
		text("Click the disks to get points. \n Game will end after 60 seconds, \n or if all the disks are clicked.", 255,400);
		
		//print text on the canvas: the font size is 32, the text color is black,
		//the text as centered on its x and y coordinates
		//printing total score
		textSize(32);
		fill(0,0,0);
		textAlign(LEFT);
		text("Total score: " + totalScore, 20, 45);
		text("------------------------", 20, 75);
		//printing level
		textAlign(RIGHT);
		text("Level: " + level, 470, 45);
		//printing the seconds passed
		textAlign(CENTER);
		text("Time in seconds: " + frameCount / 60, 170, 100);
	}
	
	/**
	 * This method deactivates a disk if it is clicked.
	 */
	public void mousePressed() {
		for (int i = 0; i < disks.length; i++) {
			int scoreToAdd = disks[i].intersects(mouseX, mouseY); //the score of the clicked on disk is received
			
			if (scoreToAdd != -1) { //add score if the disks are clicked
				totalScore += scoreToAdd;
				disks[i].deactivate(); //then deactivate the disks
			}
		}		
	}
	
	/**
	 * This method checks if the game is over or not, due to disk clicking.
	 * @return boolean false if the game is not over, true if it is.
	 */
	public boolean gameOver () {
		for (int i = 0; i < disks.length; i++) {
			if(disks[i].active){ //if any disk is active, then return false
				return false;
			}
		}
		return true; //if all the disks are inactive, return true
		
	}
}
