package assignment5;
import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

// NoGainzCritter
//This critter needs to hit the gym

//this is a very weak critter
// however he is slightly above average
//these critters are just like Craig
//similar in style to Craig
//these critters will always run from a fight
//UNLESS it is with algae, then they will try and eat it
// Critter1 > Algae (BARELY)


public class Critter3 extends Critter {

    @Override
    public javafx.scene.paint.Color viewFillColor() {return Color.HOTPINK; }

    @Override
    public CritterShape viewShape()
    {
        return CritterShape.DIAMOND;
    }

    @Override
    public String toString() { return "3"; }

    private static final int GENE_TOTAL = 24;
    private int[] genes = new int[8];
    private int dir;

    public Critter3() {
        for (int k = 0; k < 8; k += 1) {
            genes[k] = GENE_TOTAL / 8;
        }
        dir = Critter.getRandomInt(8);
    }

    public boolean fight(String opponent) { // this is the defining characteristic of a coward critter, it will try to run from a fight and then return false as it does not want to fight
        if(opponent.equals("@")){ // if opponent is algae it will try to eat it
            return true;
        }
        else{
            run(dir);
            return false;
        }
    }

    @Override
    public void doTimeStep() {

        String a = look(this.dir, false);
        walk(dir);

        if (getEnergy() > 150) {
            Critter3 child = new Critter3();
            for (int k = 0; k < 8; k += 1) {
                child.genes[k] = this.genes[k];
            }
            int g = Critter.getRandomInt(8);
            while (child.genes[g] == 0) {
                g = Critter.getRandomInt(8);
            }
            child.genes[g] -= 1;
            g = Critter.getRandomInt(8);
            child.genes[g] += 1;
            reproduce(child, Critter.getRandomInt(8));
        }

        /* pick a new direction based on our genes */
        int roll = Critter.getRandomInt(GENE_TOTAL);
        int turn = 0;
        while (genes[turn] <= roll) {
            roll = roll - genes[turn];
            turn = turn + 1;
        }
        assert(turn < 8);

        dir = (dir + turn) % 8;
    }

    public static String runStats(java.util.List<Critter> weakCritter) {
        int total_straight = 0;
        int total_left = 0;
        int total_right = 0;
        int total_back = 0;
        for (Object obj : weakCritter) {
            Critter3 c = (Critter3) obj;
            total_straight += c.genes[0];
            total_right += c.genes[1] + c.genes[2] + c.genes[3];
            total_back += c.genes[4];
            total_left += c.genes[5] + c.genes[6] + c.genes[7];
        }
        String s = "";
        s+= weakCritter.size() + " total Weaklings    ";
        s += total_straight / (GENE_TOTAL * 0.01 * weakCritter.size()) + "% straight   ";
        s += total_back / (GENE_TOTAL * 0.01 * weakCritter.size()) + "% back   ";
        s+= total_right / (GENE_TOTAL * 0.01 * weakCritter.size()) + "% right   ";
        s+= total_left / (GENE_TOTAL * 0.01 * weakCritter.size()) + "% left   ";

        System.out.println(s + "\n");
        return s;
    }
}
