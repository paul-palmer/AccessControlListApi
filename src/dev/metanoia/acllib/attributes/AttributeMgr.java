package dev.metanoia.acllib.attributes;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.function.Supplier;

public class AttributeMgr implements IAttributeMgr {

    private final Map<String, Supplier<Object>> attributes;

    public AttributeMgr(final Map<String, Supplier<Object>> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getString(final String attributeName) {
        if (attributeName.equals("from.world.name")) {
            return getFrom().getWorld().getName();
        } else if (attributeName.equals("to.world.name")) {
            return getTo().getWorld().getName();
        }

        return null;
    }

    @Override
    public boolean hasPermission(final String permissionName) {
        final Player player = getPlayer();
        return player != null && player.hasPermission(permissionName);
    }



    ///
    /// private
    ///

    private Player getPlayer() {
        return (Player) this.attributes.get("player").get();
    }

    private Location getFrom() {
        return (Location) this.attributes.get("from").get();
    }

    private Location getTo() {
        return (Location) this.attributes.get("to").get();
    }

}
