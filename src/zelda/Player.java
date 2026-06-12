package zelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
    

    public static boolean shoot = false;
    public int spd = 4;
    public boolean right, left, up, down;

    public int curAnimation = 0;
    public int curFrames = 0;
    public int targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    // 0=right, 1=left, 2=up, 3=down
    private int facing = 0;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        boolean moved = false;
        int oldFacing = facing;

        // Movimento horizontal
        if (right && World.isFree(x + spd, y)) {
            x += spd;
            moved = true;
            facing = 0;
        } else if (left && World.isFree(x - spd, y)) {
            x -= spd;
            moved = true;
            facing = 1;
        }

        // Movimento vertical
        if (up && World.isFree(x, y - spd)) {
            y -= spd;
            moved = true;
            facing = 2;
        } else if (down && World.isFree(x, y + spd)) {
            y += spd;
            moved = true;
            facing = 3;
        }

        // Se mudou de direção, reseta a animação para evitar IndexOutOfBounds
        if (facing != oldFacing) {
            curAnimation = 0;
            curFrames = 0;
        }

        // Atualiza animação só quando move
        if (moved) {
            curFrames++;
            if (curFrames >= targetFrames) {
                curFrames = 0;
                int len = getSpritesLength();
                curAnimation = (curAnimation + 1) % len;
            }
        } else {
            // Opcional: Reseta para o frame inicial parado quando não estiver se movendo
            curAnimation = 0;
        }

        // Sistema de Tiro (Garante que a bala saiba a direção correta do jogador)
        if (shoot) {
            shoot = false;
            // Passamos o 'facing' atual para que a Bullet saiba para onde ir (0, 1, 2 ou 3)
            bullets.add(new Bullet(x + 8, y + 8, facing)); 
        }

        // Atualiza as balas
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }
    }

    public void render(Graphics g) {
        int len = getSpritesLength();
        int index = curAnimation % len;

        // Desenha o player baseado na direção atual
        if (facing == 0) {
            g.drawImage(Spritesheet.player_right[index], x, y, 32, 32, null);
        } else if (facing == 1) {
            g.drawImage(Spritesheet.player_left[index], x, y, 32, 32, null);
        } else if (facing == 2) {
            g.drawImage(Spritesheet.player_up[index], x, y, 32, 32, null);
        } else if (facing == 3) {
            g.drawImage(Spritesheet.player_down[index], x, y, 32, 32, null);
        }

        // Renderiza as balas
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }

    // Método auxiliar para evitar repetição de código (Clean Code)
    private int getSpritesLength() {
        if (facing == 0 && Spritesheet.player_right != null) return Spritesheet.player_right.length;
        if (facing == 1 && Spritesheet.player_left != null) return Spritesheet.player_left.length;
        if (facing == 2 && Spritesheet.player_up != null) return Spritesheet.player_up.length;
        if (facing == 3 && Spritesheet.player_down != null) return Spritesheet.player_down.length;
        return 2; // Valor padrão caso os sprites não tenham sido carregados
    }
}