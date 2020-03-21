package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.AbstractModule;
import com.github.jan222ik.di.own.DIFramework;
import com.github.jan222ik.di.own.IModule;
import com.github.jan222ik.di.own.IService;
import com.github.jan222ik.di.own.OurInject;
import com.github.jan222ik.di.own.ServiceA;
import com.github.jan222ik.di.own.ServiceC;
import lombok.Getter;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings({"InnerClassMayBeStatic", "WeakerAccess"})
public class FieldInjectionTest {
    private static final Class<? extends IService> EXPECTING_CLASS_C = ServiceC.class;

    @Test
    public void fieldInjection() throws Exception {
        IModule mappingModule = new ModuleFieldInjection();
        DIFramework framework = new DIFramework(mappingModule);

        ClientFieldInjection instance = framework.createInstanceOf(ClientFieldInjection.class);

        assertNotNull(instance.getServiceA());

        IService serviceChanging = instance.getServiceChanging();
        assertNotNull(serviceChanging);
        assertThat(serviceChanging.getClass(), CoreMatchers.equalTo(EXPECTING_CLASS_C));
    }

    public static class ClientFieldInjection {
        @OurInject
        @Getter private ServiceA serviceA;
        @OurInject
        @Getter private IService serviceChanging;
    }

    public class ModuleFieldInjection extends AbstractModule {
        @Override
        public void configure() {
            //createMapping(IService.class, ServiceA.class);
            createMapping(IService.class, EXPECTING_CLASS_C);
        }
    }
}
