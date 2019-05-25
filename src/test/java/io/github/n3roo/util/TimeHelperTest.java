package io.github.n3roo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeHelperTest {

    @Test
    void timeElapsed(){
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();

        try {
            Thread.sleep(500);
            assertTrue(timeHelper.timeElapsed() >= 480);
            assertTrue(timeHelper.timeElapsed() <= 520);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeHelper.reset();

        try {
            Thread.sleep(320);
            assertTrue(timeHelper.timeElapsed() >= 300);
            assertTrue(timeHelper.timeElapsed() <= 340);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void pauseResume() {
        long delay = 1500;
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();

        // 1: wait 500 ms, delay is not completed
        assertFalse(timeHelper.isDelayComplete(delay));
        try {
            Thread.sleep(500);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2: pause, wait 500 ms, delay is not completed because it did not change (STOPPED)
        timeHelper.pause();
        try {
            Thread.sleep(200);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3: resume, wait 500 ms, delay is still not completed
        timeHelper.resume();
        try {
            Thread.sleep(500);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4: pause, wait 200 ms, delay is not completed
        timeHelper.pause();
        try {
            Thread.sleep(200);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5: resume, wait 150 ms, delay is completed
        timeHelper.resume();
        try {
            Thread.sleep(150);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startStop() {
        long delay = 1000;
        TimeHelper timeHelper = new TimeHelper();

        timeHelper.start();
        try {
            Thread.sleep(500);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeHelper.stop();
        timeHelper.start();
        try {
            Thread.sleep(500);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
            assertTrue(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isStopped() {
        TimeHelper timeHelper = new TimeHelper();
        assertTrue(timeHelper.isStopped());
        timeHelper.start();
        assertFalse(timeHelper.isStopped());
        timeHelper.stop();
        assertTrue(timeHelper.isStopped());
    }

    @Test
    void isDelayComplete() {
        long delay = 1000;
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();

        assertFalse(timeHelper.isDelayComplete(delay));
        try {
            Thread.sleep(500);
            assertFalse(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
            assertTrue(timeHelper.isDelayComplete(delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}