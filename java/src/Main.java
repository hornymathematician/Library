import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBaseConnect connect = new DataBaseConnect();
//        connect.getConnection();
//       connect.Insert("黄洪飞","123456");
//        connect.Insert("胡彩月", "123456");
//       connect.Delete("haungg","23132");
//
        connect.AddBook("高数", "黄鸿飞", "999", 2);
    }
}

//class Login extends JFrame implements ActionListener {
//
//    //登录、注册、取消
//    private JButton btnLogin, btnRegister, btnCancel;
//    private JTextField jTextFieldUser, jTextFieldPassWord;
//
//    Login(String title) {
//        super(title);
//        JPanel pnlNorth = new JPanel();
//        JLabel jLabel1 = new JLabel("欢迎进入图书管理系统！");
//        pnlNorth.add(jLabel1);
//        this.add(pnlNorth, BorderLayout.NORTH);
//        //登录
//        JPanel pnlCenter = new JPanel();
//        JPanel pnlCenter1 = new JPanel();
//        //注册面板
//        JPanel pnlCenter2 = new JPanel();
//        pnlCenter.setLayout(new BorderLayout());
//        //用户
//        JLabel jLabelUser = new JLabel(" 用 户：");
//        jTextFieldUser = new JTextField(15);
//        pnlCenter1.add(jLabelUser);
//        pnlCenter1.add(jTextFieldUser);
//        pnlCenter.add(pnlCenter1, BorderLayout.NORTH);
//        //密码
//        JLabel jLabelPassWord = new JLabel("密 码：");
//        jTextFieldPassWord = new JTextField(15);
//        pnlCenter2.add(jLabelPassWord);
//        pnlCenter2.add(jTextFieldPassWord);
//        pnlCenter.add(pnlCenter2, BorderLayout.CENTER);
//        this.add(pnlCenter, BorderLayout.CENTER);
//        JPanel pnlSouth = new JPanel();
//        btnLogin = new JButton("登录");
//        btnLogin.addActionListener(this);
//        btnRegister = new JButton("注册");
//        btnRegister.addActionListener(this);
//        btnCancel = new JButton("取消");
//        btnCancel.addActionListener(this);
//        pnlSouth.add(btnLogin);
//        pnlSouth.add(btnRegister);
//        pnlSouth.add(btnCancel);
//        this.add(pnlSouth, BorderLayout.SOUTH);
//        this.setSize(400, 200);
//        this.setVisible(true);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        //登录
//        DataBaseConnect connect = new DataBaseConnect();
//        if (e.getSource() == btnLogin) {
//            String user = jTextFieldUser.getText();
//            if (user.equals("")) {
//                JOptionPane.showMessageDialog(this, "请输入账号");
//            } else {
//                try {
//                    if (!connect.Select(user)) {
//                        JOptionPane.showMessageDialog(this, "恭喜您，登陆成功！");
//                        setVisible(false);
//                        new MenuJFrame().setVisible(true);
//                        dispose();
//
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(this, "该用户不存在,请先注册");
//                    }
//                } catch (SQLException | ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        //注册
//        else if (e.getSource() == btnRegister) {
//            String user = jTextFieldUser.getText();
//            String password = jTextFieldPassWord.getText();
//            try {
//                boolean result = connect.Insert(user, password);
//                if (result) {
//                    connect.Insert(user, password);
//                    JOptionPane.showMessageDialog(this, "恭喜,注册成功");
//                } else {
//                    JOptionPane.showMessageDialog(this, "该账号已经存在！");
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//
//        } else if (e.getSource() == btnCancel) {
//            JOptionPane.showMessageDialog(this, "告辞");
//            System.exit(0);
//        }
//    }
//}
