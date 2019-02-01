package org.misha;

import com.google.common.base.Joiner;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.net.URLDecoder.decode;
import static java.net.URLEncoder.encode;
import static org.misha.HttpURLPostParams.Convert.*;

public class HttpURLPostParams {
    private static final Logger LOG = Logger.getLogger("");
    private static final String ENC = "UTF-8";
    private static final Pattern pattern = Pattern.compile("^((.)+=(.)+)(\\+&(.)+=(.))*");

    private static String getQuery(LinkedHashMap<String, String> params) {
        return params.entrySet()
                  .stream()
                  .map(e -> joinEntry(ENCODE, e.getKey(), e.getValue()))
                  .reduce((x, y) -> Joiner.on("&").join(x, y))
                  .orElse("error");
    }

    enum Convert {
        ENCODE {
            @Override
            String convert(final String data) throws UnsupportedEncodingException {
                return encode(data, ENC);
            }
        },
        DECODE {
            @Override
            String convert(final String data) throws UnsupportedEncodingException {
                return decode(data, ENC);
            }
        };

        abstract String convert(String data) throws UnsupportedEncodingException;
    }

    private static String joinEntry(Convert option, String... pair) {
        try {
            return Joiner.on("=").join(option.convert(pair[0]), option.convert(pair[1]));
        } catch (UnsupportedEncodingException e) {
            return "\n\n\n" + e.getMessage();
        }
    }

    public static void main(String... args) {
        final String query = getQuery(new LinkedHashMap<String, String>() {{
            put("author", "Тереньтьев Терентий Тереньтьевич <teren'tiy@gmail.com>");
            put("token", "AAAhgdjdb+mnd744607474HGHbvxvv==");
        }});
        LOG.error(query);
        LOG.error(Arrays.stream(query.split("&"))
                        .map(pair -> joinEntry(DECODE, pair.split("=")))
                        .reduce((x, y) -> Joiner.on("&").join(x, y))
                        .orElse("error"));
        checkArgument(pattern.matcher(Arrays.stream(query.split("&"))
                                            .map(pair -> joinEntry(DECODE, pair.split("=")))
                                            .reduce((x, y) -> Joiner.on("&").join(x, y))
                                            .orElse("error")).matches());
        checkArgument(pattern.matcher(query).matches());
    }
}
