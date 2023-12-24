package org.gamblelife.gamble.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.gamblelife.gamble.GambleLife;
import org.bukkit.Particle;

import java.util.ArrayList;
import java.util.List;

public class RegenerationWind implements Listener {
    private JavaPlugin plugin;
    private NamespacedKey key;

    public RegenerationWind(JavaPlugin plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "regeneration_wind");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.GREEN_DYE && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                // 이 부분을 수정하여 특정 아이템에 대한 쿨다운만 확인합니다.
                NamespacedKey cooldownKey = new NamespacedKey(plugin, "regeneration_wind_cooldown");
                long lastUsed = meta.getPersistentDataContainer().getOrDefault(cooldownKey, PersistentDataType.LONG, 0L);
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastUsed > 60000) { // 1분 쿨다운
                    // 재생 효과 부여
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2)); // 10초, 레벨 3

                    // 사용자 피드백 개선: 사운드와 메시지
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
                    player.sendMessage("§a재생의 바람을 사용하여 재생 효과를 얻었습니다!");

                    // 여기에 파티클 효과 추가
                    player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, 0);


                    // 재사용 대기시간 설정 (1분)
                    meta.getPersistentDataContainer().set(cooldownKey, PersistentDataType.LONG, currentTime);
                    item.setItemMeta(meta);

                } else {
                    long timeLeft = (60000 - (currentTime - lastUsed)) / 1000;
                    player.sendMessage("§c아직 재사용 대기시간이 남았습니다! 남은 시간: " + timeLeft + "초");
                }
            }
        }
    }

    // '재생의 바람' 아이템 생성 메소드
    public static ItemStack createRegenerationWindItem() {
        ItemStack item = new ItemStack(Material.GREEN_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("재생의 바람");
            List<String> lore = new ArrayList<>();
            lore.add("§7이 신비로운 씨앗은");
            lore.add("§7사용자에게 자연의 치유력을 부여합니다.");
            lore.add("");
            lore.add("§6효과:");
            lore.add("§b재생 III (10초 동안)");
            lore.add("");
            lore.add("§e재사용 대기시간: §c1분");
            lore.add("");
            lore.add("§7왕나무를 처치하고 얻은");
            lore.add("§7강력한 자연의 선물입니다.");
            meta.setLore(lore);
            // NBT 태그 설정
            meta.getPersistentDataContainer().set(new NamespacedKey(GambleLife.getPlugin(GambleLife.class), "regeneration_wind"), PersistentDataType.INTEGER, 1);
            item.setItemMeta(meta);
        }
        return item;
    }
}