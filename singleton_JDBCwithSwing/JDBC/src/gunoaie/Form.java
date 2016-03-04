package gunoaie;

import aplicatie.dao.JDBCDAO;
import aplicatie.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.lang.String;


public class Form {


//    Connection conn;

    public static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(dbURL, username, password);
    }

    ScrollPane userpanel = new ScrollPane();

    JButton buttoninsert = new JButton("Insereaza");
    JLabel lbid = new JLabel("UserID");
    JLabel lbusername = new JLabel("Username");
    JLabel lbpassword = new JLabel("Password");
    JLabel lbfullname = new JLabel("FullName");
    JLabel lbemail = new JLabel("Email");

    JTextField tfid = new JTextField(1);
    JTextField tfusername = new JTextField(10);
    JTextField tfpassword = new JTextField(10);
    JTextField tffullname = new JTextField(10);
    JTextField tfemail = new JTextField(10);

    JButton buttonsterge = new JButton("Sterge");
    JButton buttonafiseaza = new JButton("Afiseaza");
    JButton buttonmodifica = new JButton("Modifica");
    JButton buttonpopuleaza = new JButton("Populeaza tabela");
    JButton buttoncreaza = new JButton(new AbstractAction("Adauga dinamic") {
        @Override
        public void actionPerformed(ActionEvent e) {


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainframe.add(new JLabel("Bla"));
                    mainframe.validate();
                    mainframe.repaint();
                }
            });
        }
    });
    JLabel headerlabel = new JLabel();
    JPanel panel = new JPanel();
    JFrame mainframe = new JFrame("this is mainframe");
    JLabel statuslabel = new JLabel();
    JTable table = new JTable();


    public Form() {
        prepareGUI();
    }

    private void prepareGUI() {

        mainframe = new JFrame("Java SWING Examples");
        mainframe.setSize(400, 400);
        mainframe.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        headerlabel = new JLabel("", JLabel.LEFT);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(headerlabel, c);


        statuslabel = new JLabel("", JLabel.CENTER);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(statuslabel, c);
        JScrollPane userpanel = new JScrollPane(table);
        userpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(userpanel, c);

        //JOptionPane.showMessageDialog(null, new JScrollPane(table));
        statuslabel.setSize(253, 100);
        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

        });
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        mainframe.add(headerlabel);
        mainframe.add(panel);
        mainframe.add(statuslabel);
        mainframe.setVisible(true);


    }

    public static ResultSet Populatetable() throws SQLException {
        String sql = "select * from users";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);

        return rs;

    }

    private void arataeventForm() {
        headerlabel.setText("TO DO Design");
        buttoninsert.setActionCommand("insert");
        buttonsterge.setActionCommand("sterge");
        buttonafiseaza.setActionCommand("afiseaza");
        buttoncreaza.setActionCommand("creaza");
        buttonmodifica.setActionCommand("modifica");
        buttonpopuleaza.setActionCommand("populeaza");
        buttoninsert.addActionListener(new ButtonClickListener());
        buttonsterge.addActionListener(new ButtonClickListener());
        buttonafiseaza.addActionListener(new ButtonClickListener());
        buttonmodifica.addActionListener(new ButtonClickListener());
        buttonpopuleaza.addActionListener(new ButtonClickListener());
        //adauga elementele in panel
        panel.add(buttoninsert);
        panel.add(lbid);
        panel.add(tfid);
        panel.add(lbemail);
        panel.add(lbusername);
        panel.add(tfusername);
        panel.add(lbpassword);
        panel.add(tfpassword);
        panel.add(lbfullname);
        panel.add(tffullname);
        panel.add(lbemail);
        panel.add(tfemail);
        panel.add(buttonsterge);
        panel.add(buttonafiseaza);
        panel.add(buttonmodifica);
        panel.add(buttoncreaza);
        panel.add(userpanel);
        panel.add(table);

        panel.add(buttonpopuleaza);
        mainframe.setVisible(true);
    }


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("insert")) {
                statuslabel.setText("insert command taken.");
                User user = new User();
                user.setUserName(tfusername.getText());
                user.setPassword(tfpassword.getText());
                user.setFullName(tffullname.getText());
                user.setEmail(tfemail.getText());

                //insert


                //insert
                try {
                    JDBCDAO.INSTANCE.insertUser(user, getConnection());
                    // JDBCInsert.insertUser(user, getConnection());
                    // ((AbstractTableModel)table.getModel()).fireTableCellUpdated(0,0);
                } catch (SQLException e1) {
                    System.out.print(e1);
                }
                table.repaint();


            } else if (command.equals("sterge")) {

                try {
                    JDBCDAO.INSTANCE.deleteUser(Long.parseLong(tfid.getText()), getConnection());
                    // JDBCDelete.deleteUser(Integer.parseInt(tfid.getText()), getConnection());
                } catch (SQLException e1) {
                    System.out.println(e1);
                }
                table.repaint();
                statuslabel.setText("Inregistrarea s-a sters.");
            } else {
                if (command.equals("afiseaza")) {
                    User user = null;
                    try {
                        user = JDBCDAO.INSTANCE.getUser(getConnection(), Long.parseLong(tfid.getText()));
                        // user = JDBCSelect.getUser(getConnection(), Integer.parseInt(tfid.getText()));
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }

                    tffullname.setText(user.getFullName());
                    tfpassword.setText(user.getPassword());
                    tfemail.setText(user.getEmail());
                    tfusername.setText(user.getUserName());

                    statuslabel.setText("afiseaza command taken.");


                } else if (command.equals("creaza")) {
                    statuslabel.setText("creaza");


                } else if (command.equals("populeaza")) {
                    try {
                        table.setModel(JDBCDAO.buildTableModel(Populatetable()));

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
                        JDBCDAO.INSTANCE.updateUser(user, getConnection());
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                    table.repaint();
                    statuslabel.setText("modificarea s-a efectuat");
                } else {
                    statuslabel.setText("or else");
                }
            }
        }

    }


    public static void main(String[] args) {

      new Form().arataeventForm();



    }


}

