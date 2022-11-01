package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class Auton {
    private boolean direction;
    private int parkingZone;
    public Auton(boolean left, int tag_id) {
        this.direction = left;
        tag_id++;
        if(tag_id == 2){
            parkingZone = 1;
        }
        if(tag_id == 1)
        {
            parkingZone = 2;
        }
        if(tag_id == 3)
        {
            parkingZone = 3;
        }
        ;//0-->1, 1-->2, 2-// ->3

    }

    public void runAutonParkOnly(SampleMecanumDrive drive, HardwareMap hardwareMap)
    {
        Servo intake = hardwareMap.get(Servo.class, "intake");

        drive.setMotorPowers(0.1,0.1,0.1,0.1);
        intake.setPosition(0.4);
        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(2)
                .turn(1)
                .forward(1)
                .waitSeconds(2)
                .back(1)
                .turn(-1)
                .forward(23)
                .build();
        drive.followTrajectorySequence(trajSeq);



        if (parkingZone == 1) { // red
            Trajectory park = drive.trajectoryBuilder(new Pose2d())
                    .strafeLeft(36)
                    .build();
            drive.followTrajectory(park);
        } else if (parkingZone == 3) { // blue
            Trajectory park = drive.trajectoryBuilder(new Pose2d())
                    .strafeRight(36)
                    .build();
            drive.followTrajectory(park);
        }

    }

    public void runAutonWithCone(SampleMecanumDrive drive, HardwareMap hardwareMap)
    {
        Servo intake = hardwareMap.get(Servo.class, "intake");
        DcMotor motorLiftLeft = hardwareMap.get(DcMotorEx.class, "leftLift");
        DcMotor motorLiftRight = hardwareMap.get(DcMotorEx.class, "rightLift");

        int angle = -70;
        if(direction)
        {
            angle = 70;
        }

        drive.setMotorPowers(0.1,0.1,0.1,0.1);
        intake.setPosition(0.6);
        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(25)
                .turn(angle)
                .build();
        drive.followTrajectorySequence(trajSeq1);

        // lift go up

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(13)
                .build();
        drive.followTrajectorySequence(trajSeq2);

        intake.setPosition(0);

        // lift go down

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(new Pose2d())
                .back(13)
                .turn(-angle)
                .build();
        drive.followTrajectorySequence(trajSeq2);

        if (parkingZone == 1) { // red
            Trajectory park = drive.trajectoryBuilder(new Pose2d())
                    .strafeLeft(36)
                    .build();
            drive.followTrajectory(park);
        } else if (parkingZone == 3) { // blue
            Trajectory park = drive.trajectoryBuilder(new Pose2d())
                    .strafeRight(36)
                    .build();
            drive.followTrajectory(park);
        }

    }

    public void linSlidesLift() {
        int n = 4;
        double a = (200 / Math.pow(n, 2));
        int m = 30;

        double x = 0;
        double y = 0;
        double startPosition = 0;//replace with slides.getPosition();

    }

    public void placeFirstCone() {
        // places first cone
    }

    public void pickUpSecondCone() {
        // picks up second cone
    }

    public void placeSecondCone() {
        // places second cone
    }
}
