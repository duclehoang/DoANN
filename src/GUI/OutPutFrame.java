package GUI;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;

public class OutPutFrame {
    public JFrame f;
    public JScrollPane jScrollPane;
    public TextArea area;
    public OutPutFrame() {

        f = new JFrame();
       area = new TextArea();
        area.setBounds(4, 20, 780, 240);
        area.setEditable(false);
        area.setBackground(new Color(193, 141, 106));
        f.setTitle("Out Put");
        f.add(area);
      //  f.setLocationRelativeTo(null);
        f.setBounds(390,240,800, 300);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(false);

    }


}
