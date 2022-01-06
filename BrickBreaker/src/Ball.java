import java.awt.Color;
import java.awt.Graphics;

public class Ball extends CollisionObject {

	private Color color;
	private double velocityY;
	private double velocityX;
	private boolean movingUp;
	private boolean movingRight;
	
	public Ball(int posX, int posY, int width, Color color, double speed) {
		super(posX, posY, width, width);
		this.color = color;
		this.velocityY = -speed;
		this.velocityX = speed * (Math.random() > 0.5 ? -1 : 1);
	}
	
	public void updatePosition(int frameWidth, int frameHeight) {
		
		switch (checkWallCollision(frameWidth, frameHeight)) {
			case TOP: velocityY = Math.abs(velocityY); break;
			case BOTTOM: velocityY = Math.abs(velocityY) * -1; break;
			case RIGHT: velocityX = Math.abs(velocityX) * -1; break;
			case LEFT: velocityX = Math.abs(velocityX); break;
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
		
		g.setColor(color);
		g.fillOval(posX, posY, width, width);
		
	}
	
	public void flipVelX() {
		velocityX *= -1;
	}
	
	public void flipVelY() {
		velocityY *= -1;
	}
	
}
