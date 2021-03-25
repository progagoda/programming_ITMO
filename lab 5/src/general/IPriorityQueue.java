package general;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
public interface IPriorityQueue  extends Iterable<StudyGroup>{
    /**
     * Возвращает ссылку на коллекцию товаров.
     * @return коллекция
     */
      PriorityQueue<StudyGroup> getProducts();
    /**
     * Возвращает товар с переданным номером или null, если такого нет.
    * @param id номер (ID) товара.
    * @return объект класса {@link StudyGroup} или null.
    */
    StudyGroup getStudyGroupId(long id);
    /**
     * Удаляет товар с указанным номером.
     * @param id номер (ID) товара.
     * @return true, если такой товар был и удалён, иначе false.
     */
    boolean removeStudyGroupId();
    /**
     * Возвращает дату инициализации в виде строки.
     * @return строковое представление даты.
     */
    String getInitializationDataString();

}
