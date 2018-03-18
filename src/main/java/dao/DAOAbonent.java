package dao;

import entities.Abonent;
import entities.Entity;
import utils.FilterUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAbonent  extends Connect implements DAOInterface{

    public void insert(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            Abonent abonent = (Abonent) T;
            statement.executeUpdate(String.format("INSERT INTO \"phoneTalking\".\"abonent\"(phone, fio, address, facility) VALUES" +
                    " (\'%s\', \'%s\', \'%s\', \'%b\')", abonent.getPhone(), abonent.getFio(), abonent.getAddress(), abonent.getFacility()));
        }
    }

    public void update(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            Abonent abonent = (Abonent) T;
            statement.executeUpdate(String.format("UPDATE  \"phoneTalking\".\"abonent\" SET phone = \'%s\', fio = \'%s\', address = \'%s\', " +
                    "facility = \'%b\'  where abonentid = \'%d\'", abonent.getPhone(), abonent.getFio(), abonent.getAddress(), abonent.getFacility(), abonent.getId()));
        }
    }

    public void delete(Entity T) throws SQLException {
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            Abonent abonent = (Abonent) T;
            statement.executeUpdate(String.format("DELETE FROM \"phoneTalking\".\"abonent\" where abonentid = \'%d\'", abonent.getId()));
        }
    }

    public ArrayList<Abonent> select() throws SQLException {
        try (Connection connection = connect()){
            String phone = "";
            String fio = "";
            String address = "";
            Boolean facility = false;
            int id =0;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\""));
            ArrayList<Abonent> list = new ArrayList<>();
            while (rs.next()) {
                phone = rs.getString("phone");
                fio =  rs.getString("fio");
                address =  rs.getString("address");
                facility =  rs.getBoolean("facility");
                id =  rs.getInt("abonentid");
                Abonent abonent = new Abonent (id,phone, fio, address, facility);
                list.add(abonent);
            }
            return list;
        }
    }

    public ArrayList<Abonent> sort(String value, String field) throws SQLException {
        try (Connection connection = connect()){
            String phone = "";
            String fio = "";
            String address = "";
            Boolean facility = false;
            int id =0;
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            if(value.equals("asc")) {
                switch (field) {
                    case "fio":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by fio"));
                        break;
                    case "phone":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by phone"));
                        break;
                    case "address":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by address"));
                        break;
                    case "facility":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by facility"));
                        break;
                }
            }
            else{
                switch (field) {
                    case "fio":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by fio desc"));
                        break;
                    case "phone":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by phone desc"));
                        break;
                    case "address":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by address desc"));
                        break;
                    case "facility":
                        rs = statement.executeQuery(String.format("SELECT * FROM \"phoneTalking\".\"abonent\" order by facility desc"));
                        break;
                }
            }
            ArrayList<Abonent> list = new ArrayList<>();
            while (rs.next()) {
                phone = rs.getString("phone");
                fio =  rs.getString("fio");
                address =  rs.getString("address");
                facility =  rs.getBoolean("facility");
                id =  rs.getInt("abonentid");
                Abonent abonent = new Abonent (id,phone, fio, address, facility);
                list.add(abonent);
            }
            return list;
        }
    }

    public ArrayList<Abonent> filtr(Entity T) throws SQLException {
        try (Connection connection = connect()){
            String phone = "";
            String fio = "";
            String address = "";
            Boolean facility = false;
            int id =0;
            Abonent ab = (Abonent) T;
            Statement statement = connection.createStatement();
            FilterUtils.FilterFormatter filterFormatter = new FilterUtils.FilterFormatter();
            filterFormatter.addValueWithRegisters("fio", ab.getFio());
            filterFormatter.addValueWithRegisters("phone", ab.getPhone());
            filterFormatter.addValueWithRegisters("address", ab.getAddress());
            filterFormatter.addValue("facility", ab.getFacility());
            ResultSet rs = statement.executeQuery(filterFormatter.getFormattedRequestForAbonentDB());
            ArrayList<Abonent> list = new ArrayList<>();
            while (rs.next()) {
                phone = rs.getString("phone");
                fio =  rs.getString("fio");
                address =  rs.getString("address");
                facility =  rs.getBoolean("facility");
                id =  rs.getInt("abonentid");
                Abonent abonent = new Abonent (id,phone, fio, address, facility);
                list.add(abonent);
            }
            return list;
        }
    }


}
