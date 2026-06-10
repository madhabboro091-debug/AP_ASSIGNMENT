import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

// ================= PRODUCT MODULE =================

class Product {

        private int productId;
        private String productName;
        private double price;

        public Product(int productId,
                        String productName,
                        double price) {

                this.productId = productId;
                this.productName = productName;
                this.price = price;
        }

        public int getProductId() {
                return productId;
        }

        public String getProductName() {
                return productName;
        }

        public double getPrice() {
                return price;
        }
}

// ================= PAYMENT MODULE =================

interface PaymentMethod {
        void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {

        @Override
        public void pay(double amount) {

                System.out.println(
                                "Paid ₹" + amount + " using Credit Card.");
        }
}

class UPIPayment implements PaymentMethod {

        @Override
        public void pay(double amount) {

                System.out.println(
                                "Paid ₹" + amount + " using UPI.");
        }
}

class WalletPayment implements PaymentMethod {

        @Override
        public void pay(double amount) {

                System.out.println(
                                "Paid ₹" + amount + " using Wallet.");
        }
}

// ================= NOTIFICATION MODULE =================

interface NotificationService {
        void sendNotification(String message);
}

// Email Notification
class EmailNotification implements NotificationService {

        private String email;

        public EmailNotification(String email) {
                this.email = email;
        }

        @Override
        public void sendNotification(String message) {

                System.out.println(
                                "\nEmail Sent To: " + email);

                System.out.println(
                                "Message: " + message);
        }
}

// SMS Notification
class SMSNotification implements NotificationService {

        private String phoneNumber;

        public SMSNotification(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        @Override
        public void sendNotification(String message) {

                System.out.println(
                                "\nSMS Sent To: " + phoneNumber);

                System.out.println(
                                "Message: " + message);
        }
}

// Push Notification
class PushNotification implements NotificationService {

        @Override
        public void sendNotification(String message) {

                System.out.println(
                                "\nPush Notification Sent");

                System.out.println(
                                "Message: " + message);
        }
}

// ================= ORDER MODULE =================

abstract class Order {

        protected int orderId;
        protected Product product;
        protected double amount;

        public Order(int orderId, Product product) {

                this.orderId = orderId;
                this.product = product;
                this.amount = product.getPrice();
        }

        public int getOrderId() {
                return orderId;
        }

        public double getAmount() {
                return amount;
        }

        public Product getProduct() {
                return product;
        }

        public abstract void processOrder();
}

// Regular Order
class RegularOrder extends Order {

        public RegularOrder(int orderId, Product product) {
                super(orderId, product);
        }

        @Override
        public void processOrder() {

                System.out.println(
                                "\nProcessing Regular Order...");
        }
}

// Discounted Order
class DiscountedOrder extends Order {

        public DiscountedOrder(int orderId,
                        Product product) {

                super(orderId, product);
        }

        @Override
        public void processOrder() {

                amount = amount * 0.9;

                System.out.println(
                                "\nProcessing Discounted Order...");

                System.out.println(
                                "Discount Applied Successfully.");

                System.out.println(
                                "Final Amount: ₹" + amount);
        }
}

// Priority Order
class PriorityOrder extends Order {

        public PriorityOrder(int orderId,
                        Product product) {

                super(orderId, product);
        }

        @Override
        public void processOrder() {

                System.out.println(
                                "\nProcessing Priority Order "
                                                + "with fast delivery...");
        }
}

// ================= STORAGE MODULE =================

interface OrderStorage {
        void saveOrder(Order order);
}

// Database Storage
class DatabaseStorage implements OrderStorage {

        @Override
        public void saveOrder(Order order) {

                System.out.println(
                                "\nOrder saved to Database successfully.");
        }
}

// File Storage
class FileStorage implements OrderStorage {

        @Override
        public void saveOrder(Order order) {

                try {

                        FileWriter writer = new FileWriter("orders.txt", true);

                        writer.write(
                                        "Order ID: " + order.getOrderId()
                                                        + ", Product: "
                                                        + order.getProduct().getProductName()
                                                        + ", Amount: ₹"
                                                        + order.getAmount()
                                                        + "\n");

                        writer.close();

                        System.out.println(
                                        "\nOrder saved to File successfully.");

                } catch (IOException e) {

                        System.out.println(
                                        "\nError saving order to file.");
                }
        }
}

// ================= ORDER SERVICE =================

class OrderService {

        private PaymentMethod paymentMethod;
        private NotificationService notificationService;
        private OrderStorage orderStorage;

        // Dependency Injection
        public OrderService(PaymentMethod paymentMethod,
                        NotificationService notificationService,
                        OrderStorage orderStorage) {

                this.paymentMethod = paymentMethod;
                this.notificationService = notificationService;
                this.orderStorage = orderStorage;
        }

