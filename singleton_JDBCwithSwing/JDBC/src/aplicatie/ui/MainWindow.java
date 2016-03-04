package aplicatie.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 10/21/2015.
 */
class MainWindow extends JFrame {

    JPanel userPanel = new UserPanel();
    JPanel usersPanel = new UsersPanel();

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation(100, 100);
        setTitle("Main window");

        setLayout(new GridBagLayout());
        add(userPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(usersPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pack();

        // set look and feel
        {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("no nimbus found");
                // If Nimbus is not available, you can set the GUI to another look and feel.
            }
        }

    }

}
