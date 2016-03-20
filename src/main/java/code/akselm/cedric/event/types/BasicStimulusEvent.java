package code.akselm.cedric.event.types;

import code.akselm.cedric.event.Event;
import code.akselm.cedric.peripherals.Peripheral;
import code.akselm.cedric.users.User;

/**
 * Created by Axel on 3/12/2016.
 */
public class BasicStimulusEvent implements Event {
    String message;
    User user;
    Peripheral peripheral;

    public BasicStimulusEvent(String message, User user, Peripheral peripheral) {
        this.message = message;
        this.user = user;
        this.peripheral = peripheral;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public Peripheral getPeripheral() {
        return peripheral;
    }
}
