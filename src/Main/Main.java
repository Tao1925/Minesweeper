package Main;

import UI.MainUI;

import java.net.PortUnreachableException;

public class Main {

    public static MainUI mainUI;
    public static void main(String[] args) {
        mainUI = new MainUI(9, 9);
    }
}
