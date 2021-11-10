package dev.metanoia.acllib.attributes;


public interface IAttributeMgr {
    String getString(String attributeName);
    boolean hasPermission(String permissionName);
}
