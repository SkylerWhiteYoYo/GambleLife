package org.gamblelife.gamble.Taxi;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.gamblelife.gamble.GambleLife;


public class InventoryClickListener implements Listener {

    private Taxicommander taxicommander;



    public InventoryClickListener(Taxicommander taxicommander) {
        this.taxicommander = taxicommander;
    }



    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != taxicommander.getInventory()) return;

        e.setCancelled(true); // 인벤토리에서 아이템을 드래그해서 움직이지 못하게 함

        final Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;

        // 아이템의 이름을 가져와서 기능을 수행
        final String name = e.getCurrentItem().getItemMeta().getDisplayName();
        //yaw 90 서쪽 -90동쪽 ,-180 북, 0남
        if (name.equals(ChatColor.LIGHT_PURPLE + "경마장")) {
            p.sendMessage(ChatColor.GREEN + "경마장으로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 173, 64, -104, -180, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.AQUA + "초급 사냥터")) {
            p.sendMessage(ChatColor.GREEN + "초급 사냥터로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 52, 64, 112, -180, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.AQUA + "중급 사냥터")) {
            p.sendMessage(ChatColor.GREEN + "중급 사냥터로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), -11, 64, -185, 90, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.AQUA + "고급 사냥터")) {
            p.sendMessage(ChatColor.GREEN + "고급 사냥터로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 155, 64, -19, -180, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.GOLD + "직업 학교")) {
            p.sendMessage(ChatColor.GREEN + "직업 학교로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 11, 64, 100, 0, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.GRAY + "LSJ 팬클럽 빌딩(사냥터)")) {
            p.sendMessage(ChatColor.GREEN + "LSJ 팬클럽 빌딩으로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 48, 64, -100, 0, 0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.GREEN + "경찰서")) {
            p.sendMessage(ChatColor.GREEN + "경찰서로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), -195, 64, -50); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.BLUE + "활쏘기 도박장")) {
            p.sendMessage(ChatColor.GREEN + "활쏘기 도박장으로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), 29, 64, -142,-180,0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
        else if (name.equals(ChatColor.BLUE + "홀짝 게임방")) {
            p.sendMessage(ChatColor.GREEN + "홀짝 게임방으로 이동합니다!");
            Location destination = new Location(Bukkit.getWorld("city2"), -96, 64, -90,-180,0); // 목적지 설정
            p.teleport(destination); // 플레이어를 목적지로 텔레포트
        }
// 추가적인 아이템에 대한 처리를 계속해서 추가하면 됩니다.
    }
}