import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class SedentaryReminder {
    private final JFrame jFrame = new JFrame("久坐提醒系统"); // 整个面板
    private final JLabel inputLable = new JLabel("工作时长");
    private final JTextField timeEnter = new JTextField(); // 输入框
    private final JButton okbtn = new JButton("确定"); // 确定按钮
    private final JButton cancelbtn = new JButton("取消"); // 取消按钮
    private static int workingTime; // 定义已工作时长

    public SedentaryReminder() {
        // 主窗体部分
        //设置窗体的位置及大小
        jFrame.setBounds(600, 300, 200, 120);
        // 窗口不可改变大小
        jFrame.setResizable(false);
        //设置一层相当于桌布的东西
        Container content = jFrame.getContentPane();
        content.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 结束主窗体部分

        //初始化--往窗体里放其他控件
        // 输入部分--Center
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        inputLable.setBounds(30, 15, 60, 30);
        fieldPanel.add(inputLable);
        timeEnter.setBounds(110, 15, 60, 30);
        timeEnter.setText("45");
        fieldPanel.add(timeEnter);
        content.add(fieldPanel, "Center");
        // 结束输入部分

        // 按钮部分--South
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        content.add(buttonPanel, "South");
        // 结束按钮部分

        //设置窗体可见
        jFrame.setVisible(true);
    }

    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                // 工作时长
                workingTime = 60 * Integer.parseInt(timeEnter.getText());
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.dispose();
                while (workingTime > 0) {
                    try {
                        workingTime--;
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    String cmd = "pmset displaysleepnow";
                    Runtime.getRuntime().exec(cmd);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        //取消按下去清空
        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //程序入口
    public static void main(String[] args) {
        SedentaryReminder manager = new SedentaryReminder();
        manager.listerner();
    }
}