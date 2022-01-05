import java.awt.Color;
import java.awt.Graphics;
//import javafx.scene.layout.Stackpane; 
//import javafx.scene.effect.InnerShadow;


public class Brick extends CollisionObject {
	
	private Color color;
	
	public Brick(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height);
		this.color = color;
	}
	
	public void draw(Graphics g) {
				
		g.setColor(color);
		g.fillRect(this.posX, this.posY, this.width, this.height);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
