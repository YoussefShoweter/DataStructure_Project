package sample;

public class Table {
    private int Sorted ;
    private  int Unsorted;
    private int Index;

    public Table(int sorted, int unsorted, int index) {

        Sorted = sorted;
        Unsorted = unsorted;
        Index = index;
    }

    public int getSorted() {
        return Sorted;
    }

    public void setSorted(int sorted) {
        Sorted = sorted;
    }

    public int getUnsorted() {
        return Unsorted;
    }

    public void setUnsorted(int unsorted) {
        Unsorted = unsorted;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
}
