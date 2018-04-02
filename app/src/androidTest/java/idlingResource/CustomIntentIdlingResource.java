package idlingResource;

import android.app.ActivityManager;
import android.content.Context;
import android.support.test.espresso.IdlingResource;

import com.example.servicedemo.DemoService;

public class CustomIntentIdlingResource implements IdlingResource {

    private ResourceCallback callback;
    private Context context;

    public CustomIntentIdlingResource(Context context) {
        this.context = context;
    }

    @Override
    public String getName() {
        return CustomIntentIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = !isIntentServiceRunning();
        if (idle && callback != null) {
            callback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    private boolean isIntentServiceRunning() {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo info : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (DemoService.class.getName().equals(info.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
