package j2os.service;

import j2os.common.JPA;
import j2os.entity.secondLevelCache.Person;
import net.sf.ehcache.CacheManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

/**
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2023.05.13   Mahsa
 */
public class PersonService1 {

    public static void save() {
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Person person = new Person().setName("Test2").setFamily("Test2").setSalary(2000).setNationalCode("222222222");
        Person person1 = new Person().setName("Test1").setFamily("Test1").setSalary(2000).setNationalCode("2313213");
        Person person2 = new Person().setName("Test3").setFamily("Test3").setSalary(2000).setNationalCode("2222212354562222");
        Person person3 = new Person().setName("Test4").setFamily("Test4").setSalary(2000).setNationalCode("45453");
        Person person4 = new Person().setName("Test5").setFamily("Test5").setSalary(2000).setNationalCode("4153453");
        entityManager.persist(person);
        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.persist(person4);
        entityManager.persist(person4);
        entityManager.persist(person4);
        entityManager.persist(person4);

        entityTransaction.commit();
        entityManager.close();

    }

    public static void findOne() {
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Person person = entityManager.find(Person.class, 1);
        System.out.println("name::::" + person.getName() + "family::::" + person.getFamily());
        List<CacheManager> allCacheManagers = CacheManager.ALL_CACHE_MANAGERS;
        for (CacheManager allCacheManager : allCacheManagers) {
            System.out.println(Arrays.toString(allCacheManager.getCacheNames()));
        }
        entityManager.close();

        EntityManager entityManager1 = JPA.getEntityManager();
        EntityTransaction transaction1 = entityManager1.getTransaction();
        transaction1.begin();

        Person person1 = entityManager1.find(Person.class, 1);
        System.out.println("name::::" + person1.getName() + "family::::" + person1.getFamily());
        List<CacheManager> allCacheManagers1 = CacheManager.ALL_CACHE_MANAGERS;
        for (CacheManager allCacheManager : allCacheManagers1) {
            System.out.println(Arrays.toString(allCacheManager.getCacheNames()));
        }

        int cacheSize = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("Person").getSize();
        System.out.println(cacheSize);
        entityManager1.close();


    }

    public static void main(String[] args) {
        findOne();
    }
}
