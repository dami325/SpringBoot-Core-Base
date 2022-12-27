package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }


        /**
         * @Nullable, Optional은 스프링 전반에 걸쳐서 지원된다.
         * 생성자 자동 주입에서 특정 필드에만 사용해도 된다.
         * @Nullable = 없으면 null 출력
         * Optional<Member> 없으면 Optional.empty 출력
         */


        @Autowired
        public void setNoBean2(@Nullable Member noBean1) {
            System.out.println("noBean2 = " + noBean1);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }


    }


}
