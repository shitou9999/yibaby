package edu.jereh.com.mymoduletest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 条码二维码扫描功能实现
 */
public class CaptureActivity extends Activity{
	private static final String TAG = CaptureActivity.class.getSimpleName();

	private boolean hasSurface;
	public SharedPreferences mSharedPreferences;// 存储二维码条形码选择的状态
	public static String currentState;// 条形码二维码选择状态
	private String characterSet;

	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private TextView statusView;
	private TextView scanTextView;
	private View resultView;
	private ImageView onecode;
	private ImageView qrcode;

	/**
	 * 活动监控器，用于省电，如果手机没有连接电源线，那么当相机开启后如果一直处于不被使用状态则该服务会将当前activity关闭。
	 * 活动监控器全程监控扫描活跃状态，与CaptureActivity生命周期相同.每一次扫描过后都会重置该监控，即重新倒计时。
	 */

	private CameraManager cameraManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSetting();
		setContentView(R.layout.activity_capture);
	}

	/**
	 * 初始化窗口设置
	 */
	private void initSetting() {
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 保持屏幕处于点亮状态
		// window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 竖屏
	}

//	/**
//	 * 初始化功能组件
//	 */
//	private void initComponent() {
//		hasSurface = false;
//		inactivityTimer = new InactivityTimer(this);
//		beepManager = new BeepManager(this);
//		mSharedPreferences = PreferenceManager
//				.getDefaultSharedPreferences(this);
//		currentState = this.mSharedPreferences.getString("currentState",
//				"qrcode");
//		cameraManager = new CameraManager(getApplication());
//	}



}
