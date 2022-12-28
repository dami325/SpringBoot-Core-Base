package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *  수정자 주입(setter 주입)
 *  setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법
 *  선택, 변경 가능성이 있는 의존관계에 사용
 *  자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.

 @Autowired
 public void setMemberRepository(MemberRepository memberRepository) {
 this.memberRepository = memberRepository;
 }

 @Autowired
 public void setDiscountPolicy(DiscountPolicy discountPolicy) {
 this.discountPolicy = discountPolicy;
 }

    final = 불변, 필수 의존관계에 사용
    필드 주입(옛날에 자주 쓰던 방식, 외부에서 변경이 불가능해 테스트하기 어려운 단점때문에 현재에는 잘 사용하지 않음)
    @Autowired private  MemberRepository memberRepository; // 맴버 정보 리파지토리
    @Autowired private  DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 코드를 변경


    일반 메서드 주입 (한번에 여러 필드를 주입 받을 수 있다. 일반적으로 잘 사용하지 않는다)
     @Autowired
     public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
     this.memberRepository = memberRepository;
     this.discountPolicy = discountPolicy;
     }
 */


@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; // 맴버 정보 리파지토리
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 코드를 변경

    // 생성자가 딱 1개만 있으면 @Autowired 생략 가능 (스프링 빈에만 해당)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
