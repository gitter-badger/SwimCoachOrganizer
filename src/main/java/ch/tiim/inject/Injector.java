package ch.tiim.inject;

import ch.tiim.log.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private static final Log LOGGER = new Log(Injector.class);
    private static final Injector INSTANCE = new Injector();
    private HashMap<String, Object> toInject = new HashMap<>();


    private Injector() {
    }

    public void addInjectable(Object o, String name) {
        toInject.put(name, o);
    }

    public void inject(Object o, Map<String, Object> customInjections) {
        try {
            injectFields(o, customInjections);
            callMethod(o);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error while injecting " + o.getClass());
            throw new IllegalStateException(e);
        }
    }

    private void injectFields(Object o, Map<String, Object> customInjections) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Inject a = f.getAnnotation(Inject.class);
            if (a == null) {
                continue;
            }
            f.setAccessible(true);
            if (a.newInstance()) {
                f.set(o, f.getType().newInstance());
            } else {
                Object obj;
                if (customInjections != null && customInjections.containsKey(a.name())) {
                    obj = customInjections.get(a.name());
                } else if (toInject.containsKey(a.name())) {
                    obj = toInject.get(a.name());
                } else {
                    LOGGER.warning("Injectable object with key " + a.name() + " not found.");
                    continue;
                }
                if (f.getType().isInstance(obj)) {
                    f.set(o, obj);
                }
            }
        }

    }

    private void callMethod(Object o) throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = o.getClass();
        try {
            Method method = clazz.getDeclaredMethod("injected");
            method.setAccessible(true);
            Inject i = method.getAnnotation(Inject.class);
            if (i != null) {
                try {
                    method.invoke(o);
                } catch (Exception ex) {
                    LOGGER.warning("Exception in injected callback: ", ex);
                }
            }
        } catch (NoSuchMethodException ignored) {
        }
    }

    public static Injector getInstance() {
        return INSTANCE;
    }
}
