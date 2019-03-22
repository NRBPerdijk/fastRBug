import java.io.IOException;
import java.util.function.Function;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class Main {

    public static void main(String... args) throws IOException {
        Context graalContext = Context.newBuilder().allowAllAccess(true).build();

        final Function<String, String> directCallToOtherRFunctionInJar = otherRFunctionDirectly(graalContext);
        final Function<String, String> x = rFunctionThatImportsAndCallsOtherRFunction(graalContext);

        System.out.println(directCallToOtherRFunctionInJar.apply("Hello Directly"));
        System.out.println(x.apply(directPathToOtherRFunctionInJar()));
    }

    private static Function<String, String> rFunctionThatImportsAndCallsOtherRFunction(Context graalContext) throws IOException {
        Source sourceFirstR  = Source.newBuilder("R", Main.class.getResource("r/rFunctionThatCallsOtherRFunction.R")).build();
        return graalContext.eval(sourceFirstR).as(Function.class);
    }

    private static Function<String, String> otherRFunctionDirectly(Context graalContext) throws IOException {
        Source sourceFirstR  = Source.newBuilder("R", Main.class.getResource("r/otherRFunctionInJar.R")).build();
        return graalContext.eval(sourceFirstR).as(Function.class);
    }

    private static String directPathToOtherRFunctionInJar() {
        return Main.class.getResource("r/otherRFunctionInJar.R").getPath();
    }

}
