package dev.metanoia.acllib;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public final class AccessControlLists extends JavaPlugin {

    public static RuleSet fromMapList(final List<Map<?, ?>> mapList, final Consumer<String> errorCallback) {
        return RuleSet.fromList(mapList, errorCallback);
    }

}
