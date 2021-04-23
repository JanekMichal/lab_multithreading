package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {
    private PatriotBattery battery;
    private ExecutorService executorService;
    private int numberOfMisslesToLunch;


    public BetterRadar(int numberOfMisslesToLunch, PatriotBattery battery, ExecutorService executorService) {
        this.numberOfMisslesToLunch = numberOfMisslesToLunch;
        this.battery = battery;
        this.executorService = executorService;
    }

    public void launchPatriot(Scud enemyMissle) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < numberOfMisslesToLunch; i++) {
                battery.launchPatriot(enemyMissle);
            }
        };

        executorService.submit(launchPatriotTask);
    }

    public void notice(Scud enemyMissle) {
        launchPatriot(enemyMissle);
    }
}
