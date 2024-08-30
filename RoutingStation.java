import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class RoutingStation implements Runnable {
    private static final int MAX_STATIONS = 10;
    private static ReentrantLock[] conveyors = new ReentrantLock[MAX_STATIONS];
    private int stationId;
    private int workload;
    private int inbound;
    private int outbound;
    final int sleepWait = 1000;

    static {
        for (int i = 0; i < MAX_STATIONS; i++) {
            conveyors[i] = new ReentrantLock();
        }
    }

    public RoutingStation(int stationId, int workload) {
        this.stationId = stationId;
        this.workload = workload;
        this.inbound = stationId;
        this.outbound = (stationId + 1) % MAX_STATIONS;
        System.out.println("Routing Station S" + stationId + ": Input conveyor assigned to conveyor number C" + inbound);
        System.out.println("Routing Station S" + stationId + ": Output conveyor assigned to conveyor number C" + outbound);
        System.out.println("Routing Station S" + stationId + " Has Total Workload of " + workload + " Package Groups.");
    }

    @Override
    public void run() {
        try {
            while (workload > 0) {
                movePackages();
                workload--;
                System.out.println("Routing Station S" + stationId + ": Package group completed - " + workload + " package groups remaining to move.");
                Thread.sleep((int)(Math.random()*sleepWait));
            }
            System.out.println("# # Routing Station S" + stationId + ": going offline – work completed!   BYE!  # #");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void movePackages() throws InterruptedException {
        if (conveyors[inbound].tryLock()) {
            try {
                System.out.println("Routing Station S" + stationId + ": Currently holds lock on input conveyor C" + inbound);
                if (conveyors[outbound].tryLock()) {
                    try {
                        System.out.println("Routing Station S" + stationId + ": Currently holds lock on output conveyor C" + outbound);
                        System.out.println("* * * * Routing Station S" + stationId + ": * * * * CURRENTLY HARD AT WORK MOVING PACKAGES. * * * *");
                        Thread.sleep((int)(Math.random()*sleepWait));
                    } finally {
                        conveyors[outbound].unlock();
                        System.out.println("Routing Station S" + stationId + ": Unlocks/releases output conveyor C" + outbound);
                    }
                } else {
                    System.out.println("Routing Station S" + stationId + ": UNABLE TO LOCK OUTPUT CONVEYOR C" + outbound +
                            ". SYNCHRONIZATION ISSUE: Station S" + (stationId + 1) % MAX_STATIONS + " currently holds the lock on output conveyor C" +
                            outbound + " – Station S" + stationId + " releasing lock on input conveyor C" + inbound);
                }
            } finally {
                conveyors[inbound].unlock();
                System.out.println("Routing Station S" + stationId + ": Unlocks/releases input conveyor C" + inbound);
            }
        }
    }
}
