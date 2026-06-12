package zelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	public static int WIDTH = 720, HEIGTH = 480;
	public static int SCALE = 3;
	public static Player player;
	public Pedro pedro;

	public World world;
	public List<Pedro> inimigos = new ArrayList<Pedro>();

	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGTH));

		new Spritesheet();
		player = new Player(32, 32);
		world = new World();

		inimigos.add(new Pedro(62, 62));

	}

	public void tick() {
        player.tick();

        // Loop atualizado para controlar a vida e a remoção dos inimigos
        for (int i = inimigos.size() - 1; i >= 0; i--) {
            Pedro inimigoAtual = inimigos.get(i);
            inimigoAtual.tick();

            // Se a vida do Pedro zerar ou ficar menor que zero, remove do jogo
            if (inimigoAtual.vida <= 0) {
                inimigos.remove(i);
            }
        }
    }

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;

		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, WIDTH * SCALE, HEIGTH * SCALE);

		world.render(g);

		player.render(g);
		for (int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).render(g);

		}

		bs.show();
	}

	public static void main(String[] args) {
		Game jogo = new Game();
		JFrame frame = new JFrame();

		frame.setTitle("Mini Zelda");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new java.awt.BorderLayout());

		// Garantir tamanho/área do Canvas (importante para o BufferStrategy)
		//jogo.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
		jogo.setSize(1024, 768);
		frame.add(jogo, java.awt.BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		jogo.requestFocus();
		new Thread(jogo).start();
	}


	@Override
	public void run() {

		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		if (key == KeyEvent.VK_UP) {
			player.up = true;
		}
		if (key == KeyEvent.VK_CONTROL) {
			Player.shoot = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		if (key == KeyEvent.VK_UP) {
			player.up = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
