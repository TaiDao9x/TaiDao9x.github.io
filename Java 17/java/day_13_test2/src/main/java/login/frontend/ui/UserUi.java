package login.frontend.ui;

import login.backend.controler.UserControler;
import login.backend.exception.NotFoundException;
import login.backend.model.User;
import login.backend.request.Request;

import java.util.Scanner;

public class UserUi {
    private final Scanner sc = new Scanner(System.in);
    private final UserControler userControler = new UserControler();

    public void runMenu() {

        boolean isQuit = false;
        int option;

        while (!isQuit) {
            showMenu();

            try {
                System.out.print("Hãy nhập lựa chọn của bạn: ");
                option = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại! ");
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.println("\n------------ ĐĂNG NHẬP ------------");
                    String email = getEmailForLogin();

                    System.out.print("Nhập vào password của bạn: ");
                    String password = sc.nextLine();

                    Request checkLogin = new Request(email, password);

                    try {
                        userControler.checkLogin(checkLogin);

                        if (email.equals("admin@gmail.com")) {

                            int optionAdminMenu;
                            boolean isQuitAdminMenu = false;

                            while (!isQuitAdminMenu) {

                                adminMenu();

                                try {
                                    System.out.print("Lựa chọn của bạn: ");
                                    optionAdminMenu = Integer.parseInt(sc.nextLine());

                                } catch (Exception e) {
                                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại! ");
                                    continue;
                                }

                                switch (optionAdminMenu) {
                                    case 1 -> {
                                        System.out.print("\n------------------ DANH SÁCH USER ------------------");
                                        formattingDisplay();
                                        for (Object user : userControler.findAll()) {
                                            System.out.println(user);
                                        }
                                    }
                                    case 2 -> {
                                        System.out.println("\n------------ TÌM TÀI KHOẢN ------------");
                                        System.out.println(findUserByEmail());
                                    }
                                    case 3 -> {
                                        System.out.println("\n------------ XÓA TÀI KHOẢN ------------");
                                        deleteUser();
                                    }
                                    case 4 -> {
                                        System.out.println("\n------------- HẸN GẶP LẠI! -------------");
                                        isQuitAdminMenu = true;
                                    }
                                    case 5 -> {
                                        System.out.println("\n------------- HẸN GẶP LẠI! -------------");
                                        isQuitAdminMenu = true;
                                        isQuit = true;
                                    }
                                    default -> System.out.println("Lựa chọn không tồn tại!");
                                }
                            }
                        } else {

                            System.out.println("\n-------------- ĐĂNG NHẬP THÀNH CÔNG --------------");
                            System.out.printf("Chào mừng %s, bạn có thể thực hiện các công việc sau: \n",
                                    userControler.findUserByEmail(email).getUserName());

                            int optionSubMenu;
                            boolean isQuitSubMenu = false;

                            while (!isQuitSubMenu) {

                                subMenu();

                                try {
                                    System.out.print("Lựa chọn của bạn: ");
                                    optionSubMenu = Integer.parseInt(sc.nextLine());

                                } catch (Exception e) {
                                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại! ");
                                    continue;
                                }

                                switch (optionSubMenu) {
                                    case 1 -> {
                                        System.out.println("\n------------ THAY ĐỔI USERNAME ------------");
                                        System.out.print("Nhập username mới của bạn: ");
                                        String newUsername = sc.nextLine();

                                        userControler.updateUsername(email, newUsername);
                                        System.out.println("Thay đổi username thành công!");
                                    }
                                    case 2 -> {
                                        System.out.println("\n------------ THAY ĐỔI EMAIL ------------");
                                        String newEmail = getEmail();

                                        userControler.updateEmail(email, newEmail);
                                        System.out.println("Thay đổi email thành công!");
                                    }
                                    case 3 -> {
                                        System.out.println("\n------------ THAY ĐỔI MẬT KHẨU ------------");
                                        changePassword(email);
                                    }
                                    case 4 -> {
                                        System.out.println("\n------------- HẸN GẶP LẠI! -------------");
                                        isQuitSubMenu = true;
                                    }
                                    case 5 -> {
                                        System.out.println("\n------------- HẸN GẶP LẠI! -------------");
                                        isQuitSubMenu = true;
                                        isQuit = true;
                                    }
                                    default -> System.out.println("Lựa chọn không tồn tại!");
                                }
                            }
                        }
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n------------ ĐĂNG KÝ TÀI KHOẢN ------------");
                    System.out.print("Nhập UserName: ");
                    String userName = sc.nextLine();

                    String email = getEmail();
                    String password = getPassword();

                    User newUser = new User(userName, email, password);
                    userControler.createUser(newUser);

                    System.out.println("\nĐăng ký tài khoản thành công!");
                    formattingDisplay();
                    System.out.println(userControler.findUserByEmail(email));
                }
                case 3 -> {
                    System.out.println("\n------------ QUÊN MẬT KHẨU ------------");
                    System.out.print("Nhập vào email của bạn: ");
                    String email = sc.nextLine();

                    if (userControler.checkEmail(email)) {
                        changePassword(email);

                    } else {
                        System.out.println("Tài khoản không tồn tại!");
                    }
                }
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại! ");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n---------------------------------");
        System.out.println("------------ WELCOME ------------\n");
        System.out.println("""
                1. Đăng nhập
                2. Đăng ký
                3. Quên mật khẩu
                """);
    }

    private void subMenu() {
        System.out.println("""
                \n1. Thay đổi username
                2. Thay đổi email (Không được trùng với email của user khác trong List)
                3. Thay đổi mật khẩu
                4. Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                5. Thoát chương trình
                """);

    }

    private void adminMenu() {
        System.out.println("\n------------ ADMIN ------------");
        System.out.println("""
                1. Hiển thị tất cả các tài khoản
                2. Tìm tài khoản 
                3. Xóa tài khoản
                4. Đăng xuất
                5. Thoát chương trình
                """);
    }

    private User findUserByEmail() {
        boolean checkEmail = false;
        User user = new User();
        while (!checkEmail) {
            System.out.print("Nhập email: ");
            String email = sc.nextLine();
            if (userControler.checkEmailValid(email)) {
                if (userControler.checkEmailExists(email)) {
                    user = userControler.findUserByEmail(email);
                    formattingDisplay();
                    checkEmail = true;
                } else {
                    System.out.println("Email không tồn tại!");
                }
            } else {
                System.out.println("Email không hợp lệ!");
            }
        }
        return user;
    }

    private void deleteUser() {
        User user = findUserByEmail();
        System.out.println(user);

        boolean isDelete = false;
        while (!isDelete) {
            System.out.printf("\nBạn có thực sự muốn xóa tài khoản %s không?\n", user.getEmail());
            System.out.println("1. Có \t\t 2. không");
            int option = 0;
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
            }
            if (option == 1) {
                userControler.deleteUser(user);
                System.out.println("Đã xóa tài khoản thành công!");
                isDelete = true;
            } else if (option == 2) {
                isDelete = true;
            } else {
                System.out.println("Lựa chọn không có. Hãy chọn lại!");
            }
        }
    }

