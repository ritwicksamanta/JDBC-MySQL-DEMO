import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class RainfallReport {

	//Write the required business logic as expected in the question description
	public List<AnnualRainfall> generateRainfallReport(String filePath) {
	    
		//fill the code
		List<AnnualRainfall> cityList = new ArrayList<AnnualRainfall>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = null;
			try {
				while((str = br.readLine())!=null) {
					String[] strArray = str.trim().split(",",0);
					try{
						if(validate(strArray[0])) {
							AnnualRainfall report = new AnnualRainfall();
							report.setCityPincode(Integer.parseInt(strArray[0]));
							report.setCityName(strArray[1]);
							double[] arr = new double[strArray.length-2];
							for(int i=2,j=0;i<strArray.length;i++,j++)
								arr[j] = Double.parseDouble(strArray[i]);
							report.calculateAverageAnnualRainfall(arr);
							cityList.add(report);
							
					}	
					}catch(InvalidCityPincodeException ex) {
						System.out.println(ex.getMessage());
				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOException occured");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		return cityList;
	}
	
	public List<AnnualRainfall>  findMaximumRainfallCities() {
	
		//fill the code
		Connection con = new DBHandler().establishConnection();
		List<AnnualRainfall> list = new ArrayList<AnnualRainfall>();
		try {
			if(!(con==null)) {
				Statement smt = con.createStatement();
				String sql = "select * from AnnualRainfall where average_annual_rainfall=(select max(average_annual_rainfall) from AnnualRainfall)";
				ResultSet result = smt.executeQuery(sql);
				while(result.next()) {
					AnnualRainfall res = new AnnualRainfall();
					//In ResultSet the cursor indexing starts from 1;
					res.setCityPincode(result.getInt(1));
					res.setCityName(result.getString(2));
					res.setAverageAnnualRainfall(result.getDouble(3));
					list.add(res);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public boolean validate(String cityPincode) throws InvalidCityPincodeException {
	
		//fill the code
		char[] arr = cityPincode.toCharArray();
		boolean flag = true;
		if(arr.length==5) {
			for(char i : arr) {
				if(!Character.isDigit(i)) {
					flag = false;
					break;
				}
			}
		}else flag=false;
    	if(!flag) throw new InvalidCityPincodeException("Invalid City Pincode");
    	else return flag;
	}

}
