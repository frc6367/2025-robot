package frc.robot;

import edu.wpi.first.math.util.Units;

public class Constant {


    public static final int LEFT_AXIS_Y = 1;
    public static final int LEFT_AXIS_X = 2;
    public static final int RIGHT_AXIS_X = 0;
    public static final int JOYSTICK = 0;
    public static final int button_X = 1;
    public static final int button_A = 2;
    public static final int button_B = 3;
    public static final int button_Y = 4;
    public static final int LeftBumper = 5;
    public static final int RightBumper= 6;
    public static final int LeftTrigger= 7;
    public static final int RightTrigger = 8;

    public static final int GamePad_Port = 0;
    public static final int Joystick_Port = 1;



    // // elevator constants
    // public static final int kMotorPort = 0;
    // public static final int kEncoderAChannel = 0;
    // public static final int kEncoderBChannel = 1;
    // public static final int kJoystickPort = 0;
  
    // public static final double kElevatorKp = 5;
    // public static final double kElevatorKi = 0;
    // public static final double kElevatorKd = 0;
  
    // public static final double kElevatorkS = 0.0; // volts (V)
    // public static final double kElevatorkG = 0.762; // volts (V)
    // public static final double kElevatorkV = 0.762; // volt per velocity (V/(m/s))
    // public static final double kElevatorkA = 0.0; // volt per acceleration (V/(m/s²))
  
    // public static final double kElevatorGearing = 10.0;
    // public static final double kElevatorDrumRadius = Units.inchesToMeters(2.0);
    // public static final double kCarriageMass = 4.0; // kg
  
    // public static final double kSetpointMeters = 0.75;
    // // Encoder is reset to measure 0 at the bottom, so minimum height is 0.
    // public static final double kMinElevatorHeightMeters = 0.0;
    // public static final double kMaxElevatorHeightMeters = 1.25;
  
    // // distance per pulse = (distance per revolution) / (pulses per revolution)
    // //  = (Pi * D) / ppr
    // public static final double kElevatorEncoderDistPerPulse =
        // 2.0 * Math.PI * kElevatorDrumRadius / 4096;


    // coral sensor constants
    public static int coralThreshold = 1000; 
    public static double effectorLeftSpeed = 1.0;
    public static double effectorRightSpeed = 1.0;
    public static double effectorLeftLowSpeed = 1.0;
    public static double effectorRightLowSpeed = 1.0;

    

}
