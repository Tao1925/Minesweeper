package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import Event.PressEvent;

public class GridButtons extends JPanel {

    public static ArrayList<JButton> buttons;
    public static int[][] mineMap;

    public static int X,Y;
    public static int mineNum;
    public static int mineFlag;
    public static int openCell;


    public GridButtons(int x,int y){
        X = x;
        Y = y;
        mineNum = x * y / 8;
        mineFlag = 0;
        openCell = 0;
        setMine(x ,y);
        init(x, y);
        setName();
        allEvents();
    }

    public void init(int x,int y){
        buttons = new ArrayList<>();
        this.setLayout(new GridLayout(x,y));
        for (int i = 0; i < x * y; i++){
            JButton temp_button = new JButton();
            temp_button.setPreferredSize(new Dimension(MainUI.cellSize,MainUI.cellSize));
            buttons.add(temp_button);
            this.add(temp_button);
        }
    }

    public void setMine(int x, int y){
        mineMap = new int[x][y];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                mineMap[i][j] = 0;
            }
        }
        Random random = new Random();
        for (int i = 0; i < mineNum; i++){
            int rand = random.nextInt(x * y);
            // System.out.println(rand);
            int cur_x = rand / y;
            int cur_y = rand % y;
            if (mineMap[cur_x][cur_y] == 1){
                i--;
            }else {
                mineMap[cur_x][cur_y] = 1;
            }
        }
    }

    public void setName(){
        int len = buttons.size();
        for (int i = 0; i < len; i++){
            buttons.get(i).setName(Integer.toString(i));
        }
    }

    void allEvents(){
        PressEvent pressEvent = new PressEvent();
        for (JButton cur_button:buttons){
            cur_button.addMouseListener(pressEvent);
        }
    }

    public static void mineClicked(){
        for (JButton cur_button:buttons){

        }
    }
}
