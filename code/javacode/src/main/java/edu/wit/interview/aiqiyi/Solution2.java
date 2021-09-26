package edu.wit.interview.aiqiyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution2 {
    // 创建结果集
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solve(int n){
        ArrayList<StringBuilder> track = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            track.add(sb);
        }
        backTrack(track, 0);
        return res;
    }

    public void backTrack(ArrayList<StringBuilder> track, int row){
        if(row == track.size()){
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < track.size(); i++) {
                temp.add(track.get(i).toString());
            }
            res.add(temp);
            return;
        }
        int n = track.get(row).length();
        for (int col = 0; col < n; col++) {
            if(!isValid(track,row,col)) continue;
            track.get(row).setCharAt(col,'Q');
            backTrack(track,row+1);
            track.get(row).setCharAt(col,'.');
        }
    }

    public boolean isValid(ArrayList<StringBuilder> track, int row, int col){
        int n = track.size();
        for (int i = 0; i < n;  i++) {
            if(track.get(i).charAt(col) == 'Q'){
                return false;
            }
        }
        for (int i = row - 1,j = col + 1; i >= 0 && j < n ; i--,j++) {
            if(track.get(i).charAt(j) == 'Q'){
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0  ; i--, j--) {
            if(track.get(i).charAt(j) == 'Q'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(new Solution2().solve(n));
    }
}
