import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

	public static void main(String[] args) {
		Timer t = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = format.format(date);
				System.out.println(time);
			}
		};
		t.schedule(task, 500);
	}

}
