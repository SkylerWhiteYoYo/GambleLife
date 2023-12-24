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
        inv.addItem(createGuiItem(Material.STONE_AXE, ChatColor.AQUA + "초급 사냥터"));
        inv.addItem(createGuiItem(Material.STONE_AXE, ChatColor.AQUA + "중급 사냥터"));
        inv.addItem(createGuiItem(Material.STONE_AXE, ChatColor.AQUA + "고급 사냥터"));
        inv.addItem(createGuiItem(Material.WRITABLE_BOOK, ChatColor.GOLD + "직업 학교"));
        inv.addItem(createGuiItem(Material.IRON_SWORD, ChatColor.GRAY + "LSJ 팬클럽 빌딩(사냥터)"));
        inv.addItem(createGuiItem(Material.SPAWNER, ChatColor.GREEN + "경찰서"));
        inv.addItem(createGuiItem(Material.BOW, ChatColor.BLUE + "활쏘기 도박장"));
        inv.addItem(createGuiItem(Material.REDSTONE_WALL_TORCH, ChatColor.BLUE + "홀짝 게임방"));
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

        // 명령어를 실행하는 사람이 OP인지 확인합니다.
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다.");
            return true;
        }
        // 인자로 플레이어 이름이 제공되었는지 확인합니다.
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /택시 <playername>");
            return true;
        }

        // 지정된 플레이어 이름으로 플레이어 객체를 가져옵니다.
        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            // 해당 플레이어가 온라인이 아니면 오류 메시지를 보냅니다.
            sender.sendMessage(ChatColor.RED + "Player not found or not online.");
            return true;
        }

        // 찾은 플레이어에게 택시 GUI 인벤토리를 엽니다.
        targetPlayer.openInventory(inv);
        return true;
    }

    // 인벤토리 가져오기
    public Inventory getInventory() {
        return inv;
    }
}

