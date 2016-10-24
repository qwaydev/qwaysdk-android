package com.example.qwayphone;

import java.io.Serializable;









import org.linphone.LinphoneManager;

import qway.logininterface.QwayCallListener;

import com.qway.application.QVGlobalAp;
import com.qway.im.QVClient;
import com.qway.im.event.IObserver;
import com.qway.ui.eventdata.QV;
import com.qway.util.QwayCall;
import com.qway.util.QwayPhone;





import com.qway.util.VibratorUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 拨打电话通话界面
 * @author Administrator
 *
 */

public class DialCallActivity extends Activity implements QwayCallListener,Serializable{
	private static final String TAG="DialCallActivity";
	private TextView tv_name;//顯示的電話號碼
	private LinearLayout speaker_layout,keypad_layout,rl_paytong;//擴音，鍵盤
	private RelativeLayout rl_hub;//掛斷
	private boolean soundStatus=false;
	//dmf：為方便只測試一個
	private ImageButton dial_btn_num1,dial_btn_num0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	this.setContentView(R.layout.activity_dialcall);
	initView();
	
	}
	private void initView(){
		tv_name=(TextView)findViewById(R.id.tv_name);
		tv_name.setText(getIntent().getStringExtra("number"));
		speaker_layout=(LinearLayout)findViewById(R.id.speaker_layout);
		keypad_layout=(LinearLayout)findViewById(R.id.keypad_layout);
		rl_paytong=(LinearLayout)findViewById(R.id.rl_paytong);
		rl_hub=(RelativeLayout)findViewById(R.id.rl_hub);
		dial_btn_num1=(ImageButton)findViewById(R.id.dial_btn_num1);
		dial_btn_num0=(ImageButton)findViewById(R.id.dial_btn_num0);
		
		keypad_layout.setOnClickListener(OnClick);
		speaker_layout.setOnClickListener(OnClick);
		dial_btn_num1.setOnClickListener(OnClick);
		dial_btn_num0.setOnClickListener(OnClick);
		rl_hub.setOnClickListener(OnClick);
		placeCall();
	}
	private void placeCall() {
		if(null!=getIntent().getStringExtra("number")){
		
			QwayCall.sendCall(getIntent().getStringExtra("number"));}
		
		
	}
	private OnClickListener OnClick=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.speaker_layout:
				//免提
				soundStatus=!soundStatus;
				QwayCall.soundReinforcement(soundStatus);				
				break;
			case R.id.keypad_layout:
				if(rl_paytong.getVisibility()==View.GONE){
					rl_paytong.setVisibility(View.VISIBLE);
				}else{
					rl_paytong.setVisibility(View.GONE);
				}
				
				break;
				
			case R.id.dial_btn_num1:
				//dmf 只测试1 其他都相同
				QwayCall.placeDtmf("1");
				break;
			case R.id.dial_btn_num0:
				//dmf 只测试1 其他都相同
				QwayCall.placeDtmf("0");
				break;
			case R.id.rl_hub:
				QwayCall.hangUp();
				finish();
				break;
				
			}
			
		}
	};
     protected void onResume() {
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
		// 对方振铃
		
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
