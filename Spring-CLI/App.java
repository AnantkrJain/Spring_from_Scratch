@RestController
public class App{
	
	@RequestMapping("/")
	public String m1(){
		return "App is running";
	}
}