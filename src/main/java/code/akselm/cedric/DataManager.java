package code.akselm.cedric;

import code.akselm.cedric.data.Savable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Axel on 3/20/2016.
 */
public interface DataManager {

    public void save(String category, String name, Savable save);
    public HashMap<String,Serializable> get(String category, String name);

    public boolean exists(String category, String name);
}
