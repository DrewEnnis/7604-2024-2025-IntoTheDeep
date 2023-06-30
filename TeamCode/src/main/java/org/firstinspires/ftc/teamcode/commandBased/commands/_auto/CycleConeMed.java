package org.firstinspires.ftc.teamcode.commandBased.commands._auto;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commandBased.Constants;
import org.firstinspires.ftc.teamcode.commandBased.classes.enums.Stack;
import org.firstinspires.ftc.teamcode.commandBased.commands._groups.GrabConeStack;
import org.firstinspires.ftc.teamcode.commandBased.commands._groups.LiftMoveRotateArm;
import org.firstinspires.ftc.teamcode.commandBased.commands._groups.ScoreCone;
import org.firstinspires.ftc.teamcode.commandBased.commands._groups.ScoreConeStack;
import org.firstinspires.ftc.teamcode.commandBased.commands._rr.FollowTrajectorySequenceAsync;
import org.firstinspires.ftc.teamcode.commandBased.commands.elevator.MoveElevatorToPosition;
import org.firstinspires.ftc.teamcode.commandBased.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;

public class CycleConeMed extends SequentialCommandGroup {

    public CycleConeMed(
            Subsystems subsystems,
            TrajectorySequence medTraj,
            TrajectorySequence stackTraj,
            Stack.Cone coneNumber
    ) {
        addCommands(
                new ParallelCommandGroup(
                        new LiftMoveRotateArm(
                                subsystems.getEle(),
                                subsystems.getArm(),
                                subsystems.getRot(),
                                Constants.ARM_ANGLE_BACK,
                                Constants.ELE_MID,
                                Constants.ROTATOR_BACK
                        ),
                        new SequentialCommandGroup(
                                new WaitCommand(75),
                                new FollowTrajectorySequenceAsync(subsystems.rrDrive(), medTraj)
                        )
                ),
                new ScoreConeStack(subsystems.getArm(), subsystems.getRot(), subsystems.getIntake()),
                new ParallelCommandGroup(
                        new MoveElevatorToPosition(subsystems.getEle(), Constants.ELE_STACK),
                        new FollowTrajectorySequenceAsync(subsystems.rrDrive(), stackTraj)
                ),
                new GrabConeStack(
                        subsystems.getEle(),
                        subsystems.getArm(),
                        subsystems.getRot(),
                        subsystems.getIntake(),
                        coneNumber
                )
        );
    }
}
