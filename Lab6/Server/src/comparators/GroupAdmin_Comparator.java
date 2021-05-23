package comparators;

import general.StudyGroup;

import java.util.Comparator;

public class GroupAdmin_Comparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup o1, StudyGroup o2) {
        return o2.getGroupAdmin().compareTo(o1.getGroupAdmin());
    }
}

