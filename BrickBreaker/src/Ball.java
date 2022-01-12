import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ball extends CollisionObject {

	private double velocityY;
	private double velocityX;
	private boolean movingUp;
	private boolean movingRight;
	private Image image;
	
	public Ball(String img, int posX, int posY, int width, double speed) {
		super(posX, posY, width, width);
		this.image = getImage(img);
		
		this.velocityY = -speed;
		this.velocityX = speed * (Math.random() > 0.5 ? -1 : 1);
	}
	
	public void updatePosition(int frameWidth, int frameHeight) {
		
		switch (checkWallCollision(frameWidth, frameHeight)) {
			case TOP: velocityY = Math.abs(velocityY); break;
			case BOTTOM: velocityY = Math.abs(velocityY) * -1; break;
			case RIGHT: velocityX = Math.abs(velocityX) * -1; break;
			case LEFT: velocityX = Math.abs(velocityX); break;
			case NONE: break;
		}
		
		posX += velocityX;
		posY += velocityY;
	}
	
	private Side checkWallCollision(int frameWidth, int frameHeight) {
		// right wall collision
		if (posX + width >= frameWidth)
			return Side.RIGHT; 
		
		// left wall collision
		else if (posX <= 0)
			return Side.LEFT;

		// ceiling collision
		else if (posY <= 0)
			return Side.TOP;

		// floor collision
		else if (posY + height >= frameHeight)
			return Side.BOTTOM;
		
		else return Side.NONE;
	}
	
	public void draw(Graphics g) {
		
		AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
		tx.setToTranslation(posX, posY);
		tx.scale(.05, .05);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, tx, null);
		
//		g.setColor(color);
//		g.fillOval(posX, posY, width, width);
		
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

	
	
	public void flipVelX() {
		velocityX *= -1;
	}
	
	public void flipVelY() {
		velocityY *= -1;
	}
	
}
