package zelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Pedro extends Rectangle {

    public static boolean shoot = false;
    public int spd = 2;
    public int right, left, up, down;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public int dir = 1;

    // --- NOVA VARIÁVEL DE VIDA ---
    public int vida = 3; 

    public Pedro(int x, int y) {
        super(x, y, 32, 32);
    }

    public boolean perseguirplayer() {
        Player p = Game.player;
        boolean movido = false;

        if (x < p.x && World.isFree(x + spd, y)) {
            x += spd;
            movido = true;
        } else if (x > p.x && World.isFree(x - spd, y)) {
            x -= spd;
            movido = true;
        }
        if (y < p.y && World.isFree(x, y + spd)) {
            y += spd;
            movido = true;
        } else if (y > p.y && World.isFree(x, y - spd)) {
            y -= spd;
            movido = true;
        }
        
        return movido; // Retorna true se ele realmente andou
    }

    public void tick() {
        // Agora o 'moved' recebe o resultado real se ele andou perseguindo o player
        boolean moved = perseguirplayer();

        if (moved) {
            curFrames++;
            if (curFrames >= targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.pedro_right.length) {
                    curAnimation = 0;
                }
            }
        } else {
            curAnimation = 0; // Fica estático no primeiro frame se não conseguir se mover
        }

        // --- NOVO: SISTEMA DE COLISÃO COM AS BALAS DO PLAYER ---
       // --- ATUALIZE ESSE LOOP NO TICK DO PEDRO ---
        // Loop invertido para poder remover a bala da lista do Player imediatamente
        for (int i = Player.bullets.size() - 1; i >= 0; i--) {
            Bullet b = Player.bullets.get(i);
            
            // Se o Pedro encostar na bala
            if (this.intersects(b)) {
                this.vida--; // Pedro perde vida
                
                // Remove a bala DIRETAMENTE da lista do Player na mesma hora
                Player.bullets.remove(i); 
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
        g.drawImage(Spritesheet.pedro_right[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }
}