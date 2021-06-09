package org.emp.gl.logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.emp.gl.lookup.Lookup;

/**
 * Hello world!
 *
 */
public class ConsoleLogger implements PropertyChangeListener {

    public void newServiceIsRegistred(Class service, Object instance) {
        System.out.println("- A new Service is r;egistred");

    }

    public void newServiceIsRequested(Class service, Object returnedInstance) {
        System.out.println("- A new Service is requested");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Lookup.SERVICE_REGESTIRED:
                System.out.println("- A new Service is r;egistred");
                break;
            case Lookup.SERVICE_REQUESTED:
                System.out.println("- A new Service is requested");
                break;
            case Lookup.SERVICE_DELETED:
                System.out.println("- A Service is Deleted");
                break;
            default:
                break;

        }
    }

}
