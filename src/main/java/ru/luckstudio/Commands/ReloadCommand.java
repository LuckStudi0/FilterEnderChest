package ru.luckstudio.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import ru.luckstudio.FilterEnderChest;
import ru.luckstudio.HexUtils;

public class ReloadCommand implements CommandExecutor {

    FilterEnderChest plugin;

    public ReloadCommand(FilterEnderChest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("filench.admin")) {
            ConfigurationSection mess = plugin.getConfig().getConfigurationSection("messages");
            if (mess == null) {
                commandSender.sendMessage("Значения в конфиге не обнаружены, удалите папку FilterEnderChest!");
                return true;
            }
            String reloadMessage = HexUtils.color(mess.getString("reload"));
            String console = HexUtils.color(mess.getString("console"));
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(console);
                return true;
            }

            if (strings.length == 1 && strings[0].equalsIgnoreCase("reload")) {
                commandSender.sendMessage(reloadMessage);
                plugin.reloadConfig();
            }

            return true;
        }
        return false;
    }
}
