import java.math.BigInteger;
import java.sql.SQLClientInfoException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BalancedString {
    static MyPredicate b = a -> a % 2 == 0;

    public static void main(String[] args) throws SQLClientInfoException {

        System.out.println(new BigInteger("28c06eb41c4dc9c3ae114831efcac7446c8747777fca8b145ecd31ff8480ae88", 16));

        Arrays.asList("28c06eb41c4dc9c3ae114831efcac7446c8747777fca8b145ecd31ff8480ae88"
                ,"4d4abb9168114e349672b934d16ed201a919cb49e28b7f66a240e62c92ee007f"
                ,"fce514f84f37934bc8aa0f861e4f7392273d71b9d18e8209d21e4192a7842058")
                .parallelStream()
                .reduce((s, s2) -> String.format("%02x", calcula(s, s2))).ifPresent(System.out::println);

//        Stream.of("28c06eb41c4dc9c3ae114831efcac7446c8747777fca8b145ecd31ff8480ae88"
//                ,"4d4abb9168114e349672b934d16ed201a919cb49e28b7f66a240e62c92ee007f"
//                ,"fce514f84f37934bc8aa0f861e4f7392273d71b9d18e8209d21e4192a7842058").
//                .reduce((s, s2) -> String.format("%02x", calcula(s, s2).get())).ifPresent(System.out::println);
    }

    public static BigInteger calcula(String cid1, String cid2) {
        return Optional.ofNullable(cid1)
                .map(v -> new BigInteger(v, 16))
                .map(v -> v.xor(new BigInteger(cid2, 16))).orElse(null);
    }
}

