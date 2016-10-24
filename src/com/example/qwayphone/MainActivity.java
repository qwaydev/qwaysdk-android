package com.example.qwayphone;

import qway.logininterface.QwayClientListener;



import com.qway.util.QwayPhone;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity{
	private EditText et_appId,et_memberid,et_memberkey;
	private RelativeLayout login_btn_layout;
	private Context mContext;
	private String appid="a2c29623ef7435e35426175f4bd755d0";
	private String memberid,key;
	private int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        initView();
    }
    private void initView(){
    	et_appId=(EditText)findViewById(R.id.et_appId);
    	et_memberid=(EditText)findViewById(R.id.et_memberid);
    	et_memberkey=(EditText)findViewById(R.id.et_memberkey);
    	login_btn_layout=(RelativeLayout)findViewById(R.id.login_btn_layout);
    	login_btn_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i==1){memberid="test01@qq.com";key="65c47523b3284f74";}else{
					memberid="test02@qq.com";key="6ed2a1a6a1e690bf";
				}
	      QwayPhone.loginSdk(appid,memberid ,key,new QwayClientListener() {
					
					@Override
					public void onConnectionSuccessful() {
						// TODO Auto-generated method stub
						QwayPhone.startPhoneService(MainActivity.this, AnswerCallActivity.class);
					    startActivity(new Intent(MainActivity.this,DialTelephoneActivity.class));
					    finish();	
					}
					
					@Override
					public void onConnectionFailed(String error) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "失败原因"+error, 1).show();
					}
		});
				
			}
		});

}
    }
