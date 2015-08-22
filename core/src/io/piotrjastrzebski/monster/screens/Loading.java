package io.piotrjastrzebski.monster.screens;

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
		timer += delta;
		if (assets.update() && timer > SPLASH_TIME) {
			locator.game.setScreen(new MainMenu(locator));
		}
	}
}
