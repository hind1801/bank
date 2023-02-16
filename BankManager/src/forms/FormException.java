package forms;

public class FormException extends Exception{
    private String formName , fieldName;
    public FormException(){super("Erreur dans le formulaire !!");}
    public FormException(String errorMsg){super(errorMsg);}
    public FormException(String errorMsg,String formName , String fieldName){
        super(errorMsg);
        this.fieldName = fieldName;
        this.formName = formName;
    }
    public FormException(String formName , String fieldName){
        this();
        this.fieldName = fieldName;
        this.formName = formName;
    }


    public String getFieldName() {
        return fieldName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
