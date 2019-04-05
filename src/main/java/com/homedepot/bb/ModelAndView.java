package com.homedepot.bb;

public class ModelAndView {

    private Object model;
    private String viewName;

    public ModelAndView(Object model, String view){
        this.model = model;
        this.viewName = view;
    }

    public Object getModel() {
        return model;
    }

    public String getViewName() {
        return viewName;
    }

}
