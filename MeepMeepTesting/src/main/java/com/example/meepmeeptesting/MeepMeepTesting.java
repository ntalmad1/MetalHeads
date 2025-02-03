package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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

        MeepMeep meepMeep = new MeepMeep(800);

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


                .turnTo(Math.toRadians(151))

                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(24.6, -37.9, Math.toRadians(-125)), Math.toRadians(55))
                .splineToConstantHeading(new Vector2d(32, -37.9), Math.toRadians(55))

                .turnTo(Math.toRadians(145))

                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(40, -28.7, Math.toRadians(-160)), Math.toRadians(-32))



                //Push Final Specimen
                .turnTo(Math.toRadians(146))


                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48, -45, Math.toRadians(90)), Math.toRadians(-90))
                .lineToY(-59.8)


                //SPECIMEN #2
                .setTangent(Math.toRadians(180))
                .lineToX(32,
                        new TranslationalVelConstraint(45),
                        new ProfileAccelConstraint(-120, 20))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(115)), Math.toRadians(115),
                        new TranslationalVelConstraint(45),
                        new ProfileAccelConstraint(-60, 120))


                .setTangent(Math.toRadians(295))
                .splineToLinearHeading(new Pose2d(37, -53, Math.toRadians(90)), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -60.2), Math.toRadians(-90))
                .waitSeconds(0.08)



                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setBackgroundAlpha(0.95f)
                .addEntity(isaacBot)
                .start();
    }
}