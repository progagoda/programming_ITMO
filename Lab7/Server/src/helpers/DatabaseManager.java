package helpers;

import general.StudyGroup;
import collection.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;


public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "your_login";
    private final String password = "your_password";
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

    public void connect(PriorityQueue<StudyGroup> collection) throws SQLException {
        connection = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            System.out.println("База данных подключена");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не найден");
        } catch (SQLException e) {
            System.out.println("SQL exeption");
        }

        ResultSet rs = statement.executeQuery("SELECT * FROM person INNER JOIN studygroup USING (passportid) ;");

        Parser.parseDatabase(rs, collection);
        this.connection = connection;
    }

    public void saveCollection(PriorityQueue<StudyGroup> collection) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM studygroup");
            statement.executeUpdate("DELETE FROM person");
            Iterator itr = collection.iterator();
            for (Iterator<StudyGroup> it = collection.iterator(); it.hasNext(); ) {
                StudyGroup e = it.next();
                statement.executeUpdate("INSERT INTO person (adminname, passportid, eyecolor, haircolor,nationality) VALUES ("
                        + "\'" + e.getGroupAdmin().getName() + "\',"
                        + "\'" + e.getGroupAdmin().getPassportID() + "\',"
                        + "\'" + e.getGroupAdmin().getEyeColor() + "\',"
                        + "\'" + e.getGroupAdmin().getHairColor() + "\',"
                        + "\'" + e.getGroupAdmin().getNationality() + "\'" + ");"
                );

                statement.executeUpdate("INSERT INTO studygroup (id,name, coordinatesx, coordinatesy, date, studentcount, expelledstudents, formofeducation, semester, passportid, creator) VALUES ("
                        + e.getId() + ","
                        + "\'" + e.getName() + "\',"
                        + e.getCoordinates().getX() + ","
                        + e.getCoordinates().getY() + ","
                        + "\'" + e.getCreationDate() + "\'" + "::date,"
                        + e.getStudentsCount() + ","
                        + e.getExpelledStudents() + ","
                        + "\'" + e.getFormOfEducation() + "\',"
                        + "\'" + e.getSemesterEnum() + "\',"
                        + "\'" + e.getGroupAdmin().getPassportID() + "\',"
                        + "\'" + e.getCreator() + "\'" + ");"
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception");
        }
    }


    public boolean checkLogin(ServerUser serverUser) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            for (; result.next(); ) {
                String login = result.getString("login");
                String password = result.getString("password");
                if (serverUser.getLogin().equals(login)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkUser(ServerUser serverUser) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            for (String login, password; result.next(); ) {
                login = result.getString("login");
                password = result.getString("password");

                if (serverUser.getLogin().equals(login)) {
                    if (hash(serverUser.getPassword()).equals(password)) {
                        return true;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void registerUser(ServerUser serverUser) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(login, password) VALUES( \'" + serverUser.getLogin() + "\'," +
                    "\'" + hash(serverUser.getPassword()) + "\');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO Переписать на SHA-256
    public String hash(String str){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
}


