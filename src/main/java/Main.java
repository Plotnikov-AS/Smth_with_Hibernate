import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import javax.management.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        //PurchaseList query
        CriteriaQuery<PurchaseList> purchaseListCriteriaQuery = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> purchaseListRoot = purchaseListCriteriaQuery.from(PurchaseList.class);

        //Students query
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);

        //Course query
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);

        purchaseListCriteriaQuery.select(purchaseListRoot);
        List<PurchaseList> purchaseListItems = session.createQuery(purchaseListCriteriaQuery).getResultList();

        int studentId;
        String studentName;
        int courseId;
        String courseName;

        for (PurchaseList item : purchaseListItems){
            //Get student info (name, id)
            studentName = item.getId().getStudentName();
            studentCriteriaQuery.select(studentRoot).where(builder.equal(studentRoot.get("name"), studentName));
            studentId = session.createQuery(studentCriteriaQuery).getSingleResult().getId();

            //Get course info (name, id)
            courseName = item.getId().getCourseName();
            courseCriteriaQuery.select(courseRoot).where(builder.equal(courseRoot.get("name"), courseName));
            courseId = session.createQuery(courseCriteriaQuery).getSingleResult().getId();

            //Add id`s to database
            item.setStudentId(studentId);
            item.setCourseId(courseId);
            session.save(item);
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }
}
