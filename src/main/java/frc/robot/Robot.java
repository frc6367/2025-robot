// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.ml.RTrees;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

// import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //make instances of each of the subsystems 
  private DriveTrain dt=new DriveTrain();
  private Joystick gamepad=new Joystick(Constant.GamePad_Port); 
  private Joystick moveJoystick = new Joystick(Constant.Joystick_Port);
  private final Elevator elevator = new Elevator();
  public static CoralEffector coralEffector = new CoralEffector(); 
  public static Intake intake = new Intake();
  // public AnalogInput coralSensor = new AnalogInput(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("xStrafeMultiplier", 1.0);
    SmartDashboard.putNumber("yStrafeMultiplier", 1.0);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  

    coralEffector.putReadings();
    intake.putReadings();

  }

  @Override
  public void simulationPeriodic() {
    // Update the simulation model.
  
  }

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

    double xStrafeMultiplier = SmartDashboard.getNumber("xStrafeMultiplier", 1.0);
    double yStrafeMultiplier = SmartDashboard.getNumber("yStrafeMultiplier", 1.0);

    boolean moveTrigger = moveJoystick.getRawButton(1);

    double vertical = moveJoystick.getRawAxis(Constant.LEFT_AXIS_Y)*-1 * yStrafeMultiplier;
    double spin = moveJoystick.getRawAxis(Constant.LEFT_AXIS_X);
    if (spin < 0.3 && spin > -0.3) {
      spin = 0.0;
    }
    double horizontal = moveJoystick.getRawAxis(Constant.RIGHT_AXIS_X)  * xStrafeMultiplier;
    double maxSpeed = 0.8;
    double max = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(spin), 1);
    double fl = (vertical + horizontal + spin)/max;
    double bl = (vertical - horizontal + spin)/max;
    double fr = (vertical - horizontal - spin)/max;
    double br = (vertical + horizontal - spin)/max;

    SmartDashboard.putNumber("Max Speed", maxSpeed);
    if (moveTrigger) {
      fl = fl/2;
      bl = bl/2;
      fr = fr/2;
      br = br/2;
    }
    SmartDashboard.putNumber("blOutput", bl);

    dt.setMotors(fl, bl, fr, br);
  

    // // for the elevator
    // if (gamepad.getTrigger()) {
    //   // Here, we set the constant setpoint of 0.75 meters.
    //   elevator.reachGoal(Constant.kSetpointMeters);
    // } else {
    //   // Otherwise, we update the setpoint to 0.
    //   elevator.reachGoal(0.0);
    // }



    //doing the x-button 
    boolean XPressed = gamepad.getRawButton(Constant.button_X);
    boolean YPressed = gamepad.getRawButton(Constant.button_Y);
    if (XPressed){
      coralEffector.takeIn();
    } else if (YPressed) {
      coralEffector.shootStraight(0.0);
    }
    else {
      coralEffector.stop();
    }



    boolean APressed = gamepad.getRawButton(Constant.button_A);
    boolean BPressed = gamepad.getRawButton(Constant.button_B);
    if (APressed){
      intake.armDown(); 
    }
    else if (BPressed){
      intake.armUp();
    }
    else{
      intake.stopArm();
    }



    boolean ltPressed= gamepad.getRawButton(Constant.LeftTrigger);
    boolean rtPressed = gamepad.getRawButton(Constant.RightTrigger);
    

    if (ltPressed){
      intake.rollerIn(); 
  }
    else if (rtPressed){
      intake.rollerOut();
    }
    else{
      intake.stopRoller();
    }

    boolean rightPressed = gamepad.getRawButton(Constant.RightBumper);
    boolean leftPressed = gamepad.getRawButton(Constant.LeftBumper);
   
    if(rightPressed){
      elevator.elevatorDown();
    }
    if(leftPressed){
      elevator.elevatorUp();
    }
    else{
      elevator.stopElevator();
    }

  }



  


  // @Override
  // public void disabledInit() {
  //   // This just makes sure that our simulation code knows that the motor's off.
  
  // }

  // @Override
  // public void close() {
  
  // }

}
