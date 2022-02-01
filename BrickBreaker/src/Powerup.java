
public class Powerup extends CollisionObject {

	private Picture image;
	private int speed;  
	private int timeout;
	private PowerUpType powerUpType;
	
	enum PowerUpType {
		THREE_BALLS, EXTEND
	};
	
	public Powerup(String imgPath, int posX, int posY, int width, int height, int speed, int timeout, PowerUpType powerUpType) {
		super(posX, posY, width, height);
		image = new Picture(imgPath, 1, 1, posX, posY);
		this.speed = speed;
		this.timeout = timeout;
		this.powerUpType = powerUpType;
	}
	
	public void move() {
		this.posY += this.speed;
		image.updatePosition(this.posX, this.posY);
	}
	
}
