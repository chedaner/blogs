package annotation_1;

import java.lang.annotation.*;

/**
 * ˮ����Ӧ��ע��
 * @author peida
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    /**
     * ��Ӧ�̱��
     * @return
     */
    public int id() default -1;

    /**
     * ��Ӧ������
     * @return
     */
    public String name() default "";

    /**
     * ��Ӧ�̵�ַ
     * @return
     */
    public String address() default "";
}
