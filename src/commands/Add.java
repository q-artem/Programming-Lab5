//package commands;
//
//import commands.utils.Command;
//import managers.CollectionManager;
//import models.HumanBeing;
//import models.creators.HumanBeingCreator;
//import utility.ExecutionResponse;
//import utility.console.Console;
//
///**
// * Команда 'add'. Добавляет новый элемент в коллекцию.
// */
//public class Add extends Command {
//    private final Console console;
//    private final CollectionManager collectionManager;
//
//    public Add(Console console, CollectionManager collectionManager) {
//        super("add {element}", "добавить новый элемент в коллекцию");
//        this.console = console;
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Выполняет команду
//     * @return Успешность выполнения команды и сообщение об успешности.
//     */
//    @Override
//    public ExecutionResponse apply(String argument) {
//        try {
//            if (!argument.isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
//
//            console.println("* Создание нового Aboba:");
//            HumanBeing a = HumanBeingCreator.createHumanBeing(console);
//
//            if (a != null && a.validate()) {
//                collectionManager.add(a);
//                return new ExecutionResponse("Aboba успешно добавлен!");
//            } else
//                return new ExecutionResponse(false, "Поля aboba не валидны! Aboba не создан!");
//        } catch (AskBreak e) {
//            return new ExecutionResponse(false, "Отмена...");
//        }
//    }
//}