package edu.erau.eagleeye;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    Application testApp;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        testApp = getApplication();
    }

    public void testTest() {
        Comparator cTest = new Comparator();
        cTest.attemptMatch(testApp, 0);
    }

}