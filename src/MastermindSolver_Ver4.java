
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Checkbox;
import java.awt.Color;
import java.text.DecimalFormat;



public class MastermindSolver_Ver4 extends JFrame {

	private static final long serialVersionUID = -3341706108662622967L;
	private JPanel contentPane;
	private JTextField inputField;
	
	MastermindSolverAverage_1 average = new MastermindSolverAverage_1();
	Algorithm_1 algorithm = new Algorithm_1();
	
	MastermindSolverAverage_2 average_2 = new MastermindSolverAverage_2();
	Algorithm_2 algorithm_2 = new Algorithm_2();
	
	MastermindSolverAverage_3 average_3 = new MastermindSolverAverage_3();
	Algorithm_3 algorithm_3 = new Algorithm_3();

	Algorithm_Manual algorithm_m = new Algorithm_Manual();
	private JTextField textField;
	
	int STEP = 3;
	int COUNT = 2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MastermindSolver_Ver4 frame = new MastermindSolver_Ver4();
					frame.setVisible(true);
					frame.setTitle("Mastermind Solver Ver4.3.1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MastermindSolver_Ver4() {
			
		/**
		 * Create the Content Pane.
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**
		 * Create the Outcome text area.
		 */
		final JTextArea outComePane = new JTextArea();
		outComePane.setForeground(Color.BLACK);
		outComePane.setEditable(false);
		outComePane.setLineWrap(true);
		outComePane.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		outComePane.setBounds(174, 70, 252, 182);
		contentPane.add(outComePane);
		
		/**
		 * Create the notice Label.
		 */
		JLabel lblPleaseEnterYour = new JLabel("Please enter your number:");
		lblPleaseEnterYour.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		lblPleaseEnterYour.setBounds(5, 49, 172, 16);
		contentPane.add(lblPleaseEnterYour);
		
		/**
		 * Create the title label.
		 */
		JLabel lblMastermind = new JLabel("Mastermind - UltimateCode");
		lblMastermind.setFont(new Font("Lithos Pro", Font.PLAIN, 18));
		lblMastermind.setHorizontalAlignment(SwingConstants.CENTER);
		lblMastermind.setBounds(5, 6, 439, 36);
		contentPane.add(lblMastermind);
		
		/**
		 * Create the input area.
		 */
		inputField = new JTextField();
		inputField.setBounds(5, 70, 134, 28);
		contentPane.add(inputField);
		inputField.setColumns(4);
		
		/**
		 * Create the Radio button 1.
		 */
		final JRadioButton algorithmRB = new JRadioButton("Algorithm 1");
		algorithmRB.setFont(new Font("Lithos Pro", Font.PLAIN, 11));
		algorithmRB.setBounds(5, 110, 141, 23);
		algorithmRB.setSelected(true);
		contentPane.add(algorithmRB);
		
		/**
		 * Create the Radio button 2.
		 */
		final JRadioButton algorithmRB_1 = new JRadioButton("Algorithm 2");
		algorithmRB_1.setFont(new Font("Lithos Pro", Font.PLAIN, 11));
		algorithmRB_1.setBounds(5, 139, 141, 23);
		contentPane.add(algorithmRB_1);
		
		/**
		 * Create the Radio button 3.
		 */
		final JRadioButton algorithmRB_2 = new JRadioButton("Algorithm 3");
		algorithmRB_2.setFont(new Font("Lithos Pro", Font.PLAIN, 11));
		algorithmRB_2.setBounds(5, 169, 141, 23);
		contentPane.add(algorithmRB_2);
		
		/**
		 * Create the Radio button group.
		 */
		ButtonGroup al = new ButtonGroup();
		al.add(algorithmRB);
		al.add(algorithmRB_1);
		al.add(algorithmRB_2);
		
		
		final Checkbox averageCheckbox = new Checkbox("Calculate Average.");
		averageCheckbox.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		averageCheckbox.setBounds(10, 210, 163, 18);
		contentPane.add(averageCheckbox);
		
		final JTextField abField = new JTextField();
		abField.setVisible(false);
		abField.setColumns(4);
		abField.setBounds(5, 133, 134, 28);
		contentPane.add(abField);
		

		
		final JButton nextButton = new JButton("Next");
		nextButton.addMouseListener(new MouseAdapter() {
			int A = 0;
			int B = 0;
			public void mouseClicked(MouseEvent arg0) {
				
				
				if(abField.getText().length() == 4){
					
					if(!algorithm_m.finish){
						
					
						if(COUNT == 2){
							int chaA = abField.getText().charAt(0);
							int chaB = abField.getText().charAt(2);
							
						
							for(int i = 0; i < 5; i++){
								if (chaA == (48 + i) ){
									A = i;
								}
								if (chaB == (48 + i) ){
									B = i;
								}
							}
							algorithm_m.firstGuessUpdate(A, B);
							algorithm_m.secondGuess(A, B);
							outComePane.append(algorithm_m.outcome);
							COUNT = 3;
							
						} else if(COUNT == 3){
							
							int chaA = abField.getText().charAt(0);
							int chaB = abField.getText().charAt(2);
							
							int a = 0;
							int b = 0;
						
							for(int i = 0; i < 5; i++){
								if (chaA == (48 + i) ){
									a = i;
								}
								if (chaB == (48 + i) ){
									b = i;
								}
							}
							algorithm_m.secondGuessUpdate(A, B, a, b);
							algorithm_m.guess(a, b);
							outComePane.append(a + "A" + b + "B" + "\n" + "Step " + STEP + ": " + algorithm_m.outcome);
							STEP++;
							COUNT++;
						} else{
							
							int chaA = abField.getText().charAt(0);
							int chaB = abField.getText().charAt(2);
							
							int a = 0;
							int b = 0;
						
							for(int i = 0; i < 5; i++){
								if (chaA == (48 + i) ){
									a = i;
								}
								if (chaB == (48 + i) ){
									b = i;
								}
							}
							
							algorithm_m.guessUpdate(a, b);
							algorithm_m.guess(a, b);
							outComePane.append(a + "A" + b + "B" + "\n" + "Step " + STEP + ": " + algorithm_m.outcome);

							STEP++;
							COUNT++;
						}
						
					}

				}
			}
		});
		nextButton.setVisible(false);
		nextButton.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		nextButton.setBounds(220, 264, 97, 29);
		contentPane.add(nextButton);		
		
		final JLabel abLabel = new JLabel("Please enter your response:");
		abLabel.setVisible(false);
		abLabel.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		abLabel.setBounds(5, 117, 172, 16);
		contentPane.add(abLabel);
		
		final Checkbox manualCheckbox = new Checkbox("Manual Response Input.");
		manualCheckbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(manualCheckbox.getState()){
					abField.setVisible(true);
					nextButton.setVisible(true);
					abLabel.setVisible(true);
					algorithmRB.setVisible(false);
					algorithmRB_1.setVisible(false);
					algorithmRB_2.setVisible(false);
					averageCheckbox.setVisible(false);
				} else {
					nextButton.setVisible(false);
					abField.setVisible(false);
					abLabel.setVisible(false);
					algorithmRB.setVisible(true);
					algorithmRB_1.setVisible(true);
					algorithmRB_2.setVisible(true);
					averageCheckbox.setVisible(true);
				}
			}
		});
		manualCheckbox.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		manualCheckbox.setBounds(10, 234, 163, 18);
		contentPane.add(manualCheckbox);
		
		
		/**
		 * Create the Figure out button.
		 */
		JButton btnClickHereTo = new JButton("Click here to figure it out!");
		btnClickHereTo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				outComePane.setText("");

				/*When we need check the algorithm by manual response.*/
				if(manualCheckbox.getState()){
					if(inputField.getText().length() > 4 || inputField.getText().length() < 4){ // valid number check
						outComePane.append("Please enter a four digits number.");
						
					}else if(inputField.getText().charAt(0) == inputField.getText().charAt(1)  // valid number check
							|| inputField.getText().charAt(0) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(0) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(2) == inputField.getText().charAt(3)
							){
						outComePane.append("Every digit must be differenaverage.");
						
					}else if(inputField.getText().length() == 4){ 
						
						algorithm_m.finish = false;
						algorithm_m.generateAnswerSet();
						algorithm_m.initCheckSet();
						algorithm_m.guessNumber = "";
						STEP = 3;
						COUNT = 2;
						algorithm_m.firstGuess(inputField.getText());
						
				        outComePane.append(algorithm_m.outcome);

						
					}
					
					
				}
				
				// When CheckBox and Radiobutton_1 all selected.
				// Then the function calculate the first algorithm's average guess times.
				if(averageCheckbox.getState() && algorithmRB.isSelected() && !manualCheckbox.getState() ){
					average.generateAnswerSet();
			        int[] guessNb = new int[11];
			        for (int i = 0; i < guessNb.length; i++) {
			            guessNb[i] = 0;
			        }
			 
			        long startTime = System.currentTimeMillis();
			        for (int i = 0; i < average.answerSet.length; i++) {
			            average.initValidSet();
			            int guessTimes = average.guess(average.testSet[i]);
			            guessNb[guessTimes]++;
			        }
			        long endTime = System.currentTimeMillis();
			        String cost = String.valueOf((endTime - startTime));
			        
			        int sum = 0;
			        for (int i = 0; i < guessNb.length; i++) {
			            sum += i * guessNb[i];
			        }
			        
			        for (int i = 1; i < guessNb.length; i++) {
			        	double percent = ( (double) guessNb[i] / 5040.0) * 100.00;
			        	percent = (double) Math.round( percent * 100 ) / 100;
			        	
			        	if (String.valueOf(guessNb[i]).length() == 1 && i == 1){
			        		outComePane.append(i + "  time:  " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 && i == 10 ){
			        		outComePane.append(i + " times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 2){
			        		outComePane.append(i + "  times: " + guessNb[i] + "     (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 3){
			        		outComePane.append(i + "  times: " + guessNb[i] + "    (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 4){
			        		outComePane.append(i + "  times: " + guessNb[i] + "   (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 ){
			        		outComePane.append(i + "  times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}
			        	
			        }
			        DecimalFormat six = new DecimalFormat("#.######");
			        outComePane.append("\nAverage: " + sum + "/" + average.answerSet.length
			                + " = " + (six.format((double) sum / average.answerSet.length)) + "\n");
			        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
				}
				
				
				// When Radiobutton selected.
				// Then the function guess the input number by using algorithm 1.
				if( algorithmRB.isSelected() && !averageCheckbox.getState() && !manualCheckbox.getState()){ // determine which algorithm would apply.
				
					if(inputField.getText().length() > 4 || inputField.getText().length() < 4){ // valid number check
						outComePane.append("Please enter a four digits number.");
						
					}else if(inputField.getText().charAt(0) == inputField.getText().charAt(1)  // valid number check
							|| inputField.getText().charAt(0) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(0) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(2) == inputField.getText().charAt(3)
							){
						outComePane.append("Every digit must be differenaverage.");
						
					}else if(inputField.getText().length() == 4){ 
						long startTime = System.currentTimeMillis();
						
						algorithm.generateAnswerSet();
						algorithm.initCheckSet();
						algorithm.guess(inputField.getText());
						
						long endTime = System.currentTimeMillis();
				        String cost = String.valueOf((endTime - startTime));
				        outComePane.append(algorithm.outcome);
				        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
						
					}
	
				}
				
				
				
				// When CheckBox and Radiobutton_1 all selected.
				// Then the function calculate the second algorithm's average guess times.
				if(averageCheckbox.getState() && algorithmRB_1.isSelected() && !manualCheckbox.getState() ){
					average_2.generateAnswerSet();
			        int[] guessNb = new int[11];
			        for (int i = 0; i < guessNb.length; i++) {
			            guessNb[i] = 0;
			        }
			 
			        long startTime = System.currentTimeMillis();
			        for (int i = 0; i < average_2.answerSet.length; i++) {
			            average_2.initValidSet();
			            int guessTimes = average_2.guess(average_2.testSet[i]);
			            guessNb[guessTimes]++;
			        }
			        long endTime = System.currentTimeMillis();
			        String cost = String.valueOf((endTime - startTime));
			        
			        int sum = 0;
			        for (int i = 0; i < guessNb.length; i++) {
			            sum += i * guessNb[i];
			        }
			        
			        for (int i = 1; i < guessNb.length; i++) {
			        	double percent = ( (double) guessNb[i] / 5040.0) * 100.00;
			        	percent = (double) Math.round( percent * 100 ) / 100;
			        	
			        	if (String.valueOf(guessNb[i]).length() == 1 && i == 1){
			        		outComePane.append(i + "  time:  " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 && i == 10 ){
			        		outComePane.append(i + " times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 2){
			        		outComePane.append(i + "  times: " + guessNb[i] + "     (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 3){
			        		outComePane.append(i + "  times: " + guessNb[i] + "    (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 4){
			        		outComePane.append(i + "  times: " + guessNb[i] + "   (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 ){
			        		outComePane.append(i + "  times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}
			        	
			        }
			        DecimalFormat six = new DecimalFormat("#.######");
			        outComePane.append("\nAverage: " + sum + "/" + average_2.answerSet.length
			                + " = " + (six.format((double) sum / average_2.answerSet.length)) + "\n");
			        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
				}
				
				
				// When Radiobutton_1 selected.
				// Then the function guess the input number by using algorithm 2.
				if( algorithmRB_1.isSelected() && !averageCheckbox.getState() && !manualCheckbox.getState() ){ // determine which algorithm would apply.
				
					if(inputField.getText().length() > 4 || inputField.getText().length() < 4){ // valid number check
						outComePane.append("Please enter a four digits number.");
						
					}else if(inputField.getText().charAt(0) == inputField.getText().charAt(1)  // valid number check
							|| inputField.getText().charAt(0) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(0) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(2) == inputField.getText().charAt(3)
							){
						outComePane.append("Every digit must be differenaverage.");
						
					}else if(inputField.getText().length() == 4){ 
						long startTime = System.currentTimeMillis();
						
						algorithm_2.generateAnswerSet();
						algorithm_2.initCheckSet();
						algorithm_2.guess(inputField.getText());
						
						long endTime = System.currentTimeMillis();
				        String cost = String.valueOf((endTime - startTime));
				        outComePane.append(algorithm_2.outcome);
				        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
						
					}
	
				}
			
			
			
				// When CheckBox and Radiobutton_2 all selected.
				// Then the function calculate the second algorithm's average guess times.
				if(averageCheckbox.getState() && algorithmRB_2.isSelected() && !manualCheckbox.getState() ){
					average_3.generateAnswerSet();
			        int[] guessNb = new int[11];
			        for (int i = 0; i < guessNb.length; i++) {
			            guessNb[i] = 0;
			        }
			 
			        long startTime = System.currentTimeMillis();
			        for (int i = 0; i < average_3.answerSet.length; i++) {
			            average_3.initValidSet();
			            int guessTimes = average_3.guess(average_3.testSet[i]);
			            guessNb[guessTimes]++;
			        }
			        long endTime = System.currentTimeMillis();
			        String cost = String.valueOf((endTime - startTime));
			        
			        int sum = 0;
			        for (int i = 0; i < guessNb.length; i++) {
			            sum += i * guessNb[i];
			        }
			        
			        for (int i = 1; i < guessNb.length; i++) {
			        	double percent = ( (double) guessNb[i] / 5040.0) * 100.00;
			        	percent = (double) Math.round( percent * 100 ) / 100;
			        	
			        	if (String.valueOf(guessNb[i]).length() == 1 && i == 1){
			        		outComePane.append(i + "  time:  " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 && i == 10 ){
			        		outComePane.append(i + " times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 2){
			        		outComePane.append(i + "  times: " + guessNb[i] + "     (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 3){
			        		outComePane.append(i + "  times: " + guessNb[i] + "    (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 4){
			        		outComePane.append(i + "  times: " + guessNb[i] + "   (" + percent + "%)" + "\n");
			        	}else if(String.valueOf(guessNb[i]).length() == 1 ){
			        		outComePane.append(i + "  times: " + guessNb[i] + "      (" + percent + "%)" + "\n");
			        	}
			        	
			        }
			        DecimalFormat six = new DecimalFormat("#.######");
			        outComePane.append("\nAverage: " + sum + "/" + average_3.answerSet.length
			                + " = " + (six.format((double) sum / average_3.answerSet.length)) + "\n");
			        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
				}
				
				
				// When Radiobutton_2 selected.
				// Then the function guess the input number by using algorithm 2.
				if( algorithmRB_2.isSelected() && !averageCheckbox.getState() && !manualCheckbox.getState() ){ // determine which algorithm would apply.
				
					if(inputField.getText().length() > 4 || inputField.getText().length() < 4){ // valid number check
						outComePane.append("Please enter a four digits number.");
						
					}else if(inputField.getText().charAt(0) == inputField.getText().charAt(1)  // valid number check
							|| inputField.getText().charAt(0) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(0) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(2)
							|| inputField.getText().charAt(1) == inputField.getText().charAt(3)
							|| inputField.getText().charAt(2) == inputField.getText().charAt(3)
							){
						outComePane.append("Every digit must be differenaverage.");
						
					}else if(inputField.getText().length() == 4){ 
						long startTime = System.currentTimeMillis();
						
						algorithm_3.generateAnswerSet();
						algorithm_3.initCheckSet();
						algorithm_3.guess(inputField.getText());
						
						long endTime = System.currentTimeMillis();
				        String cost = String.valueOf((endTime - startTime));
				        outComePane.append(algorithm_3.outcome);
				        outComePane.append("\nThe Total time cost is: "+ cost +" ms.");
						
					}
	
				}
				}
		
		});
		
		btnClickHereTo.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClickHereTo.setBounds(5, 264, 202, 29);
		contentPane.add(btnClickHereTo);
		
		
		/**
		 * 
		 */
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				outComePane.setText("");
				inputField.setText("");
			}
		});
		btnClear.setFont(new Font("Lithos Pro", Font.PLAIN, 10));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(329, 264, 97, 29);
		contentPane.add(btnClear);
		

		

		
	}
}
