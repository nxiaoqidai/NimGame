
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NimGame g = new NimGame();
		String command = g.keyboard.nextLine();
		String[] value = command.split("\\ ");
		if (value[0].equals("a"))
			System.out.println("yes");
		else
			System.out.println(value.length);
	}

}
