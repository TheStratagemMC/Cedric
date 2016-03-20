package code.akselm.cedric.event;

import code.akselm.cedric.event.types.BasicStimulusEvent;
import code.akselm.cedric.peripherals.Peripheral;
import code.akselm.cedric.users.User;

/**
 * Created by Axel on 3/12/2016.
 */
public class MessageToEvent {

    public static synchronized Event getEvent(String message, User user, Peripheral peripheral){


        return new BasicStimulusEvent(message, user, peripheral);
    }
}
