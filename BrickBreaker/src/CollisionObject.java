
public class CollisionObject {

	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	
	enum Side {
		TOP, BOTTOM, LEFT, RIGHT, NONE;
	}
	
	
	public CollisionObject(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public Side checkCollision (CollisionObject collider) {
		
		int colliderCenterX = collider.posX + (int)(width / 2);
		int colliderCenterY = collider.posY + (int)(height / 2);
		
		// Check collisions
		if (colliderCenterX > this.posX && colliderCenterX < (this.posX + width)) {
			if (collider.posY <= (this.posY + this.height) && collider.posY > this.posY)
				
				// Collision from top
				if (collider.posY > this.posY)
					return Side.TOP;
				if (collider.posY + height > this.posY + height)
					return Side.BOTTOM;
				if (collider.posX + collider.width > this.posX + width)
					return Side.RIGHT;
				if (collider.posX < this.posX)
					return Side.LEFT;
		}
		
		return Side.NONE;
	}
	
}
