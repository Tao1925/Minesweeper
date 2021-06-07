package UI;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame{

    JPanel control_panel;
    JButton reset_button;
    public static JLabel info_label;
    GridButtons gridButtons;

    final static int X = 9;
    final static int Y = 9;


    public MainUI(){
        init();
        setVisible(true);
        // setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
    }

    public void init(){
        // set control panel
        control_panel = new JPanel();
        control_panel.setSize(500,100);
        control_panel.setLayout(new BorderLayout());

        reset_button = new JButton("RESET");
        control_panel.add(reset_button,BorderLayout.WEST);
        gridButtons = new GridButtons(X,Y);
        info_label = new JLabel("mines remain:"+ GridButtons.mineNum +"/" + GridButtons.mineNum);
        control_panel.add(info_label,BorderLayout.EAST);


        this.setSize(450,550);
        this.setLayout(new BorderLayout());
        // gridButtons = new GridButtons(X,Y);
        this.add(control_panel,BorderLayout.NORTH);
        this.add(gridButtons,BorderLayout.SOUTH);

    }

    public static void updateLabel(){
        info_label.setText("mines remain:"+ (GridButtons.mineNum - GridButtons.mineFlag) +"/" + GridButtons.mineNum);
    }
}
