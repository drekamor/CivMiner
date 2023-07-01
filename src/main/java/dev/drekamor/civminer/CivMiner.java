package dev.drekamor.civminer;

import com.google.gson.Gson;
import dev.drekamor.civminer.bot.BotExecutor;
import dev.drekamor.civminer.config.ConfigProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CivMiner implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("civ-miner");
	public static final Gson gson = new Gson();
	public static BotExecutor botExecutor;
	public static KeyBinding toggleBot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"Toggle bot",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_H,
			"CivMiner"
	));
	@Override
	public void onInitializeClient() {
		LOGGER.info("[CivMiner] Hi");
		ClientLifecycleEvents.CLIENT_STARTED.register((e) -> {
			this.init();
		});
	}

	private void init(){
		this.botExecutor = new BotExecutor();
		ConfigProvider.load();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleBot.wasPressed()) {
				botExecutor.onKeyBind();
			}
		});
	}
}
