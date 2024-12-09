package ra.entity;

import ra.business.BookTypeBusiness;

import java.util.Scanner;

public class BookType implements IBookManagement {
    private int typeId;
    private String typeName;
    private String description;
    private boolean isDeleted;

    public BookType() {
    }

    public BookType(int typeId, String typeName, String description, boolean isDeleted) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.typeName = inputBookTypeName(scanner);
        System.out.println("Nhập vào mô tả loại sách:");
        this.description = scanner.nextLine();
    }

    public String inputBookTypeName(Scanner scanner) {
        System.out.println("Nhập vào tên loại sách:");
        do {
            String typeName = scanner.nextLine();
            boolean isExist = BookTypeBusiness.isExistBookTypeName(0, typeName);
            if (isExist) {
                System.err.println("Tên loại sách đã tồn tại, vui lòng nhập lại");
            } else {
                return typeName;
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Mã loại sách: %d - Tên loại sách: %s - Mô tả: %s - Trạng thái: %s\n",
                this.typeId, this.typeName, this.description, this.isDeleted ? "Đã xóa" : "Hoạt động");
    }
}
