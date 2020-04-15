package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.AbstractModule;
import com.github.jan222ik.di.own.Client;
import com.github.jan222ik.di.own.Injector;
import com.github.jan222ik.di.own.IModule;
import com.github.jan222ik.di.own.IService;
import com.github.jan222ik.di.own.OurInject;
import com.github.jan222ik.di.own.ServiceA;
import com.github.jan222ik.di.own.ServiceB;
import com.github.jan222ik.di.own.ServiceC;
import com.github.jan222ik.helper.C;
import org.junit.Test;

public class ConstructorInjectionTest extends C {

    @Test
    public void constructorInjection() throws Exception {
        ModuleConstructorInjection mod = new ModuleConstructorInjection();
        Injector injector = new Injector(mod);

        Client instance = injector.createInstanceOf(ClientConstructorInjection.class);
    }

    /**
     * A <b>Client</b> using <b>Constructor</b> Dependency Injection
     */
    public class ClientConstructorInjection implements Client {

        /**
         * <b>Annotated constructor<b/>.
         */
        @OurInject
        public ClientConstructorInjection(ServiceA serviceA, IService serviceChanging) {
            logObj("ClientConstructorInjection",
                    param("serviceA", serviceA),
                    param("serviceChanging", serviceChanging)
            );
        }
    }

    /**
     * A <b>module</b> defines mappings between the Interface and one of its Implementations.
     */
    public class ModuleConstructorInjection extends AbstractModule {
        @Override
        public void configure() {
            // Creates Mapping for service interface
            createMapping(IService.class, ServiceC.class);

            // Other example:
            //createMapping(List.class, LinkedList.class);
        }

    }


    private static final Object[] _imports = {ServiceA.class, ServiceB.class, ServiceC.class};
}
