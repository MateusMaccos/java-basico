package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Board;
import model.GameStatusEnum;
import model.Space;

public class BoardService {
    private final static int BOARD_SIZE = 9;

    private final Board board;

    public BoardService(final Map<String,String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() { return board.getSpaces(); }

    public void reset(){
        this.board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatusEnum getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(Map<String,String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                var positionConfig = gameConfig.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected,fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        return spaces;
    }
}
