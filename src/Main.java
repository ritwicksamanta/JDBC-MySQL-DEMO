public class Main {
   public static void main(String[] args) { 
   
	   //fill the code
	   //List<AnnualRainfall> rainfallReport = new ArrayList<AnnualRainfall>();
	   RainfallReport report = new RainfallReport();
	   for(AnnualRainfall res : report.generateRainfallReport("src/AllCityMonthlyRainfall.txt")) {
		   System.out.println(res);
	   }
	   System.out.println("List from Heavy Rainfall city details");
	   for(AnnualRainfall res:report.findMaximumRainfallCities())
		   System.out.println(res);
   }
}
          