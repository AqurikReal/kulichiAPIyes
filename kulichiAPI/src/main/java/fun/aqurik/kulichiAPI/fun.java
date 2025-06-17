package fun.aqurik.kulichiAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fun implements CommandExecutor {

    private final KulichiAPI plugin;
    public fun(KulichiAPI plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("KulichiAPI.deach")){
            plugin.toggleDeachLmfao();
            player.sendMessage("§7[KulichiAPI] §aДичь " + (plugin.isDeach ? "включена" : "выключена") + " лол");

        }else{
            player.sendMessage("§7[KulichiAPI] §aАнскилл у тебя нету права §cKulichiAPI.deach");
        }
        return true;
    }


    }

