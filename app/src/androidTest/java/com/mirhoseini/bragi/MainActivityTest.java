package com.mirhoseini.bragi;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.mirhoseini.bragi.domain.client.BragiApi;
import com.mirhoseini.bragi.domain.model.Items;
import com.mirhoseini.bragi.domain.model.Sections;

import net.bragi.foo.model.NumbersResponse;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mirhoseini.bragi.support.Matcher.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.when;

/**
 * Created by Mohsen on 18/11/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final int TEST_NUMBER = 140;
    public static final String TEST_SECTION = Sections.Section1.toString();
    public static final String TEST_ITEM = Items.Item4.toString();

    private BragiTestApplication application;

    NumbersResponse expectedResult;

    @Inject
    BragiApi api;

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            // false: do not launch the activity immediately
            false);

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        application = (BragiTestApplication) instrumentation.getTargetContext().getApplicationContext();
        ApplicationTestComponent component = (ApplicationTestComponent) BragiApplication.getComponent();

        // inject mocked objects
        component.inject(this);

        // Set up the stub we want to return in the mock
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(TEST_NUMBER);

        // put the test character in a test api result
        expectedResult = new NumbersResponse(numbers);

        // Set up the mock
        when(api.getNumbers())
                .thenReturn(Observable.just(expectedResult));
    }

    @Test
    public void shouldBeAbleToLoadNumber() {
        // Launch the activity
        mainActivity.launchActivity(new Intent());

        ViewInteraction sectionTextView = onView(
                allOf(withId(R.id.section), withText(TEST_SECTION),
                        childAtPosition(
                                allOf(withId(R.id.list),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        sectionTextView.check(matches(withText("Section 1")));

        ViewInteraction itemTextView = onView(
                allOf(withId(R.id.item), withText(TEST_ITEM),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        1),
                                0),
                        isDisplayed()));
        itemTextView.check(matches(withText(TEST_ITEM)));

        ViewInteraction isCheckedImageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.list),
                                1),
                        1),
                        isDisplayed()));
        isCheckedImageView.check(matches(isDisplayed()));
    }

}