        public void placeOrder(Order order) {

                // Process Order
                order.processOrder();

                // Process Payment
                paymentMethod.pay(order.getAmount());

                // Send Notification
                notificationService.sendNotification(
                                "Order #"
                                                + order.getOrderId()
                                                + " for "
                                                + order.getProduct().getProductName()
                                                + " placed successfully.");

                // Save Order
                orderStorage.saveOrder(order);

                System.out.println(
                                "\nOrder Completed Successfully.");
        }
}

// ================= MAIN CLASS =================

public class EcommerceSystem {

        public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);
                Random random = new Random();

                System.out.println(
                                "===== E-COMMERCE ORDER SYSTEM =====");

                // ================= GENERATE ORDER ID =================

                int orderId = 1000 + random.nextInt(9000);

                System.out.println(
                                "\nGenerated Order ID: "
                                                + orderId);

                // ================= PRODUCT LIST =================

                Product p1 = new Product(1,
                                "Laptop",
                                50000);

                Product p2 = new Product(2,
                                "Smartphone",
                                20000);

                Product p3 = new Product(3,
                                "Headphones",
                                3000);

                // ================= PRODUCT SELECTION =================

                System.out.println("\nAvailable Products:");

                System.out.println(
                                "1. Laptop -> ₹50000");

                System.out.println(
                                "2. Smartphone -> ₹20000");

                System.out.println(
                                "3. Headphones -> ₹3000");

                System.out.print(
                                "\nSelect Product: ");

                int productChoice = sc.nextInt();

                Product selectedProduct;

                switch (productChoice) {

                        case 1:
                                selectedProduct = p1;
                                break;

                        case 2:
                                selectedProduct = p2;
                                break;

                        case 3:
                                selectedProduct = p3;
                                break;

                        default:
                                System.out.println(
                                                "\nInvalid Product Selection.");
                                sc.close();
                                return;
                }

                // ================= ORDER TYPE =================

                System.out.println(
                                "\nSelect Order Type:");

                System.out.println(
                                "1. Regular Order");

                System.out.println(
                                "2. Discounted Order");

                System.out.println(
                                "3. Priority Order");

                int orderChoice = sc.nextInt();

                Order order;

                switch (orderChoice) {

                        case 1:
                                order = new RegularOrder(
                                                orderId,
                                                selectedProduct);
                                break;

                        case 2:
                                order = new DiscountedOrder(
                                                orderId,
                                                selectedProduct);
                                break;

                        case 3:
                                order = new PriorityOrder(
                                                orderId,
                                                selectedProduct);
                                break;

                        default:
                                System.out.println(
                                                "\nInvalid Order Type.");
                                sc.close();
                                return;
                }

                // ================= PAYMENT METHOD =================

                System.out.println(
                                "\nSelect Payment Method:");

                System.out.println(
                                "1. Credit Card");

                System.out.println(
                                "2. UPI");

                System.out.println(
                                "3. Wallet");

                int paymentChoice = sc.nextInt();

                PaymentMethod paymentMethod;

                switch (paymentChoice) {

                        case 1:
                                paymentMethod = new CreditCardPayment();
                                break;

                        case 2:
                                paymentMethod = new UPIPayment();
                                break;

                        case 3:
                                paymentMethod = new WalletPayment();
                                break;

                        default:
                                System.out.println(
                                                "\nInvalid Payment Method.");
                                sc.close();
                                return;
                }

                // ================= NOTIFICATION METHOD =================

                System.out.println(
                                "\nSelect Notification Method:");

                System.out.println(
                                "1. Email");

                System.out.println(
                                "2. SMS");

                System.out.println(
                                "3. Push Notification");

                int notifyChoice = sc.nextInt();

                sc.nextLine();

                NotificationService notificationService;

                switch (notifyChoice) {

                        case 1:

                                System.out.print(
                                                "\nEnter Email Address: ");

                                String email = sc.nextLine();

                                notificationService = new EmailNotification(email);

                                break;

                        case 2:

                                System.out.print(
                                                "\nEnter Phone Number: ");

                                String phone = sc.nextLine();

                                notificationService = new SMSNotification(phone);

                                break;

                        case 3:

                                notificationService = new PushNotification();

                                break;

                        default:

                                System.out.println(
                                                "\nInvalid Notification Method.");

                                sc.close();
                                return;
                }

                // ================= STORAGE METHOD =================

                System.out.println(
                                "\nSelect Storage Method:");

                System.out.println(
                                "1. Database");

                System.out.println(
                                "2. File");

                int storageChoice = sc.nextInt();

                OrderStorage orderStorage;

                switch (storageChoice) {

                        case 1:
                                orderStorage = new DatabaseStorage();
                                break;

                        case 2:
                                orderStorage = new FileStorage();
                                break;

                        default:

                                System.out.println(
                                                "\nInvalid Storage Method.");

                                sc.close();
                                return;
                }

                // ================= ORDER SERVICE =================

                OrderService orderService = new OrderService(
                                paymentMethod,
                                notificationService,
                                orderStorage);

                // ================= PLACE ORDER =================

                System.out.println(
                                "\n===== ORDER PROCESSING =====");

                orderService.placeOrder(order);

                sc.close();
        }
}