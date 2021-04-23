package edu.iis.mto.multithread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    private ExecutorService executorServiceMock;

    @BeforeEach
    void setUp() {
        when(executorServiceMock.submit(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });
    }

    @RepeatedTest(25)
    void launchPatriotOnceWhenNoticesAScudMissle() {
        int howManyMissiles = 1;
        BetterRadar radar = new BetterRadar(howManyMissiles, batteryMock, executorServiceMock);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(batteryMock, times(howManyMissiles)).launchPatriot(enemyMissle);
    }

    @RepeatedTest(25)
    void launchPatriotTenTimesWhenNoticesTenScudMissles() {
        int howManyMissiles = 10;
        BetterRadar radar = new BetterRadar(howManyMissiles, batteryMock, executorServiceMock);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(batteryMock, times(howManyMissiles)).launchPatriot(enemyMissle);
    }

    @RepeatedTest(25)
    void launchPatriotZeroTimesWhenNoticesNoScudMissles() {
        int howManyMissiles = 0;
        BetterRadar radar = new BetterRadar(howManyMissiles, batteryMock, executorServiceMock);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(batteryMock, times(howManyMissiles)).launchPatriot(enemyMissle);
    }
}

