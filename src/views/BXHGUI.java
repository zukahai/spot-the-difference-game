package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BXHGUI extends JFrame {

    private JPanel contentPane;
    private JTable table;
    public Vector vT = new Vector<>();
    public Vector vD = new Vector<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BXHGUI frame = new BXHGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BXHGUI() {
        // setVisible(true);
        setBounds(100, 100, 578, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(224, 255, 255));
        scrollPane.setBounds(49, 49, 465, 299);
        contentPane.add(scrollPane);

        vT.add("Username");
        vT.add("Fullname");
        vT.add("Age");
        vT.add("Score");

        table = new JTable();
        table.setBackground(new Color(224, 255, 255));
        table.setModel(new DefaultTableModel(vD, vT));

        // Đặt màu cho các cột
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(224, 255, 255));
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        scrollPane.setViewportView(table);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void setData(Vector data) {
        vD = data;
        table.setModel(new DefaultTableModel(vD, vT));
    }
}
