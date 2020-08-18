package pizzaShop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pizza extends JTabbedPane
{
public JButton calc1, calc2, calc3;
public JLabel amount, sum1,sum2,sum3;
public int total, total1, total2, total3, savedTotal1, savedTotal2, savedTotal3;

	
public Pizza()
	{
		
		setBackground(Color.GREEN);
		addTab("Pizzas", new PizzaPanel());
		addTab("Beverages", new BeveragesPanel());
		addTab("Delivery", new DeliveryPanel());
		
		
		
	}


class PizzaPanel extends JPanel
{
	public PizzaPanel()
	{
		setLayout(new BorderLayout());
		RightPanel1 p1 = new RightPanel1();
		add(p1, BorderLayout.EAST);
		
		PizzaCenter pizzas = new PizzaCenter();
		add(pizzas, BorderLayout.CENTER);
		
	}
}

class BeveragesPanel extends JPanel
{
	public BeveragesPanel()
	{
		setLayout(new BorderLayout());
		RightPanel2 p2 = new RightPanel2();
		add(p2, BorderLayout.EAST);
		
		BeveragesCenter beverages = new BeveragesCenter();
		add(beverages, BorderLayout.CENTER);
	}
}
class DeliveryPanel extends JPanel
{
	public DeliveryPanel()
	{
		setLayout(new BorderLayout());
		RightPanel3 p3 = new RightPanel3();
		add(p3, BorderLayout.EAST);
		
		DeliveryCenter delivery = new DeliveryCenter();
		add(delivery, BorderLayout.CENTER);
	}
}


class DeliveryCenter extends JPanel
{
	public JRadioButton del1, del2;
	public JLabel standard, express, delivLabel;
	public int delPrice1, delPrice2;
	
	
	public DeliveryCenter()
	{
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		del1 = new JRadioButton("Standard (1-2 hours)      $5");
		del1.setBackground(Color.GREEN);
		del2 = new JRadioButton("Express (<1 hour)          $15");
		del2.setBackground(Color.GREEN);
		delivLabel= new JLabel (" Select delivery option: ");
		delivLabel.setFont(new Font("Ariel", Font.ITALIC, 22));
		
		
		delPrice1 = 5;
		delPrice2= 15;
		
	//*** implements listener for 3rd tab (ActionEvent) ---- DELIVERY ***
		deliveryListener listener3 = new deliveryListener();
		
		
		
		ButtonGroup gr1 = new ButtonGroup();
		gr1.add(del1);
		gr1.add(del2);
		
		del1.addActionListener(listener3);
		del2.addActionListener(listener3);
		
		add(delivLabel);
		add(Box.createVerticalGlue());
		add(del1);
		add(del2);
		add(Box.createVerticalGlue());
		setBackground(Color.GREEN);
		
	}

private class deliveryListener implements ActionListener
{
	public void actionPerformed (ActionEvent e)
	{
		Object delSource = e.getSource();
		if (delSource == del1)
			{
			total3=total3+5;	
			}
		
		if (delSource == del2)
			{
			total3=total3+15;
			
			}
			savedTotal3=total3;		//saves calculations to be used later
			sum3.setText(""+getTotal());
			sum1.setText(""+getTotal());
			sum2.setText(""+getTotal());
			total3=0;	//resets calculation
			
			
		
	}

}

}
class BeveragesCenter extends JPanel
{
	public JCheckBox bev1, bev2, bev3, bev4;
	public JLabel BeverageLabel;
	public int bev1Price, bev2Price, bev3Price, bev4Price;
	
	public BeveragesCenter()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.GREEN);
		
		bev1 = new JCheckBox("Water                  $2");
		bev1.setBackground(Color.GREEN);
		bev2 = new JCheckBox("Coke                   $3");
		bev2.setBackground(Color.GREEN);
		bev3 = new JCheckBox("Orange Juice    $4");
		bev3.setBackground(Color.GREEN);
		bev4 = new JCheckBox("Beer                   $5");
		bev4.setBackground(Color.GREEN);
		
		BeverageLabel = new JLabel (" Select your drink(s): ");
		BeverageLabel.setFont(new Font("Ariel", Font.ITALIC, 22));
		
	//***implements listener for 2nd tab (itemEvent) -- BEVERAGES ***
		foodListener listener2 = new foodListener();
		bev1.addItemListener(listener2);
		bev2.addItemListener(listener2);
		bev3.addItemListener(listener2);
		bev4.addItemListener(listener2);
		
		add(BeverageLabel);
		add(Box.createVerticalGlue());
		add(bev1);
		add(bev2);
		add(bev3);
		add(bev4);
		add(Box.createVerticalGlue());
		
		
		
	}
