package io.piotrjastrzebski.monster.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Assets {
	public static final String GAME_ATLAS = "pack.atlas";
	TextureAtlas gameAtlas;
	AssetManager manager;
	public Assets () {
		manager = new AssetManager();
		manager.load(GAME_ATLAS, TextureAtlas.class);
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
		gameAtlas = manager.get(GAME_ATLAS, TextureAtlas.class);
		// ...
	}

	public void dispose () {
		manager.dispose();
	}

	public TextureAtlas.AtlasRegion getRegion (String path) {
		// TODO cache
		return gameAtlas.findRegion(path);
	}

	public Array<TextureAtlas.AtlasRegion> getRegions (String path) {
		// TODO cache
		return gameAtlas.findRegions(path);
	}
}
