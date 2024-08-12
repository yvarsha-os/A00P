package abc;


import java.util.Stack;

//Command interface
interface Command {
 void execute();
 void undo();
}

//Receiver class for light
class Light {
 public void turnOn() {
     System.out.println("Light is turned ON");
 }

 public void turnOff() {
     System.out.println("Light is turned OFF");
 }
}

//Receiver class for thermostat
class Thermostat {
 private int temperature;

 public void setTemperature(int temperature) {
     this.temperature = temperature;
     System.out.println("Thermostat is set to " + temperature + " degrees");
 }

 public void resetTemperature() {
     System.out.println("Thermostat temperature is reset");
 }
}

//Command to turn the light on
class LightOnCommand implements Command {
 private Light light;

 public LightOnCommand(Light light) {
     this.light = light;
 }

 @Override
 public void execute() {
     light.turnOn();
 }

 @Override
 public void undo() {
     light.turnOff();
 }
}

//Command to turn the light off
class LightOffCommand implements Command {
 private Light light;

 public LightOffCommand(Light light) {
     this.light = light;
 }

 @Override
 public void execute() {
     light.turnOff();
 }

 @Override
 public void undo() {
     light.turnOn();
 }
}

//Command to set the thermostat temperature
class SetThermostatCommand implements Command {
 private Thermostat thermostat;
 private int temperature;

 public SetThermostatCommand(Thermostat thermostat, int temperature) {
     this.thermostat = thermostat;
     this.temperature = temperature;
 }

 @Override
 public void execute() {
     thermostat.setTemperature(temperature);
 }

 @Override
 public void undo() {
     thermostat.resetTemperature();
 }
}

//Invoker class
class RemoteControl {
 private Command currentCommand;
 private Stack<Command> commandHistory = new Stack<>();

 public void setCommand(Command command) {
     this.currentCommand = command;
 }

 public void pressButton() {
     currentCommand.execute();
     commandHistory.push(currentCommand);
 }

 public void pressUndo() {
     if (!commandHistory.isEmpty()) {
         Command command = commandHistory.pop();
         command.undo();
     } else {
         System.out.println("No commands to undo.");
     }
 }
}

//Client code
public class HomeAutomationApp {
 public static void main(String[] args) {
     // Create receivers
     Light livingRoomLight = new Light();
     Thermostat homeThermostat = new Thermostat();

     // Create commands
     Command lightOn = new LightOnCommand(livingRoomLight);
     Command lightOff = new LightOffCommand(livingRoomLight);
     Command setThermostat = new SetThermostatCommand(homeThermostat, 22);

     // Create invoker
     RemoteControl remote = new RemoteControl();

     // Turn the light on
     remote.setCommand(lightOn);
     remote.pressButton();

     // Turn the light off
     remote.setCommand(lightOff);
     remote.pressButton();

     // Set thermostat temperature
     remote.setCommand(setThermostat);
     remote.pressButton();

     // Undo last command (thermostat reset)
     remote.pressUndo();

     // Undo last command (light on)
     remote.pressUndo();

     // Undo last command (light off)
     remote.pressUndo();
 }
}