    private String getEmailForLogin() {
        boolean checkEmail = false;
        String email = "";
        while (!checkEmail) {
            System.out.print("\nNhập email của bạn: ");
            email = sc.nextLine();
            if (userControler.checkEmailValid(email)) {
                checkEmail = true;
            } else {
                System.out.println("Email không hợp lệ!");
            }
        }
        return email;
    }

    private String getEmail() {

        boolean checkEmail = false;
        String newEmail = "";
        while (!checkEmail) {
            System.out.print("Nhập email: ");
            newEmail = sc.nextLine();
            if (userControler.checkEmailValid(newEmail)) {
                if (!userControler.checkEmailExists(newEmail)) {
                    checkEmail = true;
                } else {
                    System.out.println("Email đã tồn tại!");
                }
            } else {
                System.out.println("Email không hợp lệ!");
            }
        }
        return newEmail;
    }

    private void changePassword(String email) {
        boolean checkPassword = false;
        while (!checkPassword) {
            System.out.print("\nNhập mật khẩu mới cho tài khoản của bạn: ");
            String newPassword = sc.nextLine();

            if (userControler.checkPassword(newPassword)) {
                Request updatePassword = new Request(email, newPassword);
                userControler.updatePassword(updatePassword);
                System.out.println("Đổi mật khẩu thành công!");
                checkPassword = true;
            } else {
                System.out.println("Mật khẩu phải có ít nhất từ 7 đến 15 ký tự");
            }
        }
    }

    private String getPassword() {
        String password = "";
        boolean checkPassword = false;
        while (!checkPassword) {
            System.out.print("Nhập mật khẩu: ");
            password = sc.nextLine();

            if (userControler.checkPassword(password)) {
                checkPassword = true;
            } else {
                System.out.println("Mật khẩu phải có ít nhất từ 7 đến 15 ký tự");
            }
        }
        return password;
    }

    private void formattingDisplay() {
        System.out.printf("\n%-15s %-25s %-15s\n", "UserName", "   Email", "Password");
        System.out.println("----------------------------------------------------");
    }
}
