package org.emp.gl.launcher;

import java.util.ArrayList;
import org.emp.gl.logger.ConsoleLogger;
import org.emp.gl.lookup.Lookup;
import org.emp.gl.serviceA.ServiceA;
import org.emp.gl.serviceAimpl.ServiceAImpl;
import org.emp.gl.serviceB.ServiceB;
import org.emp.gl.serviceBimpl.ServiceBImpl;

/**
 * Hello world!
 *
 */
public class MainApp {

    public static void main(String[] args) {
        //Add consoleLogger as a listner 
        Lookup.getLookup().addListener(new ConsoleLogger());
        //ServiceA implements Lookup.ServiceInfo (getServiceName() and  ...)
        Lookup.getLookup().register(ServiceA.class, new ServiceAImpl());
        //ServiceB does'nt implemtn Lookup.ServiceInfo 
        Lookup.getLookup().register(ServiceB.class, new ServiceBImpl());

        ArrayList<ServiceA> list = Lookup.getLookup().getAllServices(ServiceA.class);

        list.get(0).getServiceName();
        
        list.get(0).doSomething();
        System.out.println("The Name of ServiceImpl that implments ServiceA  with the highest Priority : "+
                            Lookup.getLookup().getService(ServiceA.class).getServiceName()+
                            "\n The Priorty of this Class is : "+
                            Lookup.getLookup().getService(ServiceA.class).getServicePriority());
        Lookup.getLookup().getService(ServiceA.class).doAnotherThing();
    }
}
