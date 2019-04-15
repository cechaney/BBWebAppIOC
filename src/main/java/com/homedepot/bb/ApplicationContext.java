package com.homedepot.bb;

import com.homedepot.bb.annotations.AutoWired;
import com.homedepot.bb.annotations.RequestMapping;
import com.homedepot.bb.annotations.Value;
import com.homedepot.bb.util.ControllerMapping;
import org.reflections.Reflections;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.ConstructorProperties;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationContext {

    private static final Logger LOGGER = Logger.getLogger(ApplicationContext.class.getName());

    private static final String APP_PROPS_FILENAME = "app.properties";
    private static final String BEANS_XML = "beans.xml";
    private static final String BEAN_NODE = "bean";

    public static final String APP_CONTEXT_KEY = "APP_CONTEXT";

    private Properties appProps = new Properties();
    private Map<String, Object> beanMap = new HashMap<>();
    private Map<String, ControllerMapping> controllerMap = new HashMap<>();

    public ApplicationContext(){

        loadAppProperties(APP_PROPS_FILENAME);

        loadXmlBeans(BEANS_XML);

        String controllerClasspath = appProps.getProperty("controllerRootPackage");

        loadControllers(controllerClasspath);

    }

    private void loadAppProperties(String propsFile){

        InputStream propsStream = ApplicationContext.class.getClassLoader().getResourceAsStream(propsFile);

        if(propsStream != null){
            try {
                this.appProps.load(propsStream);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Properties file load failed", e);
            }
        } else {
            LOGGER.log(Level.SEVERE, "Properties file not found");
        }

    }

    private void loadXmlBeans(String beansFile){

        InputStream beansStream = ApplicationContext.class.getClassLoader().getResourceAsStream(beansFile);

        if(beansStream != null){

            try {

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(beansStream);

                NodeList beanList = doc.getElementsByTagName(BEAN_NODE);

                int beanListLength = beanList.getLength();

                for( int beanIndex = 0; beanIndex < beanListLength; beanIndex++){

                    Node bean = beanList.item(beanIndex);

                    NamedNodeMap beanAttrs = bean.getAttributes();

                    String id = beanAttrs.getNamedItem("id").getNodeValue();
                    String className = beanAttrs.getNamedItem("class").getNodeValue();

                    LOGGER.log(
                            Level.INFO,
                            "Found bean id:"
                                    + id
                                    + " class: "
                                    + className
                    );

                    Element beanElement = (Element) bean;

                    NodeList propList = beanElement.getElementsByTagName("property");

                    int propListLength = propList.getLength();

                    Map<String, String> propMap = new HashMap<>();

                    for( int propIndex = 0; propIndex < propListLength; propIndex++){

                        NamedNodeMap propAttrs = propList.item(propIndex).getAttributes();

                        String name = propAttrs.getNamedItem("name").getNodeValue();
                        String value = propAttrs.getNamedItem("value").getNodeValue();

                        propMap.put(name, value);
                    }

                    try {

                        Class clazz = Class.forName(className);

                        Object instance = clazz.getDeclaredConstructor().newInstance();

                        List<Field> fields = Arrays.asList(instance.getClass().getDeclaredFields());

                        for(Field field : fields) {

                            field.setAccessible(true);
                            field.set(instance, propMap.get(field.getName()));

                        }


                        beanMap.put(id, instance);

                    } catch (ClassNotFoundException |
                            InstantiationException |
                            IllegalAccessException |
                            NoSuchMethodException |
                            InvocationTargetException e) {

                        LOGGER.log(Level.SEVERE, "Failed to load bean: " + id, e);

                    }

                }

            } catch (IOException | ParserConfigurationException | SAXException e) {
                LOGGER.log(Level.SEVERE, "Beans file load failed", e);
            }

        } else {
            LOGGER.log(Level.SEVERE, "Beans file not found");
        }

        LOGGER.log(Level.INFO, "Finished loading beans");

    }

    private void loadControllers(String controllerClasspath){

        Reflections reflections = new Reflections(controllerClasspath);

        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(
                com.homedepot.bb.annotations.Controller.class
        );

        for(Class clazz : controllers){

            LOGGER.log(Level.INFO, clazz.getName());

            Object instance = null;

            try {

                List<Constructor> constructors = Arrays.asList(clazz.getDeclaredConstructors());

                for(Constructor construct : constructors){

                    if(construct.isAnnotationPresent(AutoWired.class)){

                        if(construct.isAnnotationPresent(ConstructorProperties.class)){
                            ConstructorProperties cprop =
                                    (ConstructorProperties)construct
                                            .getAnnotation(ConstructorProperties.class);

                            List<String> paramNames = Arrays.asList(cprop.value());
                            List<Object> paramValues = new ArrayList<>();
                            List<Class> argsList = new ArrayList<>();

                            for (String paramName : paramNames){

                                Object paramValue = this.appProps.get(paramName);

                                if(paramValue == null){
                                    paramValue = this.beanMap.get(paramName);
                                }

                                if(paramValue != null){
                                    paramValues.add(paramValue);
                                    argsList.add(paramValue.getClass());
                                }


                            }

                            Object[] params = paramValues.toArray();

                            Class[] args = new Class[params.length];

                            for (int i = 0; i < params.length; i++){
                                args[i] = params[i].getClass();
                            }

                            instance = clazz.getDeclaredConstructor(args).newInstance(params);

                        }

                    }
                }

                if(instance == null){
                    instance = clazz.getDeclaredConstructor().newInstance();
                }

                List<Field> fields = Arrays.asList(instance.getClass().getDeclaredFields());

                for(Field field : fields){

                    if(field.isAnnotationPresent(Value.class)){

                        Value anno = field.getAnnotation(Value.class);

                        String appPropName = anno.value();

                        field.setAccessible(true);
                        field.set(instance, appProps.get(appPropName));

                    }

                    if(field.isAnnotationPresent(AutoWired.class)){

                        AutoWired anno = field.getAnnotation(AutoWired.class);

                        String beanName = anno.value();

                        field.setAccessible(true);
                        field.set(instance, beanMap.get(beanName));

                    }

                }

                List<Method> methods = Arrays.asList(instance.getClass().getDeclaredMethods());

                for(Method method : methods){

                    if(method.isAnnotationPresent(RequestMapping.class)){

                        RequestMapping anno = method.getAnnotation(RequestMapping.class);

                        String path = anno.path();
                        ControllerMapping mapping = new ControllerMapping(instance, method);

                        controllerMap.put(
                                path,
                                mapping
                        );

                        LOGGER.log(
                                Level.INFO,
                                "Added " +
                                        path +
                                        " mapping for " +
                                        clazz.getName() +
                                        "." +
                                        method.getName()
                        );
                    }
                }

            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LOGGER.log(Level.SEVERE, "Unable to create instance of " + clazz.getName());
            }

        }

    }

    public Properties getAppProps() {
        return appProps;
    }

    public Map<String, Object> getBeanMap() {
        return beanMap;
    }

    public Map<String, ControllerMapping> getControllerMap() {
        return controllerMap;
    }
}
