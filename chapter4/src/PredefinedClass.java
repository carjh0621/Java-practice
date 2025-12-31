import java.util.Date;

public class PredefinedClass {
    public static void main(String[] args) {

        System.out.println(new Date());
        String s = new Date().toString();
        System.out.println("문자열 변환: "+ s);

        Date rightNow = new Date();
        Date startTime = rightNow;

        //참조 공유
        System.out.println("rightNow: "+rightNow);
        System.out.println("startTime: "+startTime);

        startTime = null;

        if(startTime!=null){
            System.out.println(startTime);
        }
        else{
            System.out.println("startTime is null");
        }
        System.out.println("right now is not null: "+rightNow);

    }
}
