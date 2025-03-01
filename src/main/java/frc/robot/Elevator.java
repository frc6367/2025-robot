package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package edu.wpi.first.wpilibj.examples.elevatorsimulation.subsystems;


import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;


public class Elevator {
  SparkMax elevator;
  RelativeEncoder elevatorEncoder;

    public Elevator(){
      this.elevator= new SparkMax(9,MotorType.kBrushless);

      SparkMaxConfig config = new SparkMaxConfig();
      config.idleMode(IdleMode.kBrake);
      this.elevator.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
 
      this.elevatorEncoder=this.elevator.getEncoder();
    }

  public void putReadings(){
    SmartDashboard.putNumber("elevatorEncoder", this.elevatorEncoder.getPosition());
  }

    public void elevatorDown(){
      elevator.set(0.6);
  }

  public void elevatorUp(){
    if(this.elevatorEncoder.getPosition()>-210){
      elevator.set(-0.6);
    }
    else{
      stopElevator();
    }

      

  }
  
  public void stopElevator(){
      this.elevator.set(0);
      this.elevator.stopMotor();

  }




}
