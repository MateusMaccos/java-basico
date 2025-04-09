package ui.custom.screen;

import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import model.Space;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static service.EventEnum.CLEAR_SPACE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.JPanel;
import ui.custom.input.NumberText;
import service.BoardService;
import service.NotifierService;
import ui.custom.panel.MainPanel;
import ui.custom.panel.SudokuSector;
import ui.custom.button.FinishGameButton;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);
    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton resetGameButton;
    private JButton checkGameStatusButton;
    private JButton finishGameButton;

    public MainScreen(final Map<String,String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension,mainPanel);
        for(int r=0;r<9;r+=3){
            var endRow = r+2;
            for(int c=0;c<9;c+=3){
                var endColumn = c+2;
                var spaces = getSpacesFromSector(boardService.getSpaces(),c,endColumn,r,endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,final int initCol,final int endCol,final int initRow,final int endRow    ){
        List<Space> spaceSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                spaceSector.add(spaces.get(c).get(r));
            }
        }
        return spaceSector;
    }

    private JPanel generateSection(final List<Space> spaces){
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t->notifierService.subscriber(CLEAR_SPACE,t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e->{
            if(boardService.gameIsFinished()){
                showMessageDialog(null,"Parabéns, vocé ganhou o jogo!");
                resetGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            }else{
                var message = "Seu jogo possui erros! Verifique e tente novamente!";
                showMessageDialog(null,message);
            }
        });
        mainPanel.add(finishGameButton);
    }
    private void addCheckGameStatusButton(JPanel mainPanel) {
        checkGameStatusButton = new FinishGameButton(e->{
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch(gameStatus) {
                case NON_STARTED -> "O jogo ainda nao foi iniciado";
                case COMPLETED -> "Parabéns, vocé ganhou o jogo!";
                case INCOMPLETED -> "Seu jogo possui erros! Verifique e tente novamente!";
            };
            message += hasErrors ? " Verifique e tente novamente!" : " Parabéns, vocé ganhou o jogo!";
            showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetGameButton = new ResetButton(e->{
            var dialogResult = showConfirmDialog(null,"Tem certeza que deseja resetar o jogo?","Limpar o jogo", YES_NO_OPTION,QUESTION_MESSAGE);
            if(dialogResult == 0){
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
            }
        });
        mainPanel.add(resetGameButton);
    }
}
