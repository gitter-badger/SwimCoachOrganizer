package ch.tiim.inject;

import ch.tiim.log.Log;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Injector {
    private static final Log LOGGER = new Log(Injector.class);
    private static final Injector INSTANCE = new Injector();
    private HashMap<String, Object> toInject = new HashMap<>();


    private Injector() {
    }

    public static Injector getInstance() {
        return INSTANCE;
    }

    public void addInjectable(Object o, String name) {
        toInject.put(name, o);
    }

    private void injectFields(Object o) throws IllegalAccessException, InstantiationException {
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
                Object obj = toInject.get(a.name());
                if (f.getType().isInstance(obj)) {
                    f.set(o, obj);
                }
            }
        }

    }

    public void inject(Object o) {
        try {
            injectFields(o);
            callMethod(o);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error while injecting " + o.getClass());
            throw new IllegalStateException(e);
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
}
