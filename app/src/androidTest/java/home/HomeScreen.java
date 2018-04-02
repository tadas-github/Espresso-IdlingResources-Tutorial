package home;

import com.example.servicedemo.R;

import base.BaseScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class HomeScreen extends BaseScreen<HomeScreen>{

    public HomeScreen() {
        super(HomeScreen.class);
    }

    public HomeScreen downloadUserData() {
        onView(withId(R.id.btn_start_service)).perform(click());
        return self;
    }
}
