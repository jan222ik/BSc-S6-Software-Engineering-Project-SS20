package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.Injector;
import com.github.jan222ik.di.own.IService;
import com.github.jan222ik.di.own.InjectServiceInterface;
import com.github.jan222ik.di.own.InterfaceMappings;
import com.github.jan222ik.di.own.ServiceA;
import com.github.jan222ik.di.own.ServiceB;
import com.github.jan222ik.di.own.ServiceC;
import com.github.jan222ik.helper.C;
import org.junit.Test;

public class InterfaceInjectionTest extends C {
    @Test
    public void interfaceInjection() {
        Injector injector = new Injector();
        injector.setInterfaceInjects(new InterfaceMapping());


        ClientInterfaceInjection instance = new ClientInterfaceInjection();
        injector.injectInterface(instance);

        logObj("ClientInterfaceInjection",
                field("service", instance.service)
        );

        ClientInterfaceInjection instance2 = new ClientInterfaceInjection();
        injector.injectInterface(instance2);

        logObj("Comparison",
                fieldV("instance1", instance.service),
                fieldV("instance2", instance2.service)
        );
    }

    public class ClientInterfaceInjection implements InjectServiceInterface {

        private IService service;

        @Override
        public void setService(IService service) {
            this.service = service;
        }
    }

    public class InterfaceMapping extends InterfaceMappings {
        @Override
        public void configure() {
            final IService service = new ServiceB();

            createMapping(InjectServiceInterface.class, o -> ((InjectServiceInterface) o).setService(service));
        }
    }


    private static final Object[] _imports = {ServiceA.class, ServiceB.class, ServiceC.class};
}
