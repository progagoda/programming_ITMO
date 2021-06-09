package helpers;

import general.StudyGroup;
import collection.*;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;


public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "Artem123456";
    private Connection connection;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void connect (PriorityQueue<StudyGroup> collection) throws SQLException {
        connection = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не найден");
        } catch (SQLException e) {
            System.out.println("SQL exeption");
        }

        ResultSet rs = statement.executeQuery("SELECT * FROM person INNER JOIN studygroup USING (passportid) ;");

        Parser.parseDatabase(rs, collection);
        this.connection = connection;
    }

    public void saveCollection(PriorityQueue<StudyGroup> collection){
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM studygroup");
            statement.executeUpdate("DELETE FROM person");
            Iterator itr = collection.iterator();
            for (Iterator<StudyGroup> it = collection.iterator(); it.hasNext(); ) {
                StudyGroup e = it.next();
                statement.executeUpdate("INSERT INTO person (adminname, passportid, eyecolor, haircolor, nationality) VALUES ("
                        + "\'" + e.getGroupAdmin().getName() + "\',"
                        + "\'" + e.getGroupAdmin().getPassportID() + "\',"
                        + "\'" + e.getGroupAdmin().getEyeColor() + "\'" + "::date,"
                        + e.getGroupAdmin().getHairColor() + ","
                        + e.getGroupAdmin().getNationality() + ");"
                );

                statement.executeUpdate("INSERT INTO studygroup (name, x, y, creationdate, price, partNumber, manufactureCost, unitOfMeasure, passportId) VALUES ("
                        + "\'" + e.getId() + "\',"
                        + e.getName()+","
                        + e.getCoordinates().getX() +","
                        + e.getCoordinates().getY() + ","
                        + "\'" + e.getCreationDate() +"\'" + "::date,"
                        + e.getStudentsCount() + ","
                        + "\'" + e.getExpelledStudents() + "\',"
                        + e.getFormOfEducation()+ ","
                        + "\'" + e.getSemesterEnum() + "\',"
                        + "\'" + e.getGroupAdmin().getPassportID() + "\');"
                );

            }

        } catch (SQLException e) {
            System.out.println("exception");
        }
    }

    public boolean checkUser(ServerUser serverUser){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            for (String login, password;result.next();){
                login = result.getString("login");
                password = result.getString("password");

                if (serverUser.getLogin().equals(login)){
                    if (serverUser.getPassword().equals(password)){
                        return true;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void registerUser(ServerUser serverUser){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(login, password) VALUES( \'" + serverUser.getLogin() + "\'," +
                                                                                                    "\'" + serverUser.getPassword() + "\');");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
