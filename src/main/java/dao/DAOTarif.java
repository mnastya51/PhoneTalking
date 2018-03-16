package dao;

import entities.Entity;
import entities.Tarif;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOTarif extends Connect implements DAOInterface{
    public void insert(Entity T) throws SQLException {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            Tarif tarif = (Tarif) T;
            statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"Tarif\"(id_city, start_period, finish_period, cost_min) VALUES " +
                    "(\'%d\'), (\'%s\'), (\'%s\'), (\'%f\') ", tarif.getCityId(), tarif.getStartPeriod(), tarif.getFinishPeriod(), tarif.getCost()));
        }
    }

    public void update(Entity T) throws SQLException {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            Tarif tarif = (Tarif) T;
            statement.executeUpdate(String.format("UPDATE  \"phoneTalking\".\"Tarif\" SET  cost_min = \'f\' where id_city = \'d\' " +
                    "and start_period = \'s\' and finish_period = \'s\'", tarif.getCost(), tarif.getCityId(), tarif.getStartPeriod(),
                    tarif.getFinishPeriod()));
        }
    }

    public void delete(Entity T) throws SQLException {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            Tarif tarif = (Tarif) T;
            statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"Tarif\" where id_city = \'d\' and start_period = \'s\'" +
                            " and finish_period = \'s\'", tarif.getCityId(), tarif.getStartPeriod(), tarif.getFinishPeriod()));
        }
    }

    public ArrayList<Tarif> select() throws SQLException {
        try (Connection connection = connect()) {
            String nameCity = "";
            String startPeriod = "";
            String finishPeriod = "";
            Double cost = -1.0;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                    "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                    "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid"));
            ArrayList<Tarif> list = new ArrayList<>();
            while (rs.next()) {
                nameCity = rs.getString("cityname");
                startPeriod = rs.getString("periodStart");
                finishPeriod = rs.getString("periodend");
                cost = rs.getDouble("mincost");
                Tarif tarif = new Tarif (startPeriod, finishPeriod, cost, nameCity);
                list.add(tarif);
            }
            return list;
        }
    }
}
