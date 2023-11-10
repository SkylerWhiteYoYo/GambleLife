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
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 20000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 20000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "20,000이 차감되었습니다. 경마장으로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), 173, 64, -104, -180,0); // 목적지 설정
                        //yaw 90 서쪽 -90동쪽 ,-180 북, 0 : 남
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
        }
        else if (name.equals(ChatColor.AQUA + "나무꾼의 터")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 8000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 8000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 나무꾼의 터로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), 52, 64, 112,-180,0); // 목적지 설정
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
            // 다이아몬드 구역 이동 로직
        }
        else if (name.equals(ChatColor.GOLD + "직업 학교")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 7000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 7000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 직업 학교로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), 11, 64, 100,0,0); // 목적지 설정
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
            // 골드 구역 이동 로직
        }
        else if (name.equals(ChatColor.GRAY + "LSJ 팬클럽 빌딩(사냥터)")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 12000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 12000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "12,000원이 차감되었습니다. 극성 팬클럽 빌딩으로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), 48, 64, -100,0,0); // 목적지 설정
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
            // 골드 구역 이동 로직
        }
        else if (name.equals(ChatColor.GREEN + "경찰서")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 경찰서로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), -195, 64, -50); // 목적지 설정
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
            // 골드 구역 이동 로직
        }
        else if (name.equals(ChatColor.BLUE + "라피스 구역")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 라피스구역으로 이동합니다!");
                        Location destination = new Location(Bukkit.getWorld("city2"), 137, 65, -92); // 목적지 설정
                        p.teleport(destination); // 플레이어를 목적지로 텔레포트
                    } else {
                        p.sendMessage(ChatColor.RED + "거래 실패: " + r.errorMessage);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "경제 시스템에 문제가 발생했습니다.");
            }
            // 골드 구역 이동 로직
        }
        // 추가적인 아이템에 대한 처리를 계속해서 추가하면 됩니다.
    }
}