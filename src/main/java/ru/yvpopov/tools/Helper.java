package ru.yvpopov.tools;

public class Helper {

    public static int Compare(long xH, int xL, long yH, int yL) {
        int a = Long.compare(xH, yH);
        if (a == 0) {
            a = Integer.compare(xL, yL);
        }
        return a;
    }
}
