import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
        /**
         * Java timer class to schedule events.
         */
        private final Timer timer = new Timer("Game Timer");
        /**
         * Reference to the main game logic, so we can tell it when the timer has ended.
         */
        private final Wordle wordle;
        /**
         * The time the timer is scheduled to end.
         */
        private Long endTime = null;
        /**
         * The current task which will cause the game to end.
         */
        private TimerTask currentTimerTask = null;
        public Time(Wordle wordle) {
            this.wordle = wordle;
        }
        /**
         * Starts a timer of the given {@link Duration duration} to end the game.
         *
         * @param duration the {@link Duration}.
         */
        public void start(Duration duration) {
            // TODO: Should there be a check here to make sure a timer is not started when a timer is already active?
            // Get the milliseconds until the timer should end
            long millis = duration.toMillis();
            // Calculate the end time
            endTime = System.currentTimeMillis() + millis;
            currentTimerTask = new TimerTask() {
                @Override
                public void run() {
                    // Tell wordle the timer is up
                    wordle.stopGame();
                    // Reset the timer class back to the default
                    endTime = null;
                    currentTimerTask = null;
                }
            };
            // Schedule the above task
            timer.schedule(currentTimerTask, millis);
        }
        /**
         * Get the {@link Duration duration} until the timer ends.
         *
         * @return the {@link Duration}. Will be {@code null} if timer is not active.
         */
        public Duration getTimeUntilTimerEnds() {
            if (endTime == null) {
                return null;
            }
            long millisUntil = endTime - System.currentTimeMillis();
            return Duration.ofMillis(millisUntil);
        }
    }
}
