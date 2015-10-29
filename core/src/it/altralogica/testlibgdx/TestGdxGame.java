package it.altralogica.testlibgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class TestGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ArrayList<Bunny> bunnies;
	float gravity = 0.5f;
	int minX = 0;
	int maxX = 0;
	int minY = 0;
	int maxY = 0;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("wabbitalpha.png");
		bunnies = new ArrayList<Bunny>();
		maxX = Gdx.app.getGraphics().getWidth();
		maxY = Gdx.app.getGraphics().getHeight();
		font = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		for (Bunny b : bunnies) {
			batch.draw(img, b.posX, b.posY);
		}
		font.draw(batch, "Bunnies: " + bunnies.size(), 0, 100);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 300);
		batch.end();

		if(Gdx.input.isTouched()) {
			addBunnies(1000);
		}

		for(Bunny bunny : bunnies) {
			bunny.posX += bunny.speedX;
			bunny.posY += bunny.speedY;
			bunny.speedY += gravity;

			if( bunny.posX > maxX )
			{
				bunny.speedX *= -1;
				bunny.posX = maxX;
			}
			else if( bunny.posX < minX )
			{
				bunny.speedX *= -1;
				bunny.posX = minX;
			}

			if( bunny.posY > maxY )
			{
				bunny.speedY *= -0.8;
				bunny.posY = maxY;
				if( Math.random() > 0.5 )
					bunny.speedY -= 3 + Math.random() * 4;
			}
			else if( bunny.posY < minY )
			{
				bunny.speedY = 0;
				bunny.posY = minY;
			}
		}

	}

	public void addBunnies(int count) {
		for (int i = 0 ; i < count ; i++) {
			Bunny b = new Bunny();
			b.speedX = (float) (Math.random() * 5);
			b.speedY = (float) (Math.random() * 5 - 2.5);
			bunnies.add(b);
		}
	}
}
