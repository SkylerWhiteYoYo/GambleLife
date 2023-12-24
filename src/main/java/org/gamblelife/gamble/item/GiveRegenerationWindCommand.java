package org.gamblelife.gamble.item;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveRegenerationWindCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 명령어를 실행한 사람이 플레이어인지, 그리고 OP 권한이 있는지 확인합니다.
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) { // OP 권한을 가진 플레이어만 이 명령어를 사용할 수 있습니다.
                // '재생의 바람' 아이템을 생성합니다.
                ItemStack regenerationWindItem = RegenerationWind.createRegenerationWindItem();
                // 플레이어의 인벤토리에 아이템을 추가합니다.
                player.getInventory().addItem(regenerationWindItem);
                player.sendMessage("재생의 바람 아이템을 받았습니다!");
            } else {
                player.sendMessage("이 명령어를 사용할 권한이 없습니다.");
            }
            return true;
        } else {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return false;
        }
    }
}