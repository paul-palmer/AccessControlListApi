package dev.metanoia.acllib.conditions;

import dev.metanoia.acllib.attributes.IAttributeMgr;

public class ToWorldCond implements ICondition {
    private final static String ATTRIBUTE_NAME = "to.world.name";

    private final String worldName;

    public ToWorldCond(final String worldName) {
        this.worldName = worldName;
    }


    public boolean isMatch(final IAttributeMgr attributeMgr)
    {
        final String toWorldName = attributeMgr.getString(ATTRIBUTE_NAME);
        return this.worldName.equals(toWorldName);
    }

    public String toString() {
        return String.format("%s is '%s'", ATTRIBUTE_NAME, this.worldName);
    }
}
