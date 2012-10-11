public class MastermindSolverAverage_3 {
 
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
 
        
        if (a + b == 3) {
            count++;
            a = getA(solution, "4567");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "4567");
            reduce("4567", a, b);
        } else if (a == 0 && b == 2) {
            count++;
            a = getA(solution, "0168");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "0168");
            reduce("0168", a, b);
        } else if (a == 1 && b == 1) {
            count++;
            a = getA(solution, "0167");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "0167");
            reduce("0167", a, b);
        }  else if (a == 3 && b == 0) {
            count++;
            a = getA(solution, "1567");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "1567");
            reduce("1567", a, b);
        } else if (a == 2 && b == 0) {
            count++;
            a = getA(solution, "1067");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "1067");
            reduce("1067", a, b);
        } else if (a == 0 && b == 2) {
            count++;
            a = getA(solution, "4567");
            if (a == 4) {
                return count;
            }
            b = getB(solution, "4567");
            reduce("4567", a, b);
        } else if (a + b == 1) {
            reverseAnswerSet();
        }
 
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
 
}