package org.gamblelife.gamble.Money;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeToMoney implements Listener {
    private final Economy econ;
    private final Map<String, ChipInfo> chipMap;

    public ExchangeToMoney(JavaPlugin plugin, Economy econ) {
        this.econ = econ;
        this.chipMap = new HashMap<>();

        // 각 칩의 정보를 초기화합니다. 여기서 칩 이름과 로어를 정의합니다.
        chipMap.put("1000", new ChipInfo("&7게임 칩", Material.SLIME_BALL, 1000, "&f천원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000", new ChipInfo("&2무지개 칩", Material.MAGMA_CREAM, 10000, "&f만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000", new ChipInfo("&6골드 칩", Material.HONEYCOMB, 100000, "&f십만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("1000000", new ChipInfo("&b다이아몬드 칩", Material.DIAMOND, 1000000, "&f백만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000000", new ChipInfo("&a에메랄드 칩", Material.EMERALD, 10000000, "&f천만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000000", new ChipInfo("&1자수정 칩", Material.AMETHYST_SHARD, 100000000, "&f일억원 상당의 게임칩", "&f환전소에서 교환 가능하다."));

        // 이벤트 리스너 등록
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 오른쪽 클릭 여부 확인
        switch (event.getAction()) {
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                break;
            default:
                return;
        }

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            Material material = item.getType();

            // 칩 정보를 가져와 일치하는지 검사합니다.
            for (ChipInfo chip : chipMap.values()) {
                if (chip.material == material && meta.hasDisplayName() && meta.hasLore()) {
                    // 색상 코드를 제거한 displayName을 가져옵니다.
                    String cleanDisplayName = ChatColor.stripColor(meta.getDisplayName());
                    List<String> lore = meta.getLore();

                    // 칩 이름과 로어가 일치하는지 확인 (색상 코드를 제거한 상태로 비교)
                    if (cleanDisplayName.equals(ChatColor.stripColor(chip.displayName)) && lore.equals(Arrays.asList(ChatColor.stripColor(chip.loreText1), ChatColor.stripColor(chip.loreText2)))) {
                        double value = chip.value * item.getAmount();

                        // 아이템을 제거하고, 플레이어의 계좌에 돈을 입금합니다.
                        item.setAmount(0); // 아이템을 제거
                        econ.depositPlayer(player, value);
                        player.sendMessage(ChatColor.GREEN + "성공적으로 " + value + "원이 입금되었습니다!");
                        return;
                    }
                }
            }
        }
    }

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