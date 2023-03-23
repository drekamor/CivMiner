package dev.drekamor.civminer;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CivMiner implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("civ-miner");

	@Override
	public void onInitializeClient() {

		LOGGER.info("Hello Fabric world!");
	}
}
