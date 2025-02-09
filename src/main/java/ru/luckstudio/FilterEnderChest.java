package ru.luckstudio;

import org.bukkit.plugin.java.JavaPlugin;
import ru.luckstudio.Commands.ReloadCommand;
import ru.luckstudio.Event.EventListener;

public final class FilterEnderChest extends JavaPlugin {

    @Override
    public void onEnable() {
        message();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("filterenderchest").setExecutor(new ReloadCommand(this));
    }

    @Override
    public void onDisable() {
        message();

    }
    private void message() {
        getLogger().info("███████╗██╗██╗░░░░░███████╗███╗░░██╗░█████╗░██╗░░██╗");
        getLogger().info("██╔════╝██║██║░░░░░██╔════╝████╗░██║██╔══██╗██║░░██║");
        getLogger().info("█████╗░░██║██║░░░░░█████╗░░██╔██╗██║██║░░╚═╝███████║");
        getLogger().info("██╔══╝░░██║██║░░░░░██╔══╝░░██║╚████║██║░░██╗██╔══██║");
        getLogger().info("██║░░░░░██║███████╗███████╗██║░╚███║╚█████╔╝██║░░██║");
        getLogger().info("╚═╝░░░░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝░╚════╝░╚═╝░░╚═╝");
        getLogger().info("By LuckStudio (:");
    }
}
