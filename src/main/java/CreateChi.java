import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: xu.dm
 * @Date: 2019/7/2 14:51
 * @Version: 1.0
 * @Description: 生成汉字
 **/
public  class CreateChi {

    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder(124000);
        //0x3400 - 0x4DB5       // 统一汉字扩展区A，6582字
//        createData(sb, 0x3400, 0x4DB5, "A");
        //0x4E00 - 0x9FCC       // 统一汉字基本集，20941字
        createData(sb, 0x4E00, 0x9FCC, "M");
//        createData(sb, 0xF900, 0xFA2D, "1");
//        createData(sb, 0xFA30, 0xFA6D, "2");
//        createData(sb, 0xFA70, 0xFAD9, "3");
        //0x20000 - 0x2A6D6     // 统一汉字扩展区B，42711字
//        createData(sb, 0x20000, 0x2A6D6, "B");
        //0x2A700 - 0x2B734     // 统一汉字扩展区C，4149字
//        createData(sb, 0x2A700, 0x2B734, "C");
        //0x2B740 - 0x2B81D     // 统一汉字扩展区D，222字
//        createData(sb, 0x2B740, 0x2B81D, "D");
//        createData(sb, 0x2F800, 0x2FA1D, "4");

        File file = new File("d:\\temp\\allwords.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(sb.toString());
        }
        System.out.println("ok");

    }

    private static void createData(StringBuilder sb, int from, int to, String type) {
        int count = 0;
        for (int i = from; i <= to; i++) {
            count++;
            char[] chars = intToUtf16(i);
            for (char one : chars) {
                sb.append(one);
                sb.append(" ");
                if(count>9){
                    sb.append("\n");
                    count = 0;
                }

            }
        }
    }

    private static char[] intToUtf16(int value) {
        if (value < 0 || value > 0x10FFFF) throw new IllegalArgumentException("unicodeValue");

        if (value <= Character.MAX_VALUE) return new char[]{(char) value};

        int vx = value - 0x10000;
        int vHight = vx >> 10;
        //int vLow = vx << 21 >> 21;      //最高位为正负标识为，总是0，如果直接<<22，则会把标识位变成1，从而导致数值变为负数，出错。
        int vLow = vx & 0x03FF;
        int vLow1 = vx << 21 >> 21;

        int w1 = 0xD800;
        int w2 = 0xDC00;

        w1 |= vHight;
        w2 |= vLow;

        return new char[]{(char) w1, (char) w2};

    }

}