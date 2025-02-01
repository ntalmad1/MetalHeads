package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class Sweeper {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8, -61, Math.toRadians(90)))

                        .lineTo(new Vector2d(8, -36))


                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(25.6, -41.7, Math.toRadians(-120)), Math.toRadians(60))

                        // heading 151
                        .turn(Math.toRadians(-90))

                        // heading -125
                        .turn(Math.toRadians(95))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(32, -37.9, Math.toRadians(-125)), Math.toRadians(55))

                        // heading 145
                        .turn(Math.toRadians(-90))
                        .turn(Math.toRadians(90))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(40, -28.7, Math.toRadians(-148)), Math.toRadians(-32))

                        // heading 146
                        .turn(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(48.8, -45, Math.toRadians(90)), Math.toRadians(-90))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(48.8, -57, Math.toRadians(90)), Math.toRadians(-90))






                        .build());





        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

//        try {
//
//            File pathToFile = new File("resources/intothedeep_rotated.png");
//            Image image = ImageIO.read(pathToFile);
//
//            meepMeep.setBackground(image)
//                    .setDarkMode(true)
//                    .setBackgroundAlpha(0.95f)
//                    .addEntity(myBot)
//                    .start();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }
}