package assignment5;
import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;


public class Critter2 extends Critter.TestCritter
{
    @Override
    public javafx.scene.paint.Color viewFillColor() {return Color.YELLOW; }

    @Override
    public CritterShape viewShape()
    {
        return CritterShape.TRIANGLE;
    }
    @Override
    public String toString() { return "2"; }

    /**
     * Critter2 runs in a random direction when
     * doTimeStep() is called
     */



    @Override
    public void doTimeStep() {
        walk(getRandomInt(8));

        Critter2 twin = new Critter2();
        reproduce(twin, Critter.getRandomInt(8));
    }

    /**
     * @param opponent not used
     *  cause the Critter2 to run in a random direction, always return false
     * @return boolean false
     */


    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("@")){
            return true;
        }
        return false;

    }

}