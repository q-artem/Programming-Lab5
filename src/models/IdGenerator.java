package models;


import java.util.TreeMap;

public class IdGenerator {
    private static Integer HumanBeingCounter = 0;


    private static void setHumanBeingCounter(Integer idCounter) {
        HumanBeingCounter = idCounter;
    }


    public static void restoreHumanBeingCounter(TreeMap<Integer, HumanBeing> humanBeingTreeMap) {
        if (humanBeingTreeMap.isEmpty()) {
            setHumanBeingCounter(0);
            return;
        }

        // TreeMap хранит элементы в отсортированном порядке, поэтому последний элемент имеет максимальный ID
        Integer lastKey = humanBeingTreeMap.lastKey();
        if (lastKey > HumanBeingCounter) {
            setHumanBeingCounter(lastKey);
        }
    }


    public static int assignHumanBeingId() {
        HumanBeingCounter++;
        return HumanBeingCounter;
    }
}