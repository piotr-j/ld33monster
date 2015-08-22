package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.piotrjastrzebski.monster.game.components.utils.Transient;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Renderable extends PooledComponent implements Transient {
	// region? sprite?
	public TextureRegion region;
	@Override protected void reset () {

	}
}
