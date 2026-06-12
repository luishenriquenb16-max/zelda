package zelda;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {

    public static BufferedImage spritesheet;

    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_down;
    
    public static BufferedImage[] pedro_right;
    public static BufferedImage[] bullet;
    public static BufferedImage tileWall;
    public static BufferedImage[] bullet_sprite;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritess.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
// Inicializa os tamanhos dos arrays
        player_right = new BufferedImage[2];
        player_left  = new BufferedImage[2];
        player_down  = new BufferedImage[2];
        player_up    = new BufferedImage[2];
        pedro_right  = new BufferedImage[6];
        bullet_sprite = new BufferedImage[4];

        // PLAYER DIREITA (Substitua X e Y pelas posições reais mantendo 16, 16)
        player_right[0] = Spritesheet.getSprite(124, 14, 14, 15);
        player_right[1] = Spritesheet.getSprite(157, 21, 14, 16); // Exemplo de grid correto

        // PLAYER ESQUERDA (Mude o X e Y para onde o boneco está olhando para a esquerda)
        player_left[0]  = Spritesheet.getSprite(171, 14, 16, 16); // <--- Mude aqui
        player_left[1]  = Spritesheet.getSprite(189, 14, 16, 16); // <--- Mude aqui

        // PLAYER CIMA (Mude o X e Y para onde o boneco está olhando para cima)
        player_up[0]    = Spritesheet.getSprite(71 , 13, 12, 16); // <--- Mude aqui
        player_up[1]    = Spritesheet.getSprite(88, 13, 12, 16); // <--- Mude aqui

        // PLAYER BAIXO (Mude o X e Y para onde o boneco está olhando para baixo)
        player_down[0]  = Spritesheet.getSprite(1, 13, 15, 16); // <--- Mude aqui (Não deixe igual ao UP)
        player_down[1]  = Spritesheet.getSprite(19, 13, 13, 16); // <--- Mude aqui
        // INIMIGO PEDRO
        pedro_right[0]  = Spritesheet.getSprite(12, 227, 24, 35);
        pedro_right[1]  = Spritesheet.getSprite(100, 227, 22, 35);
        pedro_right[2]  = Spritesheet.getSprite(185, 226, 23, 36);
        pedro_right[3]  = Spritesheet.getSprite(271, 226, 25, 34);
        pedro_right[4]  = Spritesheet.getSprite(356, 226, 22, 38);
        pedro_right[5]  = Spritesheet.getSprite(441, 226, 21, 36);

        // PAREDE DO MAPA
        tileWall = Spritesheet.getSprite(383, 161,32, 41);

        bullet_sprite[0] = Spritesheet.getSprite(51, 187, 11, 12);
        bullet_sprite[1] = Spritesheet.getSprite(64, 187, 14, 12);
        bullet_sprite[2] = Spritesheet.getSprite(78, 187, 12, 11);
        bullet_sprite[3] = Spritesheet.getSprite(61, 201, 12, 8);
        
    }



    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}