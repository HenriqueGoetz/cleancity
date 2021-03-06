package com.grupo04.cleancity.scheduler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Lucas Hagen
 */
public class Scheduler {

    private Schedulable action;

    private Timeline timeline;
    private long DELAY = 50;
    private boolean stop = false;

    public Scheduler(Schedulable action) {
        this.action = action;
    }

    public Scheduler(Schedulable action, long delay) {
        this.action = action;
        this.DELAY = delay;
    }

    public void start() {
        if (isRunning())
            return;

        stop = false;
        timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), event -> action.loop(event)));
        timeline.setOnFinished(event -> stop = true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean isRunning() {
        return timeline != null && !stop;
    }

    public void stop() {
        timeline.stop();
        stop = true;
    }

    public long getDelay() {
        return DELAY;
    }

    public void setDelay(long delay) {
        this.DELAY = delay;
    }

}
