package dao;

import entities.Entity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInterface<T extends Entity> {
    public void insert(Entity T) throws SQLException;
    public void update(Entity T) throws SQLException;
    public void delete(Entity T) throws SQLException;
    public ArrayList<Entity> select() throws SQLException;
    public ArrayList<Entity> filter(Entity T) throws SQLException;
}
