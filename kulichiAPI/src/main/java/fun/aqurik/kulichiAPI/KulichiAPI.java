package fun.aqurik.kulichiAPI;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public final class KulichiAPI extends JavaPlugin implements Listener {

    public boolean isDeach = false;
    public boolean forceBanned = false;
    public boolean isKulichiBanned = false;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration cfg = getConfig();
        isKulichiBanned = cfg.getBoolean("isKulichiBanned", false);


        getServer().getPluginManager().registerEvents(this, this);
        getCommand("getkulichepic").setExecutor(new Commands());
        getCommand("toggledeach").setExecutor(new fun(this));
        getCommand("getkulich").setExecutor(new Cummands());
        getCommand("getmorekulich").setExecutor(new Cammands());
        getCommand("bankulichi").setExecutor(new unfun(this));
        getCommand("fban").setExecutor(new ban(this));

        getLogger().info("Йоу йоу йоу я живой");


    }




    @Override
    public void onDisable() {
        saveDefaultConfig();
        getLogger().info("Сохраняю конфиг...");
        FileConfiguration cfg = getConfig();
        cfg.set("isKulichiBanned", isKulichiBanned);
        saveConfig();
        getLogger().info("Конфиг сохранен");
        getLogger().info("Выключаюсь...");

    }

    @EventHandler
    public void onPlayerInput(PlayerInputEvent event){
        if (forceBanned){
            return;
        }
        if (isDeach){
            ItemStack kulich = new ItemStack(Material.BREAD, 1);
            ItemMeta kulichdeach = kulich.getItemMeta();


            kulichdeach.addEnchant(Enchantment.SHARPNESS, 1, true);
            kulichdeach.setDisplayName("§7[Basic] Кулич");
            kulich.setItemMeta(kulichdeach);


            event.getPlayer().give(kulich);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            event.getPlayer().sendMessage("§7[KulichiAPI] §aЙоу йоу йоу, держи кулич");
        };



    };

    @EventHandler
    public void delete(PlayerDropItemEvent event){
        if (isKulichiBanned){
            if (!event.getPlayer().hasPermission("KulichiAPI.banKulichi.bypass") && !forceBanned) {
                if (event.getItemDrop().getItemStack().getType() == Material.BREAD) {

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                    event.getPlayer().sendMessage("§7[KulichiAPI] §cКуличи были заблокированы на этом сервере.");
                    event.getItemDrop().remove();
                }
            }
        }
    }

    @EventHandler
    public void delete2(BlockDispenseEvent event){
        if (isKulichiBanned){
            if (event.getItem().getType() == Material.BREAD){
                event.setCancelled(true);
            }
        }

    }
    @EventHandler
    public void delete3(ItemSpawnEvent event) {
        if (forceBanned) {
            if (event.getEntityType() == EntityType.ITEM) {
                Item item = event.getEntity();
                if (item.getItemStack().getType() == Material.BREAD) {
                    event.setCancelled(true);
                }
            }
        }
    }




    public void toggleDeachLmfao() {
        isDeach = !isDeach;
    }

    public void toggleKulichiLmfao() {
        isKulichiBanned = !isKulichiBanned;
    }

    public void forceKulichiLmfao(){
        forceBanned = !forceBanned;
        if (!isKulichiBanned){
            isKulichiBanned = true;
        }else{
            if (forceBanned){
                isKulichiBanned = true;
            }else{
                isKulichiBanned = false;
            }
        }

    }


}

