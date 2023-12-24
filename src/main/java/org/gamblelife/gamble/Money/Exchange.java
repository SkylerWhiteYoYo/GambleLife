package org.gamblelife.gamble.Money;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exchange implements CommandExecutor {
    private final Economy econ;
    private final Map<String, ChipInfo> chipMap;

    public Exchange(Economy econ) {
        this.econ = econ;
        this.chipMap = new HashMap<>();

        // 각 칩의 정보를 초기화합니다.
        chipMap.put("1000", new ChipInfo("&7게임 칩", Material.SLIME_BALL, 1000, "&f천원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000", new ChipInfo("&2무지개 칩", Material.MAGMA_CREAM, 10000, "&f만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000", new ChipInfo("&6골드 칩", Material.HONEYCOMB, 100000, "&f10만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("1000000", new ChipInfo("&b다이아몬드 칩", Material.DIAMOND, 1000000, "&f100만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("10000000", new ChipInfo("&a에메랄드 칩", Material.EMERALD, 10000000, "&f1000만원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
        chipMap.put("100000000", new ChipInfo("&1자수정 칩", Material.AMETHYST_SHARD, 100000000, "&f1억원 상당의 게임칩", "&f환전소에서 교환 가능하다."));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 명령어 실행자가 플레이어인지 확인
        if (!(sender instanceof Player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length < 1 || !chipMap.containsKey(args[0])) {
            player.sendMessage("사용법: /환전 <원하는 액면가> <갯수 (기본값: 1)>");
            return true;
        }

        // 환전하려는 칩의 정보를 가져옵니다.
        String chipType = args[0];
        ChipInfo chipInfo = chipMap.get(chipType);
        int amount = args.length > 1 && isNumeric(args[1]) ? Integer.parseInt(args[1]) : 1; // 갯수가 지정되지 않거나 숫자가 아닌 경우 1로 설정

        // 플레이어의 돈을 확인하고 칩을 지급합니다.
        exchangeChips(player, chipInfo, amount);
        return true;
    }

    private void exchangeChips(Player player, ChipInfo chipInfo, int amount) {
        double totalCost = chipInfo.value * amount;

        // 플레이어의 잔액을 확인합니다.
        if (econ.getBalance(player) < totalCost) {
            player.sendMessage(ChatColor.RED + "돈이 부족합니다. 필요 금액: " + totalCost);
            return;
        }

        // 돈을 차감하고 칩을 지급합니다.
        econ.withdrawPlayer(player, totalCost);
        ItemStack chip = createChipItem(chipInfo, amount);
        player.getInventory().addItem(chip);

        // displayName에서 "&" 뒤에 오는 한 문자와 함께 "&"를 제거합니다.
        String cleanDisplayName = chipInfo.displayName.replaceAll("&[0-9a-fA-Fk-oK-OrR]", "");

        // 정제된 메시지를 플레이어에게 보냅니다.
        player.sendMessage(amount + "개의 " + cleanDisplayName + "을(를) 받았습니다.");
    }

    private ItemStack createChipItem(ChipInfo chipInfo, int amount) {
        ItemStack chip = new ItemStack(chipInfo.material, amount);
        ItemMeta meta = chip.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', chipInfo.displayName));
            List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', chipInfo.loreText1), ChatColor.translateAlternateColorCodes('&', chipInfo.loreText2));
            meta.setLore(lore);
            chip.setItemMeta(meta);
        }
        return chip;
    }

    // 문자열이 숫자인지 확인하는 메서드
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static class ChipInfo {
        String displayName;
        Material material;
        double value;
        String loreText1;
        String loreText2;

        ChipInfo(String displayName, Material material, double value, String loreText1, String loreText2) {
            this.displayName = displayName;
            this.material = material;
            this.value = value;
            this.loreText1 = loreText1;
            this.loreText2 = loreText2;
        }
    }
}