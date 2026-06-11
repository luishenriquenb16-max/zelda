package zelda;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

//import java.awt.Color;

public class Player extends Rectangle {

	public static boolean shoot = false;
	public int spd = 4;
	public boolean right, left, up, down;

	public int curAnimation = 0;

	public int curFrames = 0, targetFrames = 15;

	public static List<Bullet> bullets = new ArrayList<Bullet>();

	public int dir = 1;

	public Player(int x, int y) {
		super(x, y, 32, 32);
	}

	public void tick() {
		boolean moved = false;
		if (right && World.isFree(x + spd, y)) {
			x += spd;
			moved = true;
			dir  = -1;
		} else if (left && World.isFree(x - spd, y)) {
			x -= spd;
			moved = true;
			dir = -1;
		}

		if (up && World.isFree(x, y - spd)) {
			y -= spd;
			moved = true;
			dir = -1;

		} else if (down && World.isFree(x, y + spd)) {
			y += spd;
			moved = true;
			dir = 1;

		}
		if (moved) {
			curFrames++;
			if (curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if (curAnimation == Spritesheet.player_right.length) {

					curAnimation = 0;

				}

				if (curAnimation == Spritesheet.player_up.length) {

					curAnimation = 0;

				}
				if (curAnimation == Spritesheet.player_left.length) {

					curAnimation = 0;
				}
				if (curAnimation == Spritesheet.player_down.length) {

					curAnimation = 0;
				}
			}
		}
		if (shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));

		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}

	}

	public void render(Graphics g) {
		// g.setColor(Color.blue);
		// g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_right[curAnimation], x, y, 32, 32, null);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}

		if (left) {
			g.drawImage(Spritesheet.player_right[curAnimation], x, y, 32, 32, null);

		}

		if (up) {
			g.drawImage(Spritesheet.player_up[curAnimation], x, y, 32, 32, null);

		}

		{

		}

	}

}
