package dev.drekamor.civminer.mixin;

import dev.drekamor.civminer.bot.BotExecutor;
import dev.drekamor.civminer.config.ConfigProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.UUID;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {

    private BotExecutor bot = new BotExecutor();
    @Inject(at = @At("RETURN"), method = "onChatMessage", locals = LocalCapture.CAPTURE_FAILHARD)
    public void ChatListener(MessageType type, Text message, UUID sender, CallbackInfo ci){
        if(message.getString().contains(ConfigProvider.stop_text)){
            bot.disable();
            MinecraftClient.getInstance().player.sendChatMessage(ConfigProvider.message);
        }
    }
}
