package tictactoe;

import java.util.ArrayList;


interface Mapper {
    void value(int x);
}
//PlayerId can change
interface IGameState {
    int getState(int row,int col); //0,1,2 or enum
    void setState(int row,int col,int player) throws IllegalArgumentException;
    void mapRow(int row, Mapper mapper);
    void mapCol(int col, Mapper mapper);
    void mapForwardDiag(Mapper mapper);
    void mapBackwardDiag(Mapper mapper);



}
class GameState implements IGameState {
    private int n;
    private int maxPlayers;
    private ArrayList<ArrayList<Integer>> state = new ArrayList<>();

    public GameState(int n,int p) {
        this.n = n;
        this.maxPlayers = p;
        for (int j = 0;j<n;++j) {
            ArrayList<Integer> localState = new ArrayList<>();
            for (int k = 0;k<n;++k) {
                localState.add(0);
            }

            state.add(localState);
        }
    }

    @Override
    public int getState(int row, int col) {
        return state.get(row).get(col);
    }

    @Override
    public void setState(int row, int col, int player) {
        if (player > 0 && player <= maxPlayers) {
            //2 is hardcoded but can be config
            state.get(row).set(col,player);
        }else {
            throw new IllegalArgumentException("player is greater than max Player " + maxPlayers);
        }

    }

    @Override
    public void mapRow(int row, Mapper mapper) {
        for (int j = 0;j<n;++j) {
            mapper.value(state.get(row).get(j));
        }
    }

    @Override
    public void mapCol(int col, Mapper mapper) {
        for (int j = 0;j<n;++j) {
            mapper.value(state.get(j).get(col));
        }
    }

    @Override
    public void mapForwardDiag(Mapper mapper) {
        int j = 0;
        int k = 0;

        while (j < n && k < n) {
            mapper.value(state.get(j).get(k));
            ++j;
            ++k;
        }
    }

    @Override
    public void mapBackwardDiag(Mapper mapper) {
        int j = n-1;
        int k = 0;

        while (j >= 0 && k < n) {
            mapper.value(state.get(j).get(k));
            --j;
            ++k;
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArrayList<Integer> row : state) {
            for (Integer x : row) {
                stringBuilder.append(x + ",");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
class RowCol {
    private int row;
    private int col;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
interface IGameRule {
    boolean checkWin(int playerId,IGameState gameState,RowCol hint);
}

class GameRule implements IGameRule{

    @Override
    public boolean checkWin(int playerId, IGameState gameState, RowCol hint) {

        final boolean[] retValue = {true};
        gameState.mapForwardDiag(x -> {
            if (x != playerId) {
                retValue[0] = false;
            }
        });

        if (retValue[0]) {
            return true;
        }

        retValue[0] = true;
        gameState.mapBackwardDiag(x -> {
            if (x != playerId) {
                retValue[0] = false;
            }
        });

        if (retValue[0]) {
            return true;
        }

        retValue[0] = true;
        gameState.mapRow(hint.getRow(), x -> {
            if (x != playerId) {
                retValue[0] = false;
            }
        });

        if (retValue[0]) {
            return true;
        }

        retValue[0] = true;
        gameState.mapCol(hint.getCol(), x -> {
            if (x != playerId) {
                retValue[0] = false;
            }
        });

        return retValue[0];

    }
}
class CreatorPolicy {
    IGameState create(int n) {
        return new GameState(n,2);
    }

    IGameRule createRule() {
        return new GameRule();
    }
}
class TicTacToe {

    private IGameState gameState;
    private IGameRule gameRule;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        gameState = new CreatorPolicy().create(n);
        gameRule = new CreatorPolicy().createRule();
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        gameState.setState(row,col,player);
        boolean win =  gameRule.checkWin(player,gameState,new RowCol(row, col));
        if (win) {
            return player;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "TicTacToe{" +
                "gameState=\n" + gameState +
                '}';
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(2);
        System.out.println(ticTacToe);
        ticTacToe.move(0,1,1);
        System.out.println(ticTacToe);
        ticTacToe.move(1,1,2);
        System.out.println(ticTacToe);
        ticTacToe.move(1,0,1);
        System.out.println(ticTacToe);
//        ticTacToe.move(1,1,2);
//        System.out.println(ticTacToe);
//        ticTacToe.move(2,0,1);
//        System.out.println(ticTacToe);
//        ticTacToe.move(1,0,2);
//        System.out.println(ticTacToe);
//        ticTacToe.move(2,1,1);
//        System.out.println(ticTacToe);
    }
}



