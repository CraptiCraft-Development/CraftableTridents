package me.loving11ish.craftabletridents.recipes;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TridentRecipe {

    FileConfiguration configFile = CraftableTridents.getPlugin().getConfig();

    public void unEnchantedRecipe(){
        NamespacedKey key = new NamespacedKey(CraftableTridents.getPlugin(), "trident");

        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
        ItemMeta tridentMeta = trident.getItemMeta();
        if (configFile.getBoolean("standard-trident.name.enabled")){
            tridentMeta.setDisplayName(ColorUtils.translateColorCodes(configFile.getString("standard-trident.name.name")));
        }
        if (configFile.getBoolean("standard-trident.lore.enabled")){
            ArrayList<String> tridentLore = new ArrayList<>();
            List<String> loreList = configFile.getStringList("standard-trident.lore.lore");
            for (String string : loreList){
                tridentLore.add(ColorUtils.translateColorCodes(string));
            }
            tridentMeta.setLore(tridentLore);
        }
        trident.setItemMeta(tridentMeta);

        CraftableTridents.getPlugin().setTridentItem(trident);

        ShapedRecipe craftTrident = new ShapedRecipe(key, trident);
        if (configFile.getBoolean("standard-trident.custom-recipe.enabled")){
            craftTrident.shape("123", "456", "789");

            craftTrident.setIngredient('1', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.top-row.slot-1")));
            craftTrident.setIngredient('2', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.top-row.slot-2")));
            craftTrident.setIngredient('3', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.top-row.slot-3")));

            craftTrident.setIngredient('4', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.middle-row.slot-1")));
            craftTrident.setIngredient('5', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.middle-row.slot-2")));
            craftTrident.setIngredient('6', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.middle-row.slot-3")));

            craftTrident.setIngredient('7', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.bottom-row.slot-1")));
            craftTrident.setIngredient('8', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.bottom-row.slot-2")));
            craftTrident.setIngredient('9', Material.getMaterial(configFile.getString("standard-trident.custom-recipe.recipe.bottom-row.slot-3")));
        }else {
            craftTrident.shape("DDD", "PHP", " P ");
            craftTrident.setIngredient('D', Material.DIAMOND);
            craftTrident.setIngredient('P', Material.PRISMARINE_SHARD);
            craftTrident.setIngredient('H', Material.HEART_OF_THE_SEA);
        }
        Bukkit.addRecipe(craftTrident);
    }


    public void enchantedRecipe(){
        NamespacedKey key = new NamespacedKey(CraftableTridents.getPlugin(), "enchanted_trident");

        ItemStack enchanted_trident = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = enchanted_trident.getItemMeta();
        if (configFile.getBoolean("op-trident.name.enabled")){
            meta.setDisplayName(ColorUtils.translateColorCodes(configFile.getString("op-trident.name.name")));
        }
        if (configFile.getBoolean("op-trident.lore.enabled")){
            ArrayList<String> lore = new ArrayList<>();
            List<String> loreList = configFile.getStringList("op-trident.lore.lore");
            for (String string : loreList){
                lore.add(ColorUtils.translateColorCodes(string));
            }
            meta.setLore(lore);
        }
        if (configFile.getBoolean("op-trident.enchantments.enabled")){
            List<String> enchantsList = configFile.getStringList("op-trident.enchantments.enchantments");
            for (String string : enchantsList){
                String[] enchantment = string.split(" ");
                meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchantment[0])), Integer.parseInt(enchantment[1]), true);
            }
        }
        enchanted_trident.setItemMeta(meta);

        CraftableTridents.getPlugin().setOpTridentItem(enchanted_trident);

        ShapedRecipe craftEnchantedTrident = new ShapedRecipe(key, enchanted_trident);
        if (configFile.getBoolean("op-trident.custom-recipe.enabled")){
            craftEnchantedTrident.shape("123", "456", "789");

            craftEnchantedTrident.setIngredient('1', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.top-row.slot-1")));
            craftEnchantedTrident.setIngredient('2', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.top-row.slot-2")));
            craftEnchantedTrident.setIngredient('3', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.top-row.slot-3")));

            craftEnchantedTrident.setIngredient('4', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.middle-row.slot-1")));
            craftEnchantedTrident.setIngredient('5', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.middle-row.slot-2")));
            craftEnchantedTrident.setIngredient('6', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.middle-row.slot-3")));

            craftEnchantedTrident.setIngredient('7', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.bottom-row.slot-1")));
            craftEnchantedTrident.setIngredient('8', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.bottom-row.slot-2")));
            craftEnchantedTrident.setIngredient('9', Material.getMaterial(configFile.getString("op-trident.custom-recipe.recipe.bottom-row.slot-3")));
        }else {
            craftEnchantedTrident.shape("DDD", "PHP", "NPB");
            craftEnchantedTrident.setIngredient('D', Material.DIAMOND);
            craftEnchantedTrident.setIngredient('P', Material.PRISMARINE_SHARD);
            craftEnchantedTrident.setIngredient('H', Material.HEART_OF_THE_SEA);
            craftEnchantedTrident.setIngredient('N', Material.NETHER_STAR);
            craftEnchantedTrident.setIngredient('B', Material.BOOK);
        }
        Bukkit.addRecipe(craftEnchantedTrident);
    }
}
