package edu.wit.algorithm.array;
public class SmallSumMerge {

    public int getSmallNum(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSortForGetNum(nums, 0, nums.length - 1, temp);
    }

    public int mergeSortForGetNum(int[] a, int l, int r, int[] temp){
        if(l >= r) return 0;
        int mid = l + ((r - l) >> 1);
        return mergeSortForGetNum(a, l, mid, temp) + mergeSortForGetNum(a, mid + 1, r, temp) + mergeTwoArray(a, l, mid, r, temp);
    }

    public int mergeTwoArray(int[] a, int l, int mid, int r, int[] temp){
        System.arraycopy(a, l, temp, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        int res = 0;
        for(int k = l; k <= r; k++){
            if(i == mid + 1){
                a[k] = temp[j];
                j++;
            }else if(j == r + 1){
                a[k] = temp[i];
                i++;
            }else if(temp[i] <= temp[j]){
                res += temp[i] * (r - j + 1);
                a[k] = temp[i];
                i++;
            }else{
                a[k] = temp[j];
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,2,5};
        System.out.println(new SmallSumMerge().getSmallNum(nums));
    }
}