package commands;
import general.GeneralCollection;

import java.io.IOException;

/**
 * Класс, реализующий программу update id, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateId implements CommandDo{
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public UpdateId() {
        StartCommand.addCommand("update", this);
    }

    /**
     * Идет проверка на наличие элемента в коллекции {@link GeneralCollection#getGenCollection()} элемента с таким id, если элемент есть то идет обращение к методу {@link GeneralCollection#addStudyGroup(int)}
     * @param name строковое значение, аргумент команды(id)
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralCollection generalCollection) {
        try {
            Integer id = Integer.parseInt(name);
            Integer key = generalCollection.getKeyById(id);
            if (key == null){
                System.out.println("Элемента с таким id нету, для того что бы посмотреть обьекты коллекции испольщуете \"show\" команду.");
            }else {
                generalCollection.addStudyGroup(key);
                System.out.println("Обьект перезаписан");
            }
        }catch(NumberFormatException | IOException e){
            System.out.println("Id должен быть целым числом типа Integer.");
        }
    }
}

