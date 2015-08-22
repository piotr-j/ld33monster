package io.piotrjastrzebski.monster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.piotrjastrzebski.monster.utils.Locator;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class MainMenu extends Base {
	public MainMenu (Locator locator) {
		super(locator);

	}

	@Override public void render (float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		locator.game.setScreen(new Game(locator));
	}
}
