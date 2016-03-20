package code.akselm.cedric.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Axel on 3/2/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Stimulus {

    /*
    Tells the event bus when to run the handler.
    LOW - executed first
    MEDUIM - executed after low
    HIGH - executed after medium
    MONITOR - executed last: Do not use! This is for collecting solutions.
     */
    EventPriority priority() default EventPriority.MEDIUM;
}
