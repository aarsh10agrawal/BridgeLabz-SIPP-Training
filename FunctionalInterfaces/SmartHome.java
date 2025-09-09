// SmartDevice interface
interface SmartDevice {
    void turnOn();
    void turnOff();
}

// Light implementation
class Light implements SmartDevice {
    @Override
    public void turnOn() {
        System.out.println(" Light is now ON");
    }
    @Override
    public void turnOff() {
        System.out.println(" Light is now OFF");
    }
}

// AC implementation
class AC implements SmartDevice {
    @Override
    public void turnOn() {
        System.out.println(" AC is cooling the room");
    }
    @Override
    public void turnOff() {
        System.out.println(" AC is switched OFF");
    }
}

// TV implementation
class TV implements SmartDevice {
    @Override
    public void turnOn() {
        System.out.println(" TV is now ON");
    }
    @Override
    public void turnOff() {
        System.out.println(" TV is now OFF");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice light = new Light();
        SmartDevice ac = new AC();
        SmartDevice tv = new TV();

        // Turn devices ON
        light.turnOn();
        ac.turnOn();
        tv.turnOn();

        // Turn devices OFF
        light.turnOff();
        ac.turnOff();
        tv.turnOff();
    }
}
