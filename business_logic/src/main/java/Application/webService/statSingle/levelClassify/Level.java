package Application.webService.statSingle.levelClassify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Level implements Comparable<Level>{
    
    double down = 0;
    double up=Double.MAX_VALUE;
    String message;
    
    public boolean isHit(double value) {
        
        if(value<=up && value>=down) {
            return true;
        }else {
            return false;
        }
        
    }

    @Override
    public int compareTo(Level o) {
        return Double.compare(down, o.down);
    }
    
}
