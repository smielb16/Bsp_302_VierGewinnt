
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class VierGewinntGUI extends JFrame {

    private VierGewinntBL viergewinntbl = new VierGewinntBL();
    private JLabel[][] labelArr = new JLabel[7][7];

    public VierGewinntGUI() {
        super("VierGewinnt");
        initComponents();
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new VierGewinntGUI().setVisible(true);
    }

    private void initComponents() {
        Container container = this.getContentPane();
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(7, 7, 3, 3));

        JToolBar toolbar = new JToolBar();
        JButton reset = new JButton("Reset board");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanBoard();
            }
        });
        JButton close = new JButton("Close game");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolbar.add(reset);
        toolbar.add(close);
        container.setLayout(new BorderLayout());
        container.add(toolbar, BorderLayout.NORTH);

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton();
            button.setOpaque(true);
            button.setText("v");
            button.setName(i + "");
            button.setHorizontalAlignment(JLabel.CENTER);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onButtonClicked(e);
                }
            });
            panel.add(button);
        }

        for (int i = 1; i < 7; i++) { //Reihen
            for (int j = 0; j < 7; j++) { //Spalten
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(JLabel.CENTER);
                labelArr[i][j] = label;
                panel.add(label);
            }
        }
        container.add(panel, BorderLayout.CENTER);
    }

    private void onButtonClicked(ActionEvent event) {
        try {
            JButton button = (JButton) event.getSource();
            int column = Integer.parseInt(button.getName());
            Value winner = viergewinntbl.makeMove(column);
            Value val = viergewinntbl.getValueAt(column);
            int row = viergewinntbl.getRow(column);
            switch (val) {
                case X:
                    labelArr[row][column].setBackground(Color.red);
                    break;
                case O:
                    labelArr[row][column].setBackground(Color.blue);
                    break;
            }

            if (winner != Value.EMPTY) {
                JOptionPane.showMessageDialog(this, "Winner = " + winner);
                viergewinntbl.reset();
                for (int i = 1; i < labelArr.length; i++) {
                    for (int x = 0; x < labelArr[i].length; x++) {
                        labelArr[i][x].setBackground(Color.black);
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void cleanBoard() {
        viergewinntbl.reset();
        for (int i = 1; i < labelArr.length; i++) {
            for (int x = 0; x < labelArr[i].length; x++) {
                labelArr[i][x].setBackground(Color.black);
            }
        }
    }

}
