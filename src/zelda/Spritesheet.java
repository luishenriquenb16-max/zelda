
package zelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public static BufferedImage spritesheet;
	public static BufferedImage spritesheet1;

	public boolean right, left, up, down;

	public static BufferedImage[] player_right;
	public static BufferedImage[] pedro_right;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_down;

	public static BufferedImage tileWall;

	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritess.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		pedro_right = new BufferedImage[6];

		player_right[0] = Spritesheet.getSprite(94, 78, 16, 16);
		player_right[1] = Spritesheet.getSprite(35, 11, 14, 16);
		player_right[0] = Spritesheet.getSprite(94, 78, 16, 16);
		player_right[1] = Spritesheet.getSprite(35, 11, 14, 16);
		player_up[0] = Spritesheet.getSprite(94, 109, 16, 16);

		pedro_right[0] = Spritesheet.getSprite(12, 227, 24, 35);
		pedro_right[1] = Spritesheet.getSprite(100, 227, 22, 35);
		pedro_right[2] = Spritesheet.getSprite(185, 226, 23, 36);
		pedro_right[3] = Spritesheet.getSprite(271, 226, 25, 34);
		pedro_right[4] = Spritesheet.getSprite(356, 226, 22, 38);
		pedro_right[5] = Spritesheet.getSprite(441, 226, 21, 36);

		tileWall = Spritesheet.getSprite(383, 161, 47, 41);

	}

	public static  BufferedImage getSprite(int x, int y, int width, int height) {

		return spritesheet.getSubimage(x, y, width, height);

	}

}
