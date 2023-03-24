package dev.drekamor.civminer.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigProvider {
    public static String stop_text = "You sense debris";
    public static int stop_distance = 1000;
    public static Boolean safe_mode = true;
    private static Path getPath = FabricLoader.getInstance().getConfigDir();
    private static String path = getPath + "/civminer.json";

    public static void load(){
        ConfigInitializer.init();
        try {
            String content = Files.readString(Paths.get(path));
            JsonObject config = JsonParser.parseString(content).getAsJsonObject();
            stop_text = config.get("stop_text").getAsString();
            stop_distance = config.get("stop_distance").getAsInt();
            safe_mode = config.get("safe_mode").getAsBoolean();
        } catch (Exception e) {
        }
    }
}
