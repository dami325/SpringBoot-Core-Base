package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

/**
 *  AppConfig 리팩터링
 *  new MemoryMemberRepository() 부분 중복 제거 이제 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면된다.
 *
 *  AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되었는지 빠르게 파악할 수 있다.
 */

/**
 * AppConfig 에서 할인 정책 역할 객체를 변경했다.
 * OCP(Open/closed principle)개방 폐쇄 원칙과 DIP(Dependenct inversion principle)의존관계 역전 원칙을 모두 만족하는 코드를 가능하게 만들었다.
 * 구성 영역(AppConfig)는 당연히 변경된다. 구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자.
 * 공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다.
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        // 차후에 메모리가 DB로 바뀌어도 이 코드만 바꾸면됨
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }



}
