package net.slipp.moim;
/*
 * Created by joenggyu0@gmail.com on 6/13/20
 * Github : http://github.com/lenkim
 */

import io.swagger.codegen.*;
import io.swagger.codegen.languages.AbstractJavaCodegen;
import io.swagger.codegen.languages.features.BeanValidationFeatures;
import io.swagger.codegen.languages.features.OptionalFeatures;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Swagger;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static com.samskivert.mustache.Mustache.Lambda;

@Setter
public class Slipp extends AbstractJavaCodegen
        implements BeanValidationFeatures, OptionalFeatures {

    protected String title = "swagger-petstore";
    protected String configPackage = "io.swagger.configuration";
    protected String basePackage = "io.swagger";
    protected boolean interfaceOnly = false;
    protected boolean singleContentTypes = false;
    protected boolean java8 = false;
    protected boolean async = false;
    protected String responseWrapper = "";

    protected boolean useTags = false;
    protected boolean useBeanValidation = true;
    protected boolean implicitHeaders = false;
    protected boolean useOptional = false;
    protected boolean hello = false;

    public Slipp() {
        super();

        this.apiTestTemplateFiles.clear();
        this.apiTemplateFiles.clear();
        this.modelTemplateFiles.clear();
        this.modelDocTemplateFiles.clear();
        this.apiDocTemplateFiles.clear();

        embeddedTemplateDir = templateDir = "slippcodegen";
        modelTemplateFiles.put("model.mustache", ".java");
        apiTemplateFiles.put("api.mustache", ".java");

        outputFolder = "generated-code/javaSpring";
        apiPackage = "io.swagger.api";
        modelPackage = "io.swagger.model";
        invokerPackage = "io.swagger.api";
        artifactId = "swagger-spring";

        additionalProperties.put(DefaultConstant.CONFIG_PACKAGE.getValue(), configPackage);
        additionalProperties.put(DefaultConstant.BASE_PACKAGE.getValue(), basePackage);

        // spring uses the jackson lib
        additionalProperties.put("jackson", "true");

        cliOptions.add(new CliOption(DefaultConstant.TITLE.getValue(), "server title name or client service name"));
        cliOptions.add(new CliOption(DefaultConstant.CONFIG_PACKAGE.getValue(), "configuration package for generated code"));
        cliOptions.add(new CliOption(DefaultConstant.BASE_PACKAGE.getValue(), "base package (invokerPackage) for generated code"));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.INTERFACE_ONLY.getValue(), "Whether to generate only API interface stubs without the server files."));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.JAVA_8.getValue(), "use java8 features like the new date library"));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.ASYNC.getValue(), "use async Callable controllers"));
        cliOptions.add(new CliOption(DefaultConstant.RESPONSE_WRAPPER.getValue(), "wrap the responses in given type (Future,Callable,CompletableFuture,ListenableFuture,DeferredResult,HystrixCommand,RxObservable,RxSingle or fully qualified type)"));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.USE_TAGS.getValue(), "use tags for creating interface and controller classnames"));
        cliOptions.add(CliOption.newBoolean(USE_BEANVALIDATION, "Use BeanValidation API annotations"));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.IMPLICIT_HEADERS.getValue(), "Use of @ApiImplicitParams for headers."));
        cliOptions.add(CliOption.newBoolean(DefaultConstant.HELLO.getValue(), "XXXXX"));

        cliOptions.add(CliOption.newBoolean(USE_OPTIONAL,
                "Use Optional container for optional parameters"));

        supportedLibraries.put(DefaultConstant.DEFAULT_LIBRARY.getValue(), "Spring-boot Server application using the SpringFox integration.");
        setLibrary(DefaultConstant.DEFAULT_LIBRARY.getValue());

        CliOption library = new CliOption(CodegenConstants.LIBRARY, "library template (sub-template) to use");
        library.setDefault(DefaultConstant.DEFAULT_LIBRARY.getValue());
        library.setEnum(supportedLibraries);
        library.setDefault(DefaultConstant.DEFAULT_LIBRARY.getValue());
        cliOptions.add(library);
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return "Slipp";
    }

    @Override
    public String getHelp() {
        return "Generates Custom CodeGenConfig";
    }

    @Override
    public void processOpts() {

        if (additionalProperties.containsKey(DefaultConstant.JAVA_8.getValue())) {
            this.setJava8(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.JAVA_8.getValue()).toString()));
        }
        if (this.java8) {
            additionalProperties.put("javaVersion", "1.8");
            additionalProperties.put("jdk8", "true");
            if (!additionalProperties.containsKey(DATE_LIBRARY)) {
                setDateLibrary("java8");
            }
        }

        // set invokerPackage as basePackage
        if (additionalProperties.containsKey(CodegenConstants.INVOKER_PACKAGE)) {
            this.setBasePackage((String) additionalProperties.get(CodegenConstants.INVOKER_PACKAGE));
            additionalProperties.put(DefaultConstant.BASE_PACKAGE.getValue(), basePackage);
        }

        super.processOpts();

        if (additionalProperties.containsKey(DefaultConstant.TITLE.getValue())) {
            this.setTitle((String) additionalProperties.get(DefaultConstant.TITLE.getValue()));
        }

        if (additionalProperties.containsKey(DefaultConstant.CONFIG_PACKAGE.getValue())) {
            this.setConfigPackage((String) additionalProperties.get(DefaultConstant.CONFIG_PACKAGE.getValue()));
        }

        if (additionalProperties.containsKey(DefaultConstant.BASE_PACKAGE.getValue())) {
            this.setBasePackage((String) additionalProperties.get(DefaultConstant.BASE_PACKAGE.getValue()));
        }

        if (additionalProperties.containsKey(DefaultConstant.INTERFACE_ONLY.getValue())) {
            this.setInterfaceOnly(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.INTERFACE_ONLY.getValue()).toString()));
        }

        if (additionalProperties.containsKey(DefaultConstant.JAVA_8.getValue())) {
            this.setJava8(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.JAVA_8.getValue()).toString()));
        }

        if (additionalProperties.containsKey(DefaultConstant.ASYNC.getValue())) {
            this.setAsync(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.ASYNC.getValue()).toString()));
        }

        if (additionalProperties.containsKey(DefaultConstant.RESPONSE_WRAPPER.getValue())) {
            this.setResponseWrapper((String) additionalProperties.get(DefaultConstant.RESPONSE_WRAPPER.getValue()));
        }

        if (additionalProperties.containsKey(DefaultConstant.USE_TAGS.getValue())) {
            this.setUseTags(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.USE_TAGS.getValue()).toString()));
        }

        if (additionalProperties.containsKey(USE_BEANVALIDATION)) {
            this.setUseBeanValidation(convertPropertyToBoolean(USE_BEANVALIDATION));
        }

        if (additionalProperties.containsKey(USE_OPTIONAL)) {
            this.setUseOptional(convertPropertyToBoolean(USE_OPTIONAL));
        }

        writePropertyBack(USE_BEANVALIDATION, useBeanValidation);

        if (additionalProperties.containsKey(DefaultConstant.IMPLICIT_HEADERS.getValue())) {
            this.setImplicitHeaders(Boolean.parseBoolean(additionalProperties.get(DefaultConstant.IMPLICIT_HEADERS.getValue()).toString()));
        }

        if (additionalProperties.containsKey(DefaultConstant.HELLO.getValue())) {
            additionalProperties.put("hello", DefaultConstant.HELLO.getValue());
        }

        typeMapping.put("file", "Resource");
        importMapping.put("Resource", "org.springframework.core.io.Resource");

        if (this.java8) {
            additionalProperties.put("javaVersion", "1.8");
            additionalProperties.put("jdk8", "true");
            if (this.async) {
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "CompletableFuture");
            }
        } else if (this.async) {
            additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "Callable");
        }

        // Some well-known Spring or Spring-Cloud response wrappers
        switch (this.responseWrapper) {
            case "Future":
            case "Callable":
            case "CompletableFuture":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "java.util.concurrent" + this.responseWrapper);
                break;
            case "ListenableFuture":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "org.springframework.util.concurrent.ListenableFuture");
                break;
            case "DeferredResult":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "org.springframework.web.context.request.async.DeferredResult");
                break;
            case "HystrixCommand":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "com.netflix.hystrix.HystrixCommand");
                break;
            case "RxObservable":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "rx.Observable");
                break;
            case "RxSingle":
                additionalProperties.put(DefaultConstant.RESPONSE_WRAPPER.getValue(), "rx.Single");
                break;
            default:
                break;
        }

        // add lambda for mustache templates
        additionalProperties.put("lambdaEscapeDoubleQuote", (Lambda) (fragment, writer) -> writer.write(fragment.execute().replaceAll("\"", Matcher.quoteReplacement("\\\""))));
        additionalProperties.put("lambdaRemoveLineBreak", (Lambda) (fragment, writer) -> writer.write(fragment.execute().replaceAll("\\r|\\n", "")));
    }

    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        super.addOperationToGroup(tag, resourcePath, operation, co, operations);
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        super.preprocessSwagger(swagger);
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
        if (operations != null) {
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (final CodegenOperation operation : ops) {
                List<CodegenResponse> responses = operation.responses;
                if (responses != null) {
                    for (final CodegenResponse resp : responses) {
                        if ("0".equals(resp.code)) {
                            resp.code = "200";
                        }
                        doDataTypeAssignment(resp.dataType, new Slipp.DataTypeAssigner() {
                            @Override
                            public void setReturnType(final String returnType) {
                                resp.dataType = returnType;
                            }

                            @Override
                            public void setReturnContainer(final String returnContainer) {
                                resp.containerType = returnContainer;
                            }
                        });
                    }
                }

                doDataTypeAssignment(operation.returnType, new Slipp.DataTypeAssigner() {

                    @Override
                    public void setReturnType(final String returnType) {
                        operation.returnType = returnType;
                    }

                    @Override
                    public void setReturnContainer(final String returnContainer) {
                        operation.returnContainer = returnContainer;
                    }
                });

                if (implicitHeaders) {
                    removeHeadersFromAllParams(operation.allParams);
                }
            }
        }

        return objs;
    }

    private interface DataTypeAssigner {
        void setReturnType(String returnType);

        void setReturnContainer(String returnContainer);
    }

    /**
     * @param returnType       The return type that needs to be converted
     * @param dataTypeAssigner An object that will assign the data to the respective fields in the model.
     */
    private void doDataTypeAssignment(String returnType, DataTypeAssigner dataTypeAssigner) {
        final String rt = returnType;
        if (rt == null) {
            dataTypeAssigner.setReturnType("Void");
        } else if (rt.startsWith("List")) {
            int end = rt.lastIndexOf(">");
            if (end > 0) {
                dataTypeAssigner.setReturnType(rt.substring("List<".length(), end).trim());
                dataTypeAssigner.setReturnContainer("List");
            }
        } else if (rt.startsWith("Map")) {
            int end = rt.lastIndexOf(">");
            if (end > 0) {
                dataTypeAssigner.setReturnType(rt.substring("Map<".length(), end).split(",")[1].trim());
                dataTypeAssigner.setReturnContainer("Map");
            }
        } else if (rt.startsWith("Set")) {
            int end = rt.lastIndexOf(">");
            if (end > 0) {
                dataTypeAssigner.setReturnType(rt.substring("Set<".length(), end).trim());
                dataTypeAssigner.setReturnContainer("Set");
            }
        }
    }

    /**
     * This method removes header parameters from the list of parameters and also
     * corrects last allParams hasMore state.
     *
     * @param allParams list of all parameters
     */
    private void removeHeadersFromAllParams(List<CodegenParameter> allParams) {
        if (allParams.isEmpty()) {
            return;
        }
        final ArrayList<CodegenParameter> copy = new ArrayList<>(allParams);
        allParams.clear();

        for (CodegenParameter p : copy) {
            if (!p.isHeaderParam) {
                allParams.add(p);
            }
        }
        allParams.get(allParams.size() - 1).hasMore = false;
    }

    @Override
    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
        return objs;
    }

    @Override
    public String toApiName(String name) {
        if (name.length() == 0) {
            return "DefaultApi";
        }
        name = sanitizeName(name);
        return camelize(name) + "Api";
    }

    @Override
    public String toApiTestFilename(String name) {
        return toApiName(name) + "ControllerIntegrationTest";
    }

    @Override
    public void setParameterExampleValue(CodegenParameter p) {
        String type = p.baseType;
        if (type == null) {
            type = p.dataType;
        }

        if ("File".equals(type)) {
            String example;

            if (p.defaultValue == null) {
                example = p.example;
            } else {
                example = p.defaultValue;
            }

            if (example == null) {
                example = "/path/to/file";
            }
            example = "new org.springframework.core.io.FileSystemResource(new java.io.File(\"" + escapeText(example) + "\"))";
            p.example = example;
        } else {
            super.setParameterExampleValue(p);
        }
    }

    @Override
    public void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
        super.postProcessModelProperty(model, property);

        if ("null".equals(property.example)) {
            property.example = null;
        }

        if (Boolean.FALSE.equals(model.isEnum)) {
            model.imports.add("JsonProperty");

            if (Boolean.TRUE.equals(model.hasEnums)) {
                model.imports.add("JsonValue");
            }
        } else {

            if (additionalProperties.containsKey("jackson")) {
                model.imports.add("JsonCreator");
            }
        }
    }

    @Override
    public Map<String, Object> postProcessModelsEnum(Map<String, Object> objs) {
        objs = super.postProcessModelsEnum(objs);

        //Add imports for Jackson
        List<Map<String, String>> imports = (List<Map<String, String>>) objs.get("imports");
        List<Object> models = (List<Object>) objs.get("models");
        for (Object _mo : models) {
            Map<String, Object> mo = (Map<String, Object>) _mo;
            CodegenModel cm = (CodegenModel) mo.get("model");
            // for enum model
            if (Boolean.TRUE.equals(cm.isEnum) && cm.allowableValues != null) {
                cm.imports.add(importMapping.get("JsonValue"));
                Map<String, String> item = new HashMap<String, String>();
                item.put("import", importMapping.get("JsonValue"));
                imports.add(item);
            }
        }

        return objs;
    }

    @Override
    public CodegenOperation fromOperation(String path, String httpMethod, Operation operation, Map<String, Model> definitions, Swagger swagger) {
        CodegenOperation codegenOperation = super.fromOperation(path, httpMethod, operation, definitions, swagger);
        codegenOperation.httpMethod = camelize(codegenOperation.httpMethod.toLowerCase());
        return codegenOperation;
    }

    @Override
    public void setUseBeanValidation(boolean useBeanValidation) {
        this.useBeanValidation = useBeanValidation;
    }

    @Override
    public void setUseOptional(boolean useOptional) {
        this.useOptional = useOptional;
    }
}
