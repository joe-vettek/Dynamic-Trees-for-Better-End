package xueluoanping.dtbetterend.util;

import java.util.function.Supplier;

public  class LazyGet<T> {
    private final Supplier<T> cache;
    private T lock;

    private LazyGet(Supplier<T> supplier) {
        this.cache = supplier;
    }

    public static <T> LazyGet<T> of(Supplier<T> supplier) {
        return new LazyGet<>(supplier);
    }

    public T get() {
        if (lock == null)
            lock = cache.get();
        return lock;
    }
}
