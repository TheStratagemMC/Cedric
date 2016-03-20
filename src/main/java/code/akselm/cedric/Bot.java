package code.akselm.cedric;

/**
 * Created by Axel on 3/1/2016.
 */
public abstract class Bot {
    protected EventManager eventManager;
    protected DataManager dataManager;

    public DataManager getDataManager(){
        return dataManager;
    }
    public EventManager getEventManager(){
        return eventManager;
    }
}
