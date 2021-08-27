import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnualRainfall {
	
	
	private int cityPincode;
	private String cityName;
	private double averageAnnualRainfall;

	public int getCityPincode() {
		return cityPincode;
	}

	public void setCityPincode(int cityPincode) {
		this.cityPincode = cityPincode;
	}

	public String getCityName(){
		return cityName;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}


	public double getAverageAnnualRainfall(){
		return averageAnnualRainfall;
	}


	public void setAverageAnnualRainfall(double averageAnnualRainfall){
		this.averageAnnualRainfall = averageAnnualRainfall;
	}

	//Write the required business logic as expected in the question description
	public void calculateAverageAnnualRainfall (double monthlyRainfall [ ]){
	    
		//fill the code
		List<Double> list = new ArrayList<Double>();
		for(double i:monthlyRainfall)
			list.add(i);
		double sum = list.stream().collect(Collectors.summingDouble(Double::valueOf));
		this.setAverageAnnualRainfall(sum/list.size());	
	}

	@Override
	public String toString() {
		return this.getCityPincode()+"\t"+this.getCityName()+"\t"+String.format("%.2f", this.getAverageAnnualRainfall());
	}
}
