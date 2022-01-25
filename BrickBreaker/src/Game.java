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
	
	public Game(int lives, String heartPath) {
		this.lives = lives;
		this.heartPath = heartPath;
		level = 1;
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
		int heartSpacing = 50;
		for (int x = heartSpacing / 2; x <= (this.lives) * heartSpacing; x+= heartSpacing) {
			
			Image heartImage = null;
			try {
				URL imageURL = Duck.class.getResource(this.heartPath);
				heartImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
			tx.setToTranslation(x, (headerHeight - heartSpacing) / 2);
			tx.scale(.07, .07);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(heartImage, tx, null);
			
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
		
}
