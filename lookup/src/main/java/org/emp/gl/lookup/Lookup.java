package org.emp.gl.lookup;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Lookup {

    final static public String SERVICE_REGESTIRED = "Service regestired";
    final static public String SERVICE_REQUESTED = "Service requested";
    final static public String SERVICE_DELETED = "Service deleted";

    public interface ServiceInfo {

        public String getServiceName();
        public int getServicePriority();
    }

    static private Lookup INSTANCE;

    private Lookup() {
    }

    static public Lookup getLookup() {
        if (INSTANCE == null) {
            INSTANCE = new Lookup();
        }
        return INSTANCE;
    }

    private  Map<Class, ArrayList<Object>> serv = new HashMap<>();
    private  PropertyChangeSupport pce = new PropertyChangeSupport(this);

    public <T> void register(Class<? super T> service, T instance) {
        if (ServiceInfo.class.isInstance(instance)) {
            if (serv.containsKey(service)) {
                serv.get(service).add(instance);
            } else {
                ArrayList<Object> newList = new ArrayList<>();
                newList.add(instance);
                serv.put(service, newList);
            }
            pce.firePropertyChange(SERVICE_REGESTIRED, null, service);
        } else {
            System.out.println("It must implment Lookup.ServiceInfo interface");
        }
    }

    public <T> T getService(Class<T> service) {
        ArrayList<T> instances = (ArrayList<T>) serv.get(service);
        int max = 0;
        T instanceMaxPriority = null;
        for (T instance : instances) {
            int p = ((ServiceInfo) instance).getServicePriority();
            if (p > max) {
                instanceMaxPriority = instance;
                max=p;
            }
        }
        pce.fireIndexedPropertyChange(SERVICE_REQUESTED, max, service, service);
        return instanceMaxPriority;
    }

    public <T> ArrayList<T> getAllServices(Class<T> service) {
        
        ArrayList<T> instances = (ArrayList<T>) serv.get(service);
        pce.firePropertyChange(SERVICE_REQUESTED, null, service);

        return instances;
    }

    public <T> void unRegister(Class<? super T> service) {
        serv.remove(service);
        pce.firePropertyChange(SERVICE_DELETED, null, service);
    }

    public void addListener(PropertyChangeListener l) {
        pce.addPropertyChangeListener(l);
    }

    public void removeListener(PropertyChangeListener l) {
        pce.removePropertyChangeListener(l);
    }
}
