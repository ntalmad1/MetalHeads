package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class Specimens {

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

        isaacBot.runAction(isaacBot.getDrive().actionBuilder(initialPose)


                .lineToY(-37,
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-60, 20))

                //Retreat From Bar
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(24.6, -41.7, Math.toRadians(-120)), Math.toRadians(60))


                .waitSeconds(0.2)


                .turnTo(Math.toRadians(151),
                        new TurnConstraints(9,-8, 8))
                .turnTo(Math.toRadians(-125),
                        new TurnConstraints(9,-8, 8))


                .setTangent(Math.toRadians(10))
                .splineToLinearHeading(new Pose2d(32, -37.9, Math.toRadians(-125)), Math.toRadians(55))


                .turnTo(Math.toRadians(145),
                        new TurnConstraints(9,-8, 8))
                .turnTo(Math.toRadians(-133),
                        new TurnConstraints(9,-8, 8))


                .setTangent(Math.toRadians(120))
                .splineToLinearHeading(new Pose2d(38.5, -35.5, Math.toRadians(-160)), Math.toRadians(-32))





                //Push Final Specimen
                .turnTo(Math.toRadians(155),
                        new TurnConstraints(9,-8, 8))




                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48.2, -60, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-25, 60))











                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setBackgroundAlpha(0.95f)
                .addEntity(isaacBot)
                .start();
    }
}