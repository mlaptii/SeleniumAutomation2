package model;

public class PasswordDecodingService {
    //private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass().getName().getClass());

    /**
     * Copied from old automation framework
     *
     * @param input encoded String password read from file
     * @return decoded String password
     */
    public String decodePassword(String input) {
        String lo = input.substring(0, (input.length() / 2) + (input.length() % 2));
        String hi =
                input.substring((input.length() / 2) + (input.length() % 2));
        StringBuffer sb = new StringBuffer(lo);
        sb.reverse();
        input = hi + sb.toString();
        byte[] c = fromHex(input);
        input = new String(c);
        lo = input.substring(0, (input.length() / 2) + (input.length() % 2));
        hi = input.substring((input.length() / 2) + (input.length() % 2));
        sb = new StringBuffer(lo);
        sb.reverse();
        //log.info("Successfully decoded.");
        return hi + sb.toString();
    }

    private byte[] fromHex(String buf) {
        byte[] a = new byte[buf.length() / 2];
        for (int i = 0, k = 0; i < buf.length(); i += 2, k++) {
            a[k] = Byte.parseByte(buf.substring(i, i + 2), 16);
        }
        //log.info("Successfully converted from HEX.");
        return a;
    }
}

