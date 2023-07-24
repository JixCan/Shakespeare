package com.jixcan.shakespeare;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = ShakespeareMod.MODID, name = ShakespeareMod.NAME, version = ShakespeareMod.VERSION)
public class ShakespeareMod {
    public static final String MODID = "shakespeare";
    public static final String NAME = "ShakespeareMod";
    public static final String VERSION = "1.0";
    public static KeyBinding[] keyBindings = {new KeyBinding("Включить защиту", Keyboard.KEY_RCONTROL, "Shakespeare Mod")};
    public static boolean modEnabled = false;
    public static final String prefix = "§6[§eTesla§aCraft§6] ";
    @Mod.EventHandler
    public void preinit(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Listener());
        ClientRegistry.registerKeyBinding(keyBindings[0]);
    }

    static class Listener {
        @SubscribeEvent
        public void onKeyInput(InputEvent.KeyInputEvent event) {
            if (keyBindings[0].isPressed()) {
                modEnabled = !modEnabled;
                EntityPlayer player = Minecraft.getMinecraft().player;
                if (modEnabled) {
                    player.sendMessage(new TextComponentString(prefix + "§aЗащита от мата включена."));
                    player.world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                } else {
                    player.sendMessage(new TextComponentString(prefix + "§cЗащита от мата выключена."));
                    player.world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            }
        }
        @SubscribeEvent
        public void onClientChat(ClientChatEvent event) {
            if (modEnabled){
                String originalMessage = event.getMessage();
                String modifiedMessage = originalMessage.toLowerCase()
                        .replaceAll("[xх][уy]й", "писюн")
                        .replaceAll("[pрп][иi][3зz][dд][aа]", "писька")
                        .replaceAll("[xх][уy][eеё][вВ].*", "плоховатенько")
                        .replaceAll("[aаоo0][xх][уy][её].*", "мегахорош")
                        .replaceAll("[eе][6бb][aа].*", "ОГО!")
                        .replaceAll("[pрп][иi][3з][dд][eе]*.", "ёколомоне")
                        .replaceAll("[сc][уy][кk].*", "чёрт возьми!")
                        .replaceAll("[6бb]ля.*", "блин ")
                        .replaceAll("[3зz][aа].[уy][pрп].*", "писька")
                        .replaceAll("[eе][6б]л.*", "епарасете ")
                        .replaceAll("[3зz][aа][eеё][6бb].*", "надоело это всё! ")
                        .replaceAll("[dд][ао].[6бb][ао][её][6бb].*", "дурачок) ")
                        .replaceAll(".*[её][6бb].*", "***")
                        .replaceAll("[xх][eе][рp]", "хрен");
                event.setMessage(modifiedMessage);
            }
        }
    }
}
