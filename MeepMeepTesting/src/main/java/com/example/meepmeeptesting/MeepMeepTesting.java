package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    /**
     */
    private static final Pose2d initialPose = new Pose2d(8, -61, Math.toRadians(90));

    /**
     *
     * @param Args
     */
    public static void main(String[] Args) {

        System.setProperty("sun.java2d.opengl", "true");

        MeepMeep meepMeep = new MeepMeep(1000);

        RoadRunnerBotEntity isaacBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        isaacBot.runAction(isaacBot.getDrive().actionBuilder(initialPose)



                .lineToY(-37,
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-60, 20))



                //Retreat from Bar
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(24.6, -41.7, Math.toRadians(-120)), Math.toRadians(60))


                .waitSeconds(0.2)


                //sample 1
                .turnTo(Math.toRadians(151))

                //sample 2
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(24.6, -37.9, Math.toRadians(-125)), Math.toRadians(0),
                        null,
                        new ProfileAccelConstraint(-60, 55))
                .splineToConstantHeading(new Vector2d(32, -37.9), Math.toRadians(55))


                //into obs zone
                .splineToLinearHeading(new Pose2d(32, -34, Math.toRadians(145)), Math.toRadians(0))

                //sample 3
                .splineToLinearHeading(new Pose2d(38, -30, Math.toRadians(-160)), Math.toRadians(-32),
                        null,
                        new ProfileAccelConstraint(-60, 10))



                //Push Final Specimen
                        //.turnTo(Math.toRadians(146))



                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setBackgroundAlpha(0.95f)
                .addEntity(isaacBot)
                .start();
    }
}