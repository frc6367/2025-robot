package frc.robot;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.AlternateEncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

//motors are sparks

public class Intake {
    SparkMax arm;
    RelativeEncoder armEncoder;
    SparkMax roller;
    
    public Intake(){
        this.arm= new SparkMax(8,MotorType.kBrushless); 
        this.roller= new SparkMax(7,MotorType.kBrushless); 

        
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake);

        // config.apply(new AlternateEncoderConfig().inverted(true).countsPerRevolution(1250));
        this.arm.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
        this.roller.configure(new SparkMaxConfig().apply(config),  SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);

        this.armEncoder = this.arm.getEncoder();
        this.armEncoder.setPosition(0.0);
    }

    public double getArmPosition() {
        return this.armEncoder.getPosition();
    }

    public void putReadings(){
        SmartDashboard.putNumber("armEncoder",this.getArmPosition());
    }

    public void armUp(){
        arm.set(-0.25);
    }

    public void armDown(){
        arm.set(0.25);
    }

    public void rollerOut(){
        roller.set(1);
    }

    public void rollerIn(){
        roller.set(-1.0);
    }
    
    public void stopArm(){
        this.arm.set(0);
        this.arm.stopMotor();

    }

    public void stopRoller(){
        this.roller.set(0);    
    }
    
    public void getBallIn(){
        double armPosition = this.getArmPosition();
        SmartDashboard.putNumber("XCommandArmPosition", armPosition);
        this.rollerIn();
        if (armPosition < 13){
            this.armDown();
        }
        else {
            this.stopArm();
        }    
    }
    public void zeroEncoder(){
        // this.armEncoder.setPosition(0.0);
    }

}
