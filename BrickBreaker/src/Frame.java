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
	Ball ball = new Ball(250, 400, 10, Color.black, 3);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		boolean hit = false;
		
		for (Brick brick : bricks) {
			
		}
		
		
		
		switch (bricks.get(0).checkCollision(ball)) {
			case RIGHT: ball.flipVelX(); 			hit = true;
			case LEFT: ball.flipVelX(); 			hit = true;
			case BOTTOM: ball.flipVelY(); 			hit = true;
			case TOP: ball.flipVelY(); 			hit = true;
		}
			if (hit.)
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
		bricks.add(new Brick(100, 100, 100, 100, Color.red));

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
