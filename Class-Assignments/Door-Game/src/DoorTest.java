import java.util.Random;
import java.util.*;

class DoorTest {
    private static final int NUM_TRIALS = 10000;

    public static void main(String[] args) {
        luckCheck(false, NUM_TRIALS);
        luckCheck(true, NUM_TRIALS);
    }

    /*
     * (a) Each door has a 1/3 chance of hiding the prize.
     * (b) Switching wins 2/3 times, staying wins 1/3 times.
     */
    private static void luckCheck(boolean switchDoor, int trials) {
        int wins = 0;
        Random random = new Random();

        for (int i = 0; i < trials; i++) {
            Door[] doors = {new Door(), new Door(), new Door()};
            
            // Randomly place the prize behind one of the doors
            int prizeDoor = random.nextInt(3);
            doors[prizeDoor].placePrize();

            // Contestant makes an initial choice
            int chosenDoor = random.nextInt(3);

            // Host reveals a door that is not the prize or the chosen one
            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3);
            } while (revealedDoor == chosenDoor || doors[revealedDoor].hasPrize());

            // If switching, change the chosen door
            if (switchDoor) {
                for (int j = 0; j < 3; j++) {
                    if (j != chosenDoor && j != revealedDoor) {
                        chosenDoor = j;
                        break;
                    }
                }
            }

            // Check if the chosen door has the prize
            if (doors[chosenDoor].hasPrize()) {
                wins++;
            }
        }
        System.out.printf("Win percentage when %s: %.2f%%%n", (switchDoor ? "switching" : "staying"), (wins / (double) trials * 100));
    }
}
