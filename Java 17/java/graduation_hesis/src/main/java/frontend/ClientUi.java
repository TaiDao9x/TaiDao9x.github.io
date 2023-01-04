package frontend;

import backend.User.controler.BookControler;
import backend.User.controler.UserControler;
import backend.User.model.Book;
import backend.User.model.User;
import backend.User.request.UserRequest;
import backend.User.ultils.BookFileUltis;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientUi {
    Scanner sc = new Scanner(System.in);
    BookControler bookControler = new BookControler();

    // KHUNG TRONG CLIENT (lv2)
    // Login thành công (client)

    public void subClientMenu() {
        System.out.println("\nBạn có thể thực hiện:");
        System.out.println("""
                1. Xem danh sách sản phẩm 
                2. Tìm kiếm sản phẩm
                3. Quản lý giỏ hàng
                4. Lịch sử mua hàng
                5. Sửa thông tin cá nhân
                9. Đăng xuất                                
                """);
        System.out.print("Bạn chọn: ");
    }

    // Menu khi login vào tài khoản client (màn hình sau khi đăng nhập thành công)
    public void clientLoginSuccess(User clientLogin) {
        System.out.println("\n----------- ĐĂNG NHẬP THÀNH CÔNG -----------");
        System.out.printf("Chào mừng \"%s\"... \n", clientLogin.getUserName().toUpperCase());

        int option;
        boolean isQuitLogin = false;
        while (!isQuitLogin) {
            try {
                subClientMenu();
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
                continue;
            }

            switch (option) {
                case 1 -> {
                    showBook();

                }
                case 2 -> {
                    findBook();
                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 9 -> isQuitLogin = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
    }


    // TẦNG SHOW DỮ LIỆU (lv3, lv4)
    // 1. Xem danh sách sản phẩm

    public void showBook() {
        int option;
        boolean backToMenu = false;

        while (!backToMenu) {
            System.out.println("""
                    1. Theo thể loại
                    2. Theo nhà xuất bản
                    9. Quay lại
                    """);
            System.out.print("Bạn chọn: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
                continue;
            }

            switch (option) {
                case 1 -> {
                    printBook(showBookByCategory());
                    backToMenu = true;
                }
                case 2 -> {
                    printBook(showBookByPulisherCompany());
                    backToMenu = true;
                }
                case 9 -> backToMenu = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
    }

    // 1.1. Xem theo thể loại
    public void listCategory() {

        System.out.println("""
                1. Văn học
                2. Kinh tế
                3. Tâm lý - Kỹ năng sống
                4. Nuôi dạy con
                5. Thiếu nhi
                9. Quay lại menu
                """);
        System.out.print("Bạn chọn: ");
    }

    // Trả về list sách theo thể loại khi đã lấy được dữ liệu loại sách muốn tìm
    public ArrayList<Book> showBookByCategory() {
        int option;
        boolean backToMenu = false;
        String category = "";

        while (!backToMenu) {
            try {
                listCategory();
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
                continue;
            }

            switch (option) {
                case 1 -> {
                    category = "Văn học";
                    backToMenu = true;
                }
                case 2 -> {
                    category = "Kinh tế";
                    backToMenu = true;
                }
                case 3 -> {
                    category = "Tâm lý - Kỹ năng sống";
                    backToMenu = true;
                }
                case 4 -> {
                    category = "Nuôi dạy con";
                    backToMenu = true;
                }
                case 5 -> {
                    category = "Thiếu nhi";
                    backToMenu = true;
                }
                case 9 -> backToMenu = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
        return bookControler.showBookByCategory(category);
    }

    // 1.2. Xem danh sách sản phẩm theo nhà xuất bản
    public void listPushlisherCompany() {
        System.out.println("""
                1. NXB trẻ
                2. NXB Kim Đồng
                3. NXB Lao Động
                4. NXB Nhã Nam
                9. Quay lại
                """);
        System.out.print("Bạn chọn: ");
    }

    public ArrayList<Book> showBookByPulisherCompany() {
        int option;
        boolean backToMenu = false;
        String pulisherCompany = "";

        while (!backToMenu) {
            try {
                listPushlisherCompany();
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
                continue;
            }

            switch (option) {
                case 1 -> {
                    pulisherCompany = "NXB trẻ";
                    backToMenu = true;
                }
                case 2 -> {
                    pulisherCompany = "NXB Kim Đồng";
                    backToMenu = true;
                }
                case 3 -> {
                    pulisherCompany = "NXB Lao Động";
                    backToMenu = true;
                }
                case 4 -> {
                    pulisherCompany = "NXB Nhã Nam";
                    backToMenu = true;
                }
                case 9 -> backToMenu = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
        return bookControler.showBookByPulisherCompany(pulisherCompany);
    }


    // 2. Tìm kiếm sản phẩm
    public void findBook() {
        int option;
        boolean backToMenu = false;

        while (!backToMenu) {
            System.out.println("""
                    1. Tìm theo tên sách
                    2. Tìm theo tên tác giả
                    9. Quay lại
                    """);
            System.out.print("Bạn chọn: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại!");
                continue;
            }

            switch (option) {
                case 1 -> {
                    ArrayList<Book> books = findBookByName();
                    if (books.size() != 0) {
                        printBook(books);
                    } else {
                        System.out.println("Không có sách bạn muốn tìm!");
                    }
                    backToMenu = true;
                }
                case 2 -> {
                    ArrayList<Book> books = findBookByAuthor();
                    if (books.size() != 0) {
                        printBook(books);
                    } else {
                        System.out.println("Không có tác giả bạn muốn tìm!");
                    }
                    backToMenu = true;
                }
                case 9 -> backToMenu = true;
                default -> System.out.println("Lựa chọn không tồn tại. Hãy chọn lại!");
            }
        }
    }

    // 2.1 Tìm theo tên
    public ArrayList<Book> findBookByName() {
        System.out.print("Nhập vào tên sách bạn muốn tìm: ");
        String title = sc.nextLine();
        return bookControler.findBookByName(title);
    }

    public ArrayList<Book> findBookByAuthor() {
        System.out.print("Nhập vào tên tác giả: ");
        String author = sc.nextLine();
        return bookControler.findBookByAuthor(author);
    }


//    5. Sửa thông tin cá nhân

    // Method phụ
    public void printBook(ArrayList<Book> books) {
        BookFileUltis.printBook(books);
    }

    // Đã làm xong chức năng tìm kiếm và show sản phẩm. Tiếp tục chuwcs năng mua hàng và sửa thông tin cá nhân

}
