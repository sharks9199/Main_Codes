package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.math.controller.PIDController;

public class Limelight {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //Valores output da Limelight
    double x = tx.getDouble(0.0); //Erro X
    double y = ty.getDouble(0.0); //Erro Y
    double area = ta.getDouble(0.0); //Area do alvo na camera
    //double last_x = x;

    //Constantes do controle PID
    double Kp = 0.09;
    double Ki = 0.0;
    double Kd = 0.0;
    PIDController pid = new PIDController(Kp, Ki, Kd);


    void update(){
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

    double pid(double error){
        double output = pid.calculate(error);
        return output;
    }

    void dashboard(){
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }
}
