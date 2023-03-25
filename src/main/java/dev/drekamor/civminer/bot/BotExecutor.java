package dev.drekamor.civminer.bot;

import dev.drekamor.civminer.config.ConfigProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BotExecutor {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final KeyBinding attackKey = client.options.attackKey;
    private final KeyBinding sneakKey = client.options.sneakKey;
    private final KeyBinding forwardKey = client.options.forwardKey;
    private final MinecraftClient mc = MinecraftClient.getInstance();
    public boolean shouldRun = false;
    public void onKeyBind(){
        if(mc.player != null){
            shouldRun = !shouldRun;
            if(shouldRun) {
                float yaw = Math.round(mc.player.getHeadYaw() / 90) * 90;
                mc.player.setYaw(yaw);
                mc.player.setPitch(14.6f);
            }
            forwardKey.setPressed(shouldRun);
            attackKey.setPressed(shouldRun);
            if(ConfigProvider.safe_mode){
                sneakKey.setPressed(shouldRun);
            }
            else unsafeMode();}
    }

    public void disable(){
        shouldRun = false;
        forwardKey.setPressed(false);
        attackKey.setPressed(false);
        sneakKey.setPressed(false);
    }

    private void unsafeMode(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if(shouldRun){
                    sneakKey.setPressed(true);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    sneakKey.setPressed(false);
                }
                else {
                    executor.shutdown();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
