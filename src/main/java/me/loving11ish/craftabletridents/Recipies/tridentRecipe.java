package me.loving11ish.craftabletridents.Recipies;

import me.loving11ish.craftabletridents.craftableTridents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class tridentRecipe {

    public void unEnchantedRecipe(){
        NamespacedKey key = new NamespacedKey(craftableTridents.getPlugin(), "trident");
        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
        ShapedRecipe craftTrident = new ShapedRecipe(key, trident);
        craftTrident.shape("DDD", "PHP", " P ");
        craftTrident.setIngredient('D', Material.DIAMOND);
        craftTrident.setIngredient('P', Material.PRISMARINE_SHARD);
        craftTrident.setIngredient('H', Material.HEART_OF_THE_SEA);
        Bukkit.addRecipe(craftTrident);
    }
    public void enchantedRecipe(){
        NamespacedKey key = new NamespacedKey(craftableTridents.getPlugin(), "enchanted_trident");

        ItemStack enchanted_trident = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = enchanted_trident.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "Poseidon's Staff");
        meta.addEnchant(Enchantment.CHANNELING, 1, false);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        enchanted_trident.setItemMeta(meta);

        ShapedRecipe craftEnchantedTrident = new ShapedRecipe(key, enchanted_trident);
        craftEnchantedTrident.shape("DDD", "PHP", "NPB");
        craftEnchantedTrident.setIngredient('D', Material.DIAMOND);
        craftEnchantedTrident.setIngredient('P', Material.PRISMARINE_SHARD);
        craftEnchantedTrident.setIngredient('H', Material.HEART_OF_THE_SEA);
        craftEnchantedTrident.setIngredient('N', Material.NETHER_STAR);
        craftEnchantedTrident.setIngredient('B', Material.BOOK);
        Bukkit.addRecipe(craftEnchantedTrident);
    }
}
