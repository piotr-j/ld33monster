package io.piotrjastrzebski.monster.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.piotrjastrzebski.monster.utils.Locator;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Game extends Base {

	World world;
	public Game (Locator locator) {
		super(locator);
		WorldConfiguration config = new WorldConfiguration();

		world = new World(config);


	}

	@Override public void render (float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.delta = delta;
		world.process();
	}

	@Override public void resize (int width, int height) {
		super.resize(width, height);
		// todo handle resize
	}

	@Override public void dispose () {
		super.dispose();
		world.dispose();
	}
}
