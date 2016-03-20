package code.akselm.cedric.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Axel on 3/2/2016.
 */
public interface Savable {

    public HashMap<String,Serializable> save();
    public void load(HashMap<String,Serializable> map);
}