private class foodListener implements ItemListener
{
	public void itemStateChanged (ItemEvent event)
	{
		//Object bevSource = event.getSource();
		if (bev1.isSelected())
		{
			total2 = total2+2;
		}
		if (bev2.isSelected())
		{
			total2 = total2+3;
		}
		if (bev3.isSelected())
		{
			total2 = total2+4;
		}
		if (bev4.isSelected())
		{
			total2 = total2+5;
		}
		savedTotal2=total2;
		sum2.setText(""+getTotal());
		sum1.setText(""+getTotal());
		sum3.setText(""+getTotal());
		total2=0;
		
		
	}
}
}
class PizzaCenter extends JPanel
{
	public JCheckBox piz1, piz2, piz3, piz4, piz5;
	public JLabel pizLabel;
	public int piz1Price, piz2Price, piz3Price, piz4Price, piz5Price;
	
	public PizzaCenter()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.GREEN);
		
		piz1 = new JCheckBox("Margherita   $5");
		piz1.setBackground(Color.GREEN);
		piz2 = new JCheckBox("Funghi         $7");
		piz2.setBackground(Color.GREEN);
		piz3 = new JCheckBox("Fromagi       $7");
		piz3.setBackground(Color.GREEN);
		piz4 = new JCheckBox("Diavolo        $9");
		piz4.setBackground(Color.GREEN);
		piz5 = new JCheckBox("Speziale      $11");
		piz5.setBackground(Color.GREEN);
		pizLabel = new JLabel (" Select your pizza: ");
		pizLabel.setFont(new Font("Ariel", Font.ITALIC, 22));
		
		//***implements listener for 1st tab (itemEvent) -- PIZZAS ***
		pizzaListener listener1 = new pizzaListener();
		piz1.addItemListener(listener1);
		piz2.addItemListener(listener1);
		piz3.addItemListener(listener1);
		piz4.addItemListener(listener1);
		piz5.addItemListener(listener1);
		
		add(pizLabel);
		add(Box.createVerticalGlue());
		add(piz1);
		add(piz2);
		add(piz3);
		add(piz4);
		add(piz5);
		add(Box.createVerticalGlue());
		
	}
	private class pizzaListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent event1)
		{
		Object pizzaSource = event1.getSource();
		
		
			if(piz1.isSelected())
			{
				total1 = total1+5;
					
			}
			if (piz2.isSelected())
			{
				total1 = total1+7;

			}
			if (piz3.isSelected())
			{
				total1 = total1+7;
			}
			if (piz4.isSelected())
			{
				total1 = total1+9;
			}
			if (piz5.isSelected())
			{
				total1 = total1+11;
			}
			savedTotal1= total1;
			sum1.setText(""+getTotal());
			sum2.setText(""+getTotal());
			sum3.setText(""+getTotal());
			total1=0;
			
		}
		
	}
	
}

//defines the panel on the right (same for every tab)<==will not work this way
//only the last panel can be updated via events!
//so 3 different panels have to be instantiated
class RightPanel1 extends JPanel
{
	public RightPanel1()
	{
		setPreferredSize(new Dimension(90,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(BorderFactory.createLineBorder(Color.RED,2));
		setBackground(Color.GREEN);
		
		calc1 = new JButton("Calculate");
		amount = new JLabel("Total: ");
		sum1 = new JLabel(""+total1);
		
		add(Box.createVerticalGlue());
		add(amount);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(sum1);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(calc1);
		
		
	}
}
class RightPanel2 extends JPanel
{
	public RightPanel2()
	{
		setPreferredSize(new Dimension(90,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(BorderFactory.createLineBorder(Color.RED,2));
		setBackground(Color.GREEN);
		
		calc2 = new JButton("Calculate");
		amount = new JLabel("Total: ");
		sum2 = new JLabel(""+total2);
		
		
		add(Box.createVerticalGlue());
		add(amount);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(sum2);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(calc2);
		
		
	}
}
class RightPanel3 extends JPanel
{
	public RightPanel3()
	{
		setPreferredSize(new Dimension(90,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(BorderFactory.createLineBorder(Color.RED,2));
		setBackground(Color.GREEN);
		
		calc3 = new JButton("Calculate");
		amount = new JLabel("Total: ");
		sum3 = new JLabel(""+total3);
		
		add(Box.createVerticalGlue());
		add(amount);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(sum3);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(calc3);
		
		
	}
}
private int getTotal()
{
	total = savedTotal1+savedTotal2+savedTotal3;
	
	return total;
}
}


