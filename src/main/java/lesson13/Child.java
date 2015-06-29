package lesson13;

import akka.actor.UntypedActor;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Child extends UntypedActor {
    private int state = 0;

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Exception) {
            throw (Exception) o;
        } else if (o instanceof Integer) {
            state = (Integer) o;
        } else if (o.equals("get")) {
            getSender().tell(state, getSelf());
        } else {
            unhandled(o);
        }
    }
}
