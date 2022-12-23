package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

/**
 * 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경
 * @Configuration 이 붙은 AppConfig 를 설정(구성) 정보로 사용함
 * @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록, 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 함
 * applicationContext.getBean() 메서드를 사용해 찾을 수 있음
 */
@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()


    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        // 차후에 메모리가 DB로 바뀌어도 이 코드만 바꾸면됨
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }



}
