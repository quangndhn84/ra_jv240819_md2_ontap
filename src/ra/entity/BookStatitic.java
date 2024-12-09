package ra.entity;

public class BookStatitic {
    private int typeId;
    private String typeName;
    private int cntBook;

    public BookStatitic() {
    }

    public BookStatitic(int typeId, String typeName, int cntBook) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.cntBook = cntBook;
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

    public int getCntBook() {
        return cntBook;
    }

    public void setCntBook(int cntBook) {
        this.cntBook = cntBook;
    }
}
