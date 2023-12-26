package com.mycompany.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.List;

public class App {

    static int knapsack(int W, List<Integer> wt, List<Integer> val, int n) {

        int i, w;
        int p[][] = new int[n + 1][W + 1];
        int traceback[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {

            for (w = 0; w <= W; w++) {

                if (i == 0 || w == 0) {
                    p[i][w] = 0;
                    traceback[i][w] = 0;

                } else if (wt.get(i - 1) <= w) {
                    if (val.get(i - 1) + p[i - 1][w - wt.get(i - 1)] > p[i - 1][w]) {
                        p[i][w] = val.get(i - 1) + p[i - 1][w - wt.get(i - 1)];
                        traceback[i][w] = 1;

                    } else {
                        p[i][w] = p[i - 1][w];

                    }

                } else {
                    p[i][w] = p[i - 1][w];

                }
            }
        }

        int a = n, b = W;
        List<Integer> selectList = new ArrayList<>();
        while (a > 0 && b > 0) {
            if (traceback[a][b] == 1) {
                selectList.add(a);
                b = b - wt.get(a - 1);
            }
            a--;
        }

        Collections.reverse(selectList);
        System.out.println(selectList);

        return p[n][W];

    }

    public static void main(String[] args) {
        try {

            File file = new File("C:\\Users\\User\\Desktop\\rasel\\knapsack01.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int w = Integer.parseInt(firstLine[1]);

            ArrayList<Integer> val = new ArrayList<>();
            ArrayList<Integer> wt = new ArrayList<>();

            String[] secondLine = reader.readLine().split(" ");
            for (String s : secondLine) {
                val.add(Integer.parseInt(s));
            }
            String[] thirdLine = reader.readLine().split(" ");
            for (String sh : thirdLine) {
                wt.add(Integer.parseInt(sh));
            }

            System.out.println("Maximum total profit=" + knapsack(w, wt, val, n));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
