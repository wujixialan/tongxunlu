package com.zxg.tongxunlu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import com.zxg.tongxunlu.dao.RelationDao;
import com.zxg.tongxunlu.dao.impl.RelationDaoImpl;
import com.zxg.tongxunlu.model.Relation;

/**
 * @author 无极侠岚
 */
public class DialogRelation extends AppCompatActivity implements TextWatcher {
    private EditText name;
    private EditText telNumber;
    private RelationDao relationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_relation);

        name = (EditText) findViewById(R.id.name);
        telNumber = (EditText) findViewById(R.id.telNumber);

        name.addTextChangedListener(this);
        telNumber.addTextChangedListener(this);

    }

    public void cancel(View v) {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * 点击确定的回调函数
     * @param v
     */
    public void ok(View v) {
        String nameStr = name.getText().toString();
        String telNumberStr = telNumber.getText().toString();

        if (nameStr.trim().length() == 0 && telNumberStr.trim().length() == 0) {
            startActivity(new Intent(this, MainActivity.class));
        }
        Relation relation = new Relation();
        relation.setName(nameStr);
        relation.setNumber(telNumberStr);
        relationDao = new RelationDaoImpl(this);
        relationDao.add(relation);
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Button btn_finish = (Button) findViewById(R.id.btn_finish);
        if ((!name.getText().toString().trim().equals("")) ||
                (!telNumber.getText().toString().trim().equals(""))) {
            btn_finish.setEnabled(true);
        } else {
            btn_finish.setEnabled(false);
        }
    }
}
