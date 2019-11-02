import java.util.Date;

public class FullPurchaseInfo {
    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private int coursePrice;
    private Date subscriptionDate;

    public FullPurchaseInfo(){
    }

    public FullPurchaseInfo(int studentId, String studentName, int courseId, String courseName, int coursePrice, Date subscriptionDate){
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.subscriptionDate = subscriptionDate;
    }

    public void print(){
        System.out.println("Student name: " + studentName + " (id " + studentId + ")" +
                "\nbought: " + courseName + " (id " + courseId + ")" +
                "\nat " + subscriptionDate);
    }

}

