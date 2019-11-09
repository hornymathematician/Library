import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class BorrowBook extends JFrame implements ActionListener {
    public void Remind() {
        JFrame remind = new JFrame("请仔细阅读借书注意事项");
        JButton jButton1 = new JButton("我接受条款");
        jButton1.setFont(new Font("宋体", Font.BOLD, 24));
        jButton1.setOpaque(false);
        JTextArea jTextArea = new JTextArea("   我自愿遵守图书馆借书规则,在规定的时间内" +
                "归还书籍，爱护数据，不损坏书籍，不私自把书籍借给他人...", 50, 50);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 16));
        ImageIcon imageIcon = new ImageIcon("E:\\java\\timg.jpg");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        remind.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel panel2 = (JPanel) remind.getContentPane();
        panel2.setOpaque(false);
//        remind.setLayout(null);

        jTextArea.setLineWrap(true);
        jTextArea.setEnabled(false);
        jTextArea.setSelectionColor(Color.RED);
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        jPanel.add(jTextArea);
        jPanel1.add(jButton1);
        jPanel1.setOpaque(false);
        jPanel.setOpaque(false);
        remind.add(jPanel, BorderLayout.CENTER);
        remind.add(jPanel1, BorderLayout.SOUTH);
        remind.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        remind.setLocation(300, 300);
        remind.setVisible(true);
        remind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remind.setVisible(false);
                dispose();
                new BorrowBook().BorrowBook();
            }
        });

    }

    //借书
    public void BorrowBook() {
        JFrame borrowJFrame = new JFrame("借书");
        JPanel jPanelNorth = new JPanel(), jPanelCenter = new JPanel(), jPanelSouth = new JPanel(), jPanel = new JPanel();
        JPanel jPanelCenter1 = new JPanel(), jPanel1Center2 = new JPanel();
        JButton jButton = new JButton("借书");
        JButton jButton1 = new JButton("返回上一级目录");

        JLabel jLabel1 = new JLabel("书籍名称：");
        JTextField jTextField1 = new JTextField(15);
        jPanelNorth.add(jLabel1);
        jPanelNorth.add(jTextField1);

        JLabel jLabel2 = new JLabel("借书人：");
        JTextField jTextField2 = new JTextField(15);
        jPanelCenter1.add(jLabel2);
        jPanelCenter1.add(jTextField2);

        JLabel jLabel3 = new JLabel("归还时间：");
        JTextField jTextField3 = new JTextField(15);
        jPanel1Center2.add(jLabel3);
        jPanel1Center2.add(jTextField3);
        jPanelCenter.add(jPanelCenter1, BorderLayout.NORTH);
        jPanelCenter.add(jPanel1Center2, BorderLayout.CENTER);

        jPanelSouth.add(jButton);
        jPanelSouth.add(jButton1);

        borrowJFrame.add(jPanelNorth, BorderLayout.NORTH);
        borrowJFrame.add(jPanelCenter, BorderLayout.CENTER);
        borrowJFrame.add(jPanelSouth, BorderLayout.SOUTH);
        borrowJFrame.setSize(500, 200);
        borrowJFrame.setLocation(500, 500);
        borrowJFrame.setResizable(false);
        borrowJFrame.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = jTextField1.getText();
                String borrowName = jTextField2.getText();
                String returnTime = jTextField3.getText();
                String borrowTime = GetNowTime();
                DataBase1 dataBase1 = new DataBase1();
                try {
                    boolean result = dataBase1.Select(bookName);
                    if (result) {
                        dataBase1.addBorrowName(bookName, borrowName, borrowTime, borrowTime);
                        dataBase1.UpDate(bookName, 1);
                        JOptionPane.showMessageDialog(borrowJFrame, "借书成功");
                    } else
                        JOptionPane.showMessageDialog(borrowJFrame, "没有这本书");
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowJFrame.setVisible(false);
                dispose();
                new MenuJFrame();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public String GetNowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
        return time;
    }

    public static void main(String[] args) {
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.Remind();
    }

}
