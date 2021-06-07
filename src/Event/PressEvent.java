package Event;

import UI.GridButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PressEvent implements ActionListener {


    JButton cur_button;

    @Override
    public void actionPerformed(ActionEvent e) {
        cur_button = (JButton) e.getSource();
        int index = Integer.parseInt(cur_button.getName());
        cur_button.setEnabled(false);
        int button_value = calBtnVal(index);
        if (button_value == 0){
            cur_button.setText("");
        }else if (button_value == -1){
            cur_button.setText("*");
        }else {
            cur_button.setText(Integer.toString(button_value));
        }
    }

    public int calBtnVal(int index){
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
