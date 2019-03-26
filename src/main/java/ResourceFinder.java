import java.io.IOException;
import java.util.function.Function;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class ResourceFinder {

    public Function<Object, Object> sourceResource(String path) throws IOException {
        return Context.getCurrent().eval(Source.newBuilder("R", ResourceFinder.class.getResource(path)).build()).as(Function.class);
    }

}
