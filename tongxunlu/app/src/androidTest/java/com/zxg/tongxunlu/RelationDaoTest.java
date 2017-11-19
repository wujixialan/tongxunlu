package com.zxg.tongxunlu;

import android.test.AndroidTestCase;
import android.util.Log;
import com.zxg.tongxunlu.dao.RelationDao;
import com.zxg.tongxunlu.dao.impl.RelationDaoImpl;
import com.zxg.tongxunlu.model.Relation;

import java.util.List;

/**
 * Created by zxg on 2017/10/25.
 */
public class RelationDaoTest extends AndroidTestCase {
    public void testAdd() {
        Relation relation = new Relation();
        relation.setName("qww");
        relation.setNumber("18349763333");
        RelationDao relationDao = new RelationDaoImpl(getContext());
        relationDao.add(relation);
    }

    public void testQueryAll() {
        RelationDao relationDao = new RelationDaoImpl(getContext());
        List<Relation> list =  relationDao.queryAll();
        Log.e("TAG", list.toString());
    }

    public void testUpdate() {
        RelationDao relationDao = new RelationDaoImpl(getContext());
        Relation relation = new Relation();
        relation.setId(1);
        relation.setName("无极侠岚111");
        relationDao.update(relation);
    }

    public void testDelete() {
        RelationDao relationDao = new RelationDaoImpl(getContext());
        Relation relation = new Relation();
        relation.setId(2);
        relationDao.delete(relation);
    }

    public void testQuery() {
        RelationDao relationDao = new RelationDaoImpl(getContext());
        Relation relation = new Relation();
        relation.setName("q");
        List<Relation> list = relationDao.query(relation);
        Log.e("TAG", list.toString());
    }
}
