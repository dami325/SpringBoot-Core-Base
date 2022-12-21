package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 맴버 정보 리파지토리
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 할인 정책

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 단일 체계 원칙을 잘 지킴 -> 이제 할인 관련을 고치려면 할인쪽만 수정하면 됨.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // ----------

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
