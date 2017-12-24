package ru.vsu.amm.sportclub.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import ru.vsu.amm.sportclub.db.models.Coach;

public class CoachDAO extends BaseDaoImpl<Coach, Integer> {

    public CoachDAO(ConnectionSource connectionSource, Class<Coach> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Coach> getAllCoaches() throws SQLException {
        return this.queryForAll();
    }
}
