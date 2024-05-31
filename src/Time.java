import java.time.Duration;

/**
 * This class is used for the stopwatch.
 */
public class Time {
        private Long startTime = null;

        public void start(){
            startTime = System.currentTimeMillis();
        }

        public Duration getDurationOnTimer(){
            if(startTime == null){
                return null;
            }
            long milliSince = System.currentTimeMillis() - startTime;
            return Duration.ofMillis(milliSince);
        }
}
