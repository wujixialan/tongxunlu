package com.zxg.tongxunlu.dao;

import com.zxg.tongxunlu.model.Relation;

import java.util.List;

/**
 *
 * @author zxg
 * @date 2017/10/23
 */
public interface RelationDao {
    /**
     * 向通讯录中添加联系人
     * @param relation
     */
    public void add(Relation relation);

    /**
     * 更新选中的联系人
     * @param relation
     */
    public void update(Relation relation);

    /**
     * 删除某个联系人
     * @param relation
     */
    public void delete(Relation relation);

    /**
     * 查询所有的联系人
     * @param relation
     * @return
     */
    public List<Relation> queryAll();

    /**
     * 根据条件，查询联系人
     * @param relation
     * @return
     */
    public List<Relation> query(Relation relation);
}
