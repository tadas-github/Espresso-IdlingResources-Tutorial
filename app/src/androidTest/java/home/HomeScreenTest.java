package home;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.servicedemo.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import idlingResource.CustomIntentIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static screens.Screens.onHomeScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private CustomIntentIdlingResource idlingResource;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new CustomIntentIdlingResource(instrumentation.getContext());
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void shouldDisplayDownloadedData() throws Exception {
        onHomeScreen().downloadUserData();

        onView(withText("Data Downloaded")).check(matches(isDisplayed()));
    }

    @After
    public void clear() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}
