package io.github.n3roo.util;

public class TimeHelper {

    // last MS saved
    private long lastMS = 0L;

    // used to stop the timer
    private boolean stopped;

    // used to pause the timer
    private boolean paused;

    private long elapsedBeforePause = 0L;

    /**
     * It creates a time helper. Stopped by default.
     */
    public TimeHelper(){
        stopped = true;
    }

    /**
     * It stops and reset the timer.
     */
    public void stop(){
        stopped = true;
        reset();
    }

    /**
     * It starts and reset the timer.
     */
    public void start(){
        stopped = false;
        reset();
    }

    /**
     * It pauses the timer.
     */
    public void pause(){
        paused = true;
        elapsedBeforePause = getCurrentMS() - lastMS;
    }

    /**
     * It resumes the timer.
     */
    public void resume(){
        paused = false;
        lastMS = getCurrentMS() - elapsedBeforePause;
    }

    /**
     * It resets the timer by saving last ms with the current one.
     */
    public void reset(){
        lastMS = getCurrentMS();
        elapsedBeforePause = 0L;
    }

    /**
     * @return true if the timer is stopped.
     */
    public boolean isStopped(){
        return stopped;
    }

    /**
     * The value has a margin of error of 20 ms.
     * @param delay the delay in milliseconds.
     * @return true if the delay has been reached and the timer is not stopped.
     */
    public boolean isDelayComplete(long delay) {
        return timeElapsed() >= delay;
    }

    /**
     * The value has a margin of error of 20 ms.
     * @return the time elapsed in milliseconds (it takes in account if it is stopped or paused).
     */
    public long timeElapsed(){
        if(paused) return elapsedBeforePause;
        if(stopped) return 0L;
        return getCurrentMS() - lastMS;
    }

    /**
     * @return the current ms.
     */
    private long getCurrentMS(){
        return System.nanoTime() / 1000000L;
    }
}
