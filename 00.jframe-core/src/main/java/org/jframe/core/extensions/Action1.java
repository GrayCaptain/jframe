package org.jframe.core.extensions;

/**
 * Created by screw on 2017/5/19.
 */
@FunctionalInterface
public interface Action1<T> {
    void apply(T item);
}
