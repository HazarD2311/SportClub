package ru.vsu.amm.sportclub.db.dao;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import ru.vsu.amm.sportclub.db.models.Sportsman;

public class SportsmanDAO extends BaseDaoImpl<Sportsman, Integer> {

    public SportsmanDAO(ConnectionSource connectionSource, Class<Sportsman> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Sportsman> getAllSportsmans() throws SQLException {
        return this.queryForAll();
    }
}
