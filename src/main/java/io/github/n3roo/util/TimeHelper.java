package io.github.n3roo.util;

public class TimeHelper {

    // last MS saved
    private long lastMS = 0L;

    // used to stop the timer
    private boolean stopped;

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
     * It resets the timer by saving last ms with the current one.
     */
    public void reset(){
        this.lastMS = getCurrentMS();
    }

    /**
     * @return true if the timer is stopped.
     */
    public boolean isStopped(){
        return stopped;
    }

    /**
     * @param delay the delay in milliseconds.
     * @return true if the delay has been reached and the timer is not stopped.
     */
    public boolean isDelayComplete(long delay) {
        if(stopped) return false;
        if(System.nanoTime() / 1000000L - lastMS >= delay) {
            return true;
        }else return delay <= 0;
    }

    /**
     * @return the current ms.
     */
    private long getCurrentMS(){
        return System.nanoTime() / 1000000L;
    }
}
