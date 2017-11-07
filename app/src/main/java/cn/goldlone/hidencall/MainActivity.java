package cn.goldlone.hidencall;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText phoneNumET;
    private Button callBtn;
//    private ShowPref pref = null;
//    private int mShowType = ShowPref.TYPE_HALF_DIALOG_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phoneNumET = (EditText) findViewById(R.id.et_phone_num);
        callBtn = (Button) findViewById(R.id.btn_call);
        callBtn.setOnClickListener(this);

//        pref = ShowPref.getInstance(this);
//        mShowType = pref.loadInt(ShowPref.SHOW_TYPE);
//        pref.putInt(ShowPref.SHOW_TYPE, ShowPref.TYPE_FULL_DIALOG);
    }

    @Override
    public void onClick(View view) {
        String phoneNum = phoneNumET.getText().toString();
        Log.e("call", "拨号："+phoneNum);
        Utils.call(this, phoneNum);
    }
}
