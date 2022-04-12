package Effects;

import Entities.Entity;

public class Effect {
    private int duration;
    private int stength;
    private String effect;

    public Effect (String effect, Entity mob)
    {
        stength=1;
        this.effect=effect.toUpperCase();

        switch (effect) {
            case "slow":
                duration = 2+stength;
                mob.getSpeed();
                break;
            case "burn" :
                duration=3;
        
            default:
                break;
        }
    }
}
