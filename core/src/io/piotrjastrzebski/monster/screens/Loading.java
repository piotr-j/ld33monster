package io.piotrjastrzebski.monster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.piotrjastrzebski.monster.utils.Assets;
import io.piotrjastrzebski.monster.utils.Locator;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Loading extends Base {
	private final static float SPLASH_TIME = 0.1f;
	Assets assets;
	public Loading (Locator locator) {
		super(locator);
		assets = locator.assets;
	}

	float timer;
	@Override public void render (float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		timer += delta;
		if (assets.update() && timer > SPLASH_TIME) {
			locator.game.setScreen(new MainMenu(locator));
		}
	}
}
