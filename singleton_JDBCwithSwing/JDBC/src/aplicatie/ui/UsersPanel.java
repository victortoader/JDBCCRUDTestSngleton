package aplicatie.ui;

import aplicatie.dao.JDBCDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

/**
 * Created by Administrator on 10/21/2015.
 */
class UsersPanel extends JPanel {
    JTable table = new JTable();
    JButton refreshbutton = new JButton("Refresh");

    public static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(dbURL, username, password);
    }

    public static ResultSet Populatetable() throws SQLException {
        String sql = "select * from users";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }

    UsersPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setLayout(new GridBagLayout());
        add(refreshbutton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        refreshbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.setModel(JDBCDAO.buildTableModel(Populatetable()));
                } catch
                        (SQLException e1) {
                    System.out.print(e1.getMessage());
                }
                table.repaint();
            }
        });
        add(new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        try {

            table.setModel(JDBCDAO.buildTableModel(Populatetable()));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }


    }


}

