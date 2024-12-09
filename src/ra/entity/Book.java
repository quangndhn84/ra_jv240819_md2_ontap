package ra.entity;

import ra.business.BookBusiness;
import ra.business.BookTypeBusiness;

import java.util.List;
import java.util.Scanner;

public class Book implements IBookManagement {
    private int bookId;
    private String bookName;
    private String title;
    private String author;
    private int totalPages;
    private String content;
    private String publisher;
    private double price;
    private int typeId;
    private boolean isDeleted;

    public Book() {
    }

    public Book(int bookId, String bookName, String title, String author, int totalPages, String content, String publisher, double price, int typeId, boolean isDeleted) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.content = content;
        this.publisher = publisher;
        this.price = price;
        this.typeId = typeId;
        this.isDeleted = isDeleted;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.bookName = inputBookName(scanner);
        this.title = inputTitle(scanner);
        this.author = inputAuthor(scanner);
        this.totalPages = inputTotalPages(scanner);
        this.content = inputContent(scanner);
        this.publisher = inputPublisher(scanner);
        this.price = inputPrice(scanner);
        this.typeId = inputTypeId(scanner);
    }

    public String inputBookName(Scanner scanner) {
        System.out.println("Nhập vào tên sách:");
        do {
            String bookName = scanner.nextLine();
            if (bookName != null && bookName.trim().length() != 0) {
                Book bookCheck = BookBusiness.findBookByName(bookName);
                if (bookCheck != null) {
                    System.err.println("Tên sách đã tồn tại, vui lòng nhập lại");
                } else {
                    return bookName;
                }
            } else {
                System.err.println("Vui lòng nhập lại tên sách:");
            }

        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sách:");
        do {
            String title = scanner.nextLine();
            if (title != null && title.trim().length() != 0) {
                return title;
            }
            System.err.println("Vui lòng nhập lại tiêu đề sách");
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập vào tác giả sách:");
        do {
            String author = scanner.nextLine();
            if (author != null && author.trim().length() != 0) {
                return author;
            }
            System.err.println("Vui lòng nhập lại tác giả sách");
        } while (true);
    }

    public int inputTotalPages(Scanner scanner) {
        System.out.println("Nhập vào tổng số lượng trang sách:");
        do {
            try {
                int totalPages = Integer.parseInt(scanner.nextLine());
                if (totalPages > 0) {
                    return totalPages;
                }
                System.err.println("Tổng số trang sách phải lớn hơn 0, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Tổng số trang sách là số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputContent(Scanner scanner) {
        System.out.println("Nhập vào nội dung sách:");
        do {
            String content = scanner.nextLine();
            if (content != null && content.trim().length() != 0) {
                return content;
            }
            System.err.println("Vui lòng nhập lại nội dung sách");
        } while (true);
    }

    public String inputPublisher(Scanner scanner) {
        System.out.println("Nhập vào nhà xuất bản sách:");
        do {
            String publisher = scanner.nextLine();
            if (publisher != null && publisher.trim().length() != 0) {
                return publisher;
            }
            System.err.println("Vui lòng nhập lại nhà xuất bản sách");
        } while (true);
    }

    public double inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sách:");
        do {
            String price = scanner.nextLine();
            if (price != null && price.trim().length() > 0) {
                try {
                    double priceValue = Double.parseDouble(price);
                    if (priceValue > 0) {
                        return priceValue;
                    }
                    System.err.println("Giá sách phải lớn hơn 0, vui lòng nhập lại");
                } catch (Exception ex) {
                    System.err.println("Giá sách phải là số thực, vui lòng nhập lại");
                }
            } else {
                System.err.println("Vui lòng nhập lại giá sách");
            }
        } while (true);
    }

    public int inputTypeId(Scanner scanner) {
        List<BookType> listBookType = BookTypeBusiness.findAll();
        listBookType.forEach(bookType -> {
            System.out.printf("%d. %s\n", bookType.getTypeId(), bookType.getTypeName());
        });
        System.out.print("Lựa chọn loại sách:");
        int typeId = Integer.parseInt(scanner.nextLine());
        return typeId;
    }


    @Override
    public void displayData() {

    }
}
