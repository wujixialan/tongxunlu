package com.zxg.tongxunlu.utils;

import com.zxg.tongxunlu.model.Relation;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author zxg
 * @date 2017/11/2
 * 用于对list中的对象按照某个属性排序
 */
public class Sort implements Comparator<Relation> {
    public Collator collator = Collator.getInstance(java.util.Locale.CHINA);
	@Override
	public int compare(Relation p1, Relation p2) {
		return collator.getCollationKey(p1.getName()).compareTo(collator.getCollationKey(p2.getName()));
	}

	public void SortByName(List<Relation> list){
		Sort sort = new Sort();
		Collections.sort(list, sort);
	}
}
