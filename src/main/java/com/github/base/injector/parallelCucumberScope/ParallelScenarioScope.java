package com.github.base.injector.parallelCucumberScope;

import com.github.logging.LoggerInstanceProvider;
import com.google.inject.Key;
import com.google.inject.OutOfScopeException;
import com.google.inject.Provider;
import cucumber.runtime.java.guice.ScenarioScope;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParallelScenarioScope implements ScenarioScope {
    private static final Logger LOGGER = LoggerInstanceProvider.getLogger(ParallelScenarioScope.class);
    private final ThreadLocal<Map<Key<?>, Object>> threadLocalMap = new ThreadLocal<>();

    @Override
    public <T> Provider<T> scope(final Key<T> key, final Provider<T> unscoped) {
        return new Provider<T>() {
            public T get() {
                Map<Key<?>, Object> scopedObjects = getScopedObjectMap(key);

                @SuppressWarnings("unchecked")
                T current = (T) scopedObjects.get(key);
                if (current == null && !scopedObjects.containsKey(key)) {
                    current = unscoped.get();
                    scopedObjects.put(key, current);
                }
                return current;
            }
        };
    }

    private <T> Map<Key<?>, Object> getScopedObjectMap(Key<T> key) {
        Map<Key<?>, Object> map = threadLocalMap.get();
        if (map == null) {
            throw new OutOfScopeException("Cannot access " + key + " outside of a scoping block");
        }
        return map;
    }

    @Override
    public void enterScope() {
        checkState(threadLocalMap.get() == null, "A scoping block is already in progress");
        threadLocalMap.set(new ConcurrentHashMap<Key<?>, Object>());
    }

    @Override
    public void exitScope() {
        checkState(threadLocalMap.get() != null, "No scoping block in progress");
        threadLocalMap.remove();
    }

    private void checkState(boolean expression, String errorMessage) {
        if (!expression) {
            LOGGER.info("M=checkState, Will throw exception: " + errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }
}
