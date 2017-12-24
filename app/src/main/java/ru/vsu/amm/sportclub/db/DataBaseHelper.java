package ru.vsu.amm.sportclub.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import ru.vsu.amm.sportclub.db.dao.CoachDAO;
import ru.vsu.amm.sportclub.db.dao.SportsmanDAO;
import ru.vsu.amm.sportclub.db.models.Coach;
import ru.vsu.amm.sportclub.db.models.Sportsman;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DataBaseHelper.class.getName();
    private static final String DATABASE_NAME = "sportclub.db";
    private static final int DATABASE_VERSION = 1;

    private SportsmanDAO sportsmanDAO = null;
    private CoachDAO coachDAO = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource, Sportsman.class);
            TableUtils.clearTable(connectionSource, Coach.class);
        } catch (java.sql.SQLException e) {
            Log.d("DATABASE_LOG", "error creating db " + DATABASE_NAME);
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Sportsman.class, true);
            TableUtils.dropTable(connectionSource, Coach.class, true);
        } catch (java.sql.SQLException e) {
            Log.d("DATABASE_LOG", "error upgrading db "
                    + DATABASE_NAME + " ver. " + DATABASE_VERSION);
            e.printStackTrace();
        }
    }

    public SportsmanDAO getSportsmanDAO() throws java.sql.SQLException {
        if (sportsmanDAO == null) {
            sportsmanDAO = new SportsmanDAO(getConnectionSource(), Sportsman.class);
        }
        return sportsmanDAO;
    }

    public CoachDAO getCoachDAO() throws java.sql.SQLException {
        if (coachDAO == null) {
            coachDAO = new CoachDAO(getConnectionSource(), Coach.class);
        }
        return coachDAO;
    }

    @Override
    public void close() {
        sportsmanDAO = null;
        coachDAO = null;
        super.close();
    }
}
