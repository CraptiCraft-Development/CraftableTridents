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

public class GodAppleRecipe {

    FileConfiguration configFile = CraftableTridents.getPlugin().getConfig();

    public void godAppleRecipe(){
        NamespacedKey key = new NamespacedKey(CraftableTridents.getPlugin(), "enchantedGoldenApple");

        ItemStack enchantedGoldenApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemMeta enchantedGoldenAppleMeta = enchantedGoldenApple.getItemMeta();
        if (configFile.getBoolean("god-apple.name.enabled")){
            enchantedGoldenAppleMeta.setDisplayName(ColorUtils.translateColorCodes(configFile.getString("god-apple.name.name")));
        }
        if (configFile.getBoolean("god-apple.lore.enabled")){
            ArrayList<String> elytraLore = new ArrayList<>();
            List<String> loreList = configFile.getStringList("god-apple.lore.lore");
            for (String string : loreList){
                elytraLore.add(ColorUtils.translateColorCodes(string));
            }
            enchantedGoldenAppleMeta.setLore(elytraLore);
        }
        if (configFile.getBoolean("god-apple.enchantments.enabled")){
            List<String> enchantsList = configFile.getStringList("god-apple.enchantments.enchantments");
            for (String string : enchantsList){
                String[] enchantment = string.split(" ");
                enchantedGoldenAppleMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchantment[0])), Integer.parseInt(enchantment[1]), true);
            }
        }
        enchantedGoldenApple.setItemMeta(enchantedGoldenAppleMeta);

        CraftableTridents.getPlugin().setEnchantedGoldenApple(enchantedGoldenApple);

        ShapedRecipe craftEnchantedGoldenApple = new ShapedRecipe(key, enchantedGoldenApple);
        if (configFile.getBoolean("god-apple.custom-recipe.enabled")){
            craftEnchantedGoldenApple.shape("123", "456", "789");

            craftEnchantedGoldenApple.setIngredient('1', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.top-row.slot-1")));
            craftEnchantedGoldenApple.setIngredient('2', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.top-row.slot-2")));
            craftEnchantedGoldenApple.setIngredient('3', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.top-row.slot-3")));

            craftEnchantedGoldenApple.setIngredient('4', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.middle-row.slot-1")));
            craftEnchantedGoldenApple.setIngredient('5', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.middle-row.slot-2")));
            craftEnchantedGoldenApple.setIngredient('6', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.middle-row.slot-3")));

            craftEnchantedGoldenApple.setIngredient('7', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.bottom-row.slot-1")));
            craftEnchantedGoldenApple.setIngredient('8', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.bottom-row.slot-2")));
            craftEnchantedGoldenApple.setIngredient('9', Material.getMaterial(configFile.getString("god-apple.custom-recipe.recipe.bottom-row.slot-3")));
        }else {
            craftEnchantedGoldenApple.shape("GGG", "GAG", "GGG");
            craftEnchantedGoldenApple.setIngredient('G', Material.GOLD_BLOCK);
            craftEnchantedGoldenApple.setIngredient('A', Material.APPLE);
        }
        Bukkit.addRecipe(craftEnchantedGoldenApple);
    }
}
