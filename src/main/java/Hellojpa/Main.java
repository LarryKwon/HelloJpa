package Hellojpa;

import Hellojpa.Entity.Member;
import Hellojpa.Entity.MemberType;
import Hellojpa.Entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

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
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

        Member member = new Member();
//        member.setId(1L);
        member.setName("안녕하세요");

        member.setMemberType(MemberType.User);
        member.setRegDate(new Date());
        em.persist(member);

        team.getMembers().add(member);
//
//        member.setTeam(team);
        em.flush();
        em.clear();

//        Member findMember = em.find(Member.class, member.getId());
//        Team findTeam = findMember.getTeam();
//
//        findTeam.getName();
//
//        List<Member> members = findTeam.getMembers();
//        for(Member member1 : members){
//            System.out.println("member1 = "+ member1);
//        }
//        em.persist(member);
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
