import processing.core.PApplet;

/**
 * This class creates a disk object for the game.
 * @author YunSeo (Jennifer) Kim
 * @version April 25, 2016
 *
 */
public class Disk {
	float x; //x coordinate of the disk
	float y; //y coordinate of the disk
	float rx; //x radius of the disk
	float ry; //y radius of the disk
	float height; //height of canvas
	float width; //width of canvas
	
	float xSpeed; //the speed of the disk on x axis
	float ySpeed; //speed of disk on y axis
	
	int score; //score of each of the disks
	
	boolean active = true; //checking if the disks are still active
	
	/**
	 * This constructor creates a disk object with the passed parameters.
	 * @param x, x coordinate of the disk
	 * @param y y coordinate of the disk
	 * @param height height of the canvas
	 * @param width width of the canvas
	 * @param xSpeed speed of disk on x axis
	 * @param ySpeed speed of disk on y axis
	 */
	public Disk(float x, float y, float height, float width, float xSpeed, float ySpeed){
	    this.x = x;
	    this.y = y;
	    this.height = height;
	    this.width = width;
		rx = 50; //sets x radius of disk to 50
		ry = 50; //sets x radius of disk to 50
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
		//creates array of the possible scores to be printed on the disk
		int [] possibleScores = {10, 20, 50, 100};
		int i = (int)(Math.random() * 4); //goes through array to pick a random score to assign to each disk
		score = possibleScores[i];
	}
	
	/**
	 * This method makes the disks move, making sure that the disks don't go past the canvas boundaries.
	 */
	public void move() {
		if (this.x < 0 || this.x > width) xSpeed = -xSpeed; //changes x direction when goes past canvas
		if (this.y < 0 || this.y > height) ySpeed = -ySpeed; //changes y direction when goes past canvas
		
		this.x += xSpeed; //makes a speed for x
		this.y += ySpeed; //makes a speed for y
	}
	
	/**
	 * This method detects whether a shape would be clicked by the mouse or not.
	 * @param mouseX the x-coordinates of the mouse
	 * @param mouseY the y-coordinates of the mouse
	 * @return the score of the shape that the mouse clicks on
	 */
	public int intersects(float mouseX, float mouseY) {
		if ((Math.pow(mouseX - x,2) + Math.pow(mouseY - y, 2)) < Math.pow(rx, 2)){
			//click detected
			return score; //would this get the score of the disk if the specific disk object isn't created
			//return a score of the disk
		}
		else {
			return -1;
		}			
	}
	
	/**
	 * This method deactivates (a disk).
	 * @return a boolean to see if something is active or not.
	 */
	public boolean deactivate() {
		active = false; //sets the currently true active boolean to false
		return active;
	}	
}
