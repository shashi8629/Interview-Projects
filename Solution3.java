package Practice;

import java.util.Arrays;

public class Solution3 {
	
	public static void main(String [] args )
	{
		
		int[] coins = {1,3,5,2};
		new Solution3().coinChange(coins,11);	
		
	}
	
public int coinChange(int[] coins, int amount) {
	
	int temp=amount;
	Arrays.sort(coins);
	int counter=0;
	for(int i=coins.length-1;i>=0;i--)
	{
	    counter=counter+ temp /coins[i];
		temp=temp % coins[i];
		if(temp==0)
			break;

		
	}
	
	System.out.println(counter);
	return counter;
    }

}
