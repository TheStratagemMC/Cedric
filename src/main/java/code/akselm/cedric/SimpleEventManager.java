package code.akselm.cedric;

import code.akselm.cedric.event.Event;
import code.akselm.cedric.event.EventPriority;
import code.akselm.cedric.event.Stimulus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Axel on 3/2/2016.
 */
public class SimpleEventManager implements EventManager{
    public Set<Object> listeners = new HashSet<>();

    @Override
    public void registerListener(Object listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Object listener) {
        listeners.remove(listener);
    }

    @Override
    public void unregisterAllListeners() {
        listeners.clear();
    }

    @Override
    public void callEvent(Event event) {
        HashMap<EventPriority, HashSet<Exec>> map = new HashMap<>();

        for (Object obj : listeners){


            for (Method m : obj.getClass().getMethods()){
                if (m.getParameterTypes().length < 1) continue;

                if (m.isAnnotationPresent(Stimulus.class)){

                    Stimulus s = m.getAnnotation(Stimulus.class);
                    HashSet<Exec> list;
                    if (map.containsKey(s.priority())) list = map.get(s.priority());
                    else list = new HashSet<>();

                    list.add(new Exec(obj, m));
                    map.put(s.priority(), list);
                }
            }


        }

        execute(event, EventPriority.LOW, map);
        execute(event, EventPriority.MEDIUM, map);
        execute(event, EventPriority.HIGH, map);
        execute(event, EventPriority.MONITOR, map);
    }

    public class Exec{
        Object obj;
        Method method;

        public Exec(Object obj, Method method) {
            this.obj = obj;
            this.method = method;
        }

        public Object getObj() {
            return obj;
        }

        public Method getMethod() {
            return method;
        }
    }
    public void execute(Event event, EventPriority priority, HashMap<EventPriority, HashSet<Exec>> map){
        if (map.containsKey(priority)){
            for (Exec e : map.get(priority)){
                try{
                    Method m = e.getMethod();
                    m.setAccessible(true);
                    m.invoke(e.getObj(), event);
                }catch(Exception ex){
                   // ex.printStackTrace();
                }
            }
        }
    }
}
