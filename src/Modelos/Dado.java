package Modelos;
import java.util.Random;

public class Dado {

	public int Joga() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
}
