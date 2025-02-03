package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.nio.file.Path;


public class RightObsBot {

    /**
     */
    private static final Pose2d initialPose = new Pose2d(8, -61, Math.toRadians(90));
    private static final Pose2d specimenPose = new Pose2d(48, -59.8, Math.toRadians(90));

    /**
     *
     * @param Args
     */
    public static void main(String[] Args) {

        System.setProperty("sun.java2d.opengl", "true");


        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity isaacBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        isaacBot.runAction(isaacBot.getDrive().actionBuilder(specimenPose)




                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100))

                .setTangent(Math.toRadians(-10))
                .splineToLinearHeading(new Pose2d(48, -59.8, Math.toRadians(90)), Math.toRadians(-90))











                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setBackgroundAlpha(0.95f)
                .addEntity(isaacBot)
                .start();
    }
}