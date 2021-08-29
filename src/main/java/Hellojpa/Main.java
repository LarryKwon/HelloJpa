package Hellojpa;

import Hellojpa.Entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션을 획득
        EntityTransaction tx = em.getTransaction();
        //트랜잭션을 시작,데이터베이스에서 접근해서 커넥션을 들고온 다음 트랜잭션을 실행행
        tx.begin();
    try {

        tx.commit();
    }catch (Exception e) {
        //transaction이 끝나면 entitymanager를 닫음으로서 데이터베이스 리소스를 반환한다.
        tx.rollback();
    }finally{
        em.close();
    }
    //웹앱이 끝난다면 닫아준다.
    emf.close();
    }
}
