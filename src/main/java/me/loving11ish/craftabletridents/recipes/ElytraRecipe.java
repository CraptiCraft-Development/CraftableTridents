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

public class ElytraRecipe {

    private static final FileConfiguration configFile = CraftableTridents.getPlugin().getConfig();

    public void elytraRecipe(){
        NamespacedKey key = new NamespacedKey(CraftableTridents.getPlugin(), "elytra");

        ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
        ItemMeta elytraMeta = elytra.getItemMeta();
        if (configFile.getBoolean("elytra.name.enabled")){
            elytraMeta.setDisplayName(ColorUtils.translateColorCodes(configFile.getString("elytra.name.name")));
        }
        if (configFile.getBoolean("elytra.lore.enabled")){
            ArrayList<String> elytraLore = new ArrayList<>();
            List<String> loreList = configFile.getStringList("elytra.lore.lore");
            for (String string : loreList){
                elytraLore.add(ColorUtils.translateColorCodes(string));
            }
            elytraMeta.setLore(elytraLore);
        }
        if (configFile.getBoolean("elytra.enchantments.enabled")){
            List<String> enchantsList = configFile.getStringList("elytra.enchantments.enchantments");
            for (String string : enchantsList){
                String[] enchantment = string.split(" ");
                elytraMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchantment[0])), Integer.parseInt(enchantment[1]), true);
            }
        }
        elytra.setItemMeta(elytraMeta);

        ShapedRecipe craftElytra = new ShapedRecipe(key, elytra);
        if (configFile.getBoolean("elytra.custom-recipe.enabled")){
            craftElytra.shape("123", "456", "789");

            craftElytra.setIngredient('1', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.top-row.slot-1")));
            craftElytra.setIngredient('2', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.top-row.slot-2")));
            craftElytra.setIngredient('3', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.top-row.slot-3")));

            craftElytra.setIngredient('4', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.middle-row.slot-1")));
            craftElytra.setIngredient('5', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.middle-row.slot-2")));
            craftElytra.setIngredient('6', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.middle-row.slot-3")));

            craftElytra.setIngredient('7', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.bottom-row.slot-1")));
            craftElytra.setIngredient('8', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.bottom-row.slot-2")));
            craftElytra.setIngredient('9', Material.getMaterial(configFile.getString("elytra.custom-recipe.recipe.bottom-row.slot-3")));
        }else {
            craftElytra.shape("PLP", "LSL", "LNL");
            craftElytra.setIngredient('P', Material.PHANTOM_MEMBRANE);
            craftElytra.setIngredient('L', Material.LEATHER);
            craftElytra.setIngredient('S', Material.STRING);
            craftElytra.setIngredient('N', Material.NETHER_STAR);
        }
        Bukkit.addRecipe(craftElytra);
    }
}
