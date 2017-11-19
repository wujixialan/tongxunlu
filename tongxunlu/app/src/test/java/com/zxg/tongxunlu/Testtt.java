package com.zxg.tongxunlu;

import com.zxg.tongxunlu.model.Relation;

import java.util.Comparator;

/**
 * Created by zxg on 2017/11/2.
 */
public class Testtt implements Comparator<Relation> {
    @Override
    public int compare(Relation o1, Relation o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
