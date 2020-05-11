package tutorial.lib.commons.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

/**
 * @author JiaweiMao 2017-05-03
 * @since 1.0-SNAPSHOT
 */
public class HexTest
{
    @Test
    void test() throws DecoderException
    {
        String text = "File18457%20Spectrum45521%20scans%3a%2055268";
        System.out.println(parse(text));
    }

    String parse(String title) throws DecoderException
    {
        StringBuilder builder = new StringBuilder();
        int startId = 0;
        int index = -1;
        while ((index = title.indexOf("%", startId)) > 0) {
            builder.append(title.substring(startId, index));
            String hex = title.substring(index + 1, index + 3);
            builder.append(new String(Hex.decodeHex(hex.toCharArray())));
            startId = index + 3;
        }
        builder.append(title.substring(startId));
        return builder.toString();
    }

}
