package aplicatie.ui;

import aplicatie.dao.JDBCDAO;
import aplicatie.user.User;
import aplicatie.user.UserDao;
import aplicatie.user.UserDaoJdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Administrator on 10/21/2015.
 */
class UserPanel extends JPanel {

    private UserDao userDao;

    ScrollPane userpanel = new ScrollPane();

    JButton buttoninsert = new JButton("Insereaza");
    JLabel lbid = new JLabel("UserID");
    JLabel lbusername = new JLabel("Username");
    JLabel lbpassword = new JLabel("Password");
    JLabel lbfullname = new JLabel("FullName");
    JLabel lbemail = new JLabel("Email");

    JTextField tfid = new JTextField(2);
    JTextField tfusername = new JTextField(10);
    JTextField tfpassword = new JTextField(10);
    JTextField tffullname = new JTextField(10);
    JTextField tfemail = new JTextField(10);
    JLabel statuslabel = new JLabel();

    JButton buttonsterge = new JButton("Sterge");
    JButton buttonafiseaza = new JButton("Afiseaza");
    JButton buttonmodifica = new JButton("Modifica");
    JButton buttonpopuleaza = new JButton("Populeaza tabela");
    // JButton buttoncreaza = new JButton(new AbstractAction("Adauga dinamic");

    UserPanel() {
        // add(new JLabel("user goes here"));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.GREEN));
        add(buttoninsert, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbid, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tfid, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbusername, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tfusername, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbpassword, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tfpassword, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbpassword, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tfpassword, new GridBagConstraints(2, 4, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbfullname, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tffullname, new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(lbemail, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(tfemail, new GridBagConstraints(2, 6, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));

        add(buttonsterge, new GridBagConstraints(1, 7, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
        add(buttonafiseaza, new GridBagConstraints(1, 7, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(buttonafiseaza, new GridBagConstraints(2, 7, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(buttonmodifica, new GridBagConstraints(3, 7, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(buttonpopuleaza, new GridBagConstraints(4, 7, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        add(statuslabel, new GridBagConstraints(8, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));


        buttoninsert.setActionCommand("insert");
        buttonsterge.setActionCommand("sterge");
        buttonafiseaza.setActionCommand("afiseaza");
        //  buttoncreaza.setActionCommand("creaza");
        buttonmodifica.setActionCommand("modifica");
        buttonpopuleaza.setActionCommand("populeaza");
        buttoninsert.addActionListener(new ButtonClickListener());
        buttonsterge.addActionListener(new ButtonClickListener());
        buttonafiseaza.addActionListener(new ButtonClickListener());
        buttonmodifica.addActionListener(new ButtonClickListener());

        userDao = new UserDaoJdbc();
//        userDao = new UserDaoHibernate();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("insert")) {
                statuslabel.setText("Userul s-a inserat.");
                User user = new User();
                user.setUserName(tfusername.getText());
                user.setPassword(tfpassword.getText());
                user.setFullName(tffullname.getText());
                user.setEmail(tfemail.getText());

                //insert
                try {
                    userDao.insertUser(user);
                    // JDBCInsert.insertUser(user, getConnection());
                    // ((AbstractTableModel)table.getModel()).fireTableCellUpdated(0,0);
                } catch (SQLException e1) {
                    System.out.print(e1);
                }
                //   table.repaint();


            } else if (command.equals("sterge")) {

                try {
                    userDao.deleteUser(Long.parseLong(tfid.getText()));

                } catch (SQLException e1) {
                    System.out.println(e1);
                }
                //  table.repaint();
                statuslabel.setText("Inregistrarea s-a sters.");
            } else {
                if (command.equals("afiseaza")) {
                    User user = null;
                    tffullname.setText("");
                    tfpassword.setText("");
                    tfemail.setText("");
                    tfusername.setText("");

                    try {
                        user = userDao.getUser(Long.parseLong(tfid.getText()));

                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                    if (user==null) {
                        statuslabel.setText("Userul nu exista");
                    } else {
                        tffullname.setText(user.getFullName());
                        tfpassword.setText(user.getPassword());
                        tfemail.setText(user.getEmail());
                        tfusername.setText(user.getUserName());

                        statuslabel.setText("Userul s-a afisat");
                    }


                } else if (command.equals("creaza")) {
                    statuslabel.setText("creaza");


                } else if (command.equals("populeaza")) {
                    try {
                        new UsersPanel().table.setModel(JDBCDAO.buildTableModel(UsersPanel.Populatetable()));

                    } catch (SQLException e1) {
                        System.out.print(e1);
                    }
                    // table.setSelectionModel(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//                    JButton button=new JButton("delete");
                    statuslabel.setText("Tabela s-a populat");
                } else if (command.equals("modifica")) {
                    User user = new User();


                    user.setId(Long.parseLong(tfid.getText()));
                    user.setFullName(tffullname.getText());
                    user.setPassword(tfpassword.getText());
                    user.setEmail(tfemail.getText());
                    user.setUserName(tfusername.getText());
                    try {
                        userDao.updateUser(user);
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                    //  table.repaint();
                    statuslabel.setText("modificarea s-a efectuat");
                } else {
                    statuslabel.setText("or else");
                }
            }
        }


    }


}

