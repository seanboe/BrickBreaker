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
	
	final int SCREEN_WIDTH = 500;
	final int SCREEN_HEIGHT = 800;
	
	ArrayList<Brick> bricks = new ArrayList<Brick>();
//	Brick brick = new Brick(100, 100, 100, 100, Color.red); 
	
	
//	Brick brick = new Brick(100, 100, 100, 100, Color.red);
	Ball ball = new Ball("imgs/ball.png", 250, 400, 16, 5);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(41, 46, 55));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		boolean hit = false;
		
		for (int x = bricks.size() - 1; x >= 0; x--) {
			Brick brick = bricks.get(x);
			switch (brick.checkCollision(ball)) {
				case RIGHT: 
					ball.flipVelX();
					bricks.remove(x);
					break;
				case LEFT:
					ball.flipVelX();
					bricks.remove(x);
					break;
				case BOTTOM: 
					ball.flipVelY();
					bricks.remove(x);
					break;
				case TOP: 
					ball.flipVelY();
					bricks.remove(x);
					break;
				default: break;
			}
		}
		
		
		for (Brick brick : bricks) 
			brick.draw(g);
		
		ball.updatePosition(SCREEN_WIDTH, SCREEN_HEIGHT);
		ball.draw(g);
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("BrickBreaker");
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
		bricks.add(new Brick("/imgs/greenBrick.png", 30, 40, 52, 26));
		bricks.add(new Brick("/imgs/redBrick.png", 300, 400, 52, 26));
		bricks.add(new Brick("/imgs/yellowBrick.png", 20, 50, 52, 26));
		bricks.add(new Brick("/imgs/greenBrick.png", 550, 70, 52, 26));
		bricks.add(new Brick("/imgs/RedBrick.png", 110, 0, 52, 26));
		bricks.add(new Brick("/imgs/yellowBrick.png", 430, 650, 52, 26));
		bricks.add(new Brick("/imgs/RedBrick.png", 30, 580, 52, 26));

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
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
