package org.firstinspires.ftc.teamcode.library.arm.boom;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractCommand;

/**
 *
 */
public class GoToPositionCommand extends AbstractCommand {

    /**
     */
    private Boom boom;

    /**
     */
    private double startPosition;

    /**
     */
    private double targetPosition;


    /**
     *
     * @param startPosition
     * @param targetPosition
     */
    public GoToPositionCommand (Boom boom, double startPosition, double targetPosition) {
        this(boom);

        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    /**
     * Hidden Constructor
     *
     */
    protected GoToPositionCommand(Boom boom) {
        this.boom = boom;
        this.setSynchronous(true);
    }

    /**
     *
     * @return
     */
    public Boom getBoom () {
        return this.boom;
    }

    /**
     *
     * @return
     */
    public double getStartPosition () {
        return this.startPosition;
    }

    /**
     *
     * @return
     */
    public double getTargetPosition () {
        return this.targetPosition;
    }

    /**
     *
     * @param startPosition
     */
    public void setStartPosition (double startPosition) {
        this.startPosition = startPosition;
    }

    /**
     *
     * @param targetPosition
     */
    public void setTargetPosition (double targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     *
     */
    public void run () {
        if (this.isCompleted()) {
            return;
        }

        if (this.getTargetPosition() == this.getStartPosition()) {
            this.markAsCompleted();
            return;
        }

        Boom.Direction direction = this.getTargetPosition() > this.getStartPosition() ? Boom.Direction.REVERSE : Boom.Direction.FORWARD;

        double currentPosition = this.boom.getPosition();

        if (direction.equals(Boom.Direction.FORWARD)) {
            boom.telemetry.addLine("forwards");

            if (currentPosition <= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.boom.move(-1, this.boom.getMaxIncrement(), this.getTargetPosition(), this.boom.getMaxPosition())) {
                this.markAsCompleted();
                return;
            }
        }
        else if (direction.equals(Boom.Direction.REVERSE)) {

            this.boom.telemetry.addLine("reversing");

            if (currentPosition >= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.boom.move(1, this.boom.getMaxIncrement(), this.boom.getMinPosition(), this.getTargetPosition())) {
                this.markAsCompleted();
                return;
            }
        }
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.targetPosition;
    }
}
