package _06_Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Go through the methods and complete the steps in this class
// and the Snake class

public class _00_SnakeGame implements ActionListener, KeyListener {
	public static final Color BORDER_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	public static final Color FOOD_COLOR = Color.RED;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 12;
	public static final int WINDOW_SCALE = 50;
	public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
	public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;

	private JFrame window;
	private JPanel panel;

	private Snake snake;

	private Timer timer;

	private Location foodLocation;

	public _00_SnakeGame() {
		snake = new Snake(new Location(WIDTH / 2, HEIGHT / 2));

		window = new JFrame("Snake");
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(BACKGROUND_COLOR);
				g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

				g2.setColor(FOOD_COLOR);
				g2.drawOval(foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, Snake.BODY_SIZE,
						Snake.BODY_SIZE);
				snake.draw(g);
			}
		};

		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		window.add(panel);

		timer = new Timer(0, this);

		window.pack();
		window.addKeyListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		setFoodLocation();

		startGame();
	}

	public void startGame() {
		//1. Save the instructions for the game in the following string variable.
		String instructions = "The snake starts at the center of the board, moving north (upward).\n" + 
				"The snake moves at a constant speed.\n" + 
				"The snake moves only north, south, east, or west (ignore the versions of the game where the snake can move in curves).\n" + 
				"The snake \"moves\" by adding a square to its head and simultaneously deleting a square from the tip of its tail.\n" + 
				"\"Apples\" appear at random locations, and persist for a random amount of time (but usually long enough for it to be possible for the snake to get to the apple).\n" + 
				"There is always exactly one apple visible at any given time.\n" + 
				"When the snake \"eats\" (runs into) an apple, it gets longer.\n" + 
				"(This is hard to describe, so play a couple of games to see what I mean.) When the snake gets longer, say by n squares, it does so by not deleting squares from its tail for the next n moves.\n" + 
				"The game continues until the snake \"dies\".\n" + 
				"A snake dies by either (1) running into the edge of the board, or (2) by running into its own tail.\n" + 
				"The final score is based on the number of apples eaten by the snake.";
		
		String[] options = new String[] { "Expert", "Moderate", "Beginner" };
		int input = JOptionPane.showOptionDialog(null, instructions, "Snake", 0, -1, null, options, 0);

		String choice = options[input];
		
		//2. Use a switch statement to determine which difficulty was chosen.
		//   Use timer.setDelay(delay) with different numbers to change the speed
		//   of the game. The smaller the number, the faster it goes.
		switch (choice) {
		case "Expert":
			timer.setDelay(20);
			break;
		case "Moderate":
			timer.setDelay(30);
			break;
		case "Beginner":
			timer.setDelay(40);
			break;
		}
		//3. start the timer
		timer.start();
	}

	public static void main(String[] args) {
		new _00_SnakeGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//1. Use a switch statement on e.getKeyCode()
		//   to determine which key was pressed.
		int KeyCode = e.getKeyCode();
		switch (KeyCode) {
		case KeyEvent.VK_UP:
			snake.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.setDirection(Direction.RIGHT);
			System.out.println("no");
			break;
		case KeyEvent.VK_SPACE:
			snake.feed();
			break;
		}
		// if an arrow key is pressed, set the snake's 
		// direction accordingly
		
		// if the space key is pressed, call the snake's feed method
		
	}

	private void setFoodLocation() {
		Random random = new Random();
		//1. Create a new Location object that is set to a random location
		Location loc = new Location(random.nextInt(WIDTH), random.nextInt(HEIGHT));
		//2. set the foodLocation variable equal to the Location object you just created.
		//   use the snake's isLocationOnSnake method to make sure you don't put the food on the snake
		foodLocation = loc;
	}

	private void gameOver() {
		
		//1. stop the timer
		timer.stop();
		//2. tell the user their snake is dead
		System.out.println("Snake Ded");
		//3. ask them if they want to play again.
		String choice = JOptionPane.showInputDialog("Do you want to ply again? yes/no");
		//4. if they want to play again
		//   reset the snake and the food and start the timer
		//   else, exit the game
		switch (choice) {
		case "yes":
			snake.reset(new Location(WIDTH / 2, HEIGHT / 2));
			timer.restart();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//1. update the snake
		snake.update();
		//2. if the snake is colliding with its own body 
		//   or if the snake is out of bounds, call gameOver
		if (snake.isHeadCollidingWithBody()) {
			gameOver();
		}
		//3. if the location of the head is equal to the location of the food,
		// 	 feed the snake and set the food location
		if (snake.getHeadLocation() == foodLocation) {
			snake.feed();
			setFoodLocation();
		}

		//4. call panel.repaint();
	}
}
