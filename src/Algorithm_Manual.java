
public class Algorithm_Manual {

    String[] answerSet = new String[10 * 9 * 8 * 7]; // this set contains 5040 strings as a dictionary about this game.
  
    boolean[] checkSet = new boolean[10 * 9 * 8 * 7]; // this set has same size about AnsSet, used to check the validation about AnsSet.
	
    String outcome = "";
	String guessNumber ="";
		
    boolean finish = false;
    
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
    }
	
	/**
	 * At first we need set all checkSet is true.
	 * Then if we find the A and B not same with other strings in ansSet; 
	 * we'll set the correspond checkSet be False.
	 */
    void initCheckSet() {
        for (int i = 0; i < checkSet.length; i++) {
            checkSet[i] = true;
        }
    }
 
    /**
     * This function used to get how many As (XAXB) about the guess by compare with the input number.
     * 
     * @param s1 the guessing number.
     * @param s2 the guessing number.
     * @return the number of A.
     */ 
    int getA(String s1, String s2) {
        int a = 0;
        for (int i = 0; i < 4; i++) {
            if (s1.charAt(i) == s2.charAt(i)) { //if their digit and position both matched.
                a++;
            }
        }
        return a;
    }
 
    /**
     * This function used to get how many Bs (XAXB) about the guess by compare with the input number.
     * 
     * @param s1 the guessing number.
     * @param s2 the guessing number.
     * @return the number of B.
     */
    int getB(String s1, String s2) {
        int b = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) { // this situation has handled by getA.
                    continue;
                }
                if (s1.charAt(i) == s2.charAt(j)) { // if their digit matched, but their position must be different.
                    b++;
                }
            }
        }
        return b;
    }
    
	/**
	 * When guess function made a guess, we need update the ansSet.
	 * By set false to checkSet, we could reduce the valid strings in AnsSet.
	 * Then the guessing range has been narrowed.
	 * 
	 * @param s the input number.
	 * @param a the number of A which has been calculated by guess function.
	 * @param b the number of B which has been calculated by guess function.
	 */
    void updateAnsSet(String s, int a, int b) {
        for (int i = 0; i < checkSet.length; i++) {
            if (!checkSet[i]) {
                continue;
            }
            if ((getA(s, answerSet[i]) != a) || (getB(s, answerSet[i]) != b)) {
                checkSet[i] = false;
            }
        }
    }
    

    /**
     * When A + B = 1. Because I choose "1234" as the first guess.
     * Then the weight strings could be in last 6 digit which is "5,6,7,8,9,0"
     * 
     * Therefore, this function would rebuild the exist answer set in a reversed order.
     * The possibility about right string will be larger in this sort.
     * 
     */
    private void reverseAnsSet() {
 
        for (int start = 0, end = answerSet.length - 1; start < end; start++, end--) {
            String tmp1 = answerSet[start];
            answerSet[start] = answerSet[end];
            answerSet[end] = tmp1;
 
            boolean tmp2 = checkSet[start];
            checkSet[start] = checkSet[end];
            checkSet[end] = tmp2;
        }
    }
	
    

    void firstGuess(String solution){
		outcome = "Step 1: " + "1234    ";
    }
    
    void firstGuessUpdate(int a, int b){
    	updateAnsSet("1234", a, b);
    }

	void secondGuess(int a, int b){
        
        if (a == 4) {

    		outcome = "\nThe Goal Number is: 1234.";
    		finish = true;
        }

        if (a + b == 3) {

    		outcome = a + "A" + b + "B" + "\n" + "Step 2: " + "4567" + "    ";
	        
        } else if (a == 0 && b == 2) {

        	outcome = a + "A" + b + "B" + "\n" + "Step 2: " + "0168" + "    ";
	        
        } else if (a == 1 && b == 1) {

        	outcome = a + "A" + b + "B" + "\n" + "Step 2: " + "0167" + "    ";
            
        }  else if (a == 3 && b == 0) {

        	outcome = a + "A" + b + "B" + "\n" + "Step 2: " + "1567" + "    ";
            
        } else if (a == 2 && b == 0) {

        	outcome = a + "A" + b + "B" + "\n" + "Step 2: " + "1067" + "    " ;
            
	    } else if (a + b == 1) {
            reverseAnsSet();
            guess(a, b);
            outcome = a + "A" + b + "B" + "\n" + "Step 2: " + outcome;
        } else{
            guess(a, b);
            outcome = a + "A" + b + "B" + "\n" + "Step 2: " + outcome;
        }
 
	}
	
	void secondGuessUpdate(int a, int b, int a1, int b1){
        
        if (a + b == 3) {
        	updateAnsSet("4567", a1, b1);

        } else if (a == 0 && b == 2) {
        	updateAnsSet("0168", a1, b1);

        	
        } else if (a == 1 && b == 1) {
        	updateAnsSet("0167", a1, b1);

            
        } else if (a == 2 && b == 0) {
        	updateAnsSet("1067", a1, b1);

  	
	    } else if (a + b == 1) {
	    	updateAnsSet(guessNumber, a1, b1);
        } else{
        	updateAnsSet(guessNumber, a1, b1);
        }
 
	}
	
	void guess(int a, int b){
		
        for (int i = 0; i < checkSet.length; i++) {

            if (!checkSet[i]) {
                continue;
            }
            
    		int count = 0;
            for (int j = 0; j < checkSet.length; j++) {

                if (checkSet[j]) {
                    count++;
                }
            }
            
            System.out.print("\ncheckSet is: " + count);
            
            if (count == 0){
            	outcome = "You've entered wrong response, please check it again and click 'figure out' button.";
            	break;
            } else {
            	
            	guessNumber = answerSet[i];
    	        outcome = answerSet[i] +  "    ";
    	        if (a == 4) {
    	        	outcome = "The Goal Number is: " + answerSet[i] + ".";
    	        	finish = true;
                    break;
                }
            	break;
            }
            	
        }
	}
	
	void guessUpdate(int a, int b){
		updateAnsSet(guessNumber, a, b);
	}
	
}
