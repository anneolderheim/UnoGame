package prosjekt;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStateHandler {
    
    Game readState(String filename) throws FileNotFoundException;
    
    void writeState(Game game)  throws IOException ;
    
        


}
