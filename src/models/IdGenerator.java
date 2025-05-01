package models;


import java.util.TreeMap;

public class IdGenerator {
    private static Integer humanBeingCounter = 0;

    private static void setHumanBeingCounter(Integer idCounter) {
        humanBeingCounter = idCounter;
    }

    public static void restoreHumanBeingCounter(TreeMap<Integer, HumanBeing> humanBeingTreeMap) {
        if (humanBeingTreeMap.isEmpty()) {
            setHumanBeingCounter(0);
            return;
        }

        // TreeMap сортирован => .lastKey() - максимальный ID
        Integer lastKey = humanBeingTreeMap.lastKey();
        if (lastKey > humanBeingCounter) {
            setHumanBeingCounter(lastKey);
        }
    }

    public static int assignHumanBeingId() {
        humanBeingCounter++;
        return humanBeingCounter;
    }
}