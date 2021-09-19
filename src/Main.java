import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame {
	public Main() {
		Begin();
	}

	private void Begin() {
		add(new Screen());
        setResizable(false);
        pack();
        setTitle("Quadtree");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });
        
    }
	
}