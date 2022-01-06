
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
		
		int colliderCenterX = collider.posX + (int)(collider.width / 2);
		int colliderCenterY = collider.posY + (int)(collider.height / 2);
		
		int thisCenterX = this.posX + (this.width / 2);
		int thisCenterY = this.posY + (this.height / 2);

		
		// Check collisions
		if (colliderCenterX > this.posX && colliderCenterX < (this.posX + this.width)) {
			if ((collider.posY >= this.posY) && (collider.posY <= this.posY + this.height)) {
				
				System.out.println("Collision");
				
				int dyTop = Math.abs(this.posY - colliderCenterY);
				int dyBottom = Math.abs(this.posY + this.height - colliderCenterY);
				int dxLeft = Math.abs(colliderCenterX - this.posX);
				int dxRight = Math.abs(colliderCenterX - this.posX + this.width);
				
				if (dyTop < dyBottom && dxLeft < dxRight) {
					// Top left quadrant
					if (Math.sin(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)) > Math.cos(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)))
						return Side.TOP;
					else 
						return Side.LEFT;
				}
				
				if (dyTop > dyBottom && dxLeft < dxRight) {
					// Bottom left quadrant
					if (Math.sin(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)) > Math.cos(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)))
						return Side.BOTTOM;
					else 
						return Side.LEFT;
				}
				
				if (dyTop > dyBottom && dxLeft > dxRight) {
					// Top right quadrant
					if (Math.sin(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)) > Math.cos(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)))
						return Side.BOTTOM;
					else 
						return Side.RIGHT;
				}
				
				if (dyTop < dyBottom && dxLeft > dxRight) {
					// Top right quadrant
					if (Math.sin(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)) > Math.cos(Math.abs(colliderCenterY - thisCenterY) / Math.abs(colliderCenterX - thisCenterX)))
						return Side.TOP;
					else 
						return Side.RIGHT;
				}
				
//				if (collider.posY < this.posY) {
//					System.out.println("Top");
//					return Side.TOP;
//				}
//				if (collider.posY + collider.height > this.posY + this.height) {
//					System.out.println("Bottom");
//					return Side.BOTTOM;
//				}
//
//				if (collider.posX + collider.width > this.posX + width) {
//					System.out.println("Right");
//					return Side.RIGHT;
//				}
//
//				if (collider.posX < this.posX) {
//					System.out.println("Left");
//					return Side.LEFT;
//				}
				
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
