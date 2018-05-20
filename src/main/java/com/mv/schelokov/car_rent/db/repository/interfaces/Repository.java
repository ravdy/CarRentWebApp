package com.mv.schelokov.car_rent.db.repository.interfaces;

import java.util.Map;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 * @param <T> entity class
 */
public interface Repository<T> {
    
    boolean add(T item);

    boolean add(Iterable<T> items);

    boolean update(T item);

    boolean remove(T item);

    boolean remove(Criteria criteria);

    Map<Integer, T> query(Criteria criteria);
    
}