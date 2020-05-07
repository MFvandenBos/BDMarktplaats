package util;

import java.util.Scanner;

public class ScannerWrapper {
    Scanner scanner = new Scanner(System.in);

    public String nextLine() { return scanner.nextLine(); }

}