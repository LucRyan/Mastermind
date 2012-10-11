public class MastermindSolverAverage_1 {
 
    String[] answerSet = new String[10 * 9 * 8 * 7];
 
    String[] testSet = new String[10 * 9 * 8 * 7];
 
    boolean[] validSet = new boolean[10 * 9 * 8 * 7];
 
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
 
    int guess(String solution) {
        int count = 0;
 
        count++;
        int a = getA(solution, "1234");
        if (a == 4) {
            return count;
        }
        int b = getB(solution, "1234");
        reduce("1234", a, b);
 
        for (int i = 0; i < validSet.length; i++) {
            if (!validSet[i]) {
                continue;
            }
            count++;
            a = getA(solution, answerSet[i]);
            if (a == 4) {
                break;
            }
            b = getB(solution, answerSet[i]);
            reduce(answerSet[i], a, b);
        }
 
        return count;
    }
  
}