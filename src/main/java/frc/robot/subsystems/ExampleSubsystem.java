// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class ExampleSubsystem extends SubsystemBase implements Loggable{

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  private enum ColorName {
    Yellow,
    Purple,
    NoColorDetected,
  }

  //colors correspond to color names except NoColorDetected, which has no color
  private Color[] colors = {
    new Color(.4582, .4678, .0845),
    new Color(.2974, .3516, .3516),
  };

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {

  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  //could be useful for state machines
  public ColorName getDetectedColor()
  {
    if (isColor(ColorName.Yellow))
      return ColorName.Yellow;
    else if (isColor(ColorName.Purple))
      return ColorName.Purple;
    else return ColorName.NoColorDetected;
  }

  @Log(name = "testGetDetectedColor")
  private String testDetectedColor()
  {
    return getDetectedColor().name();
  }

  public boolean isColor(ColorName intendedColorName)
  {
    //grabs the color that is associated with the color name
    Color intendedColorValue = colors[ColorName.valueOf(intendedColorName.name()).ordinal()];
    //will put in constants later
    double colorTolerance = .05;
    if (Math.abs(intendedColorValue.blue - m_colorSensor.getColor().blue) < colorTolerance)
      return true;
    return false;
  }

  @Log(name = "testIsColor")
  private boolean testIsColor()
  {
    return isColor(ColorName.Purple);
  }

  @Log(name = "TestOutput")
  private String getColorFromSensor()
  {
    String color = "Red: " + m_colorSensor.getColor().red + ", " +
                   "Blue: " + m_colorSensor.getColor().blue + ", " +
                   "Green: " + m_colorSensor.getColor().green;

    return color;
  }

}
