package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;




public class Zadatak3IzmenaVrednosti {
    static Dao<Roba,Integer> robaDao;
    static Dao<Avion,Integer> avionDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            robaDao = DaoManager.createDao(connectionSource, Roba.class);
            avionDao = DaoManager.createDao(connectionSource, Avion.class);




            List<Roba> roba = robaDao.queryForAll();
            for (Roba r : roba)
                System.out.println(r);

            PreparedQuery<Roba> robaPripremljen=robaDao.queryBuilder().where().eq(Roba.POLJE_OPIS,"Plasticna stolica").prepare();
            final Roba r = robaDao.queryForFirst(robaPripremljen);

            Roba robaZaIzmenu = robaDao.queryForEq(Roba.POLJE_OPIS, "Plasticna stolica");
            robaZaIzmenu.setOpis("Drvena stolica");
            robaDao.update(robaZaIzmenu);


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
