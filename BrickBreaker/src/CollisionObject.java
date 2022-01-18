import java.awt.Rectangle;
import java.awt.Shape;

public class CollisionObject {

	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
		
	protected boolean isActive;
	
	enum Side {
		TOP, BOTTOM, LEFT, RIGHT, NONE;
	}
	
	
	public CollisionObject(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
				
		this.isActive = true;
	}
	
	public Side checkCollision (CollisionObject collider) {
		
		Rectangle thisHitbox = new Rectangle(this.posX, this.posY, width, height);
		Rectangle colliderHitbox = new Rectangle(collider.posX, collider.posY, collider.width, collider.height);
				
		if (thisHitbox.intersects(colliderHitbox)) {
			if (colliderHitbox.getCenterX() < this.posX + this.width && colliderHitbox.getCenterX() > this.posX)  {
				if (colliderHitbox.getCenterY() < this.posY)
					return Side.TOP;
				if (colliderHitbox.getCenterY() > this.posY)
					return Side.BOTTOM;
			}
			
			if (colliderHitbox.getCenterY() > this.posY && colliderHitbox.getCenterY() < this.posY + this.height) {
				if (colliderHitbox.getCenterX() < this.posX)
					return Side.LEFT;
				if (colliderHitbox.getCenterX() > this.posX + this.width)
					return Side.RIGHT;
			}
		}
		
		return Side.NONE;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
}
