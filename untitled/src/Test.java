import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date date = new Date(1649817000000L);
        Date date1 = new Date(1649815404340L);
        String format = s.format(date);
        String format1 = s.format(date1);
        System.out.println(format);
        System.out.println(format1);
    }
}
