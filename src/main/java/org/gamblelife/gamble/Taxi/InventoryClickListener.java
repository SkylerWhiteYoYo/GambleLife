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

        if (name.equals(ChatColor.LIGHT_PURPLE + "경마장")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 경마장으로 이동합니다!");
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
        }
        else if (name.equals(ChatColor.AQUA + "다이아몬드 구역")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 다이아몬드 구역 으로 이동합니다!");
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
            // 다이아몬드 구역 이동 로직
        }
        else if (name.equals(ChatColor.GOLD + "골드 구역")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 골드 구역으로 이동합니다!");
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
        else if (name.equals(ChatColor.GRAY + "아이언 구역")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 아이언구역 으로 이동합니다!");
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
        else if (name.equals(ChatColor.GREEN + "에메랄드 구역")) {
            Economy econ = GambleLife.getEconomy();
            if (econ != null) {
                if (econ.has(p, 10000)) { // 플레이어가 10,000을 가지고 있는지 확인
                    EconomyResponse r = econ.withdrawPlayer(p, 10000); // 10,000 차감
                    if(r.transactionSuccess()) {
                        p.sendMessage(ChatColor.GREEN + "10,000이 차감되었습니다. 에메랄드 구역으로 이동합니다!");
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