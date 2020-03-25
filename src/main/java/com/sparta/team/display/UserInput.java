package com.sparta.team.display;

import java.io.File;
import java.util.Scanner;

public class UserInput {

    private Scanner scanner = new Scanner(System.in);

    public int getTerminalOutputForResultType() {
        String type1 = "1";
        String type2 = "2";
        System.out.println("Pick your simulations result format:");
        System.out.println(type1 + ". Monthly");
        System.out.println(type2 + ". End of simulation");

        String resType = scanner.nextLine();
        if (!resType.equals(type1) && !resType.equals(type2)) {
            getTerminalOutputForResultType();
        }
        return Integer.parseInt(resType);
    }
}
