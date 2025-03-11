package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class Basket {

    /**
     */
    private static final Pose2d initialPose = new Pose2d(-39, -61, Math.toRadians(90));

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

        isaacBot.runAction(isaacBot.getDrive().actionBuilder(initialPose)

                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(225),
                                null,
                                new ProfileAccelConstraint(-15, 35))


                        //sample 1
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(-49.36, -37.82, Math.toRadians(90)), Math.toRadians(90))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(-135))

                        //sample 2
                        .setTangent(Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(-59.6, -38.2, Math.toRadians(90)), Math.toRadians(90))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(-80))

                        //sample 3
                        .setTangent(Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(-58.25, -45.5, Math.toRadians(120)), Math.toRadians(90))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(-80))

                        //Level 1 Hang
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-24, -8, Math.toRadians(180)), Math.toRadians(0))


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setBackgroundAlpha(0.95f)
                .addEntity(isaacBot)
                .start();
    }
}