package com.example.qwayphone;

import com.qway.application.QVGlobalAp;

import android.app.Application;

public class QVApplication  extends Application{
	@Override
	public void onCreate() {
		QVGlobalAp.init(this);
		QVGlobalAp.setDebug(false);
		super.onCreate();
	}

}
