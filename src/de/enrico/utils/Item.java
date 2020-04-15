package de.enrico.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {


    private ItemStack current;


    public Item(ItemStack i) {
        this.current = i;
    }

    /**
     * is a method that will create a Item with a Material and the subID
     *
     * @param m
     * @param amount
     * @param ID
     */
    @SuppressWarnings("deprecation")
    public Item(Material m, int amount, short ID) {
        this(new ItemStack(m, amount, ID));
    }

    /**
     * is a method that will create a Item with a Material
     *
     * @param m
     * @param amount
     */
    @SuppressWarnings("deprecation")
    public Item(Material m, int amount) {
        this(new ItemStack(m, amount));
    }

    /**
     * is a method that will create a Item with the numeric ID of the Item Block and the SubID
     * for Color Like Colored Glass you will need a SubID
     *
     * @param ID
     * @param amount
     * @param doubleID
     */
    @SuppressWarnings("deprecation")
    public Item(int ID, int amount, short doubleID) {
        this(new ItemStack(ID, amount, doubleID));
    }

    /**
     * is a method that will create a Item with the numeric ID of the Item BLock
     * this method is used when you need simple blocks with no SubID
     * like Normal Glass Block
     *
     * @param ID
     * @param amount
     */
    @SuppressWarnings("deprecation")
    public Item(int ID, int amount) {
        this(new ItemStack(ID, amount));
    }

    /**
     * is a method to set a Name that will be Displayed
     *
     * @param displayname
     * @return
     */

    public Item setDisplayname(String displayname) {
        ItemMeta m = current.getItemMeta();
        m.setDisplayName(displayname);
        current.setItemMeta(m);
        return this;
    }


    /**
     * is a method that sets a Description to the item
     * Use Arrays.asList("your description") to set the description
     * why because you need a List to be as Lore
     *
     * @param lore
     * @return
     */
    public Item setLore(List<String> lore) {
        ItemMeta m = current.getItemMeta();
        m.setLore(lore);
        current.setItemMeta(m);
        return this;
    }

    /**
     * is a method to add Enchantment to things that are Able to be Enchanted like Shoes
     *
     * @param ench
     * @param level
     * @param overMax
     * @return
     */
    public Item addEnchantment(Enchantment ench, int level, boolean overMax) {
        ItemMeta m = current.getItemMeta();
        m.addEnchant(ench, level, overMax);
        current.setItemMeta(m);
        return this;
    }

    /**
     * is a method to addEnchantment to other stuff like Steak and things that Normal aren't able to be Enchanted
     *
     * @param ench
     * @param level
     * @return
     */
    public Item addUnsafeEnchantment(Enchantment ench, int level) {
        current.addUnsafeEnchantment(ench, level);
        return this;
    }


    /**
     * is a method to set if the Generated Item is Unbreakable
     *
     * @param isUnbreakable
     * @return
     */
    public Item setUnbreakable(boolean isUnbreakable) {
        ItemMeta m = current.getItemMeta();
        m.spigot().setUnbreakable(isUnbreakable);
        return this;
    }

    /**
     * is a method to set the Durability of the Generated Item
     *
     * @param durability
     * @return
     */
    public Item setDurability(short durability) {
        current.setDurability(durability);
        return this;
    }

    /**
     * is a method that needs to be executed to Build the Current Item
     *
     * @return
     */
    public ItemStack build() {
        return this.current;
    }

}
