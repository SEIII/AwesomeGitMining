package Application.webService.statSingle.levelClassify;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LevelClassify {
    List<Level> levels = new ArrayList<>();
    
    public LevelClassify(String levelFilePath) {
        
        Properties properties = new Properties();
        try {
//            FileInputStream fis = new FileInputStream(levelFilePath); 
//            InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
            FileReader input = new FileReader(new File(levelFilePath));
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        double base = Double.valueOf(properties.getProperty("base"));
        properties.remove("base");
        
        for(Object key:properties.keySet()) {
            String keyString = key.toString();
            handleProperties(keyString, properties.getProperty(keyString),base);
        }
        
        levels.sort(null);
    }
    
    protected void handleProperties(String key,String value,double base) {
        String[] splits = key.split("-");
        
        double down = Double.valueOf(splits[0])*base;
        
        double up =0;
        if(splits[1].equals("inf")) {
            up = Double.MAX_VALUE;
        }else {
            up = Double.parseDouble(splits[1])*base;
        }
        addLevel(down, up, value);
    }
    
    protected void addLevel(double down,double up,String message) {
        levels.add(new Level(down,up,message));
    }
    
    public String getLevelMessage(double value) {
        for(Level level : levels) {
            if(level.isHit(value)) {
                return level.getMessage();
            }
        }
        return null;
    }
    
  
}
