package io.piotrjastrzebski.monster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.piotrjastrzebski.monster.screens.Loading;
import io.piotrjastrzebski.monster.utils.Assets;
import io.piotrjastrzebski.monster.utils.Locator;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class MonsterGame extends Game {
	Locator locator;
	@Override public void create () {
		locator = new Locator();
		locator.game = this;
		locator.assets = new Assets();
		locator.batch = new SpriteBatch();
		locator.shapeRenderer = new ShapeRenderer();

		setScreen(new Loading(locator));

	}

	@Override public void dispose () {
		super.dispose();
		locator.batch.dispose();
		locator.shapeRenderer.dispose();
		locator.assets.dispose();
		locator = null;
	}
}
