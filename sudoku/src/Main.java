import java.util.Scanner;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import model.Board;
import model.Space;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    
    private static Board board;

    private final static int BOARD_SIZE = 9;

    public static void main(String[] args) {
        final var positions = Stream.of(args).collect(
            toMap(k->k.split(";")[0],v->v.split(";")[1])
        );
        var option = -1;
        while(true){
            System.out.println("Selecione a opção desejada:");
            System.out.println("1 - Iniciar jogo");
            System.out.println("2 - Colocar valor na posição");
            System.out.println("3 - Limpar posição");
            System.out.println("4 - Visualizar tabuleiro");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar tabuleiro");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();
            switch(option){
                case 1:
                    startGame(positions);
                    break;
                case 2:
                    inputNumber();
                    break;
                case 3:
                    removeNumber();
                    break;
                case 4:
                    showCurrentGame();
                    break;
                case 5:
                    showGameStatus();
                    break;
                case 6:
                    clearGame();
                    break;
                case 7:
                    finishGame();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
        }
    }
}

    private static void clearGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearGame'");
    }

    private static void finishGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finishGame'");
    }

    private static void showGameStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showGameStatus'");
    }

    private static void showCurrentGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showCurrentGame'");
    }

    private static void removeNumber() {
        if(isNull(board)){
            System.out.println("Jogo ainda não iniciado!");
            return;
        }

        System.out.println("Informe a coluna:");
        var col = runUntilGetValidNumber(0,BOARD_SIZE-1);

        System.out.println("Informe a linha:");
        var row = runUntilGetValidNumber(0,BOARD_SIZE-1);

        if(board.clearValue(col,row)){
            System.out.println("Posição da linha %s, coluna %s removida com sucesso!".formatted(row,col));
        }
    }

    private static void inputNumber() {
        if(isNull(board)){
            System.out.println("Jogo ainda não iniciado!");
            return;
        }

        System.out.println("Informe a coluna:");
        var col = runUntilGetValidNumber(0,BOARD_SIZE-1);

        System.out.println("Informe a linha:");
        var row = runUntilGetValidNumber(0,BOARD_SIZE-1);

        System.out.println("Informe o valor na posição: %s,%s".formatted(col,row));
        var value = runUntilGetValidNumber(1,BOARD_SIZE);

        if(board.changeValue(col,row,value)){
            System.out.println("Posição da linha %s, coluna %s alterada com sucesso!".formatted(row,col));
        }
    }

    private static void startGame(Map<String, String> positions) {
        if(nonNull(board)){
            System.out.println("Jogo já iniciado!");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected,fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
        System.out.println("Jogo pronto para iniciar!");
    }
    
    private static int runUntilGetValidNumber(final int min, final int max) {
        var current = scanner.nextInt();
        while(current < min || current > max){
            System.out.println("Informe um valor entre %s e %s:".formatted(min,max));
            current = scanner.nextInt();
        }
        return current;
    }
}