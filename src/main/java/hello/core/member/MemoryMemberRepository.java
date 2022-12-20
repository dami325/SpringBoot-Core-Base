package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) { // 회원 저장(가입)
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) { // 아이디 찾기
        return store.get(memberId);
    }
}
