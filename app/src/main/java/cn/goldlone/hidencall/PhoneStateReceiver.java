
package cn.goldlone.hidencall;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.TelephonyManager;

public class PhoneStateReceiver extends BroadcastReceiver {

    /**
     * 电话管理
     */
    private TelephonyManager telMgr = null;

    private static final Object monitor = new Object();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Context ctx = context;

        telMgr = (TelephonyManager)ctx.getSystemService(Service.TELEPHONY_SERVICE);
        switch (telMgr.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING:// 来电响铃
                System.out.println("....................主人，那家伙又来电话了....................");
                final String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                System.out.println("number:" + number);
                // 启动悬浮窗
                synchronized (monitor) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showWindow(ctx, number, 100);
                        }
                    }, 100);
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:// 拨打、接听电话
                final String number1 = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                System.out.println("number:" + number1);

                synchronized (monitor) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showWindow(ctx, number1, 100);
                        }
                    }, 100);
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:// 空闲状态
                // 关闭悬浮窗
                synchronized (monitor) {
                    closeWindow(ctx);
                }
                break;
            default:
                break;
        }

    }


    /**
     * 显示来电弹窗
     * 
     * @param ctx 上下文对象
     * @param number 电话号码
     */
    private void showWindow(Context ctx, String number, int percentScreen) {
        OverlayView.show(ctx, number, percentScreen);
    }

    /**
     * 关闭来电弹窗
     * 
     * @param ctx 上下文对象
     */
    private void closeWindow(Context ctx) {
        OverlayView.hide(ctx);
    }

}
