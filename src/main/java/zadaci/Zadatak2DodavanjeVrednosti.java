package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Zadatak2DodavanjeVrednosti {

    static Dao<Roba,Integer> robaDao;
    static Dao<Avion,Integer> avionDao;


    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            robaDao = DaoManager.createDao(connectionSource, Roba.class);
            avionDao = DaoManager.createDao(connectionSource, Avion.class);

            TableUtils.clearTable(connectionSource, Roba.class);
            TableUtils.clearTable(connectionSource, Avion.class);



            Avion a1 = new Avion("avion1", 34);
            avionDao.create(a1);

            Avion a2 = new Avion("avion2", 21);
            avionDao.create(a2);

            Roba roba1 = new Roba( "Patike", "Duboke patike", 1, a1);
            robaDao.create(roba1);
            Roba roba2 = new Roba( "Kosulja", "Na duge rukave", 0.4, a1);
            robaDao.create(roba2);
            Roba roba3 = new Roba( "Voda", "Voda za pice", 1.4, a1);
            robaDao.create(roba3);
            Roba roba4 = new Roba( "Ploce", "Drvene ploce", 3.4, a2);
            robaDao.create(roba4);
            Roba roba5 = new Roba( "Stolica", "Plasticna stolica", 2.4, a2);
            robaDao.create(roba5);

            List<Roba> robe = robaDao.queryForAll();
            for (Roba r : robe)
                System.out.println(r);

            List<Avion> avion=avionDao.queryForAll();
            for(Avion a: avion)
                System.out.println(a);






        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
