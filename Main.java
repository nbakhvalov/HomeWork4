package ru.geekbrains.homework;
/*
*
* author: Nikolay Bakhvalov
* date: 2020-03-03
*
*
 */
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static final char DOT_X = 'X';
    private static int stepCounter = 0;
    private static final char DOT_O = 'O';
    private static final char DEFAULT = '*';
    private static final Random rnd = new Random();



    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер поля");
        int size = in.nextInt();
	    char [][] tab = new char[size][size]; // создаем игровое поле
	    initTable(tab); //
	    boolean isInGame = true;
	    while (isInGame) {
            System.out.println("\nВведите номер строки и столбца для хода");
            print(tab);
            int x = in.nextInt(), y = in.nextInt();
            x--; y--;
            if (isValidStep(x, y, tab)) {
                tab[x][y] = DOT_X;
                if (isWin(DOT_X, tab)) {
                    print(tab);
                    System.out.println("Вы победили!");
                    return;
                }
                stepCounter++;
                if (stepCounter == size * size) {
                    print(tab);
                    System.out.println("Ничья!");
                    return;
                }
                movePC(tab);
                System.out.println("Ход противника");
                for (int i = 0; i < 7; i++) {
                    System.out.print(" - ");
                    Thread.sleep( 350);
                }
                System.out.println();
                if (isWin(DOT_O, tab)) {
                    print(tab);
                    System.out.println("Компьютер победил!");
                    return;
                }
            }
        }
    }

    private static boolean isWin(char ch, char[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            int sx = 0, sy = 0, d1 = 0, d2 = 0;
            for (int j = 0; j < tab.length; j++) {
                sx += tab[i][j] == ch ? 1 : 0;//если равно +1 иначе +0
                sy += tab[j][i] == ch ? 1 : 0;
                d1 += tab[j][j] == ch ? 1 : 0;
                d2 += tab[j][tab.length - i - 1] == ch ? 1 : 0;
                if (sx == tab.length || sy == tab.length || d1 == tab.length || d2 == tab.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void movePC(char[][] tab) {
        while (true) {
            int x = rnd.nextInt(tab.length);
            int y = rnd.nextInt(tab.length);
            if (isValidStep(x, y, tab)) {
                tab[x][y] = DOT_O;
                stepCounter++;
                return;
            }
        }
    }

    private static boolean isValidStep(int x, int y, char[][] tab) {
        int size = tab.length;
        if (x >= 0 && x < size && y >= 0 && y < size){
            return tab[x][y] ==DEFAULT;
        }
        return false;
    }

    /* print(tab) поле для игры
    *     1  2  3
    * 1 |* |* |* |
    * 2 |* |* |* |
    * 3 |* |* |* |
    */


    private static void print(char[][] tab) { // вывод игрового поля
        System.out.print("   ");
        for (int i = 0; i <tab.length ; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i <tab.length ; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j <tab.length ; j++) {
                System.out.print("|" + tab[i][j]);
            }
            System.out.println("|");
        }
    }

    private static void initTable(char[][] tab) {
        for (int i = 0; i <tab.length ; i++) {
            for (int j = 0; j <tab.length ; j++) {
                tab[i][j] = DEFAULT; // проинициализировали игровое поле *
            }
        }
    }
}
