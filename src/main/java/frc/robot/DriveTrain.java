package frc.robot;

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
    }
    public void stop(){
        backleft.set(TalonSRXControlMode.PercentOutput, 0);
        frontleft.set(TalonSRXControlMode.PercentOutput, 0);
        backright.set(TalonSRXControlMode.PercentOutput, 0);
        frontright.set(TalonSRXControlMode.PercentOutput, 0);
    }

    public void setMotors(double fl, double bl, double fr, double br){
        backleft.set(TalonSRXControlMode.PercentOutput, bl);
        frontleft.set(TalonSRXControlMode.PercentOutput, fl);
        backright.set(TalonSRXControlMode.PercentOutput, br);
        frontright.set(TalonSRXControlMode.PercentOutput, fr);

    }
}