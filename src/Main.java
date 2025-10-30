import Manager.StudentManager;

import java.io.IOException;

import Gui.HomeFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> new Gui.LoginFrame());
        StudentManager manager = new StudentManager();
        manager.loadFromFile();
    }
}