package com.github.golovnyakpa.hw05_processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.github.golovnyakpa.hw05_processor.CustomToString")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class MyToStringProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Map<Boolean, List<Element>> annotatedElements = roundEnv
                .getElementsAnnotatedWith(CustomToString.class)
                .stream()
                .collect(Collectors.partitioningBy(x -> x.getKind() == ElementKind.CLASS));
        List<Element> annotatedClasses = annotatedElements.get(true);
        List<Element> notClasses = annotatedElements.get(false);

        notClasses.forEach(x ->
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR, "@CustomToString must be applied to a class", x
                )
        );

        annotatedClasses.forEach(cls -> {
            try {
                writePrintableClassFile(cls);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR, "Error while handling annotation " + e.getMessage()
                );
            }
        });

        return false;
    }

    private List<String> getClassFields(Element classElement) {
        return classElement
                .getEnclosedElements().stream()
                .filter(x -> x.getKind() == ElementKind.FIELD)
                .map(x -> x.getSimpleName().toString())
                .collect(Collectors.toList());
    }


    private void writePrintableClassFile(Element cls) throws IOException {
        String className = ((TypeElement) cls).getQualifiedName().toString();
        String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String printableClassName = className + "Printable";
        String simplePrintableClassName = printableClassName.substring(className.lastIndexOf('.') + 1);

        System.out.println("Path is " + Paths.get("target").toUri().toURL());

        JavaFileObject printableFile = processingEnv.getFiler().createSourceFile(printableClassName);

        List<String> classFields = getClassFields(cls);

        try (PrintWriter out = new PrintWriter(printableFile.openWriter())) {
            out.print("package ");
            out.print(packageName);
            out.println(";");
            out.println();

            out.println("import " + className + ";");

            out.print("public class ");
            out.print(simplePrintableClassName);
            out.print(" extends " + simpleClassName);
            out.println(" {");

            out.println("    @Override");
            out.println("    public String toString() {");
            out.println(buildToStringBody(classFields, simpleClassName));
            out.println("    }");

            out.println("}");
            out.println();

        }
    }

    private String buildToStringBody(List<String> fields, String simpleClassName) {
        String stringStart = "        return \"" + simpleClassName + "{";
        var res = fields.stream()
                .map(x -> x + "=\"" + "+ get" + x.substring(0, 1).toUpperCase() + x.substring(1) + "()")
                .toList();
        var allParamsString = String.join(" + \",\" + \"", res);
        System.out.println(stringStart + allParamsString);
        return stringStart + allParamsString + " + \"}\";";
    }
}
