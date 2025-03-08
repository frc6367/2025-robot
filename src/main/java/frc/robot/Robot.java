// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.kinematics.Kinematics;
// import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Autonomous definitions
  private static final String kDefaultAuto = "Drive straight";
  private static final String kCenterTroughAuto = "Center trough";
  private static final String kCenterL3Auto = "Center L3 Auto";
  // private static final String kLeftTroughAuto = "Left trough";
  // private static final String kLeftTroughAuto = "Left trough";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  
  //make instances of each of the subsystems 
  private DriveTrain dt=new DriveTrain();
  private Joystick gamepad=new Joystick(Constant.GamePad_Port); 
  private Joystick moveJoystick = new Joystick(Constant.Joystick_Port);
  private final Elevator elevator = new Elevator();
  public static CoralEffector coralEffector = new CoralEffector(); 
  public static Intake intake = new Intake();
  public Autonomous autonomous = new Autonomous(dt,coralEffector,elevator);
  // public PoseEstimator pe = new PoseEstimator<>(, null, null, null);
  // public AnalogInput coralSensor = new AnalogInput(0);
  // public CameraServer camera = new 

  public Robot() {
    m_chooser.setDefaultOption("Drive straight", kDefaultAuto);
    m_chooser.addOption("Center trough", kCenterTroughAuto);
    m_chooser.addOption("Center l3 Auto", kCenterL3Auto);
    SmartDashboard.putData("Autonomous to run", m_chooser);
    // CameraServer.startAutomaticCapture();
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("xStrafeMultiplier", 1.0);
    SmartDashboard.putNumber("yStrafeMultiplier", 1.0);
    SmartDashboard.putNumber("effectorLeftSpeed", Constant.effectorLeftSpeed);
    SmartDashboard.putNumber("effectorRightSpeed", Constant.effectorRightSpeed);
    SmartDashboard.putNumber("effectorLeftLowSpeed", Constant.effectorLeftLowSpeed);
    SmartDashboard.putNumber("effectorRightLowSpeed", Constant.effectorRightLowSpeed);
    // intake.zeroEncoder();
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
  
    // SmartDashboard.putNumber("gamepadPOV", gamepad.getPOV());
    coralEffector.putReadings();
    intake.putReadings();
    elevator.putReadings();
    elevator.sensorReadings();

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
    m_autoSelected = m_chooser.getSelected();
    autonomous.resetTime();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCenterTroughAuto:
        this.autonomous.centerTroughAuto();
        break;
      case kDefaultAuto:
        this.autonomous.driveStraightAuto();
        break;
      case kCenterL3Auto:
        this.autonomous.centerL3Auto();
        break;
      default:
        // Put default auto code here
        break;
    }



  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    this.dt.setBrake();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    double xStrafeMultiplier = SmartDashboard.getNumber("xStrafeMultiplier", 1.0);
    double yStrafeMultiplier = SmartDashboard.getNumber("yStrafeMultiplier", 1.0);
    Constant.effectorLeftSpeed=SmartDashboard.getNumber("effectorLeftSpeed", 1.0);
    Constant.effectorRightSpeed=SmartDashboard.getNumber("effectorRightSpeed", 1.0);
    Constant.effectorLeftLowSpeed=SmartDashboard.getNumber("effectorLeftLowSpeed", 1.0);
    Constant.effectorRightLowSpeed=SmartDashboard.getNumber("effectorRightLowSpeed", 1.0);

    boolean moveTrigger = moveJoystick.getRawButton(1);

    double vertical = moveJoystick.getRawAxis(Constant.LEFT_AXIS_Y)*-1 * yStrafeMultiplier;
    double horizontal = moveJoystick.getRawAxis(Constant.RIGHT_AXIS_X)  * xStrafeMultiplier;
    
    // TODO: Deadband horizontal and vertical
    
    double spin = moveJoystick.getRawAxis(Constant.LEFT_AXIS_X);
    if (spin < 0.5 && spin > -0.5) {
      spin = 0.0;
    }
    spin = spin*0.75;

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
    boolean ltPressed= gamepad.getRawButton(Constant.LeftTrigger);

    if (XPressed){
      coralEffector.takeIn();
    } else if (YPressed) {
      if (ltPressed){
        coralEffector.shootAngleL();

      }
      else{
        coralEffector.shootStraight();
      }
    }
    else {
      coralEffector.resetSawCoral();
      coralEffector.stop();
    }


    boolean rtPressed = gamepad.getRawButton(Constant.RightTrigger);
    boolean APressed = gamepad.getRawButton(Constant.button_A);
    boolean BPressed = gamepad.getRawButton(Constant.button_B);
    if (APressed){
      intake.getBallIn();
      // intake.armDown();
    }
    else if (BPressed){
      intake.armUp();
      intake.stopRoller();
    }
    else if (rtPressed) {
      intake.rollerOut();
    }
    else{
      intake.stopRoller();
      intake.stopArm();
    }



  //   boolean ltPressed= gamepad.getRawButton(Constant.LeftTrigger);
  //   boolean rtPressed = gamepad.getRawButton(Constant.RightTrigger);
    boolean rbPressed = gamepad.getRawButton(Constant.RightBumper);

  //   if (rbPressed){
  //     intake.rollerIn(); 
  //  }
  //   else if (rtPressed){
  //     intake.rollerOut();
  //   }
  //   else{
  //     intake.stopRoller();
  //   }

    double leftStick = gamepad.getRawAxis(1);

    if(leftStick>0.2){
      elevator.elevatorDown();
    }
    else if(leftStick<-0.2){
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
