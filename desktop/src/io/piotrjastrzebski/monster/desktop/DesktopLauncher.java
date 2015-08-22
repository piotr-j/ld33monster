package io.piotrjastrzebski.monster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.piotrjastrzebski.monster.MonsterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useHDPI = true;
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new MonsterGame(), config);
	}
}
