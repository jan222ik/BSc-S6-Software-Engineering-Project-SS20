package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.DIFramework;
import com.github.jan222ik.di.own.IService;
import com.github.jan222ik.di.own.InjectInterface;
import com.github.jan222ik.di.own.InjectServiceInterface;
import com.github.jan222ik.di.own.ServiceA;
import lombok.Getter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static junit.framework.TestCase.assertNotNull;

@SuppressWarnings("InnerClassMayBeStatic")
public class InterfaceInjectionTest {
    @Test
    public void interfaceInjection() {
        DIFramework framework = new DIFramework();
        framework.setInterfaceInjects(genMappings());


        ClientInterfaceInjection instance = new ClientInterfaceInjection();
        framework.injectInterface(instance);

        assertNotNull(instance.getService());
    }

    public class ClientInterfaceInjection implements InjectServiceInterface {

        @Getter
        private IService service;

        @Override
        public void setService(IService service) {
            this.service = service;
        }
    }

    private Map<Class<? extends InjectInterface>, Consumer<? extends InjectInterface>> genMappings() {
        HashMap<Class<? extends InjectInterface>, Consumer<? extends InjectInterface>> map = new HashMap<>();
        map.put(InjectServiceInterface.class, o -> {
            final IService service = new ServiceA();
            ((InjectServiceInterface) o).setService(service);
        });
        return map;
    }
}
