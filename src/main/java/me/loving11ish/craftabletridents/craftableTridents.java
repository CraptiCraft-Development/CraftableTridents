package me.loving11ish.craftabletridents;

import me.loving11ish.craftabletridents.Recipies.tridentRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class craftableTridents extends JavaPlugin {

    public static craftableTridents plugin;
    private PluginDescriptionFile pluginInfo = getDescription();
    private String pluginVersion = pluginInfo.getVersion();

    @Override
    public void onEnable() {
        //Server version compatibility check
        if (!(Bukkit.getServer().getVersion().contains("1.13")||Bukkit.getServer().getVersion().contains("1.14")||
                Bukkit.getServer().getVersion().contains("1.15")||Bukkit.getServer().getVersion().contains("1.16")||
                Bukkit.getServer().getVersion().contains("1.17"))){
            System.out.println(ChatColor.RED + "-------------------------------------------");
            System.out.println(ChatColor.RED + "CraftableTridents - This plugin is only supported on the Minecraft versions listed below:");
            System.out.println(ChatColor.RED + "CraftableTridents - 1.13.x");
            System.out.println(ChatColor.RED + "CraftableTridents - 1.14.x");
            System.out.println(ChatColor.RED + "CraftableTridents - 1.15.x");
            System.out.println(ChatColor.RED + "CraftableTridents - 1.16.x");
            System.out.println(ChatColor.RED + "CraftableTridents - 1.17.x");
            System.out.println(ChatColor.RED + "CraftableTridents - Is now disabling!");
            System.out.println(ChatColor.RED + "-------------------------------------------");
            Bukkit.getPluginManager().disablePlugin(this);
        }else {
            System.out.println(ChatColor.GREEN + "-------------------------------------------");
            System.out.println(ChatColor.GREEN + "CraftableTridents - A supported Minecraft version has been detected");
            System.out.println(ChatColor.GREEN + "CraftableTridents - Continuing plugin startup");
            System.out.println(ChatColor.GREEN + "-------------------------------------------");
        }

        // Plugin startup logic
        plugin = this;
        tridentRecipe recipe = new tridentRecipe();
        recipe.Recipe();

        //Plugin startup message
        System.out.println("-------------------------------------------");
        System.out.println(ChatColor.AQUA + "CraftableTridents - Plugin By Loving11ish");
        System.out.println(ChatColor.AQUA + "CraftableTridents - has been loaded successfully!");
        System.out.println(ChatColor.AQUA + "CraftableTridents - Plugin Version " + pluginVersion);
        System.out.println("-------------------------------------------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.clearRecipes();

        //Plugin shutdown message
        System.out.println("-------------------------------------------");
        System.out.println(ChatColor.AQUA + "CraftableTridents - Plugin By Loving11ish");
        System.out.println(ChatColor.AQUA + "CraftableTridents - has been disabled successfully!");
        System.out.println("-------------------------------------------");
    }

    public static craftableTridents getPlugin() {
        return plugin;
    }
}
