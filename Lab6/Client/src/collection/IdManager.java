package collection;

import java.util.HashSet;
import java.util.Random;

/**
 * Класс, реализующий уникальность поля id у StudyGroup.
 */
public class IdManager {
    /**
     * Поле - все уникальные id
     */
    private static final HashSet<Long> hashSetOfIds = new HashSet<>();

    /**
     * Метод - проверка уникальности
     *
     * @param id номер для проверки
     * @return true/false, уникальный или нет
     */
    public static boolean checkUniq(Long id) {
        return !hashSetOfIds.contains(id);
    }

    public static HashSet<Long> getHashSetOfIds() {
        return hashSetOfIds;
    }

    /**
     * Метод, который ищет уникальный id
     *
     * @param id рекурсивный поиск уникального id
     * @return возвращеат уникальный id
     */
    public static Long findUniq(Long id) {
        if (checkUniq(id)) {
            hashSetOfIds.add(id);
            return id;
        } else {
            return findUniq(Math.abs(new Random().nextLong()));
        }
    }

    /**
     * Метод - удалить id, который привязан к элементу колекции, которого больше нет
     *
     * @param id удаляемы id номер
     */
    public static void removeUsedId(Long id) {
        hashSetOfIds.remove(id);
    }

    /**
     * Очистить все уникальные id
     */
    public static void clearSet() {
        hashSetOfIds.clear();
    }

}
