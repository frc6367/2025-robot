package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain{
    TalonSRX backleft;
    TalonSRX backright;
    TalonSRX frontleft;
    TalonSRX frontright;
    
    public DriveTrain(){
        this.backleft=new TalonSRX(3);
        this.backright=new TalonSRX(2);
        this.frontleft=new TalonSRX(4);
        this.frontright=new TalonSRX(1);
        this.frontright.setInverted(true);
        this.backright.setInverted(true);
        this.backleft.setNeutralMode(NeutralMode.Brake);
        this.backright.setNeutralMode(NeutralMode.Brake);
        this.frontleft.setNeutralMode(NeutralMode.Brake);
        this.frontright.setNeutralMode(NeutralMode.Brake);
    }
    public void stop(){
        backleft.set(TalonSRXControlMode.PercentOutput, 0);
        frontleft.set(TalonSRXControlMode.PercentOutput, 0);
        backright.set(TalonSRXControlMode.PercentOutput, 0);
        frontright.set(TalonSRXControlMode.PercentOutput, 0);
    }

    public void setBrake(){
        this.backleft.setNeutralMode(NeutralMode.Brake);
        this.backright.setNeutralMode(NeutralMode.Brake);
        this.frontleft.setNeutralMode(NeutralMode.Brake);
        this.frontright.setNeutralMode(NeutralMode.Brake);
    }

    public void setcoast(){
        this.backleft.setNeutralMode(NeutralMode.Coast);
        this.backright.setNeutralMode(NeutralMode.Coast);
        this.frontleft.setNeutralMode(NeutralMode.Coast);
        this.frontright.setNeutralMode(NeutralMode.Coast);
    }

    public void setMotors(double fl, double bl, double fr, double br){
        backleft.set(TalonSRXControlMode.PercentOutput, bl);
        frontleft.set(TalonSRXControlMode.PercentOutput, fl);
        backright.set(TalonSRXControlMode.PercentOutput, br);
        frontright.set(TalonSRXControlMode.PercentOutput, fr);

    }
    public void driveForward(double speed){
        this.setMotors(speed,speed,speed,speed);
    }

    public void strafeRight(double speed){
        backleft.set(TalonSRXControlMode.PercentOutput, -speed);
        frontleft.set(TalonSRXControlMode.PercentOutput, speed);
        backright.set(TalonSRXControlMode.PercentOutput, speed);
        frontright.set(TalonSRXControlMode.PercentOutput, -speed);
    }

    // helps the driver do more percise turns by slowing down when the robot is turning left
    // if (driverController.getBumper(Hand.kleft)) turnPower *= 0.5; - when using arcade drive 

    // what kind of driving system are we using? 


}