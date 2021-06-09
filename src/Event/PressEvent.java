package Event;

import UI.GridButtons;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PressEvent implements MouseListener {


    JButton cur_button;
    int status = 0;


    public void clickL(int index){
        cur_button = GridButtons.buttons.get(index);
        if (!cur_button.isEnabled()) return;
        int cur_x = index / GridButtons.Y;
        int cur_y = index - (cur_x * GridButtons.Y);
        if (GridButtons.mineMap[cur_x][cur_y] == 1){
            // System.out.println("oops!");
            cur_button.setEnabled(false);
            cur_button.setText("*");
            if (status == 0)mineClicked();
            return;
        }
        int cnt = calNear(index);
        if (cnt == 0){
            cur_button.setText("");
            openNear(index);
        }else {
            // if (!cur_button.isEnabled()) return;
            cur_button.setEnabled(false);
            MainUI.checkFinished();
            cur_button.setText(Integer.toString(cnt));
        }
    }

    public void mineClicked(){
        status = 1;
        int len = GridButtons.buttons.size();
        for (int i = 0; i < len; i++) clickL(i);
        MainUI.info_label.setText("Failed");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cur_button = (JButton) e.getSource();
        int index = Integer.parseInt(cur_button.getName());
        if (e.getButton() == MouseEvent.BUTTON1){
            clickL(index);
        }else if (e.getButton() == MouseEvent.BUTTON3){
            clickR(index);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void clickR(int index){
        cur_button = GridButtons.buttons.get(index);
        // in case clickR a button mutiable times
        if (cur_button.getText().equals("F"))return;
        cur_button.setText("F");
        GridButtons.mineFlag++;
        MainUI.updateLabel();
    }

    void openNear(int index){

        Queue<Integer> q = new LinkedList<>();
        q.add(index);
        Set<Integer> emptyCell = new HashSet<>();
        int temp_x = GridButtons.X,temp_y = GridButtons.Y;
        emptyCell.add(index);
        while(!q.isEmpty()){
            int temp = q.poll();
            if (temp >=temp_y && calNear(temp - temp_y) == 0) {
                if (!emptyCell.contains(temp - temp_y)){
                    emptyCell.add(temp - temp_y);
                    q.add(temp - temp_y);
                }
            }
            if (temp < (temp_x - 1) * temp_y && calNear(temp + temp_y) == 0){
                if (!emptyCell.contains(temp + temp_y)){
                    emptyCell.add(temp + temp_y);
                    q.add(temp + temp_y);
                }
            }
            if ((temp % temp_y) != 0 && calNear(temp - 1) == 0){
                if (!emptyCell.contains(temp - 1)){
                    emptyCell.add(temp - 1);
                    q.add(temp - 1);
                }
            }
            if ((temp % temp_y) != temp_y - 1 && calNear(temp + 1) == 0){
                if (!emptyCell.contains(temp + 1)){
                    emptyCell.add(temp + 1);
                    q.add(temp + 1);
                }
            }
        }

        for (int temp:emptyCell){
            GridButtons.buttons.get(temp).setEnabled(false);
            MainUI.checkFinished();
        }
        for (int temp:emptyCell){
            int cur_x = temp / GridButtons.Y;
            int cur_y = temp - (cur_x * GridButtons.Y);
            for (int i = -1; i <= 1; i++){
                if (cur_x + i < 0||cur_x + i >= GridButtons.X)continue;
                for (int j = -1; j <= 1; j++){
                    if (cur_y + j < 0||cur_y + j >= GridButtons.Y)continue;
                    int temp_index = (cur_x + i) * GridButtons.Y + cur_y + j;
                    if (!GridButtons.buttons.get(temp_index).isEnabled())continue;
                    GridButtons.buttons.get(temp_index).setEnabled(false);
                    MainUI.checkFinished();
                    if (calNear(temp_index) != 0)
                        GridButtons.buttons.get(temp_index).setText(Integer.toString(calNear(temp_index)));
                }
            }
        }

        /*
        if (!GridButtons.buttons.get(index).isEnabled())return;
        GridButtons.buttons.get(index).setEnabled(false);
        if (index >=temp_y && calNear(index - temp_y) == 0)openNear(index - temp_y);
        if (index < (temp_x - 1) * temp_y && calNear(index + temp_y) == 0)openNear(index + temp_y);
        if ((index % temp_y) != 0 && calNear(index - 1) == 0)openNear(index - 1);
        if ((index % temp_y) != temp_y - 1 && calNear(index + 1) == 0)openNear(index + 1);
        int cur_x = index / GridButtons.Y;
        int cur_y = index - (cur_x * GridButtons.Y);
        for (int i = -1; i <= 1; i++){
            if (cur_x + i < 0||cur_x + i >= GridButtons.X)continue;
            for (int j = -1; j <= 1; j++){
                if (cur_y + j < 0||cur_y + j >= GridButtons.Y)continue;
                int temp_index = (cur_x + i) * GridButtons.Y + cur_y + j;
                GridButtons.buttons.get(temp_index).setEnabled(false);
                if (calNear(temp_index) != 0)
                    GridButtons.buttons.get(temp_index).setText(Integer.toString(calNear(temp_index)));
            }
        }

         */
    }

    int calNear(int index){
        int cur_x = index / GridButtons.Y;
        int cur_y = index % GridButtons.Y;
        // System.out.println("cur_x="+cur_x+" cur_y="+cur_y);
        if (GridButtons.mineMap[cur_x][cur_y] == 1){
            return -1;
        }
        int cnt = 0;
        for (int i = -1; i <= 1; i++){
            if (cur_x + i < 0||cur_x + i >= GridButtons.X)continue;
            for (int j = -1; j <= 1; j++){
                if (cur_y + j < 0||cur_y + j >= GridButtons.Y)continue;
                if (GridButtons.mineMap[cur_x + i][cur_y + j] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
