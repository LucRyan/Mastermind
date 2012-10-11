import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;


public class MastermindGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3377275580742158557L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MastermindGenerator frame = new MastermindGenerator();
					frame.setVisible(true);
					frame.setTitle("Mastermind Generator");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MastermindGenerator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel viewScreen = new JLabel("0000");
		viewScreen.setBounds(5, 5, 341, 230);
		viewScreen.setFont(new Font("Optima", Font.BOLD, 60));
		
		viewScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(viewScreen);
		
		JButton generateButton = new JButton("Generate Number");
		generateButton.setBounds(78, 206, 200, 29);
		generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<Integer> strArray = new ArrayList<Integer>();
				
				for(int j = 0; j < 10; j++){
					strArray.add(j, j);
				}
				
				String goalNum = "";
				
				for(int i = 0; i < 4; i++){
					int index = (int)(strArray.size() * Math.random());
					goalNum += strArray.get(index).toString();
					strArray.remove(index);
				}
				
				viewScreen.setText(goalNum);
			}
		});
		contentPane.add(generateButton);
		
		JLabel lblNewLabel = new JLabel("Mastermind - UltimateCode Generator");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lithos Pro", Font.PLAIN, 14));
		lblNewLabel.setBounds(5, 6, 341, 41);
		contentPane.add(lblNewLabel);
	}

}
