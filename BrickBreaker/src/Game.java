import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Game {
	
	final int redBrickHealth = 1;
	final int greenBrickHealth = 1;
	final int yellowBrickHealth = 2;


// Level Formats
	private String[][] level1Format = new String[][] {
		{"r", "r", "r", "r", "r", "r"},
		{"r", "g", "g", "g", "g", "r"},
		{"r", "g", "y", "y", "g", "r"}, 
		{"r", "g", "g", "g", "g", "r"},
		{"r", "r", "r", "r", "r", "r"}
	};
	
	private int lives;
	private String heartPath;
	private int level;
	private boolean displayingLevelScreen;
	
	public Game(int lives, String heartPath) {
		this.lives = lives;
		this.heartPath = heartPath;
		this.level = 1;
		this.displayingLevelScreen = false;
	}
	
	public String[][] getLevelFormat(int level) {
		switch (level) {
			case 1: return level1Format;
		}
		return level1Format;
	}
	
	public void drawHeader(Graphics g, int screenWidth, int headerHeight) {
		g.setColor(new Color(28, 28, 28));
		g.fillRect(0, 0, screenWidth, headerHeight);
		
		// Draw the hearts
		int heartSpacing = 35;
		for (int x = heartSpacing / 2; x <= (this.lives) * heartSpacing; x+= heartSpacing) {
			Picture heart = new Picture("imgs/heart.png", 0.01, 0.01, x, (headerHeight - heartSpacing) / 2);
			heart.draw(g);
		}
		
	}
	
	public int removeLife() {
		this.lives--;
		return this.lives;
	}

//	public int endGame() {
//		
//	}
	
	public boolean checkLevelEnd(ArrayList<Brick> bricks) {
		if (bricks.size() <= 0) {
			this.level++;
			return true;
		}
		return false;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int lowerLevel() {
		this.level--;
		return this.level;
	}
	
	public void resetGame() {}
	
//	public void updateLevelStatus(Graphics g, int screen_width, int screen_height) {
//	
//		int heartSpacing = 35;
//		
//		Picture screen = new Picture("imgs/black_screen.png", 1.25, 2, 0, 0);
//		screen.draw(g);
//		
//		System.out.println("123");
//		
//		for (int x = 0; x < 5; x++) {
//			for (int a = (screen_width / 2) - ((lives * heartSpacing) / 2); a < this.lives; a+= heartSpacing) {
//								
////				Picture heart = new Picture("imgs/heart.png", 0.01, 0.01, a, (screen_height / 2) + x * heartSpacing);
//				Picture heart = new Picture("imgs/heart.png", 0.01, 0.01, 0, 0);
//				heart.draw(g);
//				System.out.println("Here");
//			}	
//			pause(300);
//			
//			if (x == 3)
//				this.lives--;
//		}
//				
//	}
	
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
