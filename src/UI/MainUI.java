package UI;

import javax.swing.*;
import java.awt.*;
import Event.ResetEvent;
import Event.MenuEvent;

public class MainUI extends JFrame{

    JPanel control_panel;
    JButton reset_button;
    public static JLabel info_label;
    GridButtons gridButtons;

    public static int X;
    public static int Y;

    final static int cellSize = 50;

    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem menuItem1_1,menuItem1_2,menuItem1_3;

    public MainUI(int x,int y){
        X = x;
        Y = y;
        init();
        setVisible(true);
        // setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
    }

    public void init(){

        // set menu bar
        menuBar = new JMenuBar();
        menu1 = new JMenu("Difficulty");
        menuItem1_1 = new JMenuItem("Easy");
        menuItem1_2 = new JMenuItem("Medium");
        menuItem1_3 = new JMenuItem("Hard");
        menu1.add(menuItem1_1);
        menu1.add(menuItem1_2);
        menu1.add(menuItem1_3);
        menuBar.add(menu1);
        this.setJMenuBar(menuBar);
        // set control panel
        control_panel = new JPanel();
        control_panel.setSize(Y * cellSize,100);
        control_panel.setLayout(new BorderLayout());

        reset_button = new JButton("RESET");
        control_panel.add(reset_button,BorderLayout.WEST);
        gridButtons = new GridButtons(X,Y);
        info_label = new JLabel("mines remain:"+ GridButtons.mineNum +"/" + GridButtons.mineNum);
        control_panel.add(info_label,BorderLayout.EAST);


        this.setSize(Y * cellSize,100 + X * cellSize);
        this.setLayout(new BorderLayout());
        // gridButtons = new GridButtons(X,Y);
        this.add(control_panel,BorderLayout.NORTH);
        this.add(gridButtons,BorderLayout.SOUTH);


        setName();
        allEvents();

    }

    public static void updateLabel(){
        info_label.setText("mines remain:"+ (GridButtons.mineNum - GridButtons.mineFlag) +"/" + GridButtons.mineNum);
    }

    public static void checkFinished(){
        GridButtons.openCell++;
        System.out.println("openCell="+GridButtons.openCell);
        if (GridButtons.openCell + GridButtons.mineNum == GridButtons.X * GridButtons.Y){
            info_label.setText("Finished");
        }
    }


    void setName(){
        menuItem1_1.setName("easy");
        menuItem1_2.setName("medium");
        menuItem1_3.setName("hard");
    }
    void allEvents(){
        ResetEvent resetEvent = new ResetEvent();
        reset_button.addActionListener(resetEvent);

        MenuEvent menuEvent = new MenuEvent();
        menuItem1_1.addActionListener(menuEvent);
        menuItem1_2.addActionListener(menuEvent);
        menuItem1_3.addActionListener(menuEvent);
    }
}
