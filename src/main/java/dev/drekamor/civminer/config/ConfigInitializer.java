package dev.drekamor.civminer.config;

import com.google.gson.JsonObject;
import dev.drekamor.civminer.CivMiner;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigInitializer {
    private static Path getPath = FabricLoader.getInstance().getConfigDir();
    private static String path = getPath + "/civminer.json";

    public static void init(){
        String stop_text = "You sense debris";
        int stop_distance = 1000;
        Boolean safe_mode = true;
        JsonObject config = new JsonObject();
        config.addProperty("stop_text", stop_text);
        config.addProperty("stop_distance", stop_distance);
        config.addProperty("safe_mode", safe_mode);
        String text = CivMiner.gson.toJson(config);
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
        }
    }
}
