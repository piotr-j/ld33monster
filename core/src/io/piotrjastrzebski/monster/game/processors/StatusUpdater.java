package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class StatusUpdater extends EntityProcessingSystem {
	protected ComponentMapper<Status> mStatus;
	protected ComponentMapper<Dead> mDead;
	public StatusUpdater () {
		super(Aspect.all(Status.class));
	}

	@Override protected void process (Entity e) {
		Status status = mStatus.get(e);
		if (status.health <= 0) {
			e.deleteFromWorld();
			Gdx.app.log("", "ded! " + e);
		}
	}
}
