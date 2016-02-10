package ejbm.mac.com.bighw.beans;

/**
 * Created by admin on 2/5/2016.
 */
public class Contact {

    private int id ;
    private  String name ;
    private String phone ;

    public Contact() {

    }

    public Contact(String name ) {
        this.name = name;

    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact(int i , String name, String phone) {
        this.id = i ;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contacto [ " + id + " ] " + getName() + " - " + getPhone()  ;
    }
}
