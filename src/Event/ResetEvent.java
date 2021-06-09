package Event;

import Main.Main;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int temp_x = MainUI.X;
        int temp_y = MainUI.Y;
        Main.mainUI.dispose();
        Main.mainUI = new MainUI(temp_x,temp_y);
    }
}
