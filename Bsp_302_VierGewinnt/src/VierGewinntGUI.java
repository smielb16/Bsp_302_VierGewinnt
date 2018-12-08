
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VierGewinntGUI extends JFrame {

    private boolean playerOne = true;
    private VierGewinntBL viergewinntbl;

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
        container.setLayout(new GridLayout(7, 7, 3, 3));

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton();
            button.setOpaque(true);
            button.setText("v");
            button.setName(i + "");
            button.setHorizontalAlignment(JLabel.CENTER);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent event) {
                    onButtonClicked(event);
                }
            });
            container.add(button);
        }

        for (int i = 0; i < 7; i++) { //Reihen
            for (int j = 1; j < 7; j++) { //Spalten
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(JLabel.CENTER);
                container.add(label);
            }
        }
    }

    private void onButtonClicked(MouseEvent event) {
        try{
        JButton button = (JButton) event.getSource();
        int column = Integer.parseInt(button.getName());
        Value winner = this.viergewinntbl.makeMove(column);
        Value val = this.viergewinntbl.getValueAt(column);
        /*switch (val) {
                case X:
                    label.setBackground(Color.red);
                    break;
                case O:
                    label.setBackground(Color.blue);
                    break;
            }

            if (winner != Value.EMPTY) {
                JOptionPane.showMessageDialog(this, "Winner = " + winner);
                ticTacToeBl.reset();

                for (Component component : this.getContentPane().getComponents()) {
                    JLabel lb = (JLabel) component;
                    lb.setBackground(Color.black);
                }
            }*/
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
