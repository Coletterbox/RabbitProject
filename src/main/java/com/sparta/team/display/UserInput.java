package com.sparta.team.display;

import java.io.File;
import java.util.Scanner;

public class UserInput {

    private Scanner scanner = new Scanner(System.in);

    public int getTerminalNumberOfMonths() {
        System.out.println("Pick the number of months you want to run the simulation:");

        int resType = Integer.parseInt(scanner.nextLine());
        if (!(resType > 0)) {
            getTerminalOutputForResultType();
        }
        return resType;
    }

    public int getTerminalOutputForResultType() {
        String type1 = "1";
        String type2 = "2";
        System.out.println("Pick your simulations result format:\n"
                + type1 + ". Monthly \n"
                + type2 + ". End of simulation");

        String resType = scanner.nextLine();
        if (!resType.equals(type1) && !resType.equals(type2)) {
            getTerminalOutputForResultType();
        }
        return Integer.parseInt(resType);

    }
}