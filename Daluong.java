import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Daluong {
    private JFrame frame;
    private JPanel panel;
    private JButton btnChay;
    private JTextArea textArea;

    public Daluong() {
        // Tạo cửa sổ
        frame = new JFrame("Giao diện đa luồng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo panel chứa các thành phần giao diện
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tạo nút chạy
        btnChay = new JButton("Chạy");
        btnChay.setFont(new Font("Arial", Font.BOLD, 18));

        // Thêm nút vào panel
        panel.add(btnChay, BorderLayout.NORTH);

        // Tạo vùng văn bản
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Thêm panel vào cửa sổ
        frame.add(panel);

        // Xử lý sự kiện khi nhấn nút chạy
        btnChay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tạo luồng chạy đa luồng 1
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Hiển thị thông báo bắt đầu chạy
                        appendText("Bắt đầu chạy đa luồng 1!");

                        // Thực hiện công việc trong luồng con 1
                        for (int i = 1; i <= 5; i++) {
                            try {
                                // Simulate công việc
                                Thread.sleep(1000);

                                // Hiển thị tiến trình
                                appendText("Tiến trình 1: " + i + "/5");
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }

                        // Hiển thị thông báo hoàn thành
                        appendText("Hoàn thành chạy đa luồng 1!");
                    }
                });

                // Tạo luồng chạy đa luồng 2
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Hiển thị thông báo bắt đầu chạy
                        appendText("Bắt đầu chạy đa luồng 2!");

                        // Thực hiện công việc trong luồng con 2
                        for (int i = 1; i <= 5; i++) {
                            try {
                                // Simulate công việc
                                Thread.sleep(800);

                                // Hiển thị tiến trình
                                appendText("Tiến trình 2: " + i + "/5");
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }

                        // Hiển thị thông báo hoàn thành
                        appendText("Hoàn thành chạy đa luồng 2!");
                    }
                });

                // Bắt đầu chạy luồng 1 và luồng 2
                thread1.start();
                thread2.start();
            }
        });

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }

    private void appendText(String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.append(text + "\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Daluong();
            }
        });
    }
}
