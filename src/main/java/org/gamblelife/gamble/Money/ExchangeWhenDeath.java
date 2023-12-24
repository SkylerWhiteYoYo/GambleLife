package org.gamblelife.gamble.Money;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ExchangeWhenDeath implements Listener {
    private final Economy econ;
    private final Map<String, ChipInfo> chipMap;
    private final Map<UUID, Long> lastDeathTimes;

    public ExchangeWhenDeath(JavaPlugin plugin, Economy econ) {
        this.econ = econ;
        this.chipMap = new HashMap<>();
        this.lastDeathTimes = new HashMap<>();  // 초기화

        chipMap.put("1000", new ChipInfo("&7게임 칩", Material.SLIME_BALL, 1000, "&f천원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000", new ChipInfo("&2무지개 칩", Material.MAGMA_CREAM, 10000, "&f만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000", new ChipInfo("&6골드 칩", Material.HONEYCOMB, 100000, "&f10만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("1000000", new ChipInfo("&b다이아몬드 칩", Material.DIAMOND, 1000000, "&f100만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000000", new ChipInfo("&a에메랄드 칩", Material.EMERALD, 10000000, "&f1000만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000000", new ChipInfo("&1자수정 칩", Material.AMETHYST_SHARD, 100000000, "&f1억원 상당의 게임칩", "&f환전소에서 교환 가능하다."));

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID playerId = player.getUniqueId();
        long now = System.currentTimeMillis();

        // 이전 사망 이벤트로부터 5초 이내에 중복된 이벤트는 무시
        if (lastDeathTimes.containsKey(playerId) && (now - lastDeathTimes.get(playerId) < 5000)) {
            return;
        }
        lastDeathTimes.put(playerId, now);  // 마지막 사망 시간 업데이트
        double totalValue = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName() && meta.hasLore()) {
                    String cleanDisplayName = meta.getDisplayName();  // 색상 코드를 제거하지 않음
                    List<String> cleanLore = meta.getLore();  // 여기에서도 색상 코드를 제거하지 않음

                    for (ChipInfo chip : chipMap.values()) {
                        if (item.getType() == chip.material && cleanDisplayName.equals(chip.displayName)) {
                            // Check if all lore lines match with color codes
                            if (cleanLore.equals(Arrays.asList(chip.loreText1, chip.loreText2))) {
                                double value = chip.value * item.getAmount();
                                totalValue += value;
                                item.setAmount(0); // Remove the chips from inventory
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 칩 환전 및 메시지 전송
        if (totalValue > 0) {
            econ.depositPlayer(player, totalValue);
            player.sendMessage(ChatColor.GREEN + "당신의 칩이 현금 " + totalValue + "원으로 교환됐습니다.");
        }

        // 10만원 또는 잔액 전부 차감
        double initialBalance = econ.getBalance(player);  // 초기 잔액
        double deduction = Math.min(100000, initialBalance);
        econ.withdrawPlayer(player, deduction);

        // 메시지 전송
        if (deduction == 100000) {
            player.sendMessage(ChatColor.RED + "당신은 치명적인 부상을 입었기 때문에 응급실에서 10만원을 빼갔습니다.");
        } else {
            player.sendMessage(ChatColor.RED + "충분한 돈이 없어서 응급실에서 " + deduction + "원을 가져갔습니다.");
        }
    }

    // The ChipInfo class here should be the same as in the Exchange class
    // 칩 정보를 저장하는 내부 클래스
    private static class ChipInfo {
        String displayName;
        Material material;
        double value;
        String loreText1;
        String loreText2;

        ChipInfo(String displayName, Material material, double value, String loreText1, String loreText2) {
            this.displayName = ChatColor.translateAlternateColorCodes('&', displayName);
            this.material = material;
            this.value = value;
            this.loreText1 = ChatColor.translateAlternateColorCodes('&', loreText1);
            this.loreText2 = ChatColor.translateAlternateColorCodes('&', loreText2);
        }
    }
}