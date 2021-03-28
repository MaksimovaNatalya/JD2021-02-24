package by.it.runcov.calc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class Var implements Operation {

    private static Map<String, Var> vars = new HashMap<>();

    static Var save(String name, Var var) {
        vars.put(name, var);
        return var;
    }

    static Var load(String name) {
        return vars.get(name);
    }

    static void printVar(Map<String, Var> save) {
        Set<Map.Entry<String, Var>> setVar = save.entrySet();
        Iterator<Map.Entry<String, Var>> i = setVar.iterator();
        while (i.hasNext()) {
            Map.Entry<String, Var> mapEntry = i.next();
            System.out.println(mapEntry.getKey() + "=" + mapEntry.getValue());
        }
    }

    @Override
    public Var add(Var other) {
        System.out.printf("Operation %s + %s is impossible\n", this, other);
        return null; //TODO replace throw exception
    }

    @Override
    public Var sub(Var other) {
        System.out.printf("Operation %s - %s is impossible\n", this, other);
        return null;
    }

    @Override
    public Var mul(Var other) {
        System.out.printf("Operation %s * %s is impossible\n", this, other);
        return null;
    }

    @Override
    public Var div(Var other) {
        System.out.printf("Operation %s / %s is impossible\n", this, other);
        return null;
    }

    @Override
    public String toString() {
        return "abstract Var{}";
    }
}