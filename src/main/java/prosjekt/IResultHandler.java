package prosjekt;

import java.io.FileNotFoundException;

public interface IResultHandler {
    
    Game readResult(String filename, Game game) throws FileNotFoundException;
    
    void writeResult(String filename, Game game) throws FileNotFoundException;
    
        


}
