import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTextArea;


public class MastermindSolver_Ver2 extends JFrame {

	private JPanel contentPane;
	
	private JTextField inputField;
	
    String[] answerSet = new String[10 * 9 * 8 * 7];
    
    String[] testSet = new String[10 * 9 * 8 * 7];
 
    boolean[] validSet = new boolean[10 * 9 * 8 * 7];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MastermindSolver_Ver2 frame = new MastermindSolver_Ver2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	

	
	/**
	 * Create the frame.
	 */
	public MastermindSolver_Ver2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputField = new JTextField();
		inputField.setBounds(5, 5, 440, 28);
		contentPane.add(inputField);
		inputField.setColumns(10);
		
		
		final JTextArea outComePane = new JTextArea();
		outComePane.setBounds(5, 33, 440, 211);
		outComePane.scrollRectToVisible(getBounds());
		outComePane.setEditable(false);
		outComePane.setLineWrap(true);

		contentPane.add(outComePane);
		
		
		JButton solverButton = new JButton("Click to Figure it out!");
		solverButton.setBounds(5, 244, 200, 28);
		solverButton.addMouseListener(new MouseAdapter() {
			
			
			/**
			 * Build up a AnswerSet includes 5040 combination of 0~9.
			 */

			void generateAnswerSet() {
		        int i = 0;
		        for (int a = 0; a < 10; a++) {
		            for (int b = 0; b < 10; b++) {
		                if (b == a) {
		                    continue;
		                }
		                for (int c = 0; c < 10; c++) {
		                    if (c == a || c == b) {
		                        continue;
		                    }
		                    for (int d = 0; d < 10; d++) {
		                        if (d == a || d == b || d == c) {
		                            continue;
		                        }
		                        answerSet[i++] = "" + a + b + c + d;
		                    }
		                }
		            }
		        }
		        System.arraycopy(answerSet, 0, testSet, 0, testSet.length);
		    }
			
		    void initValidSet() {
		        for (int i = 0; i < validSet.length; i++) {
		            validSet[i] = true;
		        }
		    }
		 
		    int getA(String s1, String s2) {
		        int a = 0;
		        for (int i = 0; i < 4; i++) {
		            if (s1.charAt(i) == s2.charAt(i)) {
		                a++;
		            }
		        }
		        return a;
		    }
		 
		    int getB(String s1, String s2) {
		        int b = 0;
		        for (int i = 0; i < 4; i++) {
		            for (int j = 0; j < 4; j++) {
		                if (i == j) {
		                    continue;
		                }
		                if (s1.charAt(i) == s2.charAt(j)) {
		                    b++;
		                }
		            }
		        }
		        return b;
		    }
		    
		    void reduce(String s, int a, int b) {
		        for (int i = 0; i < validSet.length; i++) {
		            if (!validSet[i]) {
		                continue;
		            }
		            if ((getA(s, answerSet[i]) != a) || (getB(s, answerSet[i]) != b)) {
		                validSet[i] = false;
		            }
		        }
		    }
		    

		 
		    private void reverseAnswerSet() {
		 
		        for (int left = 0, right = answerSet.length - 1; left < right; left++, right--) {
		            String tmp1 = answerSet[left];
		            answerSet[left] = answerSet[right];
		            answerSet[right] = tmp1;
		 
		            boolean tmp2 = validSet[left];
		            validSet[left] = validSet[right];
		            validSet[right] = tmp2;
		        }
		    }
		    
			void guess(String solution){
		        int count = 0;
		        count++;
		        int a = getA(solution, "1234");
		        int b = getB(solution, "1234");
		        if (a == 4) {
		        
		        }
		        reduce("1234", a, b);
				String step = String.valueOf(count);
		        outComePane.append("Step" + step + ": " + "1234 " + a +"A" + b + "B\n");
		 
		        
		        if (a + b == 0 || a + b == 3) {
		            count++;
		            a = getA(solution, "4567");
		            b = getB(solution, "4567");
		            
		            if (a == 4) {
				        step = String.valueOf(count);
				        outComePane.append("Step" + step + ": " + "4567 " + a +"A" + b + "B\n");
		            }
		            
		            reduce("4567", a, b);
			        step = String.valueOf(count);
			        outComePane.append("Step" + step + ": " + "4567 " + a +"A" + b + "B\n");
			        
		        } else if (a == 0 && b == 2) {
		            count++;
		            a = getA(solution, "0168");
		            b = getB(solution, "0168");
		            if (a == 4) {
				        step = String.valueOf(count);
				        outComePane.append("Step" + step + ": " + "0168 " + a +"A" + b + "B\n");
		            }
		            
		            reduce("0168", a, b);
		            
			        step = String.valueOf(count);
			        outComePane.append("Step" + step + ": " + "0168 " + a +"A" + b + "B\n");
			        
		        } else if (a == 1 && b == 1) {
		            count++;
		            a = getA(solution, "0167");
		            b = getB(solution, "0167");
		            if (a == 4) {
				        step = String.valueOf(count);
				        outComePane.append("Step" + step + ": " + "0167 " + a +"A" + b + "B\n");
		            }
		            
		            reduce("0167", a, b);
		           
			        step = String.valueOf(count);
			        outComePane.append("Step" + step + ": " + "0167 " + a +"A" + b + "B\n");
		            
		        } else if (a + b == 1) {
		            reverseAnswerSet();
		        }
		 
		        for (int i = 0; i < validSet.length; i++) {

		            if (!validSet[i]) {
		                continue;
		            }
		            count++;
		            a = getA(solution, answerSet[i]);
		            b = getB(solution, answerSet[i]);
			        step = String.valueOf(count);
			        outComePane.append("Step" + step + ": " + answerSet[i] + " " + a +"A" + b + "B\n");
		            
			        if (a == 4) {
		                break;
		            }
		            
		            reduce(answerSet[i], a, b);
		        }

		    }
			
			@Override
			public void mouseClicked(MouseEvent arg0) {

				outComePane.setText("");
				generateAnswerSet();
				initValidSet();
				guess(inputField.getText());

			}


				
				
			
		});
		
		contentPane.add(solverButton);
		
		JButton Reset = new JButton("Reset");
		Reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				outComePane.setText("");
				inputField.setText("");
			}
		});
		Reset.setBounds(239, 244, 205, 28);
		contentPane.add(Reset);
	}
	 
}
