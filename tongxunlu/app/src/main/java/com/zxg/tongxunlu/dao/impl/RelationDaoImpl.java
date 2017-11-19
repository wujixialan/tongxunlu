package com.zxg.tongxunlu.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.zxg.tongxunlu.dao.RelationDao;
import com.zxg.tongxunlu.model.Relation;
import com.zxg.tongxunlu.utils.DbHelper;
import com.zxg.tongxunlu.utils.LocationUtils;
import com.zxg.tongxunlu.utils.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxg
 * @date 2017/10/23
 */
public  class RelationDaoImpl implements RelationDao {
    private DbHelper dbHelper;

    public RelationDaoImpl(Context context) {
        this.dbHelper = new DbHelper(context);
    }

    @Override
    public void add(Relation relation) {
        relation.setLocation(LocationUtils.location(relation.getNumber()));
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", relation.getName());
        values.put("number", relation.getNumber());
        values.put("location", relation.getLocation());
        long id = database.insert("relation", null, values);
        relation.setId((int) id);
        Log.i("TAG", "add = " + id);
        dbHelper.close();
    }

    @Override
    public void update(Relation relation) {
        relation.setLocation(LocationUtils.location(relation.getNumber()));
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", relation.getName());
        values.put("number", relation.getNumber());
        values.put("location", relation.getLocation());
        database.update("relation", values, "_id=?", new String[]{relation.getId() + ""});
    }

    @Override
    public void delete(Relation relation) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete("relation", "_id=?", new String[]{relation.getId() + ""});
    }

    @Override
    public List<Relation> queryAll() {
        List<Relation> relations = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("relation", null, null, null, null,
                null, " name");
        while (cursor.moveToNext()) {
            Relation re = new Relation();
            re.setId(cursor.getInt(0));
            re.setName(cursor.getString(1));
            re.setNumber(cursor.getString(2));
            re.setLocation(cursor.getString(3));

            relations.add(re);
        }
        Sort sort = new Sort();
        sort.SortByName(relations);
        return relations;
    }

    @Override
    public List<Relation> query(Relation relation) {
        List<Relation> relations = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("relation", null, "name like ? or number like ?",
                new String[]{"%" + relation.getName() + "%", "%" + relation.getNumber() + "%"}, null,
                null, " name");
        while (cursor.moveToNext()) {
            Relation relation1 = new Relation();
            relation1.setId(cursor.getInt(0));
            relation1.setName(cursor.getString(1));
            relation1.setNumber(cursor.getString(2));
            relation1.setLocation(cursor.getString(3));

            relations.add(relation1);
        }

        Sort sort = new Sort();
        sort.SortByName(relations);
        System.out.println(relations.size());
        return relations;
    }
}
