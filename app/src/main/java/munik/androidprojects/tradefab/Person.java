package munik.androidprojects.tradefab;

public class Person {
    private String mItem;
    private String mQuantity;
    private String mColor;
    private String mCost;

    public Person(String mItem, String mQuantity, String mColor, String mCost) {
        this.mItem = mItem;
        this.mQuantity = mQuantity;
        this.mColor = mColor;
        this.mCost = mCost;
    }

    public String getmItem() {
        return mItem;
    }

    public void setmItem(String mItem) {
        this.mItem = mItem;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }
}
