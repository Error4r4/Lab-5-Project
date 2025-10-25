package Gui;

import javax.swing.*;
import java.awt.*;
import Manager.StudentManager;

public abstract class BaseStudentPanel extends JPanel {
    protected StudentManager manager;
    public BaseStudentPanel(StudentManager manager) {
        this.manager = manager;
        setBackground(new Color(245, 245, 245));
        // لا نفرض Layout هنا نهائياً — يسمح للكلاسات الفرعية باختيار الأنسب.
    }

    /** عرض رسالة موحّدة (استبدل JOptionPane في كل مكان بداله). */
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /** دالة تجريدية لإعداد واجهة كل Panel */
    public abstract void setupUI();
}
