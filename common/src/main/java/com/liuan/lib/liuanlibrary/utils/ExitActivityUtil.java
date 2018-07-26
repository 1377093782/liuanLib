package com.liuan.lib.liuanlibrary.utils;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;
 
public class ExitActivityUtil extends Activity{
	
	private long exitTime = 0;
	
	/*
	 * 重写onKeyDown方法
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			//2s之内按返回键就会推出
			if((System.currentTimeMillis() - exitTime) > 2000){
				Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
 
}
