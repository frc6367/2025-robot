package frc.robot;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;

//motors are sparks

public class Intake {
    SparkMax arm;
    SparkMax roller;

    public Intake(){
        this.arm= new SparkMax(8,MotorType.kBrushless); 
        this.roller= new SparkMax(7,MotorType.kBrushless); 
    }

    public double getArmEconder() {
        return this.arm.getAbsoluteEncoder().getPosition();
    }

    public void putReadings(){
        SmartDashboard.putNumber("arm encoder",getArmEconder());
    }

    public void armUp(){
        arm.set(-0.25);
    }

    public void armDown(){
        arm.set(0.25);
    }

    public void rollerOut(){
        roller.set(0.5);
    }

    public void rollerIn(){
        roller.set(-0.5);
    }
    
    public void stopArm(){
        this.arm.set(0);
        this.arm.stopMotor();

    }

    public void stopRoller(){
        this.roller.set(0);    
    }

    

    
}
