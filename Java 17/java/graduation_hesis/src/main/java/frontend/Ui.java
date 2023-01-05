package frontend;

import backend.User.model.Address;
import backend.User.model.Book;
import backend.User.controler.UserControler;
import backend.User.model.User;
import backend.User.request.UserRequest;
import backend.User.ultils.UserFileUltils;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    UserControler userControler = new UserControler();
    ClientUi clientUi = new ClientUi();

    public void runMenu() {

        boolean isQuit = false;

        while (!isQuit) {
            showMenuLogin();

            int option=clientUi.getOption();

            switch (option) {
                case 1 -> {
                    clientUi.showBook();
                }
                case 2 -> {
                    clientUi.findBook();
                }
                case 3 -> {
                    System.out.println("\n----------- ĐĂNG NHẬP -----------");
                    User clientLogin = getUserAfterCheckPass(getEmailToLogin());
                    clientUi.clientLoginSuccess(clientLogin);
                }
                case 4 -> {
                    System.out.println("\n----------- ĐĂNG KÝ TÀI KHOẢN -----------");
                    System.out.print("Nhập username: ");
                    String userName = sc.nextLine();

                    String email = getEmailToRegister();
                    String password = getPasswordToRegister();
                    String phone = getPhoneToRegister();
                    Address address = getAddressToRegister();

                    User newUser = new User(userName, email, password, phone, address);

                    userControler.createNewUser(newUser);
                    System.out.println("Bạn đã tạo tài khoản thành công! Xin chúc mừng. ");
                    UserFileUltils.printUser(newUser);
                }
                case 5 -> {
                    System.out.println("\n----------- QUÊN MẬT KHẨU -----------");

                    String email = getEmailToLogin();
                    System.out.println("Tài khoản email tồn tại, hãy nhập mật khẩu mới cho tài khoản của bạn!");
                    String password = getPasswordToRegister();

                    UserRequest changePasswordRequest = new UserRequest(email, password);
                    userControler.changePassword(changePasswordRequest);
                    System.out.println("Thay đổi password thành công!");
                }
                case 0 -> isQuit = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
    }

    public void showMenuLogin() {
        System.out.println("\n-----------------------------------------");
        System.out.println("------- WELCOME TO DT'S BOOKSTORE -------");
        System.out.println("""
                1. Xem sản phẩm
                2. Tìm kiếm sản phẩm
                3. Đăng nhập
                4. Đăng ký
                5. Quên mật khẩu
                0. Thoát ứng dụng
                """);
    }

    // KHUNG NGOÀI (lv1)
    // LOGIN
    // Kiểm tra, check email được nhập vào nếu đúng -> lấy ra email sang bước kiểm tra password
    // Sử dụng để lấy lại mật khẩu
    public String getEmailToLogin() {
        boolean checkEmail = false;
        String email = "";
        while (!checkEmail) {
            System.out.print("Nhập email của bạn: ");
            email = sc.nextLine();
            if (userControler.checkEmailValid(email)) {
                if (userControler.checkEmailExist(email)) {
                    checkEmail = true;
                } else {
                    System.out.println("Email không tồn tại!");
                }
            } else {
                System.out.println("Email không hợp lệ!");
            }
        }
        return email;
    }

    public User getUserByEmail(String email) {
        return userControler.getUserByEmail(email);
    }

    // Kiểm tra password theo email bên trên, nếu đúng -> lấy ra user
    public User getUserAfterCheckPass(String email) {
        User user = new User();

        boolean checkPassword = false;

        while (!checkPassword) {
            System.out.print("Nhập password: ");
            String password = sc.nextLine();
            if (password.equals(getUserByEmail(email).getPassword())) {
                user = getUserByEmail(email);
                checkPassword = true;
            } else {
                System.out.println("Password của bạn không chính xác!");
            }
        }
        return user;
    }

    // ĐĂNG KÝ TÀI KHOẢN MỚI
    // Kiểm tra tính hợp lệ của email cho phần đăng ký
    public String getEmailToRegister() {
        boolean checkEmail = false;
        String email = "";
        while (!checkEmail) {
            System.out.print("Nhập email của bạn: ");
            email = sc.nextLine();
            if (userControler.checkEmailValid(email)) {
                if (userControler.checkEmailExist(email)) {
                    System.out.println("Email đã tồn tại!");
                } else {
                    checkEmail = true;
                }
            } else {
                System.out.println("Email không hợp lệ!");
            }
        }
        return email;
    }

    // Sau khi kiemr tra email chuyển qua kiểm tra password cho phần đăng ký
    // Sử dụng để lấy lại mật khẩu

    public String getPasswordToRegister() {
        boolean checkPassword = false;
        String password = "";
        while (!checkPassword) {
            System.out.print("Nhập password: ");
            password = sc.nextLine();
            if (userControler.checkPasswordValid(password)) {
                checkPassword = true;
            } else {
                System.out.println("Email không hợp lệ!");
                System.out.println("Password phải có ít nhất 6 ký tự gồm chữ hoa, chữ thường, số và 1 ký tự đặc biệt!");
            }
        }
        return password;
    }

    // Tiếp đó là kiểm tra phone cho phần đăng ký
    public String getPhoneToRegister() {
        boolean checkPhoned = false;
        String phone = "";
        while (!checkPhoned) {
            System.out.print("Nhập số điện thoại của bạn: ");
            phone = sc.nextLine();
            if (userControler.checkPhoneValid(phone)) {
                checkPhoned = true;
            } else {
                System.out.println("Số điện không hợp lệ!");
            }
        }
        return phone;
    }

    public Address getAddressToRegister() {
        System.out.println("Địa chỉ của bạn: ");
        System.out.print("Thành phố: ");
        String city = sc.nextLine();

        System.out.print("Quận: ");
        String district = sc.nextLine();

        System.out.print("Đường: ");
        String street = sc.nextLine();

        System.out.print("Địa chỉ nhận hàng: ");
        String addressDetail = sc.nextLine();

        return new Address(city, district, street, addressDetail);
    }
}