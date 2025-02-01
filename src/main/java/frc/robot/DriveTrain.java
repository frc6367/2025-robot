package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

public class DriveTrain{
    TalonFX backleft;
    TalonFX backright;
    TalonFX frontleft;
    TalonFX frontright;
    
    public DriveTrain(){
        this.backleft=new TalonFX(3);
        this.backright=new TalonFX(1);
        this.frontleft=new TalonFX(2);
        this.frontright=new TalonFX(0);
        this.frontright.setInverted(true);
        this.backright.setInverted(true);
    }
    public void stop(){
        backleft.set(0);
        frontleft.set(0);
        backright.set(0);
        frontright.set(0);
    }

    public void setMotors(double fl, double bl, double fr, double br){
        backleft.set(bl);
        frontleft.set(fl);
        backright.set(br);
        frontright.set(fr);

    }
}