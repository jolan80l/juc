package com.jolan.algorithm.sort;

/**
 * @author jolan80
 * @date 2020-07-03 22:37
 */
public class SortArray {
    public static void main(String[] args) {
//        int[] arr1 = {1,2,3,4};
//        int[] arr2 = {5,6,7,8};

        int[] arr1 = {7,12,36,58};
        int[] arr2 = {1,6,36,92};

        int[] sortArr = new int[arr1.length + arr2.length];

        int i=0;
        int j=0;
        int k=0;
        int x = arr1[i];
        int y = arr2[j];
        int repeatCount = 0;
        for(;(i < arr1.length) && (j < arr2.length);){
            if(x < y){
                sortArr[k] = x;
                k++;
                if(i == arr1.length - 1){
                    break;
                }
                x = arr1[i+1];
                i++;
            }else if(x > y){
                sortArr[k] = y;
                k++;
                if(j == arr2.length - 1){
                    break;
                }
                y = arr2[j+1];
                j++;
            }else if(x == y){
                sortArr[k] = x;
                k++;
                repeatCount++;
                if((i == arr1.length - 1) || (j == arr2.length - 1)){
                    break;
                }
                x = arr1[i+1];
                y = arr2[j+1];
                i++;
                j++;
            }
        }
        if(i == arr1.length -1){
            for(;j < arr2.length;j++,k++){
                sortArr[k] = arr2[j];
            }
        }
        if(j == arr2.length -1){
            for(;i < arr1.length;i++,k++){
                sortArr[k] = arr1[i];
            }
        }
        for(int o=0;o<sortArr.length - repeatCount;o++){
            System.out.println(sortArr[o]);
        }
    }
}
