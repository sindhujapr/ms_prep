package general;

/*
 * http://javadude.com/articles/passbyvalue.htm
 */
public class PassByValue {
    public static void foo(Dog d) {
        d.name.equals("Max"); // true
        d = new Dog("Fifi");
        /* try the below two calls */
//      d.name = "Fifi";
//      d.setName("Fifi");
        System.out.println(d.name.equals("Fifi")); // true
    }

    public static void main(String[] args) {
        Dog aDog = new Dog("Max");
        foo(aDog);
        System.out.println(aDog.name.equals("Max")); // true
    }
}


class Dog {
    public String name;
    public Dog(String name) { this.name = name; }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return name; }
}
