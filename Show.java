import java.io.*;
import java.util.Scanner;

class Show implements Serializable {
    private static final long serialVersionUID = 1L;
    private final boolean[][] seats;
    private final int rows;
    private final int cols;

    public Show(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seats = new boolean[rows][cols]; // false = available, true = booked
    }
