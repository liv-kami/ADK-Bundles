package xyz.livdoesdevstuf.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MainClass extends JavaPlugin implements Listener {

    public static Plugin plugin;
    public static Plugin getPlugin() {return plugin;}
    @Override
    public void onEnable() {
        plugin = this;
        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }
        getLogger().info("ADK-Bundles >>> Plugin Enabled");
        plugin.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        reloadConfig();
    }

    @EventHandler
    public void onCraftPreparation(PrepareItemCraftEvent e) {
        //check recipe "matches" the shape of the recipe in config
        ItemStack[] craftMatrix = e.getInventory().getMatrix();

        //alright now we want to go through the recipe to make sure the materials are the same in all the matrix slots

        //this gets the details of the recipe from config



        String recipeRow1String = getConfig().getStringList("recipe").get(0);
        String recipeRow2String = getConfig().getStringList("recipe").get(1);
        String recipeRow3String = getConfig().getStringList("recipe").get(2);
        String[] recipeRow1 = recipeRow1String.split(":", 3);
        String[] recipeRow2 = recipeRow2String.split(":", 3);
        String[] recipeRow3 = recipeRow3String.split(":", 3);
        String slotOne = recipeRow1[0];
        String slotTwo = recipeRow1[1];
        String slotThree = recipeRow1[2];
        String slotFour = recipeRow2[0];
        String slotFive = recipeRow2[1];
        String slotSix = recipeRow2[2];
        String slotSeven = recipeRow3[0];
        String slotEight = recipeRow3[1];
        String slotNine = recipeRow3[2];
        List<String> recipeInfo = new ArrayList<>();
        recipeInfo.add(slotOne);
        recipeInfo.add(slotTwo);
        recipeInfo.add(slotThree);
        recipeInfo.add(slotFour);
        recipeInfo.add(slotFive);
        recipeInfo.add(slotSix);
        recipeInfo.add(slotSeven);
        recipeInfo.add(slotEight);
        recipeInfo.add(slotNine);

        Material bundleMaterial = null;
        List<Integer> bundleSlots = new ArrayList<>();

        //This goes through each craft matrix and checks that they are equal to the recipe given?? needs testing
        int num = 0;
        boolean otherMega = true;
        for(String s : recipeInfo) {
            if(craftMatrix[num] == null) {
                return;
            }
            if(!s.equalsIgnoreCase("BUNDLE_ITEM")) {
                if(craftMatrix[num].getType() != Material.valueOf(s)){
                    return;
                }
                if(!craftMatrix[num].getItemMeta().hasLore()){
                    otherMega = false;
                }
            } else {
                if(bundleMaterial == null) {
                    bundleMaterial = craftMatrix[num].getType();
                }
                bundleSlots.add(num);
            }
            num += 1;
        }

        //check that all bundleMats are the same material, then check they are all full stack
        boolean mega = false;
        int checker = 0;
        for(int i : bundleSlots) {
            if(craftMatrix[i].getType() == Material.AIR) {
                return;
            }
            if(craftMatrix[i].getType() != bundleMaterial) {
                return;
            }
            if(craftMatrix[i].getAmount() != craftMatrix[i].getMaxStackSize()) {
                return;
            }
            if(craftMatrix[i].getItemMeta().hasLore()) {
                checker += 1;
            }
        }
        //world, etc.
        if(!getConfig().getStringList("world-whitelist").contains(e.getViewers().get(0).getWorld().getName())) {
            return;
        }
        if(checker == bundleSlots.size()) {
            mega = true;
        }
        // have to first, figure out if it is a bundle or mega bundle.
        if(bundleMaterial == null) {
            return;
        }
        if(mega && otherMega) {
            e.getInventory().setResult(getBundle(bundleMaterial, "mega"));
        }
        else if(mega && !otherMega) {
            return;
        }else if(!mega && otherMega) {
            return;
        }else {
            e.getInventory().setResult(getBundle(bundleMaterial, "bundle"));
        }

        //show result as bundle
    }

    @EventHandler
    public void onCraftComplete(InventoryClickEvent e) {
        if(!getConfig().getStringList("world-whitelist").contains(e.getViewers().get(0).getWorld().getName())) {
            return;
        }
        if(e.getInventory().getType() != InventoryType.WORKBENCH) {
            return;
        }
        if(e.getSlot() != 0) {
            return;
        }
        CraftingInventory inv = (CraftingInventory) e.getInventory();
        ItemStack[] craftMatrix = inv.getMatrix();

        //alright now we want to go through the recipe to make sure the materials are the same in all the matrix slots

        //this gets the details of the recipe from config



        String recipeRow1String = getConfig().getStringList("recipe").get(0);
        String recipeRow2String = getConfig().getStringList("recipe").get(1);
        String recipeRow3String = getConfig().getStringList("recipe").get(2);
        String[] recipeRow1 = recipeRow1String.split(":", 3);
        String[] recipeRow2 = recipeRow2String.split(":", 3);
        String[] recipeRow3 = recipeRow3String.split(":", 3);
        String slotOne = recipeRow1[0];
        String slotTwo = recipeRow1[1];
        String slotThree = recipeRow1[2];
        String slotFour = recipeRow2[0];
        String slotFive = recipeRow2[1];
        String slotSix = recipeRow2[2];
        String slotSeven = recipeRow3[0];
        String slotEight = recipeRow3[1];
        String slotNine = recipeRow3[2];
        List<String> recipeInfo = new ArrayList<>();
        recipeInfo.add(slotOne);
        recipeInfo.add(slotTwo);
        recipeInfo.add(slotThree);
        recipeInfo.add(slotFour);
        recipeInfo.add(slotFive);
        recipeInfo.add(slotSix);
        recipeInfo.add(slotSeven);
        recipeInfo.add(slotEight);
        recipeInfo.add(slotNine);

        Material bundleMaterial = null;
        List<Integer> bundleSlots = new ArrayList<>();

        //This goes through each craft matrix and checks that they are equal to the recipe given?? needs testing
        int num = 0;
        boolean otherMega = true;
        for(String s : recipeInfo) {
            if(craftMatrix[num] == null) {
                return;
            }
            if(!s.equalsIgnoreCase("BUNDLE_ITEM")) {
                if(craftMatrix[num].getType() != Material.valueOf(s)){
                    return;
                }
                if(craftMatrix[num].getAmount() > 1) {
                    ItemStack item = craftMatrix[num];
                    int amount = item.getAmount();
                    Player player = (Player) e.getViewers().get(0);
                    ItemStack leftOver = new ItemStack(Material.STRING);
                    leftOver.setAmount(item.getAmount()-1);
                    player.getInventory().addItem(leftOver);
                }
                if(craftMatrix[num].getItemMeta().hasLore()){
                    otherMega = true;
                } else {
                    otherMega = false;
                }
            } else {
                if(bundleMaterial == null) {
                    bundleMaterial = craftMatrix[num].getType();
                }
                bundleSlots.add(num);
            }
            num += 1;
        }

        //check that all bundleMats are the same material, then check they are all full stack
        boolean mega = false;
        int checker = 0;
        for(int i : bundleSlots) {
            if(craftMatrix[i].getType() == Material.AIR) {
                return;
            }
            if(craftMatrix[i].getType() != bundleMaterial) {
                return;
            }
            if(craftMatrix[i].getAmount() != craftMatrix[i].getMaxStackSize()) {
                return;
            }
            if(craftMatrix[i].getItemMeta().hasLore()) {
                checker += 1;
            }
        }
        //world, etc.
        if(checker == bundleSlots.size()) {
            mega = true;
        }
        // have to first, figure out if it is a bundle or mega bundle.
        if(bundleMaterial == null) {
            return;
        }
        if(mega && otherMega) {
            Player player = (Player) e.getWhoClicked();
            player.getInventory().addItem(getBundle(bundleMaterial, "mega"));
            e.getInventory().clear();
        }else if(mega && !otherMega) {
            return;
        }else if(!mega && otherMega){
            return;
        }else {
            Player player = (Player) e.getWhoClicked();
            player.getInventory().addItem(getBundle(bundleMaterial, "bundle"));
            e.getInventory().clear();
        }
        //check recipe "matches" the shape of the recipe in config
        //check full stack, world, etc.
        //give item to player and remove the items in the crafting table.
    }

    @Override
    public void onDisable(){
        getLogger().info("ADK-Bundles >>> Plugin Disabled");
    }

    public ItemStack getBundle(Material mat, String which) {
        if(mat == Material.AIR) {
            return null;
        }

        //gets custom model data values from the config
        int cmodelNum = 0;
        for(String bundleType : plugin.getConfig().getStringList("bundle-model-numbers")) {
            Material bundleMat = Material.valueOf(bundleType.split("-")[0]);
            int modelNum = Integer.parseInt(bundleType.split("-")[1]);
            int megaNum = Integer.parseInt(bundleType.split("-")[2]);
            if(mat == bundleMat) {
                if(which.equalsIgnoreCase("bundle")) {
                    cmodelNum = modelNum;
                } else if(which.equalsIgnoreCase("mega")) {
                    cmodelNum = megaNum;
                }

            }
        }

        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();

        //this next method names and lores the bundles based on what type they are
        if(which.equalsIgnoreCase("bundle")) {
            for(String s : getConfig().getStringList("bundle-lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            for(String str : getConfig().getStringList("bundle-enchantments")) {
                Enchantment ench = Enchantment.getByName(str.split(":")[0]);
                int level = Integer.parseInt(str.split(":")[1]);
                meta.addEnchant(ench, level, true);
            }
            if(getConfig().getBoolean("bundle-hide-enchantments")) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("bundle-name").replaceAll("%item%", toTitleCase(mat.toString()))));
        } else if(which.equalsIgnoreCase("mega")) {
            for(String s : getConfig().getStringList("mega-bundle-lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            for(String str : getConfig().getStringList("mega-bundle-enchantments")) {
                Enchantment ench = Enchantment.getByName(str.split(":")[0]);
                int level = Integer.parseInt(str.split(":")[1]);
                meta.addEnchant(ench, level, true);
            }
            if(getConfig().getBoolean("mega-bundle-hide-enchantments")) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("mega-bundle-name").replaceAll("%item%", toTitleCase(mat.toString()))));
        }

        if(cmodelNum != 0) {
            meta.setCustomModelData(cmodelNum);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    private static String toTitleCase(String st) {

        String str = st.replaceAll("_", " ");

        if(str == null || str.isEmpty())
            return "";

        if(str.length() == 1)
            return str.toUpperCase();

        //split the string by space
        String[] parts = str.split(" ");

        StringBuilder sb = new StringBuilder( str.length() );

        for(String part : parts){

            if(part.length() > 1 )
                sb.append( part.substring(0, 1).toUpperCase() )
                        .append( part.substring(1).toLowerCase() );
            else
                sb.append(part.toUpperCase());

            sb.append(" ");
        }

        return sb.toString().trim();
    }

}
