package com.zxg.tongxunlu;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 无极侠岚
 */
public class ContractDialog extends AppCompatActivity {
    private TextView et_main_number;
    private Button btn_main_call;
    private Button btn_main_send;
    private EditText et_main_sms;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btn_main_call) {
                String action = Intent.ACTION_CALL;
                Intent intent = new Intent(action);
                String number = et_main_number.getText().toString();
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
            if (v == btn_main_send) {
                SmsManager smsManager = SmsManager.getDefault();
                /**
                 * 发送文本信息
                 */
                String number = et_main_number.getText().toString();
                number = number.substring(number.indexOf("(") + 1, number.indexOf(")"));
                String sms_content = et_main_sms.getText().toString();
                String SENT_SMS_ACTION = "SENT_SMS_ACTION";
                Intent sentIntent = new Intent(SENT_SMS_ACTION);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ContractDialog.this, 0, sentIntent, 0);

                /**
                 * destinationAddress：收信人的手机号码
                 * scAddress：发信人的手机号码
                 * sms_content：发送信息的内容
                 * pendingIntent：发送是否成功的回执，用于监听短信是否发送成功。
                 * deliveryIntent：接收是否成功的回执，用于监听短信对方是否接收成功。
                 */
                smsManager.sendTextMessage(number, null, sms_content, pendingIntent, null);


                /**
                 * 对短信是否发送成功进行了验证。
                 */
                ContractDialog.this.registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                Toast.makeText(context, "信息已发出", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(context, "未指定失败 \n 信息未发出，请重试", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(context, "无线连接关闭 \n 信息未发出，请重试", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(context, "PDU失败 \n 信息未发出，请重试", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                    }
                }, new IntentFilter(SENT_SMS_ACTION));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_dialog);
        et_main_number = (TextView) findViewById(R.id.et_main_number);
        et_main_sms = (EditText) findViewById(R.id.et_main_sms);
        btn_main_call = (Button) findViewById(R.id.btn_main_call);
        btn_main_send = (Button) findViewById(R.id.btn_main_send);

        /**
         * 创建视图，绑定参数
         */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String number = bundle.getString("number");

        et_main_number.setText(name + "(" + number + ")");

        /**
         * 添加文本改变的监听器，当文本为空时，设置发送短信的按钮为不可点击状态，
         * 当文本不为空时，设置发送短信的按钮为可点击状态
         */
        et_main_sms.addTextChangedListener(new TextWatcher() {
            /**
             * 文本内容发生之前，触发事件
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * 文本内容发生改变时，触发事件
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             * 文本内容发生改变之后，触发事件
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                if (!et_main_sms.getText().toString().trim().equals("")) {
                    btn_main_send.setEnabled(true);
                } else {
                    btn_main_send.setEnabled(false);
                }
            }
        });

        /**
         * 设置点击事件的监听器
         */
        btn_main_call.setOnClickListener(onClickListener);
        btn_main_send.setOnClickListener(onClickListener);
    }



    public void back(View v) {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
