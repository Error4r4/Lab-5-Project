import Manager.StudentManager;

import java.io.IOException;

import Gui.HomeFrame;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentManager manager = new StudentManager();
        manager.loadFromFile("src/Students.txt"); // لو موجود

        new HomeFrame(manager);
    }
}