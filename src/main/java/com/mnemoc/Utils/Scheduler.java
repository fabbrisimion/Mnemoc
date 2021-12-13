package com.mnemoc.Utils;

import com.mnemoc.Models.Card;

public class Scheduler {

    private static final long millisInDays = 60 * 60 * 24 * 1000;

    public static void schedule(Card cd,String type) {
        int quality = typeToQuality(type);
        long repetitions = 0;
        float easiness = 2.5F;
        long interval = 1;

        if (quality < 0 || quality > 5) try {
            throw new Exception("Quality out of bounds");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // retrieve the stored values (default values if new cards)
        if (!cd.getLastRev().equals(null)){
            repetitions = cd.getReps();
            easiness = cd.getENr();
            interval = cd.getIntrvl();
        }

        easiness = (float) Math.max(1.3, easiness + 0.1 - (5.0 - quality) * (0.08 + (5.0 - quality) * 0.02));

        // repetitions
        if (quality <= 3) {
            repetitions = 0;
        } else {
            repetitions += 1;
        }

        // interval
        if (repetitions <= 1) {
            interval = 1;
        } else if (repetitions == 2) {
            interval = 6;
        } else {
            interval = Math.round(interval * easiness);
        }

        long now = System.currentTimeMillis();
        long next_rev = now + millisInDays * interval;

        cd.setENr(easiness);
        cd.setIntrvl(interval);
        cd.setReps(repetitions);
        cd.setNextRev(next_rev);
    }

    private static int typeToQuality(String type){
        if(type.equals("Easy")) return 5;
        else if(type.equals("Medium")) return 3;
        else return 1;
    }
}
