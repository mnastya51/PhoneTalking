package dao;

import entities.Entity;
import entities.Talking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

    public class DAOTalking extends Connect implements DAOInterface{

        public void insert(Entity T) throws SQLException {
            try (Connection connection = connect()){
                Statement statement = connection.createStatement();
                Talking talking = (Talking) T;
                statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"Talking\"(id_abonent, cost_talk, id_city, " +
                        "count_min, date_talk, time_talk) VALUES (\'%d\', \'%f\', \'%d\', \'%d\', \'%s\', \'%s\')", talking.getAbonentId(),
                        talking.getCostTalk(), talking.getCityId(), talking.getMinCount(), talking.getTalkDate(), talking.getTalkTime()));
            }
        }

        public void update(Entity T) throws SQLException {
            try (Connection connection = connect()){
                Statement statement = connection.createStatement();
                Talking talking = (Talking) T;
                statement.executeUpdate(String.format("UPDATE  \"phoneTalking\".\"Talking\" SET id_abonent = \'d\', cost_talk = \'f\', id_city = \'d\'," +
                        "count_min = \'d\', date_talk = \'s\', time_talk = \'s\' where id_talk = \'d\'", talking.getAbonentId(), talking.getCostTalk(),
                        talking.getCityId(), talking.getMinCount(), talking.getTalkDate(), talking.getTalkTime(), talking.getTalkId()));
            }
        }

        public void delete(Entity T) throws SQLException {
            try (Connection connection = connect()){
                Statement statement = connection.createStatement();
                Talking talking = (Talking) T;
                statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"Talking\" where id_talk = \'d\'",talking.getTalkId()));
            }
        }

        public ArrayList<Talking> select() throws SQLException {
            try (Connection connection = connect()) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"Abonent\".number_tel, \"phoneTalking\".\"Talking\".cost_talk," +
                        "\"phoneTalking\".\"City\".name_city, \"phoneTalking\".\"Talking\".count_min, \"phoneTalking\".\"Talking\".date_talk," +
                        "\"phoneTalking\".\"Talking\".time_talk  FROM \"phoneTalking\".\"Talking\" JOIN \"phoneTalking\".\"Abonent\" on " +
                        "\"phoneTalking\".\"Abonent\".id_abonent = \"phoneTalking\".\"Talking\".id_abonent JOIN \"phoneTalking\".\"City\" on " +
                        "\"phoneTalking\".\"City\".id_city = \"phoneTalking\".\"Talking\".id_city"));
                ArrayList<Talking> list =  new ArrayList<>();
                while (rs.next()) {
                    Talking talking = new Talking(rs.getString("number_tel"), rs.getDouble("cost_talk"), rs.getString("name_city"),
                            rs.getInt("count_min"), rs.getString("date_talk"), rs.getString("time_talk"));
                    list.add(talking);
                }
                return list;
            }
        }
    }


