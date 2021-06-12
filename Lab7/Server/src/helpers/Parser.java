package helpers;

import general.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Parser {
    public static void parseDatabase(ResultSet rs, PriorityQueue<StudyGroup> collection) throws SQLException {
        for (int i = 0; rs.next(); i++) {
            try {
                Coordinates coor = new Coordinates();
                Person admin = new Person();
                StudyGroup group = new StudyGroup();
                group.setId(rs.getLong("id"));
                group.setName(rs.getString("name"));
                coor.setX(rs.getLong("coordinatesx"));
                coor.setY(rs.getFloat("coordinatesy"));
                group.setCoordinates(coor);
                group.setCreationDate(rs.getDate("date").toLocalDate().atTime(00, 00));
                group.setStudentsCount(rs.getLong("studentcount"));
                group.setExpelledStudents(rs.getLong("expelledstudents"));
                group.setFormOfEducation(FormOfEducation.valueOf(rs.getString("formofeducation")));
                group.setSemesterEnum(Semester.valueOf(rs.getString("semester")));
                admin.setName(rs.getString("adminname"));
                admin.setPassportID(rs.getString("passportid"));
                admin.setEyeColor(Color.valueOf(rs.getString("eyecolor")));
                admin.setHairColor(Color.valueOf(rs.getString("haircolor")));
                admin.setNationality(Country.valueOf(rs.getString("nationality")));
                group.setGroupAdmin(admin);
                group.setCreator(rs.getString("creator"));

                collection.add(group);
            } catch (SQLException | IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("exception");
            }
        }

    }
}
