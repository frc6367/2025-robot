// package frc.robot;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;

// public class Limelight {

//     // Basic targeting data
//     double tx = LimelightHelpers.getTX("");  // Horizontal offset from crosshair to target in degrees
//     double ty = LimelightHelpers.getTY("");  // Vertical offset from crosshair to target in degrees
//     double ta = LimelightHelpers.getTA("");  // Target area (0% to 100% of image)
//     boolean hasTarget = LimelightHelpers.getTV(""); // Do you have a valid target?

//     double txnc = LimelightHelpers.getTXNC("");  // Horizontal offset from principal pixel/point to target in degrees
//     double tync = LimelightHelpers.getTYNC("");  // Vertical  offset from principal pixel/point to target in degrees
    

//     NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(<value>);
//         double[] cropValues = new double[4];
//         cropValues[0] = -1.0;
//         cropValues[1] = 1.0;
//         cropValues[2] = -1.0;
//         cropValues[3] = 1.0;
//     NetworkTableInstance.getDefault().getTable("limelight").getEntry("crop").setDoubleArray(cropValues);




//     public static double[] getRobotPose() {
//         NetworkTableEntry botpose = NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose");
//         botpose.
//         return botpose.getDoubleArray(new double[6]);    
//     }


// }
