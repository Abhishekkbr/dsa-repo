package com.arrays;

public class ProductExceptSelf {
    public static void main(String[] args) {
        ProductExceptSelf solver = new ProductExceptSelf();
        int[] nums = {1, 2, 3, 4};
        int[] result = solver.productExceptSelf(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: 24 12 8 6
    }

    private int[] productExceptSelf(int[] nums) {
        int[] out = new int[nums.length];
        // multiply only left side 1, 2, 3 so in out[2] = 1*2 = 2 same for 1,2,3,4 out[3] = 1*2*3 = 6
        out[0] = 1;
        for(int i = 1 ; i < nums.length ; i++) {
            out[i] = out[i-1] * nums[i-1];
        }
        // this from right side 1,2,3,4 so for out[3] = 1 out[2] = 4 out[1] = 3*4 out[0] = 2*3*4
        int right = 1;
        for (int i = nums.length-1 ; i >= 0 ; i--) {
            out[i] = out[i] * right;
            right *= nums[i];
        }
        return out;
    }
}
