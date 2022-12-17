package ept.stochastique.travailFinal;

import java.io.IOException;

public class ReplayAllMonth {

    static int month = 0;

    public static void nextMonth() {

        if (month == 12) return;
        month += 1;

        ReplayMonth simulation = new ReplayMonth();
        try {
            simulation.simulateOneDay("calls-2014-" + String.valueOf(month + 100).substring(1) + ".csv");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ReplayAllMonth.nextMonth();

    }
}
