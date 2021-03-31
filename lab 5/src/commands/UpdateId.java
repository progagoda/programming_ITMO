package commands;
import collection.GeneralColl;

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
     * Идет проверка на наличие элемента в коллекции {@link GeneralColl#getGeneralColl()} элемента с таким id, если элемент есть то идет обращение к методу {@link GeneralColl#addStudyGroup(int)}
     * @param id - номер объекта в коллекции
     * @param generalColl класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String id, GeneralColl generalColl) {
        try {
            Integer id1 = Integer.parseInt(id);
            if (id == null){
                System.out.println("Элемента с таким id нету, для того что бы посмотреть обьекты коллекции испольщуете \"show\" команду.");
            }else {
                generalColl.addStudyGroup(id1);
                System.out.println("Обьект перезаписан");
            }
        }catch(NumberFormatException | IOException e){
            System.out.println("Id должен быть целым числом типа Integer.");
        }
    }
}

