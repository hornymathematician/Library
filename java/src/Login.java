import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Random;

class Login1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBaseConnect connect = new DataBaseConnect();
//        connect.getConnection();
//       connect.Insert("黄洪飞","123456");
        connect.Insert("胡彩月", "123456");
//       connect.Delete("haungg","23132");
        Login login = new Login();
        login.LoginJFrame();
    }
}

class Login extends JFrame implements ActionListener {
    //登录、注册、取消
    JButton btnLogin, btnRegister, btnCancel;
    JPanel pnlSouth, pnlNorth, pnlCenter1, pnlCenter2;//注册面板
    private JLabel jLabel1, jlabel;
    private JLabel jLabelPassWord;
    private JTextField jTextFieldUser, jtextField;
    private JPasswordField jPasswordField;
    static String user, passWord;
    private JPanel imagePanel;
    Code code = new Code();
    JFrame jFrame = new JFrame("图书管理系统");

    //    ImageIcon imageIcon=new ImageIcon("E:\\java\\Login.jpg");
////    private ImageIcon background=new ImageIcon("E:\\java\\Login.jpg");;
//    JFrame jFrame = new JFrame("图书管理系统");
    public void LoginJFrame() {
//        JFrame jFrame = new JFrame("图书管理系统");

        ImageIcon imageIcon = new ImageIcon("E:\\java\\Login.jpg");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel panel = (JPanel) jFrame.getContentPane();
        panel.setOpaque(false);
        jFrame.setLayout(null);

        jTextFieldUser = new JTextField(15);
        pnlNorth = new JPanel();
        jLabel1 = new JLabel("欢迎进入图书管理系统！");
        jLabel1.setFont(new Font("宋体", Font.BOLD, 24));
        pnlNorth.add(jLabel1);
        pnlNorth.setOpaque(false);
        pnlNorth.setBounds(100, 50, 300, 59);
        jFrame.add(pnlNorth);
        //登录
        pnlCenter1 = new JPanel();
        pnlCenter2 = new JPanel();
        JLabel jLabelUser = new JLabel(" 用 户：");
        jLabelUser.setFont(new Font("宋体", Font.BOLD, 16));
        jLabelUser.setOpaque(false);
        jTextFieldUser = new JTextField(15);
        pnlCenter1.add(jLabelUser);
        pnlCenter1.add(jTextFieldUser);
        pnlCenter1.setOpaque(false);

        pnlCenter1.setBounds(100, 100, 300, 60);
        jFrame.add(pnlCenter1);
        //密码
        jLabelPassWord = new JLabel("密 码：");
        jLabelPassWord.setFont(new Font("宋体", Font.BOLD, 16));
        jPasswordField = new JPasswordField(15);
        pnlCenter2.add(jLabelPassWord);
        pnlCenter2.add(jPasswordField);
        pnlCenter2.setBounds(100, 150, 300, 60);
        pnlCenter2.setOpaque(false);
        //验证码
        jlabel = new JLabel("验证码");
        jlabel.setFont(new Font("宋体", Font.BOLD, 16));
        jtextField = new JTextField(6);
        JPanel jPanelCenter = new JPanel();
        jPanelCenter.add(jlabel);
        jPanelCenter.add(jtextField);
        jPanelCenter.add(code);
        jPanelCenter.setBounds(100, 200, 300, 60);
        jPanelCenter.setOpaque(false);
        jFrame.add(jPanelCenter);
        System.out.println(code.generateCode());
        //登录
        jFrame.add(pnlCenter2);
        pnlSouth = new JPanel();
        btnLogin = new JButton("登录");
        btnLogin.addActionListener(this);
        btnRegister = new JButton("注册");
        btnRegister.addActionListener(this);
        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        pnlSouth.add(btnLogin);
        pnlSouth.add(btnRegister);
        pnlSouth.add(btnCancel);
        pnlSouth.setOpaque(false);
        pnlSouth.setBounds(130, 250, 300, 50);
        jFrame.add(pnlSouth);
        jFrame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jFrame.setVisible(true);
        jFrame.setLocation(300, 300);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getUser() {

        return this.user;
    }

    public String getPassWord() {
        return this.passWord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //登录
        DataBaseConnect connect = new DataBaseConnect();
        if (e.getSource() == btnLogin) {
            String user = jTextFieldUser.getText();
            String passWord = jPasswordField.getText();
            Login.user = user;
            Login.passWord = passWord;
            String codes = jtextField.getText();
            if (user.equals("")) {
                JOptionPane.showMessageDialog(this, "请输入账号");
            } else if (user.length() != 0) {
                try {
                    if (connect.Select(user, passWord)) {
                        if (codes.equals(code.getCode())) {
                            JOptionPane.showMessageDialog(this, "恭喜您，登陆成功！");
                            jFrame.setVisible(false);
                            dispose();
                            new MenuJFrame();
                        } else {
                            JOptionPane.showMessageDialog(this, "输入验证码有误！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "该账号不存在！");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //注册
        else if (e.getSource() == btnRegister) {
            String user = jTextFieldUser.getText();
            String password = jPasswordField.getText();
            try {
                boolean result = connect.Insert(user, password);
                if (result) {
                    connect.Insert(user, password);
                    JOptionPane.showMessageDialog(this, "恭喜,注册成功");
//                    setVisible(false);
//                    dispose();
//                    new MenuJFrame();

                } else {
                    JOptionPane.showMessageDialog(this, "该账号已经存在！");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == btnCancel) {
            JOptionPane.showMessageDialog(this, "告辞");
            System.exit(0);
        }
    }
}

class Code extends JComponent implements MouseListener {

    public static String codes;  //自动生成的验证码

    private int width, height = 40;  //设置验证码高度、宽度

    private int codesLength = 4;  //设置代码长度

    private Random random = new Random(); //生成数字的方法

    public Code() {
        width = this.codesLength * 16 + (this.codesLength - 1) * 10; //根据验证码长度设置宽度
        setPreferredSize(new Dimension(width, height));  //设置背景大小
        setSize(width, height);  //设置验证码长度和宽度
        this.addMouseListener(this);
        setToolTipText("点击可更换验证码");
    }

    //得到生成的验证码
    public int getCodesLength() {
        return codesLength;
    }


    //设置验证码的长度
    public void setCodesLength(int codeLength) {
        if (codesLength < 4) {
            this.codesLength = 4;
        } else {
            this.codesLength = codeLength;
        }

    }

    public String getCode() {
        return codes;
    }


    //让验证码产生随机的颜色
    public Color getRandColor(int min, int max) {

        if (min > 255)
            min = 255;
        if (max > 255)
            max = 255;
        int red = random.nextInt(max - min) + min;
        int green = random.nextInt(max - min) + min;
        int blue = random.nextInt(max - min) + min;
        return new Color(red, green, blue);
    }

    // 设置验证码具体的数字或字母是什么
    protected String generateCode() {
        char[] codes = new char[this.codesLength];
        for (int i = 0, len = codes.length; i < len; i++) {
            if (random.nextBoolean()) {
                codes[i] = (char) (random.nextInt(10) + 48);
            } else {
                codes[i] = (char) (random.nextInt(26) + 97);
            }
        }
        Code.codes = new String(codes);
        return Code.codes;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.codes == null || this.codes.length() != this.codesLength) {  //判断生成的验证码是否为空或超出长度
            this.codes = generateCode();
        }
        width = this.codesLength * 16 + (this.codesLength - 1) * 10;
        super.setSize(width, height);  //接口使用，验证码字体大小
        super.setPreferredSize(new Dimension(width, height));//接口使用，验证码背景大小
        Font mFont = new Font("Arial", Font.BOLD | Font.ITALIC, 25);  //设置字体和字体大小
        g.setFont(mFont);  //设置对象
        //绘制出验证码的背景的矩形轮廓
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getRandColor(200, 250));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(getRandColor(180, 200));
        g2d.drawRect(0, 0, width - 1, height - 1);
        //绘制出验证码背景的线
        int i = 0, len = 150;
        for (; i < len; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(width - 10) + 10;
            int y1 = random.nextInt(height - 4) + 4;
            g2d.setColor(getRandColor(180, 200));
            g2d.drawLine(x, y, x1, y1);
        }


        //绘制出验证码的具体字母
        i = 0;
        len = this.codesLength;
        FontMetrics fm = g2d.getFontMetrics();
        int base = (height - fm.getHeight()) / 2 + fm.getAscent();
        for (; i < len; i++) {
            int b = random.nextBoolean() ? 1 : -1;
            g2d.rotate(random.nextInt(10) * 0.01 * b);
            g2d.setColor(getRandColor(20, 130));
            g2d.drawString(codes.charAt(i) + "", 16 * i + 10, base);
        }
    }

    //下一个验证码
    public void nextCode() {
        generateCode();
        repaint();
        ;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        codes = generateCode();
        repaint();
//        nextCode();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}


