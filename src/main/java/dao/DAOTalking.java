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
                statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"talking\"(abonentid, cityid, talkcost, " +
                        "mincount, talkdate, talktime) VALUES ((select abonentid from \"phoneTalking\".\"abonent\" where phone = \'%s\')," +
                                "(select cityid FROM \"phoneTalking\".\"city\" where cityname = \'%s\'), \'%s\', \'%d\', \'%s\', \'%s\')",
                        talking.getPhoneAbonent(),talking.getCityName(), talking.getCostTalk(),  talking.getMinCount(), talking.getTalkDate(), talking.getTalkTime()));
            }
        }

        public void update(Entity T) throws SQLException {
            try (Connection connection = connect()){
                Statement statement = connection.createStatement();
                Talking talking = (Talking) T;
                statement.executeUpdate(String.format("UPDATE  \"phoneTalking\".\"talking\" SET abonentid =" +
                                "(select abonentid from \"phoneTalking\".\"abonent\" where phone = \'%s\'), talkcost = \'%s\', cityid =" +
                                "(select cityid FROM \"phoneTalking\".\"city\" where cityname = \'%s\')," +
                        "mincount = \'%d\', talkdate = \'%s\', talktime = \'%s\' where talkid = \'%d\'", talking.getPhoneAbonent(), talking.getCostTalk(),
                        talking.getCityName(), talking.getMinCount(), talking.getTalkDate(), talking.getTalkTime(), talking.getTalkId()));
            }
        }

        public void delete(Entity T) throws SQLException {
            try (Connection connection = connect()){
                Statement statement = connection.createStatement();
                Talking talking = (Talking) T;
                statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"talking\" where talkid = \'%d\'",talking.getTalkId()));
            }
        }

        public ArrayList<Talking> select() throws SQLException {
            try (Connection connection = connect()) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, "+
                        "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount,"+
                        "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                        "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                        "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                        "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid"));
                ArrayList<Talking> list =  new ArrayList<>();
                while (rs.next()) {
                    Talking talking = new Talking(rs.getInt("talkid"), rs.getString("phone"), rs.getString("cityname"),
                            rs.getInt("mincount"), rs.getString("talkdate"), rs.getString("talktime"),rs.getDouble("talkcost"));
                    list.add(talking);
                }
                return list;
            }
        }

        public ArrayList<Talking> sort(String value, String field) throws SQLException {
            try (Connection connection = connect()) {
                Statement statement = connection.createStatement();
                ResultSet rs = null;
                if(value.equals("asc")) {
                    switch (field) {
                        case "phone":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"abonent\".phone"));
                            break;
                        case "city":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"city\".cityname"));
                            break;
                        case "min":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".mincount"));
                            break;
                        case "date":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talkdate"));
                            break;
                        case "time":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talktime"));
                            break;
                        case "cost":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talkcost"));
                            break;
                    }
                }
                else {
                    switch (field) {
                        case "phone":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"abonent\".phone desc"));
                            break;
                        case "city":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"city\".cityname desc"));
                            break;
                        case "min":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".mincount desc"));
                            break;
                        case "date":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talkdate desc"));
                            break;
                        case "time":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talktime desc"));
                            break;
                        case "cost":
                            rs = statement.executeQuery(String.format("SELECT \"phoneTalking\".\"talking\".talkid, " +
                                    "\"phoneTalking\".\"abonent\".phone, \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount," +
                                    "\"phoneTalking\".\"talking\".talkdate, \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost " +
                                    "FROM \"phoneTalking\".\"talking\" JOIN \"phoneTalking\".\"abonent\" on " +
                                    "\"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid JOIN \"phoneTalking\".\"city\" on " +
                                    "\"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid " +
                                    "order by \"phoneTalking\".\"talking\".talkcost desc"));
                            break;
                    }
                }
                ArrayList<Talking> list =  new ArrayList<>();
                while (rs.next()) {
                    Talking talking = new Talking(rs.getInt("talkid"), rs.getString("phone"), rs.getString("cityname"),
                            rs.getInt("mincount"), rs.getString("talkdate"), rs.getString("talktime"),rs.getDouble("talkcost"));
                    list.add(talking);
                }
                return list;
            }
        }
    }


