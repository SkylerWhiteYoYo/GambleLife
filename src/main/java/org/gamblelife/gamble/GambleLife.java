package org.gamblelife.gamble;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.gamblelife.gamble.Money.Exchange;
import org.gamblelife.gamble.Money.ExchangeToMoney;
import org.gamblelife.gamble.Taxi.InventoryClickListener;
import org.gamblelife.gamble.Taxi.Taxicommander;
import org.gamblelife.gamble.item.GiveRegenerationWindCommand;
import org.gamblelife.gamble.item.RegenerationWind;

public final class GambleLife extends JavaPlugin {
    private static Economy econ = null;
    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new RegenerationWind(this), this);
        this.getCommand("giveRegenerationWind").setExecutor(new GiveRegenerationWindCommand());
        //택시
        Taxicommander taxicommander = new Taxicommander();
        // "/택시" 커맨드 등록
        this.getCommand("택시").setExecutor(taxicommander);
        // 이벤트 리스너 등록
        getServer().getPluginManager().registerEvents(new InventoryClickListener(taxicommander), this);
        // '환전' 명령어를 Exchange 클래스와 연결하여 등록합니다.
        this.getCommand("환전").setExecutor(new Exchange(econ));
        // ExchangeToMoney 인스턴스를 생성하고 이벤트 리스너로 등록합니다.
        ExchangeToMoney exchangeToMoney = new ExchangeToMoney(this, econ);
        getServer().getPluginManager().registerEvents(exchangeToMoney, this);

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
