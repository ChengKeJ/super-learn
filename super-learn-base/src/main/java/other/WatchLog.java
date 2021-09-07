package other;

import lombok.Data;
import org.springframework.util.StopWatch;

import java.lang.management.ManagementFactory;

/**
 * @author c.kj
 * @Description
 * @Date 2021/9/1
 * @Time 2:14 PM
 **/
@Data
public class WatchLog {

    public static final WatchLog INSTANCE = new WatchLog();

    public CharSequence getStartedMessage(StopWatch stopWatch) {

        StringBuilder message = new StringBuilder();
        message.append("Started ");
        message.append(" in ");
        message.append((double) stopWatch.getTotalTimeMillis() / 1000.0D);
        message.append(" seconds");
        try {
            double uptime = (double) ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0D;
            message.append(" (JVM running for ").append(uptime).append(")");
        } catch (Throwable var5) {
        }
        return message;
    }
}
