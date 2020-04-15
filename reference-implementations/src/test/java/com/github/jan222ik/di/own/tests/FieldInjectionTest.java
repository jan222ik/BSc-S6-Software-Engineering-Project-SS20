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

@SuppressWarnings("WeakerAccess")
public class FieldInjectionTest extends C {

    @Test
    public void fieldInjection() throws Exception {
        IModule mappingModule = new ModuleFieldInjection();
        Injector framework = new Injector(mappingModule);

        ClientFieldInjection instance = framework.createInstanceOf(ClientFieldInjection.class);

        logObj("ClientFieldInjection",
                field("serviceA", instance.serviceA),
                field("serviceChanging", instance.serviceChanging)
        );

    }

    public static class ClientFieldInjection implements Client {
        @OurInject
        public ServiceA serviceA;
        @OurInject
        private IService serviceChanging;
    }

    public class ModuleFieldInjection extends AbstractModule {
        @Override
        public void configure() {
            createMapping(IService.class, ServiceC.class);
        }
    }

    private static final Object[] _imports = {ServiceA.class, ServiceB.class, ServiceC.class};
}
