import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	public static void main(String[] args) {
		double lowerVelocity = 0.005;
		double upperVelocity = 0.01;
		
		double radius = 0.025;
		int score = 0;
		int highscore = 0;

		double playerX = 0.5;
		double playerY = 0.5;
		double playerSpeed = 0.01;
		
		int ballCount = 3;
		double[] ballsXLocation = new double[ballCount];
		double[] ballsYLocation = new double[ballCount];
		double[] ballsXVelocites = new double[ballCount];
		double[] ballsYVelocites = new double[ballCount];

		PlayerControl player = new PlayerControl(playerX, playerY, playerSpeed);
		EnemyControl enemies = new EnemyControl(ballCount, radius, lowerVelocity, upperVelocity, ballsXLocation, ballsYLocation, ballsXVelocites, ballsYVelocites);

		enemies.setInitialBalls();
		
		StdDraw.enableDoubleBuffering();
		
		long startTime = System.currentTimeMillis();
		long deltaTime = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean c = false;
			for(int i = 0; i < enemies.getBallCount(); i++) {
				
				enemies.updateBallLocation(i);
				
				double d = Math.sqrt(Math.pow(enemies.getBallX(i) - player.getPlayerX(), 2) + Math.pow(enemies.getBallY(i) - player.getPlayerY(), 2));
				if(d < 2 * radius) {
					c = true;
				}
			}
			
			//reset
			if(c) {
				ballCount = 3;
				for(int i = 0; i < ballCount; i++) {
					enemies.setBallX(i, Math.random());
					enemies.setBallY(i, Math.random());
					ballsXVelocites[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
					ballsYVelocites[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
					score = 0;
					startTime= System.currentTimeMillis();
					deltaTime= System.currentTimeMillis();
					player.setPlayerX(0.5);
					player.setPlayerY(0.5);
				}
			}
			
			//player control
			player.move();
			
			//highscore
			long now = System.currentTimeMillis();
			if(now > startTime+ 1000) {
				score++;
				if(score > highscore) {
					highscore = score;
				}
				startTime= now;
			}

			//add enemy balls
			if(now > deltaTime + 10000) {
				enemies.addBalls();
				deltaTime= now;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < ballCount; i++) {
				StdDraw.filledCircle(ballsXLocation[i], ballsYLocation[i], radius);
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(player.getPlayerX(), player.getPlayerY(), radius);
			StdDraw.text(0.5, 0.1, "Score: " + score+ " High Score: " + highscore);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}
}
