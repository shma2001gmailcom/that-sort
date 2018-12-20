package org.misha.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.lang.Thread.currentThread;

/**
 * author: misha
 * date: 8/11/18
 * time: 12:15 AM
 */
public class Launcher {
    private static final Logger log = Logger.getLogger(Launcher.class);

    public static void main(String... args) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Add add = new Add();
        add.setType("ADD");
        add.setValues(new HashMap<String, String>() {
            private static final long serialVersionUID = 6936852021651723823L;

            {
                put("one", "1");
            }
        });
        System.out.println(gson.toJson(add, Add.class));
        String jsonPatch = readJsonFile("patch-example.json");
        JsonParser jsonParser = new JsonParser();
        JsonElement patchRoot = jsonParser.parse(jsonPatch);
        JsonSpy.parse(patchRoot, 4 );
        Set<JsonElement> foundAdd = new HashSet<>();
        JsonSpy.findByName(patchRoot, "add", foundAdd);
        Add add1 = foundAdd.stream().findFirst().map(e -> gson.fromJson(e, Add.class)).orElse(null);
        Set<JsonElement> foundOp = new HashSet<>();
        JsonSpy.findByName(patchRoot, "_op", foundOp);
        Op op = foundOp.stream().findFirst().map(e -> gson.fromJson(e, Op.class)).orElse(null);
        Set<JsonElement> foundPatch = new HashSet<>();
        JsonSpy.findByName(patchRoot, "patch", foundPatch);
        Patch patch = foundPatch.stream().findFirst().map(e -> gson.fromJson(e, Patch.class)).orElse(null);
        System.out.println(patch);
    }

    private static String readJsonFile(String name) throws IOException {
        InputStream in = currentThread().getContextClassLoader().getResourceAsStream(name);
        return IOUtils.toString(in);
    }

    static class JsonSpy {
        private JsonSpy() {
        }

        static void parse(final JsonElement element, int depth) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; ++i) {
                sb.append("..");
            }
            if (element.isJsonObject()) {
                ++depth;
                for (final Map.Entry<String, JsonElement> e : element.getAsJsonObject().entrySet()) {
                    log.debug(sb.toString() + e.getKey());
                    parse(e.getValue(), depth);
                }
            }
            if (element.isJsonArray()) {
                ++depth;
                for (final JsonElement e : element.getAsJsonArray()) {
                    log.debug(sb.toString() + e);
                    parse(e, depth);
                }
            }
            if (element.isJsonPrimitive()) {
                log.debug(sb.toString() + element.getAsJsonPrimitive());
            }
            if (element.isJsonNull()) {
                log.debug(sb + "null");
            }
        }

        static void findByName(JsonElement given, String name, Collection<JsonElement> found) {
            if (given.isJsonObject()) {
                for (final Map.Entry<String, JsonElement> e : given.getAsJsonObject().entrySet()) {
                    if (e.getKey().equals(name)) {
                        found.add(e.getValue());
                    }
                    findByName(e.getValue(), name, found);
                }
            }
        }
    }

    static class Add {
        protected String type;
        Map<String, String> values;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Map<String, String> getValues() {
            return values;
        }

        void setValues(Map<String, String> values) {
            this.values = values;
        }
    }

    static class Op {
        private Add add;
        private SetAttrs setAttrs;
        private Satellites satellites;

        public Add getAdd() {
            return add;
        }

        public void setAdd(Add add) {
            this.add = add;
        }

        public SetAttrs getSetAttrs() {
            return setAttrs;
        }

        public void setSetAttrs(SetAttrs setAttrs) {
            this.setAttrs = setAttrs;
        }
    }

    private static class SetAttrs {
        protected String type;
        Map<String, String> values;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Map<String, String> getValues() {
            return values;
        }

        public void setValues(Map<String, String> values) {
            this.values = values;
        }
    }

    private static class Satellites {
        private Map<String, Op> satellites;

        public Map<String, Op> getSatellites() {
            return satellites;
        }

        public void setSatellites(Map<String, Op> satellites) {
            this.satellites = satellites;
        }
    }

    private static class Patch {
        private Map<String, Op> items;

        public Map<String, Op> getItems() {
            return items;
        }

        public void setItems(Map<String, Op> items) {
            this.items = items;
        }
    }
}
