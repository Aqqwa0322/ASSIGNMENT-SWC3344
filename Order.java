/* 
Name : MUHAMMAD NUREEL AQQWA BIN HISHAM
ID   : AM2307013932
Type : Assignment
*/
package swcAssignment;
// Class representing an Order
public class Order {
    // Instance variables
    private String orderId;   
    private String customerName;
    private String customerAddress;
    private String productName;
    private int quantity;
    private double pricePerUnit;
    private double totalPrice;
    private String orderStatus;

    // Constructor to initialize an Order object
    public Order(String orderId, String customerName, String customerAddress, String productName, int quantity, double pricePerUnit, String orderStatus) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = quantity * pricePerUnit; // Calculate total price based on quantity and price per unit
        this.orderStatus = orderStatus;
    }

    // Getters and setters for the instance variables

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // Setter for quantity, recalculates total price
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = quantity * this.pricePerUnit; // Recalculate total price
    }

    // Setter for price per unit, recalculates total price
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = this.quantity * pricePerUnit; // Recalculate total price
    }

    // Setter for order status
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // toString method to provide a string representation of the Order object
    @Override
    public String toString() {
        return "Order ID: " + orderId + "\nCustomer Name: " + customerName + "\nCustomer Address: " + customerAddress +
               "\nProduct Name: " + productName + "\nQuantity: " + quantity + "\nPrice per Unit: " + pricePerUnit +
               "\nTotal Price: " + totalPrice + "\nOrder Status: " + orderStatus;
    }
}
