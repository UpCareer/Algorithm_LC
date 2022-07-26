package com.lc;

public class LC43_MultiplyStrings {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for(int i = len1 - 1; i >= 0; i--){
            for(int j = len2 - 1; j >= 0; j--){
                int x = num1.charAt(i) - '0';
                int y = num2.charAt(j) - '0';
                int pos1 = i + j;
                int pos2 = i + j + 1;

                int sum = x * y + res[pos2] + res[pos1] * 10;

                res[pos2] = sum % 10;
                res[pos1] = sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.length; i++){
            if(sb.length() == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
