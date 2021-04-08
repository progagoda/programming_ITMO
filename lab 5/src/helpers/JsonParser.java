package helpers;
import general.StudyGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Класс-парсер для CSV
 */
public class JsonParser {
    /**
     * Парсинг строки в Массив полей
     * @param line строка
     * @return выходной массив полученных полей
     */
    public String[] parseLineToArray(String line) {
        List<String> list = new ArrayList<>();
        boolean isHereNotUsedQuote = false;
        StringBuilder field = new StringBuilder("");
        for (int i = 0; i < line.length(); i++) {
            char x = line.charAt(i);
            if (x == '"') {
                isHereNotUsedQuote = !isHereNotUsedQuote;
            } else if (x == ',') {
                if (!isHereNotUsedQuote) {
                    list.add(String.valueOf(field));
                    field = new StringBuilder("");
                } else {
                    field.append(x);
                }
            } else {
                field.append(x);
            }
        }
        list.add(String.valueOf(field));
        return !isHereNotUsedQuote ? list.toArray(new String[0]) : null;
    }
    /**
     * Создает Json строку из StudyGroup объкта
     * @param group объект, который будет преобразован в Json строку
     * @return Json line
     */
    public String makeJsonLineFromStudyGroup(StudyGroup group) {
        return group.getId() + "," +
                group.getName() + "," +
                group.getCoordinates().getX() + "," +
                group.getCoordinates().getY() + "," +
                group.getCreationDate() + "," +
                group.getStudentsCount() + "," +
                group.getExpelledStudents() + "," +
                group.getFormOfEducation() + "," +
                group.getGroupAdmin() + "," +
                group.getSemesterEnum() + ",";
    }

}
