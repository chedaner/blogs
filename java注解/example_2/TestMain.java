package annotation_2;

@HelloAnnotation(say = "Do it!")
public class TestMain {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloAnnotation annotation = TestMain.class.getAnnotation(HelloAnnotation.class);//��ȡTestMain���ϵ�ע�����
        System.out.println(annotation.say());//����ע������say����������ӡ������̨
    }
}
