// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  private DriveTrain dt=new DriveTrain();
  private Joystick gamepad=new Joystick(Constant.JOYSTICK); 
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
   


  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double vertical = gamepad.getRawAxis(Constant.LEFT_AXIS_Y)*-1;
    double spin = gamepad.getRawAxis(Constant.LEFT_AXIS_X);
    double horizontal = gamepad.getRawAxis(Constant.RIGHT_AXIS_X);
    double maxSpeed = 0.8;
    double max = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(spin), 1);
    double fl = (vertical + horizontal + spin)/max;
    double bl = (vertical - horizontal + spin)/max;
    double fr = (vertical - horizontal - spin)/max;
    double br = (vertical + horizontal - spin)/max;
    SmartDashboard.putNumber("Max Speed", maxSpeed);
    dt.setMotors(fl, bl, fr, br);
  }

}
