import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Paddle extends CollisionObject {
	

	private Direction movementDirection;
	private int velocity;
	private Image image;
	
	public Paddle(String img, int posX, int posY, int width, int height, int velocity) {
		super(posX, posY, width, height);
		this.image = getImage(img);
		this.velocity = velocity;
		movementDirection = Direction.STOP;
	}
	
	public void updatePosition(int frameWidth, int frameHeight) {
		switch (movementDirection) {
		case LEFT:
			if (this.posX <= 0)
				return;
			this.posX -= velocity;
			break;
			
		case RIGHT:
			if (this.posX + this.width >= frameWidth)
				return;
			this.posX += velocity;
			break;
		default: 
			break;
		}
	}
	
	public void setMoveDirection(Direction direction) {
		movementDirection = direction;
	}
	
	public void draw(Graphics g) {
		
		AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
		tx.setToTranslation(posX, posY);
		tx.scale(.09, .09);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, tx, null);
		
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
	
}
