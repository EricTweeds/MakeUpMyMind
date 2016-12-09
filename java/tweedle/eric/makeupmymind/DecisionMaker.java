package tweedle.eric.makeupmymind;

import java.util.List;
import java.util.Random;

/**
 * Created by Eric on 2016-10-27.
 */

public class DecisionMaker {

    private int randomInt (int range){
        Random rand = new Random();
        return (rand.nextInt(range));
    }

    public String Simple(List<String> options){
        int length = options.size();
        int choice = randomInt(length);
        return (options.get(choice));
    }
}
