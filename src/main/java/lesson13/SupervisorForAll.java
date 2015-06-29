package lesson13;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * Created by Sergius on 29.06.2015.
 */
public class SupervisorForAll  extends UntypedActor {

    private static SupervisorStrategy strategy =
            new AllForOneStrategy(10, Duration.create("1 minute"),
                    new Function<Throwable, SupervisorStrategy.Directive>() {
                        @Override
                        public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
                            if (t instanceof ArithmeticException) {
                                return SupervisorStrategy.resume(); //
                            } else if (t instanceof NullPointerException) {
                                return SupervisorStrategy.restart();
                            } else if (t instanceof IllegalArgumentException) {
                                return SupervisorStrategy.stop();
                            } else {
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Props) {
            ActorRef response = getContext().actorOf((Props) msg);
            getSender().tell(response, getSelf());
        } else {
            unhandled(msg);
        }
    }
}