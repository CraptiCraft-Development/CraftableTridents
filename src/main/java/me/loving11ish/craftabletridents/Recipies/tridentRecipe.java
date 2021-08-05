package me.loving11ish.craftabletridents.Recipies;

import me.loving11ish.craftabletridents.craftableTridents;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class tridentRecipe {

    public void Recipe(){
        NamespacedKey key = new NamespacedKey(craftableTridents.getPlugin(), "trident");
        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
        ShapedRecipe craftTrident = new ShapedRecipe(key, trident);
        craftTrident.shape("DDD", "PHP", " P ");
        craftTrident.setIngredient('D', Material.DIAMOND);
        craftTrident.setIngredient('P', Material.PRISMARINE_SHARD);
        craftTrident.setIngredient('H', Material.HEART_OF_THE_SEA);
        Bukkit.addRecipe(craftTrident);
    }

}
