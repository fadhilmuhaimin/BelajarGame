package com.belajar.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.HashMap;

public class BelajarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureAtlas textureAtlas;
	Sprite banana;
	OrthographicCamera camera;
	ExtendViewport viewport;

	final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		textureAtlas = new TextureAtlas("sprites.txt");
		banana = textureAtlas.createSprite("banana");
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(800, 600, camera);

		addSprites();
	}

	private void addSprites() {
		Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

		for (TextureAtlas.AtlasRegion region : regions) {
			Sprite sprite = textureAtlas.createSprite(region.name);

			sprites.put(region.name, sprite);
		}
	}

    private void drawSprite(String name, float x, float y) {
        Sprite sprite = sprites.get(name);

        sprite.setPosition(x, y);

        sprite.draw(batch);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
//		batch.draw(img, 0, 0);
		banana.setPosition(0 , 0);
		banana.draw(batch);

		drawSprite("banana" , 0 , 0);
		drawSprite("cherries" , 100 , 100);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		textureAtlas.dispose();
		sprites.clear();
	}
}
