/* 
Name : MUHAMMAD NUREEL AQQWA BIN HISHAM
ID   : AM2307013932
Type : Assignment
*/
package swcAssignment;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class OrderManagementSystem extends JFrame {
    private ArrayList<Order> orderList = new ArrayList<>();

    public OrderManagementSystem() {
        // Load orders from file
        loadOrdersFromFile();

        // Set up the frame
        setTitle("EzSHOPPING");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create menus
        JMenu orderMenu = new JMenu("Order");
        menuBar.add(orderMenu);

        // Create menu items
        JMenuItem addOrderItem = new JMenuItem("Add Order");
        JMenuItem updateOrderItem = new JMenuItem("Update Order");
        JMenuItem deleteOrderItem = new JMenuItem("Delete Order");
        JMenuItem displayOrdersItem = new JMenuItem("Display Orders");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to menu
        orderMenu.add(addOrderItem);
        orderMenu.add(updateOrderItem);
        orderMenu.add(deleteOrderItem);
        orderMenu.add(displayOrdersItem);
        orderMenu.addSeparator();
        orderMenu.add(exitItem);

        // Add action listeners for menu items
        addOrderItem.addActionListener(e -> addNewOrder());
        updateOrderItem.addActionListener(e -> updateOrder());
        deleteOrderItem.addActionListener(e -> deleteOrder());
        displayOrdersItem.addActionListener(e -> displayOrders());
        exitItem.addActionListener(e -> System.exit(0));
    }

    // Method to load orders from file
    private void loadOrdersFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\Order.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] data = line.split(";");
                String orderId = data[0];
                String customerName = data[1];
                String customerAddress = data[2];
                String productName = data[3];
                int quantity = Integer.parseInt(data[4]);
                double pricePerUnit = Double.parseDouble(data[5]);
                String orderStatus = data[6];

                // Create Order object and add to orderList
                Order order = new Order(orderId, customerName, customerAddress, productName, quantity, pricePerUnit, orderStatus);
                orderList.add(order);
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders from file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // For debugging purposes
        }
    }

    // Method to add a new order
    private void addNewOrder() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField orderIdField = new JTextField();
        JTextField customerNameField = new JTextField();
        JTextField customerAddressField = new JTextField();
        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField pricePerUnitField = new JTextField();
        JTextField orderStatusField = new JTextField();

        panel.add(new JLabel("Order ID:"));
        panel.add(orderIdField);
        panel.add(new JLabel("Customer Name:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Customer Address:"));
        panel.add(customerAddressField);
        panel.add(new JLabel("Product Name:"));
        panel.add(productNameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Price per Unit:"));
        panel.add(pricePerUnitField);
        panel.add(new JLabel("Order Status:"));
        panel.add(orderStatusField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Order", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String orderId = orderIdField.getText();
                for (Order order : orderList) {
                    if (order.getOrderId().equalsIgnoreCase(orderId)) {
                        JOptionPane.showMessageDialog(this, "Order ID already exists.");
                        return;
                    }
                }

                String customerName = customerNameField.getText();
                String customerAddress = customerAddressField.getText();
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double pricePerUnit = Double.parseDouble(pricePerUnitField.getText());
                String orderStatus = orderStatusField.getText();

                Order newOrder = new Order(orderId, customerName, customerAddress, productName, quantity, pricePerUnit, orderStatus);
                orderList.add(newOrder);
                JOptionPane.showMessageDialog(this, "Order added successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values for Quantity and Price per Unit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to update an order status
    private void updateOrder() {
        String orderId = JOptionPane.showInputDialog(this, "Enter Order ID to update");
        if (orderId != null && !orderId.isEmpty()) {
            boolean found = false;
            for (Order order : orderList) {
                if (order.getOrderId().equalsIgnoreCase(orderId)) {
                    found = true;
                    String newStatus = JOptionPane.showInputDialog(this, "Enter new Order Status");
                    order.setOrderStatus(newStatus);
                    JOptionPane.showMessageDialog(this, "Order updated successfully.");
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Order ID not found.");
            }
        }
    }

    // Method to delete an order
    private void deleteOrder() {
        String orderId = JOptionPane.showInputDialog(this, "Enter Order ID to delete");
        if (orderId != null && !orderId.isEmpty()) {
            Iterator<Order> iterator = orderList.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                Order order = iterator.next();
                if (order.getOrderId().equalsIgnoreCase(orderId)) {
                    iterator.remove();
                    found = true;
                    JOptionPane.showMessageDialog(this, "Order deleted successfully.");
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Order ID not found.");
            }
        }
    }

    // Method to display orders based on user choice
    private void displayOrders() {
        String[] options = {"All Orders", "Orders by Customer", "Orders by Product", "Orders by Status"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an option to display", "Display Orders",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                displayAllOrders();
                break;
            case 1:
                String customerName = JOptionPane.showInputDialog(this, "Enter Customer Name");
                if (customerName != null && !customerName.isEmpty()) {
                    displayOrdersByCustomer(customerName);
                }
                break;
            case 2:
                String productName = JOptionPane.showInputDialog(this, "Enter Product Name");
                if (productName != null && !productName.isEmpty()) {
                    displayOrdersByProduct(productName);
                }
                break;
            case 3:
                String orderStatus = JOptionPane.showInputDialog(this, "Enter Order Status");
                if (orderStatus != null && !orderStatus.isEmpty()) {
                    displayOrdersByStatus(orderStatus);
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid option.");
        }
    }

    // Method to display all orders
    private void displayAllOrders() {
        StringBuilder sb = new StringBuilder("All Orders:\n");
        for (Order order : orderList) {
            sb.append(order).append("\n\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "All Orders", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display orders by customer name
    private void displayOrdersByCustomer(String customerName) {
        StringBuilder sb = new StringBuilder("Orders for Customer: ").append(customerName).append("\n");
        for (Order order : orderList) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                sb.append(order).append("\n\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "Orders by Customer", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display orders by product name
    private void displayOrdersByProduct(String productName) {
        StringBuilder sb = new StringBuilder("Orders for Product: ").append(productName).append("\n");
        for (Order order : orderList) {
            if (order.getProductName().equalsIgnoreCase(productName)) {
                sb.append(order).append("\n\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "Orders by Product", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display orders by order status
    private void displayOrdersByStatus(String orderStatus) {
        StringBuilder sb = new StringBuilder("Orders with Status: ").append(orderStatus).append("\n");
        for (Order order : orderList) {
            if (order.getOrderStatus().equalsIgnoreCase(orderStatus)) {
                sb.append(order).append("\n\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "Orders by Status", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OrderManagementSystem().setVisible(true);
        });
    }
}
