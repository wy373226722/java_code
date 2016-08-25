import java.util.Random;

public class Test {
	public static void main(String[] args) {
		long oldTime = System.currentTimeMillis();
		for(int n=0;n<1000;n++){
			System.out.println(get_random(128));
		}
		long newTime = System.currentTimeMillis();
		System.out.println("经过了" + (newTime-oldTime)+"毫秒");
	}
	
	private static String get_random(int length){
		String str = "";
		String chars = "abcdefghijkmnpqrstuvwxyz";
		Random r = new Random();
		for(int i = 0; i < length; i++){
			if(r.nextBoolean()){
				str+=r.nextInt(10);
			}else{
				int index = r.nextInt(chars.length());
				str+=chars.charAt(index);
			}
		}
		return str;
	}

}
