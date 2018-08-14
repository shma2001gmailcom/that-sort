package org.misha.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.misha.beanutils.beans.Root;

import java.util.Map;

/**
 * author: misha
 * date: 8/11/18
 * time: 12:15 AM
 */
public class Launcher {
    private static final Logger log = Logger.getLogger(Launcher.class);

    public static void main(String... args) {
        final Gson gson = new GsonBuilder().serializeNulls().setFieldNamingStrategy(
                f -> f.getType().getCanonicalName() + " " + f.getName()).create();
        final Root root = Root.RootMaker.makeRoot();
        final String s = gson.toJson(root);
        assert gson.toJson(gson.fromJson(new JsonParser().parse(s), Root.class)).equals(s) : "strings are not equal";
        assert gson.fromJson(new JsonParser().parse(s), Root.class).equals(root) : "objects are not equal";
        JsonSpy.parse(new JsonParser().parse(s), 0);
    }

    static class JsonSpy {
        private JsonSpy() {
        }

        static void parse(final JsonElement element, int depth) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; ++i) {
                sb.append(".");
            }
            if (element.isJsonObject()) {
                ++depth;
                for (final Map.Entry<String, JsonElement> e : element.getAsJsonObject().entrySet()) {
                    log.debug(sb.toString() + e.getKey() + "=" + e.getValue());
                    parse(e.getValue(), depth);
                }
            } else if (element.isJsonArray()) {
                ++depth;
                for (final JsonElement e : element.getAsJsonArray()) {
                    log.debug(sb.toString() + e);
                    parse(e, depth);
                }
            } else if (element.isJsonPrimitive()) {
                log.debug(sb.toString() + element.getAsJsonPrimitive());
            } else {
                log.debug(sb + "null");
            }
        }
    }
}
