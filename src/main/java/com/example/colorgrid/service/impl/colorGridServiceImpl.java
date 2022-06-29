package com.example.colorgrid.service.impl;

import com.example.colorgrid.service.ColorGridService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class colorGridServiceImpl implements ColorGridService {

    @Override
    public String getDate(int cols, int rows) {
        //output should be no 1 is repeating 9 times
        int[][] input = {{1, 4, 4, 4, 4, 3, 3, 1},
                {2, 1, 1, 4, 3, 3, 1, 1},
                {3, 2, 1, 1, 2, 3, 2, 1},
                {3, 3, 2, 1, 2, 2, 2, 2},
                {3, 1, 3, 1, 1, 4, 4, 4},
                {1, 1, 3, 1, 1, 4, 4, 4}};

        // function to compute the largest
        // connected component in the grid
        return "Most Repeated Interconnected Number Count = " + computeLargestConnectedGrid(input) + " Times";
    }

    static final int Rows = 6;
    static final int Cols = 8;

    // stores information about which cell
// are already visited in a particular BFS
    static final int visited[][] = new int [Rows][Cols];

    // result stores the final result grid
    static final int result[][] = new int [Rows][Cols];

    // stores the count of
// cells in the largest
// connected component
    static int COUNT;

    // Function checks if a cell
// is valid i.e it is inside
// the grid and equal to the key
    static boolean isValid(int x, int y,
                            int key,
                            int[][] input)
    {
        if (x < Rows && y < Cols &&
                x >= 0 && y >= 0)
        {
            if (visited[x][y] == 0 &&
                    input[x][y] == key)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    // BFS to find all cells in
// connection with key = input[i][j]
    static void BFS(int x, int y, int i,
                    int j, int[][] input)
    {
        // terminating case for BFS
        if (x != y)
            return;

        visited[i][j] = 1;
        COUNT++;

        // x_move and y_move arrays
        // are the possible movements
        // in x or y direction
        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };

        // checks all four points
        // connected with input[i][j]
        for (int u = 0; u < 4; u++)
            if ((isValid(i + y_move[u],
                    j + x_move[u], x, input)))
                BFS(x, y, i + y_move[u],
                        j + x_move[u], input);
    }

    // called every time before
// a BFS so that visited
// array is reset to zero
    static void resetVisited()
    {
        for (int i = 0; i < Rows; i++)
            for (int j = 0; j < Cols; j++)
                visited[i][j] = 0;
    }

    // If a larger connected component
// is found this function is
// called to store information
// about that component.
    static void resetResult(int key,
                             int[][] input)
    {
        for (int i = 0; i < Rows; i++)
        {
            for (int j = 0; j < Cols; j++)
            {
                if (visited[i][j] ==1 &&
                        input[i][j] == key)
                    result[i][j] = visited[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }

    // function to calculate the
// largest connected component
    static int computeLargestConnectedGrid(int[][] input)
    {
        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < Rows; i++)
        {
            for (int j = 0; j < Cols; j++)
            {
                resetVisited();
                COUNT = 0;

                // checking cell to the right
                if (j + 1 < Cols)
                    BFS(input[i][j], input[i][j + 1],
                            i, j, input);

                // updating result
                if (COUNT >= currentMax)
                {
                    currentMax = COUNT;
                    resetResult(input[i][j], input);
                }
                resetVisited();
                COUNT = 0;

                // checking cell downwards
                if (i + 1 < Rows)
                    BFS(input[i][j],
                            input[i + 1][j], i, j, input);

                // updating result
                if (COUNT >= currentMax)
                {
                    currentMax = COUNT;
                    resetResult(input[i][j], input);
                }
            }
        }
        return currentMax;
    }
}


