package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Tint extends PooledComponent {
	public Color color = new Color();

	public Tint set(float r, float g, float b) {
		color.set(r, g, b, color.a);
		return this;
	}

	public Tint set(float r, float g, float b, float a) {
		color.set(r, g, b, a);
		return this;
	}

	@Override protected void reset () {
		color.set(Color.WHITE);
	}
}
