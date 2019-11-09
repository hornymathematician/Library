import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserInformation extends JFrame implements ActionListener {
    Login login = new Login();
    String user = login.getUser();
    String password = login.getPassWord();
    private JFrame userJFrame = new JFrame("个人信息");
    private JLabel jLabel1 = new JLabel("用户名：");
    private JTextField jTextField1 = new JTextField(user, 15);
    private JLabel jLabel2 = new JLabel("出生日期：");
    private JTextField jTextField2 = new JTextField(15);
    private JLabel jLabel3 = new JLabel("电子邮件：");
    private JTextField jTextField3 = new JTextField(15);
    private JPanel jPanel;
    private JButton jButton1 = new JButton("提交");
    private JButton jButton2 = new JButton("返回主菜单");

    //    Login login=new Login();
    public void Information() {
        Init();
//        String user = login.getUser();
//        String password = login.getPassWord();
        JPanel jPanelNorth = new JPanel();
        jPanelNorth.add(jLabel1);
        jPanelNorth.add(jTextField1);
        userJFrame.add(jPanelNorth, BorderLayout.NORTH);

        JPanel jPanelCenter1 = new JPanel();
        jPanelCenter1.add(jLabel2);
        jPanelCenter1.add(jTextField2);
        JPanel jPanelCenter = new JPanel();
        jPanelCenter.add(jPanelCenter1, BorderLayout.NORTH);
        JPanel jPanelCenter2 = new JPanel();
        jPanelCenter2.add(jLabel3);
        jPanelCenter2.add(jTextField3);
        jPanelCenter.add(jPanelCenter2, BorderLayout.SOUTH);
        userJFrame.add(jPanelCenter, BorderLayout.CENTER);

        JPanel jPanelSouth = new JPanel();
        jPanelSouth.add(jButton1);
        jPanelSouth.add(jButton2);
        userJFrame.add(jPanelSouth, BorderLayout.SOUTH);

        jButton1.addActionListener((ActionListener) this);
        jButton2.addActionListener((ActionListener) this);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jTextField1.getText();
                String data = jTextField2.getText();
                String email = jTextField3.getText();
                userDateBase userDateBase = new userDateBase();
                try {
                    boolean result = userDateBase.Insert(username, data, email);
                    if (result) {
                        JOptionPane.showMessageDialog(userJFrame, "修改成功");
                    } else {
                        JOptionPane.showMessageDialog(userJFrame, "该用户名已经存在,请修改用户名");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userJFrame.setVisible(false);
                dispose();
                MenuJFrame menuJFrame = new MenuJFrame();
            }
        });
    }

    public void Init() {
        userJFrame.setSize(400, 200);
        userJFrame.setLocation(500, 500);
        userJFrame.setVisible(true);
        userJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        UserInformation userInformation = new UserInformation();
        userInformation.Information();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
