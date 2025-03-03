import java.util.ArrayList;
import java.util.Scanner;

public class Potion50 extends TrainerCard
{
    public Potion50(){
        super("Potion 50");
    }
    public void playTrainer(Player gameState){

        PokemonCard active = gameState.getActive();
        ArrayList<PokemonCard> bench = gameState.getBench();
        Scanner scan = new Scanner(System.in);

        System.out.println("1) Active Pokemon");
        System.out.println("2) Bench Pokemon");
        int num = scan.nextInt();
        while(num < 1 || num > 2){
            System.out.println("Invalid number, try again");
            num = scan.nextInt();
        }

        if(num == 1){
            active.heal(80);
            System.out.println("Your Pokemon is healed by 80 HP");
        }
        if(num == 2){
            System.out.println("Choose Pokemon from your bench");
            System.out.println(bench);
            int position = scan.nextInt();
            while(position < 1 || position > bench.size() - 1){
                System.out.println("Invalid number, try again");
                position = scan.nextInt();
            }
            bench.get(position-1).heal(80);
            System.out.println("Successfully healed " + bench.get(position-1));
        }

    }

}