package Effects;

import Entities.Entity;

public class Effect {
    private int duration;
    private int stength;
    private String effect;

    public Effect (String effect, Entity mob)
    {
        this.effect=effect.toUpperCase();

        switch (effect) {
            case "slow":

                mob.getSpeed();
                break;
            case "burn" :
                duration=3;
        
            default:
                break;
        }
    }
}
