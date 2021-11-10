package dev.metanoia.acllib.conditions;


import dev.metanoia.acllib.attributes.IAttributeMgr;

public interface ICondition {
    boolean isMatch(IAttributeMgr attributeMgr);
    String toString();
}
