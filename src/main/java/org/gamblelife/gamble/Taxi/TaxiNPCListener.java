package org.gamblelife.gamble.Taxi;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TaxiNPCListener implements Listener {

    private Taxicommander taxicommander;

    public TaxiNPCListener(Taxicommander taxicommander) {
        this.taxicommander = taxicommander;
    }

    @EventHandler
    public void onNPCRightClick(NPCRightClickEvent event) {
        NPC npc = event.getNPC();
        if (npc.getName().equalsIgnoreCase("택시운전수")) { // 여기서 NPC의 ID를 확인합니다.
            Player player = event.getClicker();
            player.openInventory(taxicommander.getInventory()); // GUI를 엽니다.
        }
    }
}