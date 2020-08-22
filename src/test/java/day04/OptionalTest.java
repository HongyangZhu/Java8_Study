package day04;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void test01() {
        // Optional.of(T t) : 创建一个 Optional 实例
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }

    @Test
    public void test02() {
        // Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
        Girl girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }

    @Test
    public void test03() {
        // 当Boy为null时
        Boy boy = null;
        String girlName_java7 = getGirlName_Java7(boy);
        System.out.println(girlName_java7);
        String girlName_java8 = getGirlName_Java8(boy);
        System.out.println(girlName_java8);
        // 当Girl为null时
        boy = new Boy(null);
        girlName_java8 = getGirlName_Java8(boy);
        System.out.println(girlName_java8);
    }

    // 空指针问题
    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    // java7 优化解决空指针问题
    public String getGirlName_Java7(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
            return null;
        }
        return null;
    }

    // java8 使用Optional类解决空指针问题
    public String getGirlName_Java8(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("Lucy")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("Ann"));
        return girl1.getName();
    }

}
