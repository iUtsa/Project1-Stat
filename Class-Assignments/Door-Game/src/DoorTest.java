import java.util.Random;

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
            Door door1 = new Door();
            Door door2 = new Door();
            Door door3 = new Door();
            Door[] doors = {door1, door2, door3};
            int prizeDoor = random.nextInt(3);
            doors[prizeDoor].placePrize();

            int chosenDoor = random.nextInt(3);

            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3);
            } while (revealedDoor == chosenDoor || doors[revealedDoor].hasPrize());

            if (switchDoor) {
                for (int j = 0; j < 3; j++) {
                    if (j != chosenDoor && j != revealedDoor) {
                        chosenDoor = j;
                        break;
                    }
                }
            }

            if (doors[chosenDoor].hasPrize()) {
                wins++;
            }
        }
        System.out.println("Win percentage when " + (switchDoor ? "switching" : "staying") + ": " + (wins / (double) trials * 100) + "%");
    }
}
