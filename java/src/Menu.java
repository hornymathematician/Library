import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public static void main(String[] args) {
        new MenuJFrame();
    }
}

class MenuJFrame extends JFrame implements ActionListener {
    private JButton jButton1 = new JButton("增加图书");
    private JFrame jFrame = new JFrame("图书菜单窗口");
    private JButton jButton2 = new JButton("删除图书");

    public MenuJFrame() {
//        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("E:\\java\\Menu.gif");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel panel = (JPanel) jFrame.getContentPane();
        panel.setOpaque(false);
        jFrame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jFrame.setResizable(false);
        jFrame.setLocation(300,300);
//        jFrame.setLayout(null);

        JPanel jPanel1 = new JPanel(new BorderLayout());
        //创建面板
        jPanel1.setOpaque(false);
        JTabbedPane tabbedPane = new JTabbedPane();
        JToolBar toolBar1 = new JToolBar();
        toolBar1.setOpaque(false);
        toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JToolBar jToolBar2 = new JToolBar();
        jToolBar2.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        jToolBar2.setOpaque(false);
        JToolBar jToolBar3 = new JToolBar();
        jToolBar3.setOpaque(false);
        jToolBar3.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JToolBar jToolBar4 = new JToolBar();
        jToolBar4.setOpaque(false);
        jToolBar4.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        tabbedPane.add("图书管理", toolBar1);
        tabbedPane.setOpaque(false);
        jButton1.setOpaque(false);
        jButton1.setFont(new Font("宋体", Font.BOLD, 16));
        jButton2.setFont(new Font("宋体", Font.BOLD, 16));
//        JButton jButton1 = new JButton("增加图书");
//        JButton jButton2 = new JButton("删除图书");
        toolBar1.add(jButton1);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        toolBar1.add(jButton2);

        tabbedPane.add("借书", jToolBar2);
        JButton jButton3 = new JButton("借书");
        jButton3.setFont(new Font("宋体", Font.BOLD, 16));
        jButton3.addActionListener(this);
        JButton jButton4 = new JButton("查看书籍目录");
        jButton4.setFont(new Font("宋体", Font.BOLD, 16));
        jButton4.addActionListener(this);
        jToolBar2.add(jButton3);
        jToolBar2.add(jButton4);

        tabbedPane.add("还书", jToolBar3);
        JButton jButton5 = new JButton("还书");
        jButton5.setFont(new Font("宋体", Font.BOLD, 16));
        jButton5.addActionListener(this);
        JButton jButton6 = new JButton("选项6");
        jToolBar3.add(jButton5);
        jToolBar3.add(jButton6);
        tabbedPane.add("用户信息管理", jToolBar4);
        JButton jButton7 = new JButton("个人信息管理");
        jButton7.setFont(new Font("宋体", Font.BOLD, 16));
        JButton jButton8 = new JButton("完善用户其它信息");
        jButton8.setFont(new Font("宋体", Font.BOLD, 16));
        jToolBar4.add(jButton7);
        jToolBar4.add(jButton8);
        jFrame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
        jFrame.getContentPane().add(jPanel1, BorderLayout.CENTER);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前选项卡：" + tabbedPane.getSelectedIndex());
            }
        });

        tabbedPane.setSelectedIndex(1);
        jFrame.setVisible(true);
        //借书按钮监听器
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                dispose();
                BorrowBook borrowBook = new BorrowBook();
                borrowBook.Remind();
            }
        });
        //还书按钮监听器
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                dispose();
                ReturnBook returnBook = new ReturnBook();
                returnBook.returnBook();
            }
        });
        //个人 用户管理监听器
        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                dispose();
                UserManage userManage = new UserManage();
                userManage.User();
            }
        });
        //完善用户信息监听器
        jButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                dispose();
                UserInformation userInformation = new UserInformation();
                userInformation.Information();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            jFrame.setVisible(false);
            dispose();
//            dispose();
//            JFrame jFrame1 = new JFrame("新窗口");
//            jFrame1.setVisible(true);
            BookManege book = new BookManege();
            book.addBook();
        } else if (e.getSource() == jButton2) {
            jFrame.setVisible(false);
            dispose();
            BookManege book = new BookManege();
            book.DeleteBook();


        }
    }
}
