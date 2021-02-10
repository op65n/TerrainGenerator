package op65n.tech.terraingenerator.generation.button;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class ButtonComponent extends JButton{

    public abstract JButton handle();

    public ButtonComponent defaultHandle() {
        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(new Color(74, 74, 74));
        setForeground(Color.WHITE);

        final Border line = new LineBorder(Color.BLACK);
        final Border margin = new EmptyBorder(5, 15, 5, 15);
        final Border compound = new CompoundBorder(line, margin);
        setBorder(compound);

        return this;
    }

}
