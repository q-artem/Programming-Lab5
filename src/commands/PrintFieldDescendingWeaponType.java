package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.WeaponType;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Команда 'print_field_descending_weapon_type'.
 * Выводит значения поля weaponType всех элементов коллекции в порядке убывания.
 * Реализует интерфейсы {@link Executable} и {@link Describable}.
 */
public class PrintFieldDescendingWeaponType extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды 'print_field_descending_weapon_type'.
     * @param ignoredConsole консоль (не используется в этой команде)
     * @param collectionManager менеджер коллекции для получения данных
     */
    public PrintFieldDescendingWeaponType(Console ignoredConsole, CollectionManager collectionManager) {
        super("print_field_descending_weapon_type", "вывести значения поля weaponType всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду вывода значений поля weaponType в порядке убывания.
     * Формирует отсортированный по убыванию список значений weaponType всех элементов коллекции.
     *
     * @param arguments аргументы команды (не используются)
     * @return результат выполнения команды ({@link ExecutionResponse}) с отсортированными значениями
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        List<WeaponType> sortedWeaponTypes = new ArrayList<>();
        for (HumanBeing h : collectionManager.getCollection().values()) {
            sortedWeaponTypes.add(h.getWeaponType());
        }
        sortedWeaponTypes.sort(Comparator.reverseOrder());

        if (sortedWeaponTypes.isEmpty()) {
            return new ExecutionResponse("В коллекции нет элементов с WeaponType!");
        }

        StringBuilder result = new StringBuilder();
        for (WeaponType weaponType : sortedWeaponTypes) {
            result.append(weaponType).append("\n");
        }

        return new ExecutionResponse(result.toString());
    }
}
