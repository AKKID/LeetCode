package TheMethodOfProgramming.Chapter2Array;

public class findKthNumber {

    public static void main(String[] args){
        int[] nums = {2,3,4,4,5,6,7,8};
        findKthNumber f = new findKthNumber();
        System.out.println(f.findKth(nums,4));
    }
    public int findKth(int[] nums, int k){
        if(k == 1){
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < nums.length;i++){
                max = Math.max(max, nums[i]);
            }
            return max;
        }
        int ge = 1, pivot = nums[0];
        for(int i = 1; i < nums.length;i++){
            if(nums[i] >= pivot)
                ge++;
        }
        if(k == ge)
            return pivot;
        else if(k < ge){
            int[] ges = new int[ge];
            int j = 0;
            for(int i = 1; i < nums.length;i++){
                if(nums[i] >= pivot){
                    ges[j++] = nums[i];
                }
            }
            return findKth(ges,k);
        }else{
            int[] les = new int[nums.length - ge];
            int j = 0;
            for(int i = 0;i < nums.length;i++){
                if(nums[i]  < pivot){
                    les[j++] = nums[i];
                }
            }
            return findKth(les, k - ge);
        }
    }
    public  int findKthLargest(int[] nums, int k, int left, int right){
        if(left == right)
            return nums[left];
        int pivot = nums[left];
        int i = left + 1, j = right - 1;
        for(;;){
            while(nums[i] >= pivot) i++;
            while(nums[j] < pivot) j--;
            if(i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }else{
                break;
            }
        }
        System.out.println("i: " + i);
//        return 0;
        if(k <= i){
            return findKthLargest(nums,k,left,i);
        }else{
            return findKthLargest(nums,k -  i, i + 1, right);
        }
    }
}
