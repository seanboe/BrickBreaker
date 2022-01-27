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
	private Image image;
	private boolean collidedPaddleOnce;
	
	// reset stuff
	private double speedInitial;
	private double speedNominal;
	private int startX;
	private int startY;
		
	public Ball(String img, int posX, int posY, int width, double speedInitial, double speedNominal) {
		super(posX, posY, width, width);
		this.image = getImage(img);
		
		this.velocityY = speedInitial;
//		this.velocityX = speed * (Math.random() > 0.5 ? -1 : 1);
		this.velocityX = 0;
		collidedPaddleOnce = false;
		
		
		this.startX = posX;
		this.startY = posY;
		this.speedInitial = speedInitial;
		this.speedNominal = speedNominal;
	}
	
	public boolean updatePosition(int frameWidth, int frameHeight, int headerHeight) {
		
		boolean bottomCollision = false;
		
		switch (checkWallCollision(frameWidth, frameHeight, headerHeight)) {
			case TOP: velocityY = Math.abs(velocityY); break;
			case BOTTOM: velocityY = Math.abs(velocityY) * -1; bottomCollision = true; break;
			case RIGHT: velocityX = Math.abs(velocityX) * -1; break;
			case LEFT: velocityX = Math.abs(velocityX); break;
			case NONE: break;
		}
				
		posX += velocityX;
		posY += velocityY;
		
		return bottomCollision;
	}
	
	private Side checkWallCollision(int frameWidth, int frameHeight, int headerHeight) {
		// right wall collision
		if (posX + width >= frameWidth)
			return Side.RIGHT; 
		
		// left wall collision
		else if (posX <= 0)
			return Side.LEFT;

		// ceiling collision
		else if (posY <= headerHeight)
			return Side.TOP;

		// floor collision
		else if (posY + height >= frameHeight)
			return Side.BOTTOM;
		
		else return Side.NONE;
	}
		
	public void setVelX(double paddleVelX) {
//		velocityX = (paddleVelX) * (paddleVelX < 0 ? -1 : 1);
		velocityX = paddleVelX;
	}
	
	public double getVelX() {
		return velocityX;
	}
	
	public void reset() {
		this.posX = this.startX;
		this.posY = this.startY;
		this.velocityY = this.speedInitial;
		this.velocityX = 0;
		this.collidedPaddleOnce = false;
	}
	
	public void setToNominalSpeed() {
		this.velocityY = this.speedNominal;
	}
	
	public void draw(Graphics g) {
		
		AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
		tx.setToTranslation(posX, posY);
		tx.scale(.08, .08);
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
	
	
	public void flipVelX() {
		velocityX *= -1;
	}
	
	public void flipVelY() {
		velocityY *= -1;
	}
	
}
