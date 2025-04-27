package managers;

import models.Car;
import models.Coordinates;
import models.HumanBeing;
import models.WeaponType;
import org.w3c.dom.*;
import utility.console.StandartConsole;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DumpManager {
    private final String fileName;
    private final StandartConsole console;

    public DumpManager(String fileName, StandartConsole console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Записывает коллекцию в XML файл
     *
     * @param collection TreeMap для сохранения
     */
    public void writeCollection(TreeMap<Integer, HumanBeing> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<humanBeings>\n");

            for (Map.Entry<Integer, HumanBeing> entry : collection.entrySet()) {
                HumanBeing humanBeing = entry.getValue();
                writer.write(String.format(
                        "  <humanBeing id=\"%d\">\n" +
                                "    <name>%s</name>\n" +
                                "    <coordinates>\n" +
                                "      <x>%d</x>\n" +
                                "      <y>%f</y>\n" +
                                "    </coordinates>\n" +
                                "    <creationDate>%s</creationDate>\n" +
                                "    <realHero>%b</realHero>\n" +
                                "    <hasToothpick>%b</hasToothpick>\n" +
                                "    <impactSpeed>%f</impactSpeed>\n" +
                                "    <soundtrackName>%s</soundtrackName>\n" +
                                "    <minutesOfWaiting>%s</minutesOfWaiting>\n" +
                                "    <weaponType>%s</weaponType>\n" +
                                "    <car>%s</car>\n" +
                                "  </humanBeing>\n",
                        entry.getKey(),
                        escapeXml(humanBeing.getName()),
                        humanBeing.getCoordinates().getX(),
                        humanBeing.getCoordinates().getY(),
                        humanBeing.getCreationDate(),
                        humanBeing.getRealHero(),
                        humanBeing.getHasToothpick(),
                        humanBeing.getImpactSpeed(),
                        escapeXml(humanBeing.getSoundtrackName()),
                        humanBeing.getMinutesOfWaiting() != null ? humanBeing.getMinutesOfWaiting().toString() : "null",
                        humanBeing.getWeaponType().name(),
                        humanBeing.getCar() != null ? escapeXml(humanBeing.getCar().toString()) : "null"
                ));
            }

            writer.write("</humanBeings>");
            console.println("Коллекция успешно сохранена в XML файл!");
        } catch (IOException e) {
            console.printError("Ошибка записи в файл: " + e.getMessage());
        }
    }

    /**
     * Читает коллекцию из XML файла
     *
     * @return TreeMap с продуктами
     */
    public TreeMap<Integer, HumanBeing> readCollection() {
        TreeMap<Integer, HumanBeing> collection = new TreeMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (!scanner.hasNextLine()) {
                console.printError("Файл пуст");
                return collection;
            }

            // Skip XML declaration and root tag
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("<humanBeing ")) {
                    int id = Integer.parseInt(line.split("\"")[1]);
                    String name = "";
                    long x = 0;
                    Float y = null;
                    LocalDate creationDate = null;
                    Boolean realHero = null;
                    Boolean hasToothpick = null;
                    float impactSpeed = 0;
                    String soundtrackName = "";
                    Double minutesOfWaiting = null;
                    WeaponType weaponType = null;
                    Car car = null;

                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if (line.startsWith("</humanBeing>")) break;

                        if (line.startsWith("<name>")) {
                            name = line.replace("<name>", "").replace("</name>", "");
                        } else if (line.startsWith("<coordinates>")) {
                            while (!line.contains("</coordinates>")) {
                                line = scanner.nextLine().trim();
                                if (line.startsWith("<x>")) {
                                    x = Long.parseLong(line.replace("<x>", "").replace("</x>", ""));
                                } else if (line.startsWith("<y>")) {
                                    y = Float.parseFloat(line.replace("<y>", "").replace("</y>", "").replace(",", "."));
                                }
                            }
                        } else if (line.startsWith("<creationDate>")) {
                            creationDate = LocalDate.parse(line.replace("<creationDate>", "").replace("</creationDate>", ""));
                        } else if (line.startsWith("<realHero>")) {
                            realHero = Boolean.parseBoolean(line.replace("<realHero>", "").replace("</realHero>", ""));
                        } else if (line.startsWith("<hasToothpick>")) {
                            hasToothpick = Boolean.parseBoolean(line.replace("<hasToothpick>", "").replace("</hasToothpick>", ""));
                        } else if (line.startsWith("<impactSpeed>")) {
                            impactSpeed = Float.parseFloat(line.replace("<impactSpeed>", "").replace("</impactSpeed>", "").replace(",", "."));
                        } else if (line.startsWith("<soundtrackName>")) {
                            soundtrackName = line.replace("<soundtrackName>", "").replace("</soundtrackName>", "");
                        } else if (line.startsWith("<minutesOfWaiting>")) {
                            String value = line.replace("<minutesOfWaiting>", "").replace("</minutesOfWaiting>", "");
                            if (!value.equals("null")) {
                                minutesOfWaiting = Double.parseDouble(value.replace(",", "."));
                            }
                        } else if (line.startsWith("<weaponType>")) {
                            weaponType = WeaponType.valueOf(line.replace("<weaponType>", "").replace("</weaponType>", ""));
                        } else if (line.startsWith("<car>")) {
                            String value = line.replace("<car>", "").replace("</car>", "");
                            if (!value.equals("null")) {
                                car = new Car.Builder().name(unescapeXml(value)).build();
                            }
                        }
                    }

                    HumanBeing humanBeing = new HumanBeing.Builder(id, creationDate)
                            .name(unescapeXml(name))
                            .coordinates(new Coordinates.Builder().x(x).y(y).build())
                            .realHero(realHero)
                            .hasToothpick(hasToothpick)
                            .impactSpeed(impactSpeed)
                            .soundtrackName(unescapeXml(soundtrackName))
                            .minutesOfWaiting(minutesOfWaiting)
                            .weaponType(weaponType)
                            .car(car)
                            .build();

                    collection.put(id, humanBeing);
                }
            }

            console.println("Коллекция успешно загружена из XML!");
        } catch (Exception e) {
            console.printError("Ошибка чтения XML: " + e.getMessage());
        }

        return collection;
    }

    private String getElementText(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private String escapeXml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private String unescapeXml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&amp;", "&")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&quot;", "\"")
                .replace("&apos;", "'");

    }
}
