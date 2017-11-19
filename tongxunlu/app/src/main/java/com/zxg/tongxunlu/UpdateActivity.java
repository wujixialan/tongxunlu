package com.zxg.tongxunlu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.zxg.tongxunlu.dao.RelationDao;
import com.zxg.tongxunlu.dao.impl.RelationDaoImpl;
import com.zxg.tongxunlu.model.Relation;

/**
 * @author 无极侠岚
 */
public class UpdateActivity extends AppCompatActivity {
    private EditText telNumber;
    private EditText nameText;
    private RelationDao relationDao;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        telNumber = (EditText) findViewById(R.id.telNumber);
        nameText = (EditText) findViewById(R.id.name);

        /**
         * 通过获得意图，得到对应的参数
         */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");
        String name = bundle.getString("name");
        String number = bundle.getString("number");
        nameText.setText(name);
        telNumber.setText(number);
    }

    /**
     * 更新的回调函数
     * @param v
     */
    public void update(View v) {
        relationDao = new RelationDaoImpl(this);
        Relation relation = new Relation();
        String name = nameText.getText().toString();
        String number = telNumber.getText().toString();
        relation.setId(id);
        relation.setName(name);
        relation.setNumber(number);
        /**
         * 提交数据，并进行更新
         */
        relationDao.update(relation);
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
