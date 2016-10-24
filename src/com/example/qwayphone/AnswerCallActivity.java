package com.example.qwayphone;

import java.io.Serializable;







import org.linphone.LinphoneManager;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;

import qway.logininterface.QwayCallListener;

import com.qway.application.QVGlobalAp;
import com.qway.im.QVClient;
import com.qway.im.event.IObserver;
import com.qway.util.QwayCall;
import com.qway.util.QwayPhone;


import com.qway.util.VibratorUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 接听电话通话界面
 * @author Administrator
 *
 */

public class AnswerCallActivity extends Activity  implements Serializable,QwayCallListener {
	private static final String TAG="AnswerCallActivity";
	private TextView tv_name;
	private RelativeLayout rl_hub,rl_answer;
	private String mCallOutNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_answercall);
		
		initView();
		Bundle bundle = this.getIntent().getExtras();

		if (bundle != null) {
			mCallOutNum = bundle.getString("QCHATNO");
		} else {
			LinphoneCore lc = LinphoneManager.getLc();
			LinphoneCall currentCall = lc.getCurrentCall();
			if (currentCall != null && currentCall.getRemoteAddress() != null)
				mCallOutNum = currentCall.getRemoteAddress().getUserName();
		}
		if (mCallOutNum == null || mCallOutNum.length() <= 0) {
			finish();
		}
		tv_name.setText(mCallOutNum);
	}
	private void initView(){
		tv_name=(TextView)findViewById(R.id.tv_name);
		rl_hub=(RelativeLayout)findViewById(R.id.rl_hub);
		rl_answer=(RelativeLayout)findViewById(R.id.rl_answer);
		rl_hub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			QwayCall.hangUp();
			finish();
			}
		});
		rl_answer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (LinphoneManager.getLc().getCallsNb() >= 1) {
					Intent intent = new Intent();
					intent.setClass(AnswerCallActivity.this,
							AnswePhoneInCallActivity.class);
					intent.putExtra("PHONENUM", mCallOutNum);
					startActivity(intent);
					finish();

				} else {
					finish();
				}
				
			}
		});
		
	}

		
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		QwayPhone.reginteredCallListerner(this,this);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void inIsTalking() {
		// 正在通话中

		VibratorUtil.Vibrate(this, 300);
		finish();
		
		
	}
	@Override
	public void onBusy() {
		// 对方忙

		VibratorUtil.Vibrate(this, 300);
		finish();
		
	}
	@Override
	public void onCallAlerting() {
		
		
	}
	@Override
	public void onCallEnd() {
		// 通话结束
		VibratorUtil.Vibrate(this, 300);
		finish();
	}
	@Override
	public void onCallOut() {
		// 呼出
		
	}
	@Override
	public void onHub() {
		// 挂断电话
		VibratorUtil.Vibrate(this, 300);
		finish();
	}
	@Override
	public void onIncomingCall() {
		// 有新的来电
		
	}
	@Override
	public void onNoAnswer() {
		// 无人接听
		VibratorUtil.Vibrate(this, 300);
		finish();
		
	}
	@Override
	public void onRefuseAnswer() {
		// 拒绝接听
		VibratorUtil.Vibrate(this, 300);
		finish();
	}
	@Override
	public void onMakeCall() {
		// 建立通话
		VibratorUtil.Vibrate(this, 300);
	}
	

}
