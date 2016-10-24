package com.example.qwayphone;

import com.qway.util.QwayCall;

import com.qway.util.QwayData;
import com.qway.util.QwayPhone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialTelephoneActivity  extends Activity {
	private EditText usercode_edt;
	private RelativeLayout rl_call,rl_disconnect;
	private TextView tv_VerSion;
	private Context c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	this.setContentView(R.layout.activity_dialtelephone);
	c=this;
	initView();
	QwayCall.loginServerSip();
	}
	private void initView(){
		usercode_edt=(EditText)findViewById(R.id.usercode_edt);
		rl_call=(RelativeLayout)findViewById(R.id.rl_call);
		rl_disconnect=(RelativeLayout)findViewById(R.id.rl_disconnect);
		tv_VerSion=(TextView)findViewById(R.id.tv_VerSion);
		tv_VerSion.setText("当前版本:"+QwayData.getVersionNumber());
		rl_call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(usercode_edt!=null&&!usercode_edt.getText().toString().equals("")&&QwayPhone.getServerStatus()){
					Intent intent=new Intent(DialTelephoneActivity.this,DialCallActivity.class);
					intent.putExtra("number", usercode_edt.getText().toString());
					startActivity(intent);
				}
			}
		});
		rl_disconnect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				QwayPhone.disconnect(c);
				startActivity(new Intent(DialTelephoneActivity.this,MainActivity.class));
				finish();
				}
			
		});
		
		
	}

}
