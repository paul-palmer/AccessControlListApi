package dev.metanoia.acllib;

import dev.metanoia.acllib.attributes.IAttributeMgr;
import dev.metanoia.acllib.conditions.FromWorldCond;
import dev.metanoia.acllib.conditions.ICondition;
import dev.metanoia.acllib.conditions.PermissionCond;
import dev.metanoia.acllib.conditions.ToWorldCond;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class Rule {

    private final List<ICondition> conditions = new ArrayList<>();
    private final String action;

    public Rule(String action) {
        this.action = action;
    }


    // the rule matches if all of its conditions are true
    public boolean isMatch(final IAttributeMgr attributeMgr) {
        for (ICondition condition : this.conditions) {
            if (!condition.isMatch(attributeMgr)) {
                return false;
            }
        }

        return true;
    }

    public void addCondition(final ICondition condition)    { this.conditions.add(condition); }
    public String getAction()                               { return this.action; }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (ICondition condition : this.conditions) {
            str.append(condition);
            str.append(",");
        }

        return str.toString();
    }

    public static Rule fromHash(Map<String,String> hash, Consumer<String> errorCallback) {
        String action = hash.getOrDefault("action", "allow");
        Rule rule = new Rule(action);

        for (Map.Entry<String,String> entry: hash.entrySet()) {
            final String condType  = entry.getKey();
            final String condValue = entry.getValue();

            if (condType.equals("action")) {
                continue;
            } else if (condType.equals("permission")) {
                rule.addCondition(new PermissionCond(condValue));
            } else if (condType.equals("from.world.name")) {
                rule.addCondition(new FromWorldCond(condValue));
            } else if (condType.equals("to.world.name")) {
                rule.addCondition(new ToWorldCond(condValue));
            } else {
                errorCallback.accept(String.format("Rule condition, '%s', not recognized.", condType));
            }
        }

        return rule;
    }
}
