import java.awt.Color;
import java.awt.Font;
import java.awt.font.*;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down= false;
	
	private ImageIcon klawright;
	private ImageIcon klawup;
	private ImageIcon klawdown;
	private ImageIcon klawleft;
	
	private int lengthofsnake = 3;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakebody;
	
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,
			425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
			450,475,500,525,550,575,600,625,650};
	
	private ImageIcon enemyimage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int score = 0;		
	
	private int moves = 0;
	
	private ImageIcon titleImage;
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	
	}
	
	public void paint(Graphics g) 
	{
		if(moves == 0)
		{
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
			
		}
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		// draw title image
		titleImage = new ImageIcon("raptortitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay 
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		// keep track of score
		g.setColor(Color.WHITE);
		g.setFont(new Font("impact", Font.PLAIN, 14));
		g.drawString("Scores: "+score, 810, 95);

		// keep track of length
		g.setColor(Color.WHITE);
		g.setFont(new Font("impact", Font.PLAIN, 14));
		g.drawString("length: "+lengthofsnake, 810, 115);

		
		klawright = new ImageIcon("klawright.png");
		klawright.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a< lengthofsnake; a++)
		{
			if(a==0 && right)
			{
				klawright = new ImageIcon("klawright.png");
				klawright.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && left)
			{
				klawleft = new ImageIcon("klawleft.png");
				klawleft.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && down)
			{
				klawdown = new ImageIcon("klawdown.png");
				klawdown.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && up)
			{
				klawup = new ImageIcon("klawup.png");
				klawup.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a!=0)
			{
				snakebody = new ImageIcon("snakebody.png");
				snakebody.paintIcon(this, g, snakexlength[a], snakeylength[a]);

			}
		}
		
		enemyimage = new ImageIcon("Joelenemy.png");
		
		if((enemyxpos[xpos] == snakexlength[0]) && enemyypos[ypos] == snakeylength[0])
		{
			score++;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int b = 1; b< lengthofsnake; b++)
		{
			if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("impact", Font.BOLD, 50));
				g.drawString("Game Over, Kawhi", 300, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART", 350, 340);

			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if(right)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakexlength[r] = snakexlength[r] + 25;
				}
				else
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] > 850)
				{
					snakexlength[r] = 25;
				}
			}
			repaint();	
		}
		if(left)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakexlength[r] = snakexlength[r] -25;
				}
				else
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] < 25)
				{
					snakexlength[r] = 850;
				}
			}
			repaint();	
		}
		if(up)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeylength[r] = snakeylength[r] -25;
				}
				else
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 75)
				{
					snakeylength[r] =625;
				}
			}
			repaint();	
		}
		if(down)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeylength[r] = snakeylength[r] +25;
				}
				else
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 25)
				{
					snakeylength[r] = 850;
				}
			}
			repaint();	
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down)
			{
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up)
			{
				down = true;
			}
			else
			{
				up = true;
				down = false;
			}
			
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
