package managers;

import models.HumanBeing;
import models.IdGenerator;

import java.time.LocalDateTime;
import java.util.TreeMap;

public class CollectionManager {
    private int currentId = 1;
    TreeMap<Integer, HumanBeing> collection = new TreeMap<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    /**
     * @return Последнее время инициализации.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return коллекция.
     */
    public TreeMap<Integer, HumanBeing> getCollection() {
        return collection;
    }

    /**
     * Получить HumanBeing по ID
     */
    public HumanBeing getById(int id) {
        return collection.get(id);
    }

    /**
     * Содержит ли коллекция HumanBeing
     */
    public boolean isContain(HumanBeing e) {
        return e == null || getById(e.getId()) != null;
    }

    /**
     * Добавляет HumanBeing
     */
    public boolean add(HumanBeing a) {
        if (isContain(a)) return false;
        collection.put(a.getId(), a);
        return true;
    }


    /**
     * Обновляет HumanBeing
     */
    public boolean update(HumanBeing humanBeing) {
        if (!isContain(humanBeing)) return false;
        collection.remove(humanBeing.getId());
        collection.put(humanBeing.getId(), humanBeing);
        return true;
    }

    /**
     * Удаляет HumanBeing по ID
     */
    public boolean remove(Integer id) {
        HumanBeing humanBeing = getById(id);
        if (humanBeing == null) return false;
        collection.remove(humanBeing.getId());
        return true;
    }

    public boolean loadCollection() {
        collection.clear();
        dumpManager.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (HumanBeing humanBeing : collection.values()) if (humanBeing.getId() > currentId) currentId = humanBeing.getId();
        IdGenerator.restoreHumanBeingCounter(collection);
        return true;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (HumanBeing humanBeing : collection.values()) {
            info.append(humanBeing).append("\n\n");
        }
        return info.toString().trim();
    }
}