package lesson11;

import java.util.Optional;

import static java.util.stream.Stream.*;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App00 {
/*
    �������� �� �� �������, � ������������� �� ������������� ��������
    ���������� �������� � ������ Optionnal / Maybe
 */
    public static void main(String[] args) {
        // �������� �� ������ (������������� �������� + ��������� �������)
        Integer sum0 = of(1, 2, 3).reduce(0, (x, y) -> x + y);
        System.out.println(sum0);

        // ������: Optional / Just
        //�������� �� ������������� �������
        Optional<Integer> sum1 = of(1, 2, 3).reduce((x, y) -> x + y);
        System.out.println(sum1);

        //������: Optional / Nothing
        //�������� �� ������������� �������
        Optional<Integer> sum2 = of(1, 2, 3).filter(x -> x > 10).reduce((x, y) -> x + y);
        System.out.println(sum2);
    }
}
