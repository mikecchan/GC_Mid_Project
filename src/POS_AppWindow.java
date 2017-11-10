import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import javax.swing.Box;

public class POS_AppWindow {

	private JFrame frame;
	private Item[] data;
	private Item selectedItem;
	private int quantity = 0;
	private JPanel saveState;
	private double subtotal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POS_AppWindow window = new POS_AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public POS_AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Grand Circus Grocery Market");

		/*
		 * DefaultListModel inventory = new DefaultListModel();
		 * 
		 * inventory.addElement("Hello Moto");
		 * 
		 * Component panel = new Component(); frame.add(inventory);
		 */
		ArrayList<Item> arr = TextFile.readFromFile();

		data = arr.toArray(new Item[arr.size()]);

		JLabel lblNewLabel = new JLabel("Item");

		JList inventory = new JList(data); // data has type Object[]
		inventory.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = inventory.getSelectedIndex();
				selectedItem = data[index];
				lblNewLabel.setText(selectedItem.getName());
			}
		});

		inventory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		inventory.setLayoutOrientation(JList.VERTICAL);
		inventory.setVisibleRowCount(-1);

		ArrayList<Item> arr2 = new ArrayList<Item>();

		Item[] data2 = arr2.toArray(new Item[arr.size()]);
		DefaultListModel cart = new DefaultListModel();
		JList cartList = new JList(cart); // data has type Object[]
		cartList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		cartList.setLayoutOrientation(JList.VERTICAL);
		cartList.setVisibleRowCount(-1);

		// JScrollPane listScroller = new JScrollPane(inventory);
		// listScroller.setPreferredSize(new Dimension(250, 80));

		// frame.add(listScroller);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inventory, cartList);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(400);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		inventory.setMinimumSize(minimumSize);
		cartList.setMinimumSize(minimumSize);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblInventory = new JLabel("Inventory");
		panel_1.add(lblInventory);

		Component horizontalStrut = Box.createHorizontalStrut(400);
		panel_1.add(horizontalStrut);

		JLabel lblCart = new JLabel("Cart");
		panel_1.add(lblCart);

		frame.getContentPane().add(splitPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		panel.add(lblNewLabel);

		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity = (Integer) spinner.getValue();

			}
		});

		panel.add(spinner);

		JButton btnAddToCart = new JButton("Add to Cart");

		panel.add(btnAddToCart);

		JPanel checkout = new JPanel();
		JLabel checkoutHead = new JLabel();
		checkoutHead.setText("Checkout");

		JButton confirm = new JButton("Confirm Order");
		checkout.add(checkoutHead);

		checkout.add(confirm);

		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checkout.checkout(sc, cart);

				frame.remove(checkout);

			}
		});

		JButton btnChcekout = new JButton("Checkout");
		btnChcekout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checkout.checkout(sc, cart);

				 final JDialog dialog = new JDialog(frame, "Child", true);    
				 
				 JPanel checkout = new JPanel();
				    dialog.setSize(400, 300);
				    dialog.setLocationRelativeTo(frame);
				    JButton button = new JButton("Button");
				    button.addActionListener(new ActionListener() {
				      @Override
				      public void actionPerformed(ActionEvent e) {
				        dialog.dispose();
				      }
				    });
				    JLabel checkoutLabel = new JLabel("Checkout\n");
				    JLabel subtotalLabel = new JLabel("Subtotal: "+ (Item.formatPrice(subtotal))+"\n");
				    JLabel tax = new JLabel("Tax: "+ (Item.formatPrice(subtotal * .06))+"\n"); 
				    JLabel total = new JLabel("Tax: "+ (Item.formatPrice(subtotal * 1.06))+"\n");
				    
				    checkout.add(checkoutLabel);
				    checkout.add(subtotalLabel);
				    checkout.add(tax);
				    checkout.add(total);
				    


				    checkout.add(button);
				    dialog.add(checkout);
				    dialog.setUndecorated(true);
				    dialog.setVisible(true);
			}
		});

		Component horizontalStrut_1 = Box.createHorizontalStrut(200);
		panel.add(horizontalStrut_1);
		btnChcekout.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(btnChcekout);

		JLabel lblTotal = new JLabel("Total:");
		panel.add(lblTotal);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setText("$0.00");

		panel.add(lblNewLabel_1);

		btnAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Shop.addToCart(scnr, inventory, cart, selection, userQty)
				if (selectedItem != null && quantity != 0) {
					Item tempItem = new Item(selectedItem.getId(), selectedItem.getName(), selectedItem.getCat(),
							selectedItem.getDesc(), quantity, selectedItem.getPrice());

					arr2.add(tempItem);
					subtotal = Checkout.getSubtotal(arr2);
					lblNewLabel_1.setText(Item.formatPrice(subtotal));
					cart.addElement(tempItem);
				}
			}
		});

	}

}
