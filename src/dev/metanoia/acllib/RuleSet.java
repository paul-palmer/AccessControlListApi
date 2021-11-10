package dev.metanoia.acllib;

// A RuleSet is an ordered collection of rules. It processes the rules one at a time
// until an action is determined

import dev.metanoia.acllib.attributes.IAttributeMgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;



public class RuleSet {

    private final List<Rule> rules = new ArrayList<>();

    public RuleSet() { }

    public void add(Rule rule) { rules.add(rule); }

    public boolean isAllowed(final IAttributeMgr attributeMgr) {
        if (rules.isEmpty()) {
            return true;
        }

        for (final Rule rule : rules) {
            if (!rule.isMatch(attributeMgr)) {
                continue;
            }

            String action = rule.getAction();

            if (action.equals("allow")) {
                return true;
            } else if (action.equals("deny")) {
                return false;
            }
        }

        return false;
    }

    public String toString() {
        if (rules.isEmpty()) {
            return "allow all";
        }

        final StringBuilder str = new StringBuilder();

        for (Rule rule : rules) {
            str.append(String.format("%s if %s or\n", rule.getAction(), rule.toString()));
        }

        return str.toString();
    }


    public static RuleSet fromList(final List<Map<?,?>> mapList, final Consumer<String> errorCallback) {
        final RuleSet ruleSet = new RuleSet();

        for (final Map map : mapList) {
            final Rule rule = Rule.fromHash(map, errorCallback);
            ruleSet.add(rule);
        }

        return ruleSet;
    }
}
