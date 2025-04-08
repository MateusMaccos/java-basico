package ui.custom.screen;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.JPanel;

import service.BoardService;

import ui.custom.panel.MainPanel;
import ui.custom.button.FinishGameButton;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);
    private final BoardService boardService;

    private JButton resetGameButton;
    private JButton checkGameStatusButton;
    private JButton finishGameButton;

    public MainScreen(final Map<String,String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        var dimension = new Dimension(600, 600);
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension,mainPanel);
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e->{
            if(boardService.gameIsFinished()){
                JOptionPane.showMessageDialog(null,"Parabéns, vocé ganhou o jogo!");
                resetGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            }else{
                var message = "Seu jogo possui erros! Verifique e tente novamente!";
                JOptionPane.showMessageDialog(null,message);
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
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetGameButton = new ResetButton(e->{
            var dialogResult = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja resetar o jogo?","Limpar o jogo", YES_NO_OPTION,QUESTION_MESSAGE);
            if(dialogResult == 0){
                boardService.reset();
            }
        });
        mainPanel.add(resetGameButton);
    }
}
