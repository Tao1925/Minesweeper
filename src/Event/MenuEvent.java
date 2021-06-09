package Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.Main;
import UI.MainUI;

public class MenuEvent implements ActionListener {

    JMenuItem menuItem;
    @Override
    public void actionPerformed(ActionEvent e) {
        menuItem = (JMenuItem) e.getSource();
        if  (menuItem.getName().equals("easy")){
            Main.mainUI.dispose();
            Main.mainUI = new MainUI(9, 9);
        }else if  (menuItem.getName().equals("medium")){
            Main.mainUI.dispose();
            Main.mainUI = new MainUI(16, 16);
        }
        if  (menuItem.getName().equals("hard")){
            Main.mainUI.dispose();
            Main.mainUI = new MainUI(24, 30);
        }

    }
}
