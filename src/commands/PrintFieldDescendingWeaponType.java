package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.WeaponType;
import utility.ExecutionResponse;
import utility.console.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Команда 'print_field_descending_weapon_type'. Вывести значения поля weaponType всех элементов в порядке убывания.
 */
public class PrintFieldDescendingWeaponType extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingWeaponType(Console console, CollectionManager collectionManager) {
        super("print_field_descending_weapon_type", "вывести значения поля weaponType всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

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
