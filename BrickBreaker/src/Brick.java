import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
//import javafx.scene.layout.Stackpane; 
//import javafx.scene.effect.InnerShadow;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Brick extends CollisionObject {
	
	private Image image;
	
	public Brick(String img, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.image = getImage(img);
	}
	
	public void draw(Graphics g) {
		
		AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
		tx.setToTranslation(posX, posY);
		tx.scale(.07, .07);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, tx, null);
				
//		g.setColor(color);
//		g.fillRect(this.posX, this.posY, this.width, this.height);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Duck.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public void setColor(Color color) {
//		this.color = color;
	}

}
