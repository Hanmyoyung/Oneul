import vo.WeatherInfoVO;
import dao.WeatherInfoDAO;


public class WeatherIconInsert {

	public static void main(String[] args) {
		
		   WeatherInfoDAO dao = new WeatherInfoDAO();
		   WeatherInfoVO vo = new WeatherInfoVO();
		   
		   dao.insert(vo);
		   
		   System.out.println("입력완료");

	}

}
