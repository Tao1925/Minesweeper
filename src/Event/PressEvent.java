package Event;

import UI.GridButtons;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PressEvent implements MouseListener {


    JButton cur_button;
    int status = 0;


    public void clickL(int index){
        cur_button = GridButtons.buttons.get(index);
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
            cur_button.setEnabled(false);
            cur_button.setText(Integer.toString(cnt));
        }
    }

    public void mineClicked(){
        status = 1;
        int len = GridButtons.buttons.size();
        for (int i = 0; i < len; i++) clickL(i);
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
        cur_button.setText("F");
        GridButtons.mineFlag++;
        MainUI.updateLabel();
    }

    void openNear(int index){
        if (!GridButtons.buttons.get(index).isEnabled())return;
        GridButtons.buttons.get(index).setEnabled(false);
        int temp_x = GridButtons.X,temp_y = GridButtons.Y;
        if (index >=temp_y && calNear(index - temp_y) == 0)openNear(index - temp_y);
        if (index < (temp_x - 1) * temp_y && calNear(index + temp_y) == 0)openNear(index + temp_y);
        if ((index % temp_y) != 0 && calNear(index - 1) == 0)openNear(index - 1);
        if ((index % temp_y) != temp_y - 1 && calNear(index + 1) == 0)openNear(index + 1);
    }

    int calNear(int index){
        int cur_x = index / GridButtons.Y;
        int cur_y = index - (cur_x * GridButtons.Y);
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
