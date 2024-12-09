package ra.presentation;

import ra.business.BookBusiness;
import ra.business.BookTypeBusiness;
import ra.entity.Book;
import ra.entity.BookStatitic;
import ra.entity.BookType;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************BOOK MANAGEMENT***************");
            System.out.println("1. Quản lý loại sách");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayMenuBookType(scanner);
                    break;
                case 2:
                    displayBookMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayMenuBookType(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************BOOK TYPE MENU***************");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Tạo mới loại sách");
            System.out.println("3. Câp nhật thông tin loại sách");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thống kê số lượng sách theo loại sách");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListBookType();
                    break;
                case 2:
                    createBookType(scanner);
                    break;
                case 3:
                    updateBookType(scanner);
                    break;
                case 4:
                    deleteBookType(scanner);
                    break;
                case 5:
                    statiticBookByBookType();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public static void displayListBookType() {
        List<BookType> listBookTypes = BookTypeBusiness.findAll();
        listBookTypes.forEach(BookType::displayData);
    }

    public static void createBookType(Scanner scanner) {
        BookType bookType = new BookType();
        bookType.inputData(scanner);
        bookType.setDeleted(false);
        boolean result = BookTypeBusiness.createBookType(bookType);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateBookType(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách:");
        int typeId = Integer.parseInt(scanner.nextLine());
        BookType bookTypeUpdate = BookTypeBusiness.findById(typeId);
        if (bookTypeUpdate != null) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên loại sách");
                System.out.println("2. Cập nhật mô tả loại sách");
                System.out.println("3. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sách cần cập nhật");
                        boolean isExitTypeName = true;
                        do {
                            String typeName = scanner.nextLine();
                            boolean isExist = BookTypeBusiness.isExistBookTypeName(typeId, typeName);
                            if (isExist) {
                                System.err.println("Tên loại sách đã tồn tại, vui lòng nhập lại");
                            } else {
                                bookTypeUpdate.setTypeName(typeName);
                                isExitTypeName = false;
                            }
                        } while (isExitTypeName);
                        break;
                    case 2:
                        System.out.println("Nhập vào mô tả loại sách:");
                        bookTypeUpdate.setDescription(scanner.nextLine());
                        break;
                    case 3:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-3");
                }
            } while (isExit);
            boolean result = BookTypeBusiness.updateBookType(bookTypeUpdate);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Mã loại sách không tồn tại");
        }
    }

    public static void deleteBookType(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách cần xóa:");
        int typeId = Integer.parseInt(scanner.nextLine());
        BookType bookTypeDelete = BookTypeBusiness.findById(typeId);
        if (bookTypeDelete != null) {
            boolean result = BookTypeBusiness.deleteBookType(typeId);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã loại sách không tồn tại");
        }
    }

    public static void statiticBookByBookType() {
        List<BookStatitic> listBookStatitic = BookTypeBusiness.statiticBookByType();
        listBookStatitic.forEach(bookStatitic -> {
            System.out.printf("%d - %s: %d sách\n",
                    bookStatitic.getTypeId(), bookStatitic.getTypeName(), bookStatitic.getCntBook());
        });
    }

    public static void displayBookMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("***************BOOK MENU*************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Tạo mới sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Hiển thị danh sách sách theo giá giảm dần");
            System.out.println("6. Tìm kiếm sách theo tên hoặc nội dung");
            System.out.println("7. Thống kê số lượng sách theo nhóm");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    createBook(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (isExit);
    }

    public static void createBook(Scanner scanner) {
        System.out.println("Nhập vào thông tin sách:");
        Book book = new Book();
        book.inputData(scanner);
        boolean result = BookBusiness.createBook(book);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }
}
