import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course1 = session.get(Course.class, 1);

        System.out.println("Студенты, обучающиеся на курсе " + course1.getName() + ":");
        for (Student student : course1.getStudents()){
            System.out.println(student.getName());
        }

        Student student = session.get(Student.class, 5);
        System.out.println("Курсы, на которые подписан студент " + student.getName() + ":");
        for (Course course : student.getCourses()){
            System.out.println(course.getName());
        }

        Teacher teacher = session.get(Teacher.class, 10);
        System.out.println("Преподаватель " + teacher.getName() + " ведет курсы:");
        for (Course course : teacher.getCourses()){
            System.out.println(course.getName());
        }

        sessionFactory.close();
    }
}
