import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	JFrame f;
	
	final int SCREEN_WIDTH = 500;
	final int SCREEN_HEIGHT = 800;
	final int HEADER_HEIGHT = 50;

	final int BRICK_WIDTH = 46;
	final int BRICK_HEIGHT = 24;
	
	Game game = new Game(3, "imgs/heart.png");
	
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	Ball ball = new Ball("imgs/ball.png", 250, 500, 18, 2, 7);
	Paddle paddle = new Paddle("imgs/paddle.png", (SCREEN_WIDTH / 2) - (52 / 2), 650, 94, 22, 8);
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(41, 46, 55));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		game.drawHeader(g, SCREEN_WIDTH, HEADER_HEIGHT);
		
		boolean hit = false;
		
		for (int x = bricks.size() - 1; x >= 0; x--) {
			Brick brick = bricks.get(x);
			switch (brick.checkCollision(ball)) {
				case RIGHT: 
					ball.flipVelX();
					if (brick.isDestroyed()) {
						game.addPoints(brick.getColor());
						bricks.remove(x);
					}
					break;
				case LEFT:
					ball.flipVelX();
					if (brick.isDestroyed()) {
						game.addPoints(brick.getColor());
						bricks.remove(x);				
					}
					break;
				case BOTTOM: 
					ball.flipVelY();
					if (brick.isDestroyed()) {
						game.addPoints(brick.getColor());
						bricks.remove(x);					
					}
					break;
				case TOP: 
					ball.flipVelY();
					if (brick.isDestroyed()) {
						game.addPoints(brick.getColor());
						bricks.remove(x);					
					}
					break;
				default: break;
			}
		}
		
		
		for (Brick brick : bricks) 
			brick.draw(g);
		
		paddle.updatePosition(SCREEN_WIDTH, SCREEN_HEIGHT);
		if (paddle.checkCollision(ball) != CollisionObject.Side.NONE) {
			ball.setToNominalSpeed();
			ball.flipVelY();
			
			double newVelocity = 0;
			switch (paddle.getMoveDirection()) {
			case LEFT:
				newVelocity = ball.getVelX() - Math.abs(paddle.getVelocityX()) * 0.25;
				break;
			case RIGHT:
				newVelocity = ball.getVelX() + Math.abs(paddle.getVelocityX()) * 0.25;
				break;
			case STOP:
				newVelocity = ball.getVelX();
				break;
			}
			
			if (Math.abs(newVelocity) > Math.abs(paddle.getVelocityX() * 0.75))
				newVelocity = paddle.getVelocityX() * 0.75 * (newVelocity < ball.getVelX() ? -1 : 1);
			ball.setVelX(newVelocity);
		}
		
		paddle.draw(g);
		
		if (ball.updatePosition(SCREEN_WIDTH, SCREEN_HEIGHT, HEADER_HEIGHT)) {
			ball.reset();
			if (game.removeLife() <= 0) {
			}
		}
		ball.draw(g);
		
		// Check whether to update the level
		if (bricks.size() == 0) {
			if (ball.posY <= HEADER_HEIGHT) {
				game.increaseLevel();
				game.addLife();
				bricks.clear();
				buildBrickFormat();
				ball.posY = paddle.posY - 10;
			}
		}		
		
		// check for game end
		if (game.getLives() <= 0) {
			game.end(f, g, SCREEN_WIDTH, SCREEN_HEIGHT);
		}
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		f = new JFrame("BrickBreaker");
		f.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		buildBrickFormat();

	}
	
	public void buildBrickFormat() {
		String[][] format = game.getLevelFormat(game.getLevel());
		
		for (int x = 0; x < format.length; x++) {
			int startX = (SCREEN_WIDTH / 2) - ((format[x].length * BRICK_WIDTH) / 2);
			int startY = ((SCREEN_HEIGHT / 3) - ((format.length * BRICK_HEIGHT) / 2));
									
			for (int a = 0; a < format[x].length; a++) {
				String imagePath = "";
				int health = 0;
				Brick.BrickColor brickColor = Brick.BrickColor.RED;
				switch (format[x][a]) {
					case "r": imagePath = "/imgs/redBrick.png"; health = game.redBrickHealth; brickColor = Brick.BrickColor.RED; break;
					case "g": imagePath = "/imgs/greenBrick.png"; health = game.greenBrickHealth; brickColor = Brick.BrickColor.GREEN; break;
					case "y": imagePath = "/imgs/yellowBrick.png"; health = game.yellowBrickHealth; brickColor = Brick.BrickColor.YELLOW; break;
					default: health = 0; brickColor = Brick.BrickColor.CLEAR;
				}
				int posX = startX + a * BRICK_WIDTH;
				int posY = startY + x * BRICK_HEIGHT;
				
				bricks.add(new Brick(imagePath, posX, posY, BRICK_WIDTH, BRICK_HEIGHT, health, brickColor));
			}
		}
		
	}
		
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		
		int keyCode = arg0.getKeyCode();

		if (keyCode == 37)
			paddle.setMoveDirection(Paddle.Direction.LEFT);
		else if (keyCode == 39) 
			paddle.setMoveDirection(Paddle.Direction.RIGHT);
		else
			paddle.setMoveDirection(Paddle.Direction.STOP);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		paddle.setMoveDirection(Paddle.Direction.STOP);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
