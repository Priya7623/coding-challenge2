import java.util.*;

public class FBS{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int n = sc.nextInt();
        int[] startIndex = new int[n];
        for (int i = 0; i < n; i++) {
            startIndex[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] endIndex = new int[m];
        for (int i = 0; i < m; i++) {
            endIndex[i] = sc.nextInt();
        }

        int[] result = countFrogsBetweenBars(s, startIndex, endIndex);
        for (int res : result) {
            System.out.println(res);
        }
        
        sc.close();
    }

    public static int[] countFrogsBetweenBars(String s, int[] startIndex, int[] endIndex) {
        int n = s.length();
        int[] prefixSumFrogs = new int[n + 1]; 
        int[] leftBar = new int[n]; 
        int[] rightBar = new int[n];
        for (int i = 0; i < n; i++) {
            prefixSumFrogs[i + 1] = prefixSumFrogs[i] + (s.charAt(i) == '*' ? 1 : 0);
        }

        int lastLeftBar = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                lastLeftBar = i;
            }
            leftBar[i] = lastLeftBar;
        }

        int lastRightBar = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                lastRightBar = i;
            }
            rightBar[i] = lastRightBar;
        }


        int[] result = new int[startIndex.length];
        for (int q = 0; q < startIndex.length; q++) {
            int start = startIndex[q] - 1; 
            int end = endIndex[q] - 1; 

            int leftBound = rightBar[start]; 
            int rightBound = leftBar[end]; 

            if (leftBound != -1 && rightBound != -1 && leftBound <= rightBound) {
                result[q] = prefixSumFrogs[rightBound + 1] - prefixSumFrogs[leftBound + 1];
            } else {
                result[q] = 0;
            }
        }

        return result;
    }
}
