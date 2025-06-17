package fun.aqurik.kulichiAPI;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Cummands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("KulichiAPI.getKulich")) {
            ItemStack kulich = new ItemStack(Material.BREAD, 1);
            ItemMeta kulichdeach = kulich.getItemMeta();

            kulichdeach.addEnchant(Enchantment.SHARPNESS, 1, true);
            kulichdeach.setDisplayName("§7[Basic] Кулич");
            kulich.setItemMeta(kulichdeach);

            p.give(kulich);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            p.sendMessage("§7[KulichiAPI] §aЙоу йоу йоу, держи кулич");
        }else{
            p.sendMessage("§7[KulichiAPI] §aАнскилл у тебя нету права §cKulichiAPI.getKulich");
        }
        return true;
    }
}