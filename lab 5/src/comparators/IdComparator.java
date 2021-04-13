package comparators;

import general.StudyGroup;

import java.util.Comparator;

public class IdComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup o1, StudyGroup o2) {
        return o2.getId().compareTo(o1.getId());
    }
}

