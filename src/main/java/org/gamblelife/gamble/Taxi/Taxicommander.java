package org.gamblelife.gamble.Taxi;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
public class Taxicommander implements CommandExecutor {


    // GUI 인벤토리 생성
    private Inventory inv;

    public Taxicommander() {
        inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "택시 메뉴"); // 인벤토리 크기와 이름 설정
        initializeItems();
    }

    // 인벤토리에 아이템을 초기화하는 메서드
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.LEATHER_HORSE_ARMOR, ChatColor.LIGHT_PURPLE + "경마장"));
        // 추가적인 아이템을 여기에 설정할 수 있습니다.
        inv.addItem(createGuiItem(Material.DIAMOND, ChatColor.AQUA + "다이아몬드 구역"));
        inv.addItem(createGuiItem(Material.GOLD_INGOT, ChatColor.GOLD + "골드 구역"));
        inv.addItem(createGuiItem(Material.IRON_INGOT, ChatColor.GRAY + "아이언 구역"));
        inv.addItem(createGuiItem(Material.EMERALD, ChatColor.GREEN + "에메랄드 구역"));
        inv.addItem(createGuiItem(Material.LAPIS_LAZULI, ChatColor.BLUE + "라피스 구역"));
    }

    // GUI 아이템 생성
    protected ItemStack createGuiItem(final Material material, final String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    // 커맨드가 입력되었을 때 호출되는 메서드
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.isOp()) { // 플레이어가 OP가 아닐 경우
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        // OP인 경우 GUI를 열어줍니다.
        player.openInventory(inv);
        return true;
    }

    // 인벤토리 가져오기
    public Inventory getInventory() {
        return inv;
    }
}

