package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.AbstractModule;
import com.github.jan222ik.di.own.Client;
import com.github.jan222ik.di.own.DIFramework;
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
        IModule mappingModule = new ModuleConstructorInjection();
        DIFramework framework = new DIFramework(mappingModule);

        Client instance = framework.createInstanceOf(ClientConstructorInjection.class);
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


    private static final Object[] _imports = {ServiceA.class, ServiceB.class, ServiceC.class};
}
