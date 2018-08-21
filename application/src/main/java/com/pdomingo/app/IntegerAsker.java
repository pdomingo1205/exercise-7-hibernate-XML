package com.pdomingo.app;

import java.util.*;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.InputMismatchException;

public class IntegerAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public IntegerAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public Integer ask(String message)throws InputMismatchException {
        out.println(message);
        Integer x = 0;

        try{
            x = scanner.nextInt();
        }catch(InputMismatchException e){
            scanner.nextLine();
        }
        return x;
    }

}
