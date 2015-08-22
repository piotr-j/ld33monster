package io.piotrjastrzebski.monster.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Assets {

	AssetManager manager;
	public Assets () {
		manager = new AssetManager();
		manager.load("monster_baby.png", Texture.class);
	}

	boolean loaded;
	public boolean update() {
		if (!loaded && manager.update()) {
			finishLoading();
		}
		return loaded;
	}

	private void finishLoading () {
		loaded = true;
		manager.finishLoading();
		// ...
	}

	public void dispose () {
		manager.dispose();
	}
}
