import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ReturnBook extends JFrame implements ActionListener {
    public void returnBook() {
        JFrame returnJFrame = new JFrame("还书");
        JPanel jPanelNorth = new JPanel(), jPanelCenter = new JPanel(), jPanelSouth = new JPanel(), jPanel = new JPanel();
        JPanel jPanelCenter1 = new JPanel(), jPanel1Center2 = new JPanel();
        JButton jButton = new JButton("还书");
        JButton jButton1 = new JButton("返回上一级目录");

        JLabel jLabel1 = new JLabel("书籍名称：");
        JTextField jTextField1 = new JTextField(15);
        jPanelNorth.add(jLabel1);
        jPanelNorth.add(jTextField1);

        JLabel jLabel2 = new JLabel("还书人：");
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

        returnJFrame.add(jPanelNorth, BorderLayout.NORTH);
        returnJFrame.add(jPanelCenter, BorderLayout.CENTER);
        returnJFrame.add(jPanelSouth, BorderLayout.SOUTH);
        returnJFrame.setSize(500, 200);
        returnJFrame.setLocation(500, 500);
        returnJFrame.setResizable(false);
        returnJFrame.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = jTextField1.getText();
                String borrowName = jTextField2.getText();
                String borrowTime = jTextField3.getText();
                DataBase1 dataBase1 = new DataBase1();
                try {
                    dataBase1.returnBook(borrowName);
                    dataBase1.UpDate(bookName, 2);
                    JOptionPane.showMessageDialog(returnJFrame, "还书成功！");
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnJFrame.setVisible(false);
                dispose();
                new MenuJFrame();
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
