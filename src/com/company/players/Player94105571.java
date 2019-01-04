package com.company.players;
import com.company.Board;
import com.company.IntPair;

import java.util.LinkedList;

public class Player94105571 extends Player{

    private int limit;

    public Player94105571(int col) {
        super(col);
        limit = 5;
    }

    @Override
    public IntPair getMove(Board board) {
        int best_move = max_node(board, 0)[1];
        int x_dir = (1 - best_move / 2) * (2 * (best_move % 2) - 1);
        int y_dir = (best_move / 2) * (2 * (best_move % 2) - 1);
        IntPair my_head = board.getHead(getCol());
        IntPair new_position = new IntPair(my_head.x + x_dir, my_head.y + y_dir);
        System.out.println("Player " + getCol() + " moves to (" + new_position.x + ", " + new_position.y + ")");
        return  new_position;
    }

    private int[][] bfs(Board board, int color) {
        int[][] d = new int[Board.size][Board.size];
        boolean[][] mark = new boolean[Board.size][Board.size];
        IntPair current_node = board.getHead(color);
        mark[current_node.x][current_node.y] = true;
        LinkedList<IntPair> queue = new LinkedList<>();
        queue.add(current_node);
        d[current_node.x][current_node.y] = 0;
        while (!queue.isEmpty()) {
            IntPair head_node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x_dir = (1 - i / 2) * (2 * (i % 2) - 1);
                int y_dir = (i / 2) * (2 * (i % 2) - 1);
                int new_x = head_node.x + x_dir;
                int new_y = head_node.y + y_dir;
                if ((new_x >= 0) && (new_x <= Board.size - 1) && (new_y >= 0) && (new_y <= Board.size - 1) && (board.getCell(new_x, new_y).getColor() == 0) && (!mark[new_x][new_y])) {
                    d[new_x][new_y] = d[head_node.x][head_node.y] + 1;
                    mark[new_x][new_y] = true;
                    queue.add(new IntPair(new_x, new_y));
                }
            }
        }
        return d;
    }

    private int cut_off_case(Board board) {
        int my_len = board.getLength(getCol());
        int opponent_len = board.getLength(3 - getCol());

        int[][] my_d = bfs(board, getCol());
        int[][] opponent_d = bfs(board, 3 - getCol());

        int val = 0;
        int cnt = 0;
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                if (board.getCell(i, j).getColor() == 0) {
                    if (my_d[i][j] > opponent_d[i][j]) {
                        val -= (board.getCellValues(i, j) + 1);
                        cnt += 1;
                    } else if (my_d[i][j] < opponent_d[i][j]) {
                        val += (board.getCellValues(i, j) + 1);
                        cnt += 1;
                    }
                }
            }
        }
        System.out.println(val);
        return 2 * (val / cnt) + (my_len - opponent_len);
    }

    private int[] max_node(Board board, int depth) {
        if (depth > limit) {
            return new int[] {cut_off_case(board), -1};
        }
        int value = Integer.MIN_VALUE;
        IntPair my_head = board.getHead(getCol());
        int best_move = 0;
        for (int i = 0; i < 4; i++) {
            Board new_board = new Board(board);
            int x_dir = (1 - i / 2) * (2 * (i % 2) - 1);
            int y_dir = (i / 2) * (2 * (i % 2) - 1);
            int res = new_board.move(new IntPair(my_head.x + x_dir, my_head.y + y_dir), getCol());
            int new_value;
            if (res == -1) {
                new_value = Integer.MIN_VALUE;
            } else if (res == -2) {
                if (new_board.getLength(getCol()) > new_board.getLength(3 - getCol())) {
                    new_value = Integer.MAX_VALUE;
                } else if (new_board.getLength(getCol()) < new_board.getLength(3 - getCol())) {
                    new_value = Integer.MIN_VALUE;
                } else {
                    new_value = new_board.getLength(getCol()) - new_board.getLength(3 - getCol());;
                }
            } else {
                new_value = min_node(new_board, depth + 1)[0];
            }
            if (value < new_value) {
                value = new_value;
                best_move = i;
            }
        }
        return new int[] {value, best_move};
    }

    private int[] min_node(Board board, int depth) {
        if (depth > limit) {
            return new int[] {cut_off_case(board), -1};
        }
        int value = Integer.MAX_VALUE;
        IntPair opponent_head = board.getHead(3  - getCol());
        int best_move = 0;
        for (int i = 0; i < 4; i++) {
            Board new_board = new Board(board);
            int x_dir = (1 - i / 2) * (2 * (i % 2) - 1);
            int y_dir = (i / 2) * (2 * (i % 2) - 1);
            int res = new_board.move(new IntPair(opponent_head.x + x_dir, opponent_head.y + y_dir), 3 - getCol());
            int new_value;
            if (res == -1) {
                new_value = Integer.MAX_VALUE;
            } else if (res == -2) {
                if (new_board.getLength(getCol()) > new_board.getLength(3 - getCol())) {
                    new_value = Integer.MAX_VALUE;
                } else if (new_board.getLength(getCol()) < new_board.getLength(3 - getCol())) {
                    new_value = Integer.MIN_VALUE;
                } else {
                    new_value = new_board.getLength(getCol()) - new_board.getLength(3 - getCol());
                }
            } else {
                new_value = max_node(new_board, depth + 1)[0];
            }
            if (value > new_value) {
                value = new_value;
                best_move = i;
            }
        }
        return new int[] {value, best_move};
    }
}
