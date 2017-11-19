package com.zxg.tongxunlu;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import com.zxg.tongxunlu.dao.RelationDao;
import com.zxg.tongxunlu.dao.impl.RelationDaoImpl;
import com.zxg.tongxunlu.model.Relation;

import java.util.List;

/**
 * @author 无极侠岚
 */
public class MainActivity extends ListActivity {
    private ListView mainView;
    private RelationDao relationDao;
    private List<Relation> relationList;
    private RelationAdapter relationAdapter;
    /**
     * 存储某个对象在 list 中的位置
     */
    private int position;
    /**
     * 搜索的文本框
     */
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = getListView();
        search = (EditText) findViewById(R.id.search);
        relationAdapter = new RelationAdapter();
        relationDao = new RelationDaoImpl(this);
        relationList = relationDao.queryAll();
        mainView.setAdapter(relationAdapter);

        /**
         * 设置 EditText 内容改变的监听器
         */
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String hint = search.getText().toString();
                Relation relation = new Relation();
                relation.setName(hint);
                relation.setNumber(hint);
                relationDao = new RelationDaoImpl(MainActivity.this);
                relationList = relationDao.query(relation);
                /**
                 * 告诉适配器，数据发生了改变，需要进行校正
                 */
                relationAdapter.notifyDataSetChanged();
            }
        });
        mainView.setOnCreateContextMenuListener(this);
    }

    /**
     * 对列表中的item点击触发添加事件
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 1, Menu.NONE, "更新");
        menu.add(Menu.NONE, 3, Menu.NONE, "查看联系人");
        menu.add(Menu.NONE, 2, Menu.NONE, "删除");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final Relation relation = relationList.get(position);
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", relation.getId());
                bundle.putString("name", relation.getName());
                bundle.putString("number", relation.getNumber());
                intent.putExtras(bundle);
                finish();
                startActivityForResult(intent, 1);
                break;
            case 2:
                /**
                 * 创建一个确认框
                 */
                new AlertDialog.Builder(this)
                        .setTitle("删除确认")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                relationDao.delete(relation);
                                relationList.remove(position);
                                relationAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
                break;
            case 3:
                /**
                 * 创建一个意图
                 */
                Intent intent1 = new Intent(MainActivity.this, ContractDialog.class);
                /**
                 * 为意图绑定数据
                 */
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", relationList.get(position).getName());
                bundle1.putString("number", relationList.get(position).getNumber());
                intent1.putExtras(bundle1);
                finish();
                startActivityForResult(intent1, 1);
                break;

            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void add(View v) {
        finish();
        startActivity(new Intent(this, DialogRelation.class));
    }

    class RelationAdapter extends BaseAdapter {

        /**
         * 要绑定的条目的数目
         * @return
         */
        @Override
        public int getCount() {
            return relationList.size();
        }

        /**
         * 根据一个索引（位置）获得该位置的对象
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return relationList.get(position);
        }

        /**
         * 获取条目的id
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 获取该条目要显示的界面
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }
            Relation relation = relationList.get(position);
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            if (relation.getName().trim().equals("")) {
                tv.setText(relation.getNumber() + "\n" + relation.getNumber() + "  " + relation.getLocation());
            } else if (relation.getNumber().trim().equals("")) {
                tv.setText(relation.getName() + "\n 无号码");
            } else {
                tv.setText(relation.getName() + "\n" + relation.getNumber() + "  " + relation.getLocation());
            }
            return convertView;
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return super.onKeyUp(keyCode, event);
        }
        return super.onKeyUp(keyCode, event);
    }
}
