package Practice;

public class Heapsorts1 {

	public static void main(String[] args) {
		
		 int a []= {20,12,3,4,5,6,24,50,38,56,47};
		 new Heapsorts1(). heapSort(a,a.length);
		 //new Heapsorts1(). heapSorting(a);
		 
		 System.out.println(" sorted elements ");
		 for(int i=0;i<a.length;i++)
		 {
			 System.out.println( a [i]);
		 }
		// heapSort(a,a.length);
	}



void heapify(int arr[], int n, int i)
{
    int largest = i;  // Initialize largest as root
    int l = 2*i + 1;  // left = 2*i + 1
    int r = 2*i + 2;  // right = 2*i + 2
 
    // If left child is larger than root
    if (l < n && arr[l] > arr[largest])
        largest = l;
 
    // If right child is larger than largest so far
    if (r < n && arr[r] > arr[largest])
        largest = r;
    
    // If largest is not root
    if (largest != i)
    {
        swap(arr, i ,largest);      
        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}

/*
// main function to do heap sort
void heapSort(int arr[], int n)
{
    // Build heap (rearrange array)
    for (int i = n  - 1; i >= 0; i--)
        heapify(arr, n, i);
 
    // One by one extract an element from heap
    for (int i=n-1; i>=0; i--)
    {
        // Move current root to end
        swap(arr,0,i);
 
        // call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}

*/


void heapSort(int arr[], int n)
{
    // Build heap (rearrange array)
    for (int i = n  - 1; i >= 0; i--)
        heapify(arr, n, i);
 
    // One by one extract an element from heap
    for (int i=n-1; i>=0; i--)
    {
        // Move current root to end
        swap(arr,0,i);
 
        // call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}




 
void adjust(int a[], int length)
 {
	//int j=0;
	int index=0;
	
	 while(true)
	 { 
	   int  l= 2*index+1;
	   int  r= 2*index+2;
	   boolean flag=false;
	   
	 if(l<length )
	 {
		  if(a[l]> a[index])
		  {
			swap(a ,l,index);
			index=l;
			flag=true;
		  }
	 }
	 if(r<length )
	 {
		  if(a[r]> a[index])
		  {
			swap(a ,r,index);
			index=r;
			flag=true;
		  }
		 
	  }	 
	 if(!flag)
	 {
		break; 
	 }
	 
	 }
	 
 }
 

/*
void adjust(int a[],int n) {
	int i,j,item;
	j = 0;
	item = a[j];
	i = 2*j+1;
	
	while(i<=n-1) {
		
		
		if(i+1 <= n-1)
		   if(a[i] <a[i+1])
		    i++;
		
		
		if(item<a[i]) {
			a[j] = a[i];
			j = i;
			i = 2*j+1;
		} else
		   break;
	}
	
	a[j] = item;
}
 
 */



 
 void heap(int a[], int index )
 {
	 int z=0; 
	 while(true)
	 { 
		 if(index%2==0)
		 {
	     z= index/2-1;
		 }
		 else
		 {
		 z= index/2; 
		 }
	 
	 if(z>=0)
	 {
		  if(a[z]< a[index])
		  {
			swap(a ,z,index);
			index=z;
		  }  
		  else
		  {
			break;  
		  }	
	 }
	 else
		 break;
	 }
		  
 }
 
 private void swap(int[] a, int z, int index) {
	// TODO Auto-generated method stub
	 int temp =a[z];
	 a[z]=a[index];
	 a[index]=temp;
}

 
 
}
