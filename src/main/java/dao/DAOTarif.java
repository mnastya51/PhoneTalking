package dao;

import entities.Entity;
import entities.Tarif;
import utils.FilterUtils;

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
            statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"tarif\"(cityid, periodstart, periodend, mincost) VALUES " +
                    "((select cityid FROM \"phoneTalking\".\"city\" where cityname = \'%s\'), \'%s\', \'%s\', \'%s\') ", tarif.getNameCity(), tarif.getStartPeriod(), tarif.getFinishPeriod(), tarif.getCost()));
        }
    }

    @Override
    public void update(Entity T) throws SQLException {
    }

    public void delete(Entity T) throws SQLException {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            Tarif tarif = (Tarif) T;
            statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"tarif\" where cityid = (select cityid FROM" +
                    " \"phoneTalking\".\"city\" where cityname = \'%s\') and periodstart = \'%s\'" +
                            " and periodend = \'%s\'", tarif.getNameCity(), tarif.getStartPeriod(), tarif.getFinishPeriod()));
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
    public ArrayList<Tarif> sort(String value, String field) throws SQLException {
        try (Connection connection = connect()) {
            String nameCity = "";
            String startPeriod = "";
            String finishPeriod = "";
            Double cost = -1.0;
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            if(value.equals("asc")) {
                switch (field) {
                    case "city":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"city\".cityname"));
                        break;
                    case "startPeriod":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".periodStart"));
                        break;
                    case "finishPeriod":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".periodend"));
                        break;
                    case "minCost":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".mincost"));
                        break;
                }
            }
            else {
                switch (field) {
                    case "city":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"city\".cityname desc"));
                        break;
                    case "startPeriod":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".periodStart desc"));
                        break;
                    case "finishPeriod":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".periodend desc"));
                        break;
                    case "minCost":
                        rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                                "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                                "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid " +
                                "order by \"phoneTalking\".\"tarif\".mincost desc"));
                        break;
                }
            }
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
    public ArrayList<Tarif> filter(Entity T) throws SQLException {
        try (Connection connection = connect()) {
            String nameCity = "";
            String startPeriod = "";
            String finishPeriod = "";
            Double cost = -1.0;
            Tarif tar = (Tarif) T;
            Statement statement = connection.createStatement();
            FilterUtils.FilterFormatter filterFormatter = new FilterUtils.FilterFormatter();
            filterFormatter.addValueWithRegisters("\"phoneTalking\".\"city\".cityname", tar.getNameCity());
            if(!tar.getStartPeriod().isEmpty())
                filterFormatter.addValue("\"phoneTalking\".\"tarif\".periodStart", tar.getStartPeriod() + ":00");
            if(!tar.getFinishPeriod().isEmpty())
                filterFormatter.addValue("\"phoneTalking\".\"tarif\".periodend", tar.getFinishPeriod() + ":00");
            if (tar.getCost() >= 0)
                filterFormatter.addValue("\"phoneTalking\".\"tarif\".mincost", String.valueOf(tar.getCost()));
            ResultSet rs = statement.executeQuery(filterFormatter.getFormattedRequestForTarifDB());
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
