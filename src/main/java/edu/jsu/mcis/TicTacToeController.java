package edu.jsu.mcis;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController extends JPanel implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
    }

    public void start(){
        
    }

    public String getMarkAsString(int row, int col) {       
        return (model.getMark(row, col).toString());       
    }

    public TicTacToeView getView() {       
        return view;       
    }

    public TicTacToeModel getModel() {       
        return model;       
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonLoc;
        boolean victoryCheck;
        if (event.getSource() instanceof JButton){
            JButton button = (JButton)(event.getSource());
                int rowNum = 0;
                int colNum = 0;
                System.out.println(button.getName());
                buttonLoc = (button.getName()).replaceAll("Square","");
                rowNum = Integer.parseInt(buttonLoc.replaceFirst("[*0-9]",""));
                buttonLoc = (button.getName()).replaceAll("Square","");
                colNum = Integer.parseInt(buttonLoc.replaceFirst(Integer.toString(rowNum),""));

                model.makeMark(rowNum, colNum);
                
                view.updateSquares();

                view.showResult(model.getResult().toString());

                victoryCheck = model.isGameover();
                
                
                if(victoryCheck == true){
                    view.disableSquares();
                }   
        }
    }
}
