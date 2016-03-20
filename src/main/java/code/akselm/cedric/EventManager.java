package code.akselm.cedric;

import code.akselm.cedric.event.Event;

/**
 * Created by Axel on 3/1/2016.
 */
public interface EventManager {

    public void registerListener(Object listener);

    public void unregisterListener(Object listener);

    public void unregisterAllListeners();

    public void callEvent(Event event);
}
