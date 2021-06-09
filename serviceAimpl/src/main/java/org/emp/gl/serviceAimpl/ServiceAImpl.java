package org.emp.gl.serviceAimpl;

import org.emp.gl.lookup.Lookup;
import org.emp.gl.serviceA.ServiceA;

/**
 * Hello world!
 *
 */
public class ServiceAImpl  implements ServiceA
{

    static private int priority=3;
    @Override
    public void doSomething() {
        System.out.println("this Service A doing Something");
    }

    @Override
    public void doAnotherThing() {
        System.out.println("this Service A doing Something else");
    }

    @Override
    public String getServiceName() {
        System.out.println(this.getClass().getName());
        return this.getClass().getName();
    }

    @Override
    public int getServicePriority() {
        return priority;
    }
   
}
