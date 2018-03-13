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
            ResultSet rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"City\".name_city, \"phoneTalking\".\"Tarif\".start_period," +
                    "\"phoneTalking\".\"Tarif\".finish_period,  \"phoneTalking\".\"Tarif\".cost_min FROM \"phoneTalking\".\"Tarif\" JOIN " +
                    "\"phoneTalking\".\"City\" on \"phoneTalking\".\"City\".id_city = \"phoneTalking\".\"Tarif\".id_city"));
            ArrayList<Tarif> list = null;
            while (rs.next()) {
                nameCity = rs.getString("name_city");
                startPeriod = rs.getString("start_period");
                finishPeriod = rs.getString("finish_period");
                cost = rs.getDouble("cost_min");
                Tarif tarif = new Tarif (startPeriod, finishPeriod, cost, nameCity);
                list.add(tarif);
            }
            return list;
        }
    }
}
