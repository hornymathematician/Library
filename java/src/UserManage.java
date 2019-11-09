import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserManage extends JFrame implements ActionListener {
    public void User() {
        Login login = new Login();
        String user = login.getUser();
        String password = login.getPassWord();

        JFrame userJFrame = new JFrame("用户信息管理");
        JLabel jLabel1 = new JLabel("用户：");
        JTextField jTextField = new JTextField(user, 15);

        JLabel jLabel2 = new JLabel("密码：");
        JPasswordField jPasswordField = new JPasswordField(password, 15);
        JButton jButton = new JButton("修改");
        JButton jButton1 = new JButton("返回上一级目录");

        JPanel jPanelNorth = new JPanel();
        JPanel jPanelCenter1 = new JPanel();
        JPanel jPanelCenter2 = new JPanel();
        JPanel jPanelCenter = new JPanel();
        JPanel jPanelSouth = new JPanel();
        JLabel jLabelUser = new JLabel("用户信息");
        jPanelNorth.add(jLabelUser);
        jPanelCenter1.add(jLabel1);
        jPanelCenter1.add(jTextField);
        jPanelCenter.add(jPanelCenter1, BorderLayout.NORTH);
        jPanelCenter2.add(jLabel2);
        jPanelCenter2.add(jPasswordField);
        jPanelCenter.add(jPanelCenter2, BorderLayout.CENTER);
        jPanelSouth.add(jButton);
        jPanelSouth.add(jButton1);
        userJFrame.add(jPanelNorth, BorderLayout.NORTH);
        userJFrame.add(jPanelCenter, BorderLayout.CENTER);
        userJFrame.add(jPanelSouth, BorderLayout.SOUTH);
        userJFrame.setVisible(true);
        userJFrame.setSize(400, 400);
        userJFrame.setLocation(500, 500);
        userJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = jTextField.getText();
                String passWord = jPasswordField.getText();
                try {
                    UpdateInformation(user, passWord);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userJFrame.setVisible(false);
                dispose();
                new MenuJFrame();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        UserManage userManage = new UserManage();
        userManage.User();
    }

    public void UpdateInformation(String user, String passWord) throws SQLException, ClassNotFoundException {
        DataBaseConnect dataBaseConnect = new DataBaseConnect();
        dataBaseConnect.UpData(user, passWord);
    }
}