package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package edu.wpi.first.wpilibj.examples.elevatorsimulation.subsystems;


import edu.wpi.first.math.controller.ProfiledPIDController;
import com.revrobotics.spark.SparkLowLevel.MotorType;


public class Elevator {
  SparkMax elevator;


    public Elevator(){
        this.elevator= new SparkMax(9,MotorType.kBrushless);     
    }

    public void elevatorDown(){
      elevator.set(0.6);
  }

  public void elevatorUp(){
      elevator.set(-0.6);
  }
  
  public void stopElevator(){
      this.elevator.set(0);
      this.elevator.stopMotor();

  }




}
