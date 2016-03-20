package code.akselm.cedric.event;

/**
 * Created by Axel on 3/2/2016.
 *
 * For benchmarking purposes only.
 */
public class TimedEvent implements Event{
    public long time;

    public TimedEvent(){
        time = System.currentTimeMillis();
    }

    public long getTime(){
        return time;
    }
}
