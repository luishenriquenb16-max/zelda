package zelda;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {

    public int dir = 0;
    public int speed = 6;
    public int frames = 0;
    public boolean jogaFora = false;

    // Sistema de animação da bala
    private int curAnimation = 0;
    private int curFrames = 0;
    private int targetFrames = 10; 

    public Bullet(int x, int y, int dir) {
        super(x, y, 10, 10); 
        this.dir = dir;
    }

   public void tick() {
        boolean colidiu = false;
        int margemPrevisao = 2; // Checa apenas 2 pixels à frente em vez de 6, deixando o impacto bem mais justo

        // --- MOVIMENTAÇÃO E DETECÇÃO AJUSTADA ---
        if (dir == 0) { // Direita
            // Checa a colisão bem colada na ponta direita da bala
            if (World.isFree(x + width + margemPrevisao, y + 2) && World.isFree(x + width + margemPrevisao, y + height - 2)) {
                x += speed;
            } else {
                colidiu = true;
            }
        } else if (dir == 1) { // Esquerda
            if (World.isFree(x - margemPrevisao, y + 2) && World.isFree(x - margemPrevisao, y + height - 2)) {
                x -= speed;
            } else {
                colidiu = true;
            }
        } else if (dir == 2) { // Cima
            if (World.isFree(x + 2, y - margemPrevisao) && World.isFree(x + width - 2, y - margemPrevisao)) {
                y -= speed;
            } else {
                colidiu = true;
            }
        } else if (dir == 3) { // Baixo
            if (World.isFree(x + 2, y + height + margemPrevisao) && World.isFree(x + width - 2, y + height + margemPrevisao)) {
                y += speed;
            } else {
                colidiu = true;
            }
        }

        // Se colidiu com a parede, se auto-remove imediatamente
        if (colidiu) {
            Player.bullets.remove(this);
            return; 
        }

        // Lógica de animação...
        curFrames++;
        if (curFrames >= targetFrames) {
            curFrames = 0;
            curAnimation++;
            if (curAnimation >= Spritesheet.bullet_sprite.length) {
                curAnimation = 0;
            }
        }

        // Tempo de vida máximo do tiro
        frames++;
        if (frames >= 60) {
            Player.bullets.remove(this);
        }
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.bullet_sprite[curAnimation], x, y, width, height, null);
    }
}