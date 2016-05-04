import java.util.ArrayList;
import java.util.List;



 abstract class DessertItem {
	 


}
 
 

 class Sundae  extends IceCream

{
	private String name;
	private int toppingCost;

	
	public Sundae(String string, int i, String string2, int j) {
		// TODO Auto-generated constructor stub	
	super(string,i);
	this.name=string2;
	this.toppingCost=j;
	
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getToppingCost() {
		return toppingCost;
	}


	public void setToppingCost(int toppingCost) {
		this.toppingCost = toppingCost;
	}
		

}

 
 
 



 class Cookie  extends DessertItem{
	

	private String name;
	private int number;
	private int pricePerDozen;

	
	
	public Cookie(String name, int number, int price) {
		super();
		this.name = name;
		this.number = number;
		this.pricePerDozen = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPricePerDozen() {
		return pricePerDozen;
	}

	public void setPricePerDozen(int pricePerDozen) {
		this.pricePerDozen = pricePerDozen;
	}
	
}


 class IceCream extends DessertItem
{
	
private String icreCreamname;
private  int cost;






public int getCost() {
	return cost;
}





public void setCost(int cost) {
	this.cost = cost;
}





	public String getIcreCreamname() {
	return icreCreamname;
}





public void setIcreCreamname(String icreCreamname) {
	this.icreCreamname = icreCreamname;
}





	public IceCream(String name, int cost) {
		super();
		this.icreCreamname= name;
		this.cost = cost;
	}
	
	
	
}

public class Checkout {
	
	
	

	ArrayList<DessertItem> lists;

	public Checkout() {

		if (lists == null) {
			lists = new ArrayList<DessertItem>();
		}
	}

	// , 4 cookies @ 399 cents /dz. = 133 cents. The cost should be rounded to
	// the nearest cent.
	// The Cookie class should be derived from the DessertItem class.
	// A Cookie item has a number and a price per dozen which are used to
	// determine its cost. For example, 4 cookies @ 399 cents /dz. = 133 cents.
	// The cost should be rounded to the nearest cent.
	// The IceCream class should be derived from the DessertItem class.
	// An IceCream item simply has a cost.
	// The Sundae class should be derived from the IceCream class. The cost of a
	// Sundae is the cost of the IceCream plus the cost of the topping.

	public void enterItem(DessertItem item) {

		lists.add(item);

	}

	public void clear() {

		lists = null;
		lists = new ArrayList<DessertItem>();

	}

	public int numberOfItems() {

		return lists.size();

	}

	public int totalCost() {

		int totalcost1 = 0, totalcost2 = 0, totalcost3 = 0, totalcost = 0;

		for (int i = 0; i < lists.size(); i++) {

			DessertItem items = lists.get(i);

			if (items instanceof Cookie) {

				int pricePerDozen = ((Cookie) items).getPricePerDozen();
				int Number = ((Cookie) items).getNumber();
				totalcost1 = (Number  * pricePerDozen) / 12;

			}

			else if (items instanceof Sundae) {
				Sundae t = (Sundae) items;
				totalcost2 = t.getCost() + t.getToppingCost();

			}

			else if (items instanceof IceCream) {

				IceCream t = (IceCream) items;
				totalcost3 = t.getCost();

			}

			totalcost = totalcost1 + totalcost2 + totalcost3;

		}

		return totalcost;
	}

	@Override
			public String toString() {
				
				 
					 
				String finalString="";
				
				
					 for ( int i=0;i<lists.size();i++)
					  {
						  
						  DessertItem  items=	 lists.get(i);
						 
						   if(items instanceof  Cookie)
						  {
						
							   
							   int pricePerDozen = ((Cookie) items).getPricePerDozen();
								int Number = ((Cookie) items).getNumber();
								int totalcost1 = (Number  * pricePerDozen) / 12;
							   
							   
							   Cookie t= (Cookie) items;
							   finalString=finalString+t.getName()+"  "+t.getNumber() +"  @"+ DessertShoppe.cents2dollarsAndCents(t.getPricePerDozen())+"/dozen"+" 	"+DessertShoppe.cents2dollarsAndCents(totalcost1)+"\n";
							    
									
						  }
						  
						  else if(items instanceof Sundae )
						  {
							  
							  Sundae t1 = (Sundae) items;
								int totalcost2 = t1.getCost() + t1.getToppingCost();
								
							  Sundae t  =(Sundae) items;
							  finalString=finalString+t.getName()+" with "+t.getIcreCreamname()+"  @"+DessertShoppe.cents2dollarsAndCents( t.getCost()+t.getToppingCost())+"	 "+DessertShoppe.cents2dollarsAndCents(totalcost2)+"\n";
							 
						  }
						  
						  else if(items instanceof  IceCream )
						  {
							  

								IceCream t1 = (IceCream) items;
								int totalcost3 = t1.getCost();

							  IceCream  t =(IceCream) items;
							  finalString=finalString+t.getIcreCreamname()+"  @"+DessertShoppe.cents2dollarsAndCents(t.getCost())+"		"+DessertShoppe.cents2dollarsAndCents(totalcost3)+"\n";
							
							  
					  
						  }
						   

				
			}


					 return finalString;
					 
	}

		
			
}
