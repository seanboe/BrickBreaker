import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Toolkit;

public class Picture {

	private java.awt.Image image;
	private double scaleX;
	private double scaleY;
	private int posX;
	private int posY;
	
	public Picture(String imgPath, double scaleX, double scaleY, int posX, int posY) {
		this.image = getImage(imgPath);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void updatePosition(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
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
	
	public void draw(Graphics g) {
		
		AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
		tx.setToTranslation(posX, posY);
		tx.scale(this.scaleX, this.scaleY);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(this.image, tx, null);
		
	}
	
}
