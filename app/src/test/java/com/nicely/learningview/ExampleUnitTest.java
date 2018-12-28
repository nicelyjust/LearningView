package com.nicely.learningview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        StudentA a = new StudentA();
        StudentB b = new StudentB();
        Teacher teacher = new Teacher();
        teacher.subscribe(a);
        teacher.subscribe(b);
        teacher.update();

    }
}