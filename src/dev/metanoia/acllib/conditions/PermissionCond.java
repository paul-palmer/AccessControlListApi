package dev.metanoia.acllib.conditions;

import dev.metanoia.acllib.attributes.IAttributeMgr;

public class PermissionCond implements ICondition {
    private final String permissionName;

    public PermissionCond(final String permissionName) {
        this.permissionName = permissionName;
    }


    public boolean isMatch(final IAttributeMgr attributeMgr)
    {
        return attributeMgr.hasPermission(this.permissionName);
    }

    public String toString() {
        return String.format("player has permission '%s'", this.permissionName);
    }

}
