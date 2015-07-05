package lesson12;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static lesson12.AkkaUtils.msg;

/**
 * Created by Sergius on 29.06.2015.
 */
public class MRContainer extends UntypedActor {
    public static final int BUCKET_SIZE = 16;

    private final ActorRef[] buckets = new ActorRef[BUCKET_SIZE];
    private long lastId = 0;
    private final Map<Long, Object> results = new HashMap<>();
    private final Map<Long, Reducer> reducers = new HashMap<>();
    private final Map<Long, Integer> counts = new HashMap<>();
    private final Map<Long, ActorRef> callbacks = new HashMap<>();

    public MRContainer() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = getContext().actorOf(Props.create(MRBucket.class), "bucket-" + i);
        }
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = null;
        if (asList("put", "get", "remove", "get/result").contains(command)) {
            key = (String) msgArr[1];
        }
        switch (command) {
            case "put":
            case "remove":
                buckets[key.hashCode() % 16].tell(msg, getSelf());
                break;
            case "get":
                Object[] nextGet = {"get", key, getSender()};
                buckets[key.hashCode() % 16].tell(nextGet, getSelf());
                break;
            case "get/result":
                String value = (String) msgArr[2];
                ActorRef originalSender = (ActorRef) msgArr[3];
                originalSender.tell(msg("get/result", key, value), getSelf());
                break;
            case "map-reduce":
                Object[] newMsg = {msgArr[0], ++lastId, msgArr[1], msgArr[2], msgArr[3]};
                for (ActorRef bucket : buckets) {
                    bucket.tell(newMsg, getSelf());
                }
                results.put(lastId, msgArr[3]);
                reducers.put(lastId, (Reducer) msgArr[2]);
                counts.put(lastId, 0);
                callbacks.put(lastId, getSender());
                break;
            case "map-reduce/result":
                Long id = (Long) msgArr[1];
                counts.put(lastId, counts.get(id) + 1);
                Object oldResult = results.get(id);
                Object newResult = msgArr[2];
                results.put(id, reducers.get(id).reduce(oldResult, newResult));

                if (counts.get(id) == BUCKET_SIZE) {
                    ActorRef originalAsker = callbacks.get(id);
                    originalAsker.tell(results.get(id), getSelf());
                    counts.remove(id);
                    reducers.remove(id);
                    results.remove(id);
                    callbacks.remove(id);
                }
                break;
        }
    }
}
