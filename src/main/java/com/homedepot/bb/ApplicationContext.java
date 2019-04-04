package com.homedepot.bb;

import com.homedepot.bb.annotations.RequestMapping;
import com.homedepot.bb.util.ControllerMapping;
import org.reflections.Reflections;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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

        loadControllers();

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

                NodeList nList = doc.getElementsByTagName(BEAN_NODE);

                int nListLength = nList.getLength();

                for( int i = 0; i < nListLength; i++){

                    NamedNodeMap attrs = nList.item(i).getAttributes();

                    String id = attrs.getNamedItem("id").getNodeValue();
                    String className = attrs.getNamedItem("class").getNodeValue();

                    LOGGER.log(
                            Level.INFO,
                            "Found bean id:"
                                    + id
                                    + " class: "
                                    + className
                    );

                    try {

                        beanMap.put(id, Class.forName(className).newInstance());

                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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

    private void loadControllers(){

        String controllerClasspath = appProps.getProperty("controllerRootPackage");

        Reflections reflections = new Reflections(controllerClasspath);

        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(
                com.homedepot.bb.annotations.Controller.class
        );

        for(Class clazz : controllers){

            LOGGER.log(Level.INFO, clazz.getName());

            try {

                Object instance = clazz.newInstance();

                List<Method> methods = Arrays.asList(instance.getClass().getMethods());

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

            } catch (InstantiationException | IllegalAccessException e) {
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
