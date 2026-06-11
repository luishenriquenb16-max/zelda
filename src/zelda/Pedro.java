package zelda;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

//import java.awt.Color;

public class Pedro extends Rectangle {

    public static boolean shoot = false;
    public int spd = 2;
    public int right, left, up, down;

    public int curAnimation = 0;

    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public int dir = 1;

    public Pedro(int x, int y) {
        super(x, y, 32, 32);
    }

    public void perseguirplayer() {
        Player p = Game.player;
        if (x < p.x && World.isFree(x + spd, y)) {
            x += spd;

        } else if (x > p.x && World.isFree(x - spd, y)) {
            x -= spd;
        }
        if (y < p.y && World.isFree(x, y + spd)) {
            y += spd;

        } else if (y > p.y && World.isFree(x, y - spd)) {
            y -= spd;
        }

    }

    public void tick() {
        boolean moved = (World.isFree(x + 1, y));
        perseguirplayer();

        if (moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 2;
                curAnimation++;
                if (curAnimation == Spritesheet.pedro_right.length) {

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
        // g.setColor(Color.red);
        // g.fillRect(x, y, width, height);
        g.drawImage(Spritesheet.pedro_right[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }

        {

        }

    }

}
