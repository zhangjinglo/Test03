package WestTwoTest03;

public class StudentTest {
    private String sno;//学号（这里统一用福大的固定9位学号格式，由于有0开头所以用String保存
    private String sname;
    private int sclass;//classNo

    public StudentTest(String sno, String sname, int sclass) {
        this.sno = sno;
        this.sname = sname;
        this.sclass = sclass;
    }

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public int getSclass() {
        return sclass;
    }
}
