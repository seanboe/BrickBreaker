import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game {
	
	final int redBrickHealth = 1;
	final int greenBrickHealth = 2;
	final int yellowBrickHealth = 3;
	
	final int redBrickPoints = 100;
	final int greenBrickPoints = 150;
	final int yellowBrickPoints = 250;
	
// Level Formats

	private String[][] level1Format = new String[][] {
		{"r", "r", "r", "r", "r", "r"},
		{"r", "g", "g", "g", "g", "r"},
		{"r", "g", "y", "y", "g", "r"}, 
		{"r", "g", "g", "g", "g", "r"},
		{"r", "r", "r", "r", "r", "r"}
	};

	
	private String[][] level2Format = new String[][] {
		{"r", "r", "r", "r", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "g", "y", "g", "r"},
		{"r", "r", "r", "r", "r"}
	};
	
	private String[][] level3Format = new String[][] {
		{"r", "r", "r", "r", "r", "r", "r", "r", "r"},
		{"g", "y", "g", "y", "g", "y", "g", "y", "g"},
		{"y", "g", "y", "g", "y", "g", "y", "g", "y"},
		{"g", "y", "g", "y", "g", "y", "g", "y", "g"},
		{"r", "r", "r", "r", "r", "r", "r", "r", "r"}
	};
	
//	private String[]
	
	private int lives;
	private String heartPath;
	private int level;
	private boolean displayingLevelScreen;
	private int points; 
	
	public Game(int lives, String heartPath) {
		this.lives = lives;
		this.heartPath = heartPath;
		this.level = 1;
		this.displayingLevelScreen = false;
		this.points = 0;
	}
	
	public String[][] getLevelFormat(int level) {
		switch (level) {
			case 1: return level1Format;
			case 2: return level2Format;
			case 3: return level3Format;
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
		
		g.setColor(Color.white);
		Font font = new Font("Monospaced", Font.PLAIN, 20);
		g.setFont(font);
		g.drawString(this.points + "", screenWidth / 2, headerHeight / 2);
	
		g.drawString(this.level + "", screenWidth - 100, headerHeight / 2);
		
	}
	
	public int removeLife() {
		this.lives--;
		return this.lives;
	}
	
	public void addLife() {
		this.lives++;
	}

	public void increaseLevel() {
		this.level++;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void end(JFrame frame, Graphics g, int screenWidth, int screenHeight) {
		g.setColor(Color.white);
		Font font = new Font("Monospacedd", Font.PLAIN, 50);
		g.setFont(font);
		
		g.drawString("Game Over!", screenWidth / 2 - 100, screenHeight / 2);
		
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		
		System.out.println("Game Over!");
	}
	
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPoints(Brick.BrickColor brickColor) {
		int newPoints = 0;
		switch (brickColor) {
		case RED: newPoints = redBrickPoints; break;
		case GREEN: newPoints = greenBrickPoints; break;
		case YELLOW: newPoints = yellowBrickPoints; break;
		}
				
		this.points += newPoints;
		}
		
}
