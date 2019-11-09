import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 实现大数阶乘
 */
public class jiecheng {
    public static void main(String[] args) {
        DaShuJeChen daShuJeChen = new DaShuJeChen();
        daShuJeChen.init();
    }
}

class DaShuJeChen extends JFrame implements ActionListener {
    JFrame jFrame = new JFrame("大数阶乘");
    Button button = new Button("SRUE");
    JTextArea showArea = new JTextArea();
    JTextField jTextField = new JTextField(5);
    //        JLabel jLabel = new JLabel("输入数字n：" + JLabel.CENTER);
//        Container container = jFrame.getContentPane();
//    JButton jButton = new JButton("确定");

    void init() {
        setBounds(200, 200, 500, 500);
        JPanel pNorth = new JPanel();
        pNorth.add(new JLabel("请输入数字", 10));
        pNorth.add(jTextField);
        pNorth.add(button);
        button.addActionListener(this);
        add(pNorth, BorderLayout.NORTH);
        add(new JScrollPane(showArea), BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int b = Integer.parseInt(jTextField.getText().trim());
            Test test = new Test(b);
            String result = test.getResult();
            showArea.append("数字" + b + "的阶乘为：" + result + "\n");

        } catch (Exception ex) {
            showArea.append("输入错误" + "\n");
        }
    }
}

class Test {
    private String result = "";
    private int a[];

    public Test(int m) {
//            Scanner reader = new Scanner(System.in);
        int b = m;
        int i, j;
        int sum;
        int c;
        a = new int[10000];
        for (i = 0; i < 1000; i++)
            a[i] = 0;
        a[0] = 1;
        for (i = 2; i <= b; i++) {
            c = 0;
            for (j = 0; j < b; j++) {
                sum = a[j] * i + c;
                a[j] = sum % 10;
                c = sum / 10;
            }
        }

//            for (i = 999; i >= 0; i--) {
//                if (a[i] != 0)
//                    break;
//            }
//            for (j = i; j >= 0; j--)
//                System.out.print(a[j]);

    }

    public String getResult() {
        int i, j;
        for (i = 999; i >= 0; i--) {
            if (a[i] != 0)
                break;
        }
        for (j = i; j >= 0; j--)
            result = result + (a[j] + "");
        return result;
    }
}
