package dev.metanoia.acllib.conditions;

import dev.metanoia.acllib.attributes.IAttributeMgr;

public class FromWorldCond implements ICondition {
    private final static String ATTRIBUTE_NAME = "from.world.name";

    private final String worldName;

    public FromWorldCond(final String worldName) {
        this.worldName = worldName;
    }


    public boolean isMatch(final IAttributeMgr attributeMgr)
    {
        final String fromWorldName = attributeMgr.getString(ATTRIBUTE_NAME);
        return this.worldName.equals(fromWorldName);
    }

    public String toString() {
        return String.format("%s is '%s'", ATTRIBUTE_NAME, this.worldName);
    }
}
