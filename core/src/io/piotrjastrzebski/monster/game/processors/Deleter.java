package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.DeleteAfter;
import io.piotrjastrzebski.monster.game.components.Movement;
import io.piotrjastrzebski.monster.game.components.Rotation;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Deleter extends EntityProcessingSystem {
	protected ComponentMapper<DeleteAfter> mDeleteAfter;
	public Deleter () {
		super(Aspect.all(DeleteAfter.class));
	}

	@Override protected void process (Entity e) {
		DeleteAfter toDelete = mDeleteAfter.get(e);
		toDelete.timer += world.delta;
		if (toDelete.timer > toDelete.after) {
			e.deleteFromWorld();
		}
	}
}
