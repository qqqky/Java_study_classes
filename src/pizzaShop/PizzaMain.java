package pizzaShop;
import javax.swing.JFrame;

public class PizzaMain {

	public static void main(String []args)
	{
		
		JFrame pizza = new JFrame("Restaurant's Menu");
		pizza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Pizza panel = new Pizza();
		pizza.getContentPane().add(panel);
		
		pizza.setSize(450,300);
		pizza.setVisible(true);
		
	}
}
