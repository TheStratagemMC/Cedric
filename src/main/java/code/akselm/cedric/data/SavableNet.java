package code.akselm.cedric.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Axel on 3/20/2016.
 */
public abstract class SavableNet implements Savable{
    public abstract byte[] getBytes();

    @Override
    public HashMap<String,Serializable> save() {
        HashMap<String, Serializable> map = new HashMap<>();
        map.put("net", getBytes());
        return map;
    }

}
