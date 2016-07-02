package Application.data.DAO.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {

    public static String ALL_USER_INFO;
    public static String COLLABORATORS;
    public static String CONTRIBUTORS;
    public static String GITMININGUSERS;
    public static String LANGUELINE;
    public static String REPOFULLNAME;
    public static String TEST;
    public static Properties pro;
    static {
        pro = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("config/dbConfig.properties");
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ALL_USER_INFO = pro.getProperty("alluserinfo");
        COLLABORATORS = pro.getProperty("collaborators");
        CONTRIBUTORS = pro.getProperty("contributors");
        GITMININGUSERS = pro.getProperty("gitminingusers");
        LANGUELINE = pro.getProperty("languageline");
        REPOFULLNAME = pro.getProperty("repofullname");
        TEST = pro.getProperty("test");
        
    }
}
