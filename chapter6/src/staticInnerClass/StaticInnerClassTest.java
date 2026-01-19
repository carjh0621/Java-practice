package staticInnerClass;

/**
 * This program demonstrates the use of static inner classes.
 * @version 1.02 2015-05-12
 * @author Cay Horstmann
 */
public class StaticInnerClassTest
{
    public static void main(String[] args)
    {
        var values = new double[20];

        // 난수로 배열 채우기
        for (int i = 0; i < values.length; i++)
            values[i] = 100 * Math.random();

        // [사용] 정적 메서드 호출 및 정적 내부 클래스 리턴
        // ArrayAlg 객체를 생성하지 않고도 기능을 사용할 수 있습니다.
        ArrayAlg.Pair p = ArrayAlg.minmax(values);

        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg
{
    /**
     * A pair of floating-point numbers
     */
    // [핵심] static 내부 클래스 선언
    // 외부 클래스(ArrayAlg)의 인스턴스 없이도 생성 가능합니다.
    public static class Pair
    {
        private double first;
        private double second;

        /**
         * Constructs a pair from two floating-point numbers
         * @param f the first number
         * @param s the second number
         */
        public Pair(double f, double s)
        {
            first = f;
            second = s;
        }

        /**
         * Returns the first number of the pair
         * @return the first number
         */
        public double getFirst()
        {
            return first;
        }

        /**
         * Returns the second number of the pair
         * @return the second number
         */
        public double getSecond()
        {
            return second;
        }
    }

    /**
     * Computes both the minimum and the maximum of an array
     * @param values an array of floating-point numbers
     * @return a pair whose first element is the minimum and whose second element is the maximum
     */
    // 정적 메서드
    public static Pair minmax(double[] values)
    {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double v : values)
        {
            if (min > v) min = v;
            if (max < v) max = v;
        }

        // [생성] static 메서드 내부에서 static 내부 클래스를 생성
        return new Pair(min, max);
    }
}