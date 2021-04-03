package comparators;

import general.StudyGroup;

import java.util.Comparator;

/**
 * Класс наследуемый от Comparator StudyGroup - сортировка по полю name
 */
public class NameComparator implements Comparator<StudyGroup> {

    @Override
    public int compare(StudyGroup o1, StudyGroup o2) {
        return  o1.getName().compareTo(o2.getName());
    }
}
