package dev.drekamor.civminer.config;

import com.google.gson.JsonObject;
import dev.drekamor.civminer.CivMiner;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigInitializer {
    private static Path getPath = FabricLoader.getInstance().getConfigDir();
    private static String path = getPath + "/civminer.json";

    public static void init() {
        String stop_text = "You sense debris";
        String message = "I have found debris!";
        Boolean safe_mode = true;
        JsonObject config = new JsonObject();
        config.addProperty("stop_text", stop_text);
        config.addProperty("message", message);
        config.addProperty("safe_mode", safe_mode);
        String text = CivMiner.gson.toJson(config);
        File file = new File(path);
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(text);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
