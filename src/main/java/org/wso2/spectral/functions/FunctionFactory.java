package org.wso2.spectral.functions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;

public class FunctionFactory {
    private static final Map<String, Class<? extends LintFunction>> functionRegistry = new HashMap<>();

    // Static block to initialize the registry
    static {
        registerFunctions("org.wso2.spectral.functions.core"); // Replace with your package
    }

    private static void registerFunctions(String packageName) {
        Reflections reflections = new Reflections(packageName);
        for (Class<?> clazz : reflections.getTypesAnnotatedWith(FunctionName.class)) {
            FunctionName annotation = clazz.getAnnotation(FunctionName.class);
            functionRegistry.put(annotation.value(), clazz.asSubclass(LintFunction.class));
        }
    }

    public static LintFunction getFunction(String functionName, Map<String, Object> functionOptions) {
        Class<? extends LintFunction> functionClass = functionRegistry.get(functionName.toLowerCase());
        if (functionClass == null) {
            throw new IllegalArgumentException("Unknown shape type: " + functionName);
        }
        try {
            return (LintFunction) functionClass.getDeclaredConstructors()[0].newInstance(functionOptions);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error creating shape instance", e);
        }
    }
}
