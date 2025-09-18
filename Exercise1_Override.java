// Exercise 1: Use @Override Correctly
// Simple example by a student :)
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound...");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

public class Exercise1_Override {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.makeSound(); // should print "Woof! Woof!"
    }
}