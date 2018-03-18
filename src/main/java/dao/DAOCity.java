package dao;

import entities.City;
import entities.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOCity extends Connect implements DAOInterface {

    public void insert(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            City city = (City) T;
            statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"city\"(cityname) VALUES (\'%s\')", city.getNameCity()));
        }
    }

    public void update(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            City city = (City) T;
            statement.executeUpdate(String.format("UPDATE  \"phoneTalking\".\"City\" SET name_city = \'s\' where id_city = \'d\'", city.getNameCity(), city.getId()));
        }
    }

    public void delete(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            City city = (City) T;
            statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"city\" where cityid = \'%d\'", city.getId()));
        }
    }

    public ArrayList<City> select() throws SQLException {
        try (Connection connection = connect()){
            String name = "";
            int id = 0;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT *  FROM \"phoneTalking\".\"city\""));
            ArrayList<City> list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("cityid");
                name = rs.getString("cityname");
                City city = new City(id, name);
                list.add(city);
            }
            return list;
        }
    }

    public ArrayList<City> sort(String value) throws SQLException {
        try (Connection connection = connect()){
            String name = "";
            int id = 0;
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            if(value.equals("asc"))
                rs = statement.executeQuery(String.format("SELECT *  FROM \"phoneTalking\".\"city\" order by cityname"));
            else rs = statement.executeQuery(String.format("SELECT *  FROM \"phoneTalking\".\"city\" order by cityname desc"));
            ArrayList<City> list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("cityid");
                name = rs.getString("cityname");
                City city = new City(id, name);
                list.add(city);
            }
            return list;
        }
    }
}
