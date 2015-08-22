package io.piotrjastrzebski.monster.screens;

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
		locator.game.setScreen(new Game(locator));
	}
}
