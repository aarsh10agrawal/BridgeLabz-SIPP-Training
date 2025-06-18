public class profitPercentage{
	public static void main(String[] args){
	double costPrice = 129;
	double sellingPrice = 191;
	double Profit = sellingPrice - costPrice;
	double profitPercentage = (Profit / costPrice) * 100;
	System.out.println("The Cost Price is INR " + costPrice + " and Selling Price is INR " + sellingPrice);
	System.out.println("The Profit is INR " + Profit + " and the Profit Percentage is " + profitPercentage);

	}
}