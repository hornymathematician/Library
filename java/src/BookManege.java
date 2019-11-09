import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookManege extends JFrame implements ActionListener {
    private JButton jButton1 = new JButton("增加");
    private JButton jButton2 = new JButton("返回上一级目录");
    private JTextField jTextField1, jTextField2, jTextField3;

    void addBook() {
        this.setTitle("图书管理");
//        JButton jButton1 = new JButton("增加");
        jButton1.addActionListener(this);
//        JButton jButton2 = new JButton("返回上一级目录");
        jButton2.addActionListener(this);

        JLabel jLabel1 = new JLabel("图书名称");
        jTextField1 = new JTextField(15);
        JLabel jLabel2 = new JLabel("图书作者");
        jTextField2 = new JTextField(15);
        JLabel jLabel3 = new JLabel("入库时间");
        jTextField3 = new JTextField(15);

        JPanel jPanelNorth = new JPanel();
        JPanel jPanelCenter1 = new JPanel();
        JPanel jPanelCenter2 = new JPanel();
        JPanel jPanelCenter = new JPanel();
        JPanel jPanelSouth = new JPanel();

//       jPanelNorth.setLayout(new BorderLayout());
        jPanelNorth.add(jLabel1);
        jPanelNorth.add(jTextField1);

        jPanelCenter1.add(jLabel2);
        jPanelCenter1.add(jTextField2);

        jPanelCenter2.add(jLabel3);
        jPanelCenter2.add(jTextField3);

        jPanelCenter.add(jPanelCenter1, BorderLayout.CENTER);
        jPanelCenter.add(jPanelCenter2, BorderLayout.SOUTH);
//       this.add(jPanelNorth,BorderLayout.NORTH);
        jPanelSouth.add(jButton1);
        jPanelSouth.add(jButton2);

        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);
        this.setSize(400, 200);
        this.setVisible(true);

    }

    void DeleteBook() {
        JFrame jFrame = new JFrame("删除图书");
        JButton jButton = new JButton("删除");
        jButton.addActionListener(this);
        JButton jButton1 = new JButton("返回上一级目录");
        jButton1.addActionListener(this);
        JLabel jLabel = new JLabel("书籍名称");
        JTextField jTextField = new JTextField(15);
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        jPanel1.add(jButton);
        jPanel1.add(jButton1);
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        this.add(jPanel, BorderLayout.NORTH);
        this.add(jPanel1, BorderLayout.CENTER);
        setVisible(true);
        setSize(500, 400);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName;
                bookName = jTextField.getText();
                DataBaseConnect connect = new DataBaseConnect();
                try {
                    boolean result = connect.SelectBook(bookName);
                    if (result) {
                        connect.DeleteBook(bookName);
                        JOptionPane.showMessageDialog(jFrame, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "图书馆没有书籍信息");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new MenuJFrame();
            }
        });
    }

    //增加图书监听器
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            //增加书到数据库db_Book
            String bookName, bookAuthor, bookTime;
            bookName = jTextField1.getText();
            bookAuthor = jTextField2.getText();
            bookTime = jTextField3.getText();
            DataBaseConnect connect = new DataBaseConnect();
            try {
                String s = connect.AddBook(bookName, bookAuthor, bookTime, 1);
                JOptionPane.showMessageDialog(this, s);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == jButton2) {
            setVisible(false);
            dispose();
            new MenuJFrame();
        }
    }

//    public boolean Select(String bookName) {
//
//        return true;
//    }

    public static void main(String[] args) {
        BookManege book = new BookManege();
//        book.addBook();
        book.DeleteBook();
    }
}